package gui;

import entities.ListItem;
import entities.Organization;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ProfilePage extends JPanel {
    private GeneralFrame parent;
    private int currentUserIndex;

    public ProfilePage(GeneralFrame parent) {
        this.parent = parent;

        setSize(500,500);
        setLayout(null);
    }

    public int getCurrentUserIndex() {
        return currentUserIndex;
    }

    public void setCurrentUserIndex(int currentUserIndex) {
        this.currentUserIndex = currentUserIndex;

        if (currentUserIndex >= 0) {
            int organizationIndex = -1, i = 0;
            for (Organization organization : parent.getDatabaseManager().getOrganizations()) {
                if (organization.getOrganizationName().
                        equals(parent.getDatabaseManager().getUsers().get(currentUserIndex).getOrganization())) {
                    organizationIndex = i;
                    break;
                } else i++;
            }

           /*boolean hasActiveSubscription = false;
            int purchaseId = -1, j = 0;
            for (ListItem listItem : parent.getDatabase().getListItems()) {
                if (listItem.getUser().getUserID().equals(parent.getDatabase().getUsers().
                        get(currentUserIndex).getUserID())) {
                    if (listItem.getPurchase().isActive()) {
                        hasActiveSubscription = true;
                        purchaseId = j;
                        break;
                    }
                } else j++;
            }*/


            /*JLabel title = new JLabel("PROFILE INFO");
            title.setLocation(150, 10);
            title.setSize(300, 30);
            title.setFont(new Font("Serif", Font.BOLD, 20));
            add(title);

            JLabel fullName = new JLabel("Full name: " + parent.getDatabase().getUsers().get(currentUserIndex).
                    getFullName());
            fullName.setLocation(30, 50);
            fullName.setSize(300, 30);
            add(fullName);

            JLabel email = new JLabel("Email: " + parent.getDatabase().getUsers().get(currentUserIndex).getEmail());
            email.setLocation(30, 100);
            email.setSize(300, 30);
            add(email);

            JLabel phoneNumber = new JLabel("Phone number: " + parent.getDatabase().getUsers().get(currentUserIndex).
                    getPhoneNumber());
            phoneNumber.setLocation(30, 150);
            phoneNumber.setSize(300, 30);
            add(phoneNumber);

            JLabel country = new JLabel("Country: " + parent.getDatabase().getUsers().get(currentUserIndex).
                    getCountry());
            country.setLocation(30, 200);
            country.setSize(300, 30);
            add(country);

            JLabel organization = new JLabel("Organization: " + parent.getDatabase().getUsers().get(currentUserIndex).
                    getOrganization() + ((organizationIndex != -1) ? "(" + parent.getDatabase().getOrganizations().
                    get(organizationIndex).getDiscountPercent() + "% discount)" : ""));
            organization.setLocation(30, 250);
            organization.setSize(300, 30);
            add(organization);

            JLabel activeSubscription = new JLabel("Active subscription: " + ((hasActiveSubscription) ?
                    parent.getListItems().get(j).getSubscription().getDescription() : "None"));
            activeSubscription.setLocation(30, 300);
            activeSubscription.setSize(300, 30);
            add(activeSubscription);

            if (currentUserIndex == 0) {
                JButton adminPanelRedirect = new JButton("Admin panel");
                adminPanelRedirect.setLocation(250, 380);
                adminPanelRedirect.setSize(200, 30);
                adminPanelRedirect.addActionListener(click -> {
                    parent.getProfilePage().setVisible(false);
                    parent.getAdminPanelPage().setVisible(true);
                });
                add(adminPanelRedirect);
            }

            JButton subscriptionsPageRedirect = new JButton("Subscriptions list");
            subscriptionsPageRedirect.setLocation(30, 350);
            subscriptionsPageRedirect.setSize(175, 30);
            subscriptionsPageRedirect.addActionListener(click -> {
                parent.getProfilePage().setVisible(false);
                parent.getSubscriptionsPage().setVisible(true);
            });
            add(subscriptionsPageRedirect);


            JLabel purchaseHistoryLabel = new JLabel("Purchase history:");
            purchaseHistoryLabel.setLocation(250, 50);
            purchaseHistoryLabel.setSize(300, 30);
            add(purchaseHistoryLabel);*/

            /*JTextArea purchaseHistory = new JTextArea();
            purchaseHistory.setSize(200, 300);
            purchaseHistory.setLocation(250, 80);
            for (ListItem item : parent.getDatabase().getListItems()) {
                if (Objects.equals(item.getUser().getFullName(), parent.getDatabase().getUsers().get(currentUserIndex).
                        getFullName())) {
                    purchaseHistory.append("Sub. ID: " + item.getSubscription().getSubscriptionID() + " Price: " +
                            item.getPurchase().getPurchaseID() + " Date: " + item.getPurchase().getOrderDate() +
                            " Is active: " + item.getPurchase().isActive());
                }
            }*/

            /*add(purchaseHistory);*/
        }
    }
}
