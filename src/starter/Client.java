package starter;

import database.PackageData;
import entities.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = in.nextLine();

        while (username.isEmpty()) {
            System.out.println("You can't login as anonymous. Enter name:");
            username = in.nextLine();
        }

        try {
            Socket socket = new Socket("localhost",2002);

            ObjectOutputStream outputStream =
                    new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream =
                    new ObjectInputStream(socket.getInputStream());

            PackageData data = new PackageData();
            while (true) {
                data.setSender(username);

                System.out.print("Enter command (1 - create, 2 - list, 3 - update, 4 - delete\n" +
                        "11 - create organization, 12 - list organizations, 13 - update organization," +
                        " 14 - delete organization): ");
                data.setContent(in.nextLine());

                if (data.getContent().equals("1") || data.getContent().equalsIgnoreCase("create")) {
                    User newUser = new User();

                    System.out.print("Enter user full name: ");
                    newUser.setFullName(in.nextLine());

                    System.out.print("Enter user phone (single-line): ");
                    newUser.setPhoneNumber(in.next());

                    System.out.print("Enter user email: ");
                    newUser.setEmail(in.next());

                    in.nextLine();

                    System.out.print("Enter user password: ");
                    newUser.setPassword(in.nextLine());

                    in.nextLine();

                    System.out.print("Enter user country: ");
                    newUser.setCountry(in.nextLine());

                    System.out.print("Enter user organization: ");
                    newUser.setOrganization(in.nextLine());

                    data.setObject(newUser);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("2")) {
                    data.setObject(null);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("3")) {
                    outputStream.writeObject(new PackageData(username, "2", null));

                    System.out.print("Enter ID of necessary user: ");
                    int temp = in.nextInt();

                    User user = new User();
                    user.setUserID(temp);

                    in.nextLine();

                    System.out.print("Enter user full name: ");
                    user.setFullName(in.nextLine());

                    System.out.print("Enter user phone (single-line): ");
                    user.setPhoneNumber(in.next());

                    System.out.print("Enter user email: ");
                    user.setEmail(in.next());

                    in.nextLine();

                    System.out.print("Enter user password: ");
                    user.setPassword(in.nextLine());

                    in.nextLine();

                    System.out.print("Enter user country: ");
                    user.setCountry(in.nextLine());

                    System.out.print("Enter user organization: ");
                    user.setOrganization(in.nextLine());

                    data.setContent("3");
                    data.setObject(user);

                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("4")) {
                    outputStream.writeObject(new PackageData(username, "2", null));

                    System.out.print("Enter ID of necessary user: ");
                    int temp = in.nextInt();

                    User user = new User();
                    user.setUserID(temp);

                    data.setContent("4");
                    data.setObject(user);
                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("11")) {
                    Organization organization = new Organization();

                    System.out.print("Enter organization name: ");
                    organization.setOrganizationName(in.nextLine());

                    System.out.print("Enter corporate domain: ");
                    organization.setCorporateDomain(in.next());

                    System.out.print("Enter discount: ");
                    organization.setDiscountPercent(in.nextInt());

                    System.out.print("Enter capacity: ");
                    organization.setCapacity(in.nextInt());

                    in.nextLine();

                    System.out.print("Enter default password: ");
                    organization.setDefaultPassword(in.nextLine());

                    organization.setMembers(new HashMap<>());

                    data.setObject(organization);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("12")) {
                    data.setObject(null);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("13")) {
                    outputStream.writeObject(new PackageData(username, "12", null));

                    System.out.print("Enter ID of necessary organization: ");
                    int temp = in.nextInt();

                    Organization organization = new Organization();
                    organization.setId(temp);

                    in.nextLine();

                    System.out.print("Enter organization name: ");
                    organization.setOrganizationName(in.nextLine());

                    System.out.print("Enter corporate domain: ");
                    organization.setCorporateDomain(in.next());

                    System.out.print("Enter discount: ");
                    organization.setDiscountPercent(in.nextInt());

                    System.out.print("Enter capacity: ");
                    organization.setCapacity(in.nextInt());

                    in.nextLine();

                    System.out.print("Enter default password: ");
                    organization.setDefaultPassword(in.nextLine());

                    organization.setMembers(new HashMap<>());

                    data.setContent("13");
                    data.setObject(organization);

                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("14")) {
                    outputStream.writeObject(new PackageData(username, "12", null));

                    System.out.print("Enter ID of necessary organization: ");
                    int temp = in.nextInt();

                    Organization organization = new Organization();
                    organization.setId(temp);

                    data.setContent("14");
                    data.setObject(organization);
                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("21")) {
                    Subscription subscription = new Subscription();

                    System.out.print("Enter description: ");
                    subscription.setDescription(in.nextLine());

                    System.out.print("Enter price: ");
                    subscription.setPrice(in.nextDouble());

                    data.setObject(subscription);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("22")) {
                    data.setObject(null);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("23")) {
                    outputStream.writeObject(new PackageData(username, "22", null));

                    System.out.print("Enter ID of necessary subscription: ");
                    int temp = in.nextInt();

                    Subscription subscription = new Subscription();
                    subscription.setSubscriptionID(temp);

                    in.nextLine();

                    System.out.print("Enter subscription description: ");
                    subscription.setDescription(in.nextLine());

                    System.out.print("Enter subscription price: ");
                    subscription.setPrice(in.nextDouble());

                    data.setContent("23");
                    data.setObject(subscription);

                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("24")) {
                    outputStream.writeObject(new PackageData(username, "22", null));

                    System.out.print("Enter ID of necessary subscription: ");
                    int temp = in.nextInt();

                    Subscription subscription = new Subscription();
                    subscription.setSubscriptionID(temp);

                    data.setContent("24");
                    data.setObject(subscription);
                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("41")) {
                    ListItem item = new ListItem();

                    System.out.print("Enter ID of user: ");
                    item.setUserId(in.nextInt());

                    System.out.print("Enter ID of purchase: ");
                    item.setPurchaseId(in.nextInt());

                    System.out.print("Enter ID of subscription: ");
                    item.setSubscriptionId(in.nextInt());

                    System.out.print("Enter ID of organization: ");
                    item.setOrganizationId(in.nextInt());

                    data.setObject(item);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("42")) {
                    data.setObject(null);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("43")) {
                    outputStream.writeObject(new PackageData(username, "42", null));

                    System.out.print("Enter ID of necessary item: ");
                    int temp = in.nextInt();

                    ListItem item = new ListItem();
                    System.out.print("Enter ID of user: ");
                    item.setUserId(in.nextInt());

                    System.out.print("Enter ID of purchase: ");
                    item.setPurchaseId(in.nextInt());

                    System.out.print("Enter ID of subscription: ");
                    item.setSubscriptionId(in.nextInt());

                    System.out.print("Enter ID of organization: ");
                    item.setOrganizationId(in.nextInt());

                    data.setContent("43");
                    data.setObject(item);

                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("44")) {
                    outputStream.writeObject(new PackageData(username, "42", null));

                    System.out.print("Enter ID of necessary item: ");
                    int temp = in.nextInt();

                    ListItem item = new ListItem();
                    item.setId(temp);

                    data.setContent("44");
                    data.setObject(item);
                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("31")) {
                    Purchase purchase = new Purchase();

                    purchase.setOrderDate(new Date());

                    System.out.print("Is successfully paid?: ");
                    purchase.setSuccessfullyPaid(in.nextBoolean());

                    System.out.print("Is active?: ");
                    purchase.setActive(in.nextBoolean());

                    System.out.print("Total cost: ");
                    purchase.setTotalCost(in.nextDouble());

                    data.setObject(purchase);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("32")) {
                    data.setObject(null);
                    outputStream.writeObject(data);
                } else if (data.getContent().equals("33")) {
                    outputStream.writeObject(new PackageData(username, "32", null));

                    System.out.print("Enter ID of necessary purchase: ");
                    int temp = in.nextInt();

                    Purchase purchase = new Purchase();
                    purchase.setPurchaseID(temp);

                    purchase.setOrderDate(new Date());

                    System.out.print("Is successfully paid?: ");
                    purchase.setSuccessfullyPaid(in.nextBoolean());

                    System.out.print("Is active?: ");
                    purchase.setActive(in.nextBoolean());

                    System.out.print("Total cost: ");
                    purchase.setTotalCost(in.nextDouble());

                    data.setContent("33");
                    data.setObject(purchase);

                    outputStream.writeObject(data);
                }
                else if (data.getContent().equals("34")) {
                    outputStream.writeObject(new PackageData(username, "32", null));

                    System.out.print("Enter ID of necessary purchase: ");
                    int temp = in.nextInt();

                    Purchase purchase = new Purchase();
                    purchase.setPurchaseID(temp);

                    data.setContent("34");
                    data.setObject(purchase);
                    outputStream.writeObject(data);
                }
                else {
                    System.out.println("Unknown command!");
                    data.setObject(null);
                    outputStream.writeObject(data);
                }

                if ((data = (PackageData) inputStream.readObject()) != null) {
                    System.out.println(data);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
