package starter;

import database.DatabaseManager;
import database.PackageData;
import entities.*;
import gui.GeneralFrame;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private int id;


    public ClientHandler(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getClientId() {
        return id;
    }

    public void setClientId(int id) {
        this.id = id;
    }


    @Override
    public void run() {
        try {
            DatabaseManager db = new DatabaseManager();
            db.connect();

            ObjectInputStream inputStream =
                    new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(socket.getOutputStream());

            GeneralFrame generalFrame = new GeneralFrame();
            generalFrame.setDatabaseManager(db);
            generalFrame.setVisible(true);
            System.out.println("Testing new function!");

            PackageData data;
            while ((data = (PackageData) inputStream.readObject()) != null) {
                PackageData response = new PackageData();

                System.out.println(data);
                String responseText = "";
                try {
                    switch (data.getContent()) {
                        case "1":
                            db.addUser((User) data.getObject());
                            break;
                        case "2":
                            for (User user : db.getUsers()) {
                                responseText += user.toString() + "\n";
                            }
                            break;
                        case "3":
                            db.editUser((User) data.getObject());
                            break;
                        case "4":
                            db.deleteUser((User) data.getObject());
                            break;
                        case "11":
                            db.addOrganization((Organization) data.getObject());
                            break;
                        case "12":
                            for (Organization organization : db.getOrganizations()) {
                                responseText += organization.toString() + "\n";
                            }
                            break;
                        case "13":
                            db.editOrganization((Organization) data.getObject());
                            break;
                        case "14":
                            db.deleteOrganization((Organization) data.getObject());
                            break;
                        case "21":
                            db.addSubscription((Subscription) data.getObject());
                            break;
                        case "22":
                            for (Subscription subscription : db.getSubscriptions())
                                responseText += subscription.toString() + "\n";
                            break;
                        case "23":
                            db.editSubscription((Subscription) data.getObject());
                            break;
                        case "24":
                            db.deleteSubscription((Subscription) data.getObject());
                            break;
                        case "31":
                            db.addPurchase((Purchase) data.getObject());
                            break;
                        case "32":
                            for (Purchase purchase : db.getPurchases())
                                responseText += purchase.toString() + "\n";
                            break;
                        case "33":
                            db.editPurchase((Purchase) data.getObject());
                            break;
                        case "34":
                            db.deletePurchase((Purchase) data.getObject());
                            break;
                        case "41":
                            db.addItem((ListItem) data.getObject());
                            break;
                        case "42":
                            for (ListItem item : db.getItems())
                                responseText += item.toString() + "\n";
                            break;
                        case "43":
                            db.editItem((ListItem) data.getObject());
                            break;
                        case "44":
                            db.deleteItem((ListItem) data.getObject());
                            break;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            null, "Error while insertin new user into table: " + e.getMessage(),
                            "Fatal Error", JOptionPane.ERROR_MESSAGE
                    );
                }

                response.setSender("Server");
                response.setContent(responseText);
                outputStream.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
