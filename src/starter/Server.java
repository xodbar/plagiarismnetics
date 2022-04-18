package starter;

import database.DatabaseManager;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2002);
            int id = 0;

            while (true) {
                Socket socket = server.accept();
                id++;

                System.out.println("Client #" + id +" has connected!");
                ClientHandler handler = new ClientHandler(socket, id);
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
