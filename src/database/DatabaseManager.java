package database;

import entities.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DatabaseManager {
    private Connection connection;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/general_database?useUnicode=true&serverTimezone=UTC",
                    "root", ""
            );
            JOptionPane.showMessageDialog(null, "Successfully connected to the database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while connecting to the database!",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void addUser(User user) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO users(id, full_name, phone_number, email, password, country, organization) " +
                                    "values(NULL, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getCountry());
            statement.setString(6, user.getOrganization());

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while adding a new user: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void editUser(User user) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE users set full_name = ?, phone_number = ?, email = ?, password = ?, country = ?, organization = ? where id = ?"
                    );
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getCountry());
            statement.setString(6, user.getOrganization());

            statement.setInt(7, user.getUserID());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error while updating data of the user: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteUser(User user) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "DELETE FROM users where id = ?"
                    );
            statement.setInt(1, user.getUserID());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while deleting the user: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM users"
                    );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String fullName = resultSet.getString("full_name");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String country = resultSet.getString("country");
                String organization = resultSet.getString("organization");

                users.add(new User(
                        (int) id,
                        fullName,
                        phoneNumber,
                        email,
                        password,
                        country,
                        organization
                ));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the users: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return users;
    }

    public User getUserById(int keyId) {
        User user = new User();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM users WHERE id = ?"
                    );

            statement.setInt(1, keyId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String fullName = resultSet.getString("full_name");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String country = resultSet.getString("country");
                String organization = resultSet.getString("organization");

                user = new User(
                        (int) id,
                        fullName,
                        phoneNumber,
                        email,
                        password,
                        country,
                        organization
                );

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the user by id: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return user;
    }


    public void addOrganization(Organization organization) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO organizations(id, organization_name, corporate_domain, discount_percent, members_capacity, default_password) " +
                                    "values(NULL, ?, ?, ?, ?, ?)");
            statement.setString(1, organization.getOrganizationName());
            statement.setString(2, organization.getCorporateDomain());
            statement.setInt(3, organization.getDiscountPercent());
            statement.setInt(4, organization.getCapacity());
            statement.setString(5, organization.getDefaultPassword());

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while adding a new organization: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void editOrganization(Organization organization) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE organizations set organization_name = ?, corporate_domain = ?, discount_percent = ?, members_capacity = ?, default_password = ? where id = ?"
                    );
            statement.setString(1, organization.getOrganizationName());
            statement.setString(2, organization.getCorporateDomain());
            statement.setInt(3, organization.getDiscountPercent());
            statement.setInt(4, organization.getCapacity());
            statement.setString(5, organization.getDefaultPassword());

            statement.setInt(6, organization.getId());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error while updating data of the organization: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteOrganization(Organization organization) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "DELETE FROM organizations where id = ?"
                    );
            statement.setInt(1, organization.getId());

            statement.executeUpdate();

            PreparedStatement statementForUsers =
                    connection.prepareStatement(
                            "DELETE FROM users where organization = ?"
                    );
            statementForUsers.setString(1, organization.getOrganizationName());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while deleting the organization: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ArrayList<Organization> getOrganizations() {
        ArrayList<Organization> organizations = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM organizations"
                    );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String organizationName = resultSet.getString("organization_name");
                String corporateDomain = resultSet.getString("corporate_domain");
                int discountPercent = resultSet.getInt("discount_percent");
                int capacity = resultSet.getInt("members_capacity");
                String defaultPassword = resultSet.getString("default_password");

                HashMap<String, String> temp = new HashMap<>();

                PreparedStatement statementForMembers =
                        connection.prepareStatement(
                                "SELECT email, password FROM users WHERE organization = ?"
                        );
                statementForMembers.setString(1, organizationName);
                ResultSet membersSet = statementForMembers.executeQuery();

                while (membersSet.next()) {
                    String email = membersSet.getString("email");
                    String password = membersSet.getString("password");

                    temp.put(email, password);
                }

                organizations.add(new Organization(
                        (int) id,
                        organizationName,
                        corporateDomain,
                        discountPercent,
                        capacity,
                        defaultPassword,
                        temp
                ));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the organizations: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return organizations;
    }

    public Organization getOrganizationById(int keyId) {
        Organization organization = new Organization();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM organizations WHERE id = ?"
                    );

            statement.setInt(1, keyId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String organizationName = resultSet.getString("organization_name");
                String corporateDomain = resultSet.getString("corporate_domain");
                int discountPercent = resultSet.getInt("discount_percent");
                int capacity = resultSet.getInt("members_capacity");
                String defaultPassword = resultSet.getString("default_password");

                HashMap<String, String> temp = new HashMap<>();

                PreparedStatement statementForMembers =
                        connection.prepareStatement(
                                "SELECT email, password FROM users WHERE organization = ?"
                        );
                statementForMembers.setString(1, organizationName);
                ResultSet membersSet = statementForMembers.executeQuery();

                while (membersSet.next()) {
                    String email = membersSet.getString("email");
                    String password = membersSet.getString("password");

                    temp.put(email, password);
                }

                organization = (new Organization(
                        (int) id,
                        organizationName,
                        corporateDomain,
                        discountPercent,
                        capacity,
                        defaultPassword,
                        temp
                ));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the user by id: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return organization;
    }


    public void addPurchase(Purchase purchase) {
        try {
            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(dt);

            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO purchases(id, order_date, successfully_paid, is_active, total_cost) " +
                                    "values(NULL, ?, ?, ?, ?)");
            statement.setString(1, currentTime);
            statement.setBoolean(2, purchase.isSuccessfullyPaid());
            statement.setBoolean(3, purchase.isActive());
            statement.setDouble(4, purchase.getTotalCost());

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while adding a new purchase: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void editPurchase(Purchase purchase) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE purchases set successfully_paid = ?, is_active = ?, total_cost = ? where id = ?"
                    );
            statement.setBoolean(1, purchase.isSuccessfullyPaid());
            statement.setBoolean(2, purchase.isActive());
            statement.setDouble(3, purchase.getTotalCost());

            statement.setInt(4, purchase.getPurchaseID());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error while updating data of the purchase: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletePurchase(Purchase purchase) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "DELETE FROM purchases where id = ?"
                    );
            statement.setInt(1, purchase.getPurchaseID());

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while deleting the purchase: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ArrayList<Purchase> getPurchases() {
        ArrayList<Purchase> purchases = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM purchases"
                    );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Date orderDate = resultSet.getDate("order_date");
                boolean successfullyPaid = resultSet.getBoolean("successfully_paid");
                boolean isActive = resultSet.getBoolean("is_active");
                double totalCost = resultSet.getDouble("total_cost");

                purchases.add(new Purchase(
                        (int) id,
                        orderDate,
                        successfullyPaid,
                        isActive,
                        totalCost
                ));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the subscriptions: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return purchases;
    }

    public Purchase getPurchaseId(int keyId) {
        Purchase purchase = new Purchase();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM purchases WHERE id = ?"
                    );

            statement.setInt(1, keyId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Date orderDate = resultSet.getDate("order_date");
                boolean successfullyPaid = resultSet.getBoolean("successfully_paid");
                boolean isActive = resultSet.getBoolean("is_active");
                double totalCost = resultSet.getDouble("total_cost");

                purchase = (new Purchase(
                        (int) id,
                        orderDate,
                        successfullyPaid,
                        isActive,
                        totalCost
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the user by id: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return purchase;
    }


    public void addSubscription(Subscription subscription) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO subscriptions(id, description, price) " +
                                    "values(NULL, ?, ?)");
            statement.setString(1, subscription.getDescription());
            statement.setDouble(2, subscription.getPrice());

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while adding a new subscription: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void editSubscription(Subscription subscription) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE subscriptions set description = ?, price = ? where id = ?"
                    );
            statement.setString(1, subscription.getDescription());
            statement.setDouble(2, subscription.getPrice());

            statement.setInt(3, subscription.getSubscriptionID());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error while updating data of the subscription: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSubscription(Subscription subscription) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "DELETE FROM subscriptions where id = ?"
                    );
            statement.setInt(1, subscription.getSubscriptionID());

            statement.executeUpdate();

            /*PreparedStatement statementForUsers =
                    connection.prepareStatement(
                            "DELETE FROM subscriptions where id = ?"
                    );
            statement.setInt(1, subscription.getSubscriptionID());

            statement.executeUpdate();*/

            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while deleting the subscription: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ArrayList<Subscription> getSubscriptions() {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM subscriptions"
                    );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");

                subscriptions.add(new Subscription(
                        (int) id,
                        description,
                        price
                ));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the subscriptions: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return subscriptions;
    }

    public Subscription getSubscriptionByID(int keyId) {
        Subscription subscription = new Subscription();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM subscriptions WHERE id = ?"
                    );

            statement.setInt(1, keyId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");

                subscription = (new Subscription(
                        (int) id,
                        description,
                        price
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the user by id: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return subscription;
    }


    public void addItem(ListItem item) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO items(id, user_id, purchase_id, subscription_id, organization_id) " +
                                    "values(NULL, ?, ?, ?, ?)");
            statement.setInt(1, item.getUserId());
            statement.setInt(2, item.getPurchaseId());
            statement.setInt(3, item.getSubscriptionId());
            statement.setInt(4, item.getOrganizationId());

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while adding a new item: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void editItem(ListItem item) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE items set user_id = ?, purchase_id = ?, subscription_id = ?, organization_id = ? where id = ?"
                    );
            statement.setInt(1, item.getUserId());
            statement.setInt(2, item.getPurchaseId());
            statement.setInt(3, item.getSubscriptionId());
            statement.setInt(4, item.getOrganizationId());

            statement.setInt(5, item.getId());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error while updating data of the item: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteItem(ListItem item) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "DELETE FROM items where id = ?"
                    );
            statement.setInt(1, item.getId());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while deleting the item: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ArrayList<ListItem> getItems() {
        ArrayList<ListItem> items = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM items"
                    );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Integer userId = resultSet.getInt("user_id");
                Integer purchaseId = resultSet.getInt("purchase_id");
                Integer subscriptionId = resultSet.getInt("subscription_id");
                Integer organizationId = resultSet.getInt("organization_id");

                items.add(new ListItem(
                        (int) id,
                        userId,
                        purchaseId,
                        subscriptionId,
                        organizationId
                ));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Error while getting the items: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        return items;
    }
}
