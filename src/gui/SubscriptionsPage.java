package gui;

import entities.ListItem;
import entities.Purchase;
import entities.Subscription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SubscriptionsPage extends JPanel {
    private GeneralFrame parent;
    private int currentUserIndex;

    public SubscriptionsPage(GeneralFrame parent) {
        this.parent = parent;

        setSize(500,500);
        setLayout(null);
    }

    public int getCurrentUserIndex() {
        return currentUserIndex;
    }

    public void setCurrentUserIndex(int currentUserIndex) {
        this.currentUserIndex = currentUserIndex;

        JLabel title = new JLabel("SUBSCRIPTIONS");
        title.setLocation(150, 10);
        title.setSize(300, 30);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        add(title);

        String[] columnNames = {
                "ID",
                "Description",
                "Price ($/month)"
        };

        DefaultTableModel subscriptionsTable = new DefaultTableModel(columnNames, 0);

        int index = -1;
        /*for (int i = 0; i < parent.getDatabase().getOrganizations().size(); i++) {
            if (parent.getDatabase().getOrganizations().get(i).getOrganizationName().
                    equals(parent.getDatabase().getUsers().get(parent.getLoginPage().getCurrentUserIndex()).
                            getOrganization())) {
                index = i;
                break;
            }
        }*/

        /*for (Subscription subscription : parent.getDatabase().getSubscriptions()) {
            Object[] row = {subscription.getSubscriptionID(), subscription.getDescription(),
                    (subscription.getPrice() - (subscription.getPrice() * (parent.getDatabase().getOrganizations().
                            get(index).getDiscountPercent()*1.0/100)))};
            subscriptionsTable.addRow(row);
        }*/

        JScrollPane tableScrollable = new JScrollPane(new JTable(subscriptionsTable));
        tableScrollable.setLocation(30, 70);
        tableScrollable.setSize(400, 300);
        add(tableScrollable);

        JLabel radioBoxLabel = new JLabel("Choose ID of subscription");
        radioBoxLabel.setLocation(30, 400);
        radioBoxLabel.setSize(120, 30);
        add(radioBoxLabel);

        ArrayList<Integer> idSet = new ArrayList<>();
        /*for(int i = 0; i < parent.getDatabase().getSubscriptions().size(); i++){
            idSet.add(i);
        }*/
        JComboBox<Object> ids = new JComboBox<>(idSet.toArray());
        ids.setLocation(160, 400);
        ids.setSize(50, 30);
        add(ids);

        int ind = index;

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setLocation(220, 400);
        confirmButton.setSize(100, 30);
        /*Subscription subscription = parent.getDatabase().getSubscriptions().get(ids.getSelectedIndex());*/
        /*confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getDatabase().getPurchases().add(new Purchase(
                        parent.getDatabase().getPurchases().size()+1,
                        new Date(),
                        true,
                        true,
                        (subscription.getPrice() - (subscription.getPrice() * (parent.getDatabase().getOrganizations().
                                get(ind).getDiscountPercent()*1.0/100)))));

                parent.getDatabase().getListItems().add(new ListItem(
                        parent.getDatabase().getUsers().get(parent.getLoginPage().getCurrentUserIndex()),
                        parent.getDatabase().getPurchases().get(parent.getDatabase().getPurchases().size()-1),
                        subscription,
                        parent.getDatabase().getOrganizations().get(ind))
                );

                JOptionPane.showMessageDialog(parent, "Successfully Paid");
                try {
                    parent.updateCurrentUserIndex();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                parent.getMainPage().setVisible(true);
                parent.getSubscriptionsPage().setVisible(false);
            }
        });*/
        add(confirmButton);
    }
}
