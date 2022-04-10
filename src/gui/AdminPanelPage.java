package gui;

import javax.swing.*;
import java.awt.*;

public class AdminPanelPage extends JPanel {
    private GeneralFrame parent;

    public AdminPanelPage(GeneralFrame parent) {
        this.parent = parent;

        setSize(500,500);
        setLayout(null);

        if (parent.getProfilePage().getCurrentUserIndex() == 0) {
            JLabel title = new JLabel("ADMIN PANEL");
            title.setLocation(160, 10);
            title.setSize(300, 30);
            title.setFont(new Font("Serif", Font.BOLD, 20));
            add(title);

            JButton addDataButton = new JButton("Create data");
            addDataButton.setLocation(90, 85);
            addDataButton.setSize(300,30);
            add(addDataButton);

            JButton listDataButton = new JButton("List data");
            listDataButton.setLocation(90, 170);
            listDataButton.setSize(300,30);
            add(listDataButton);

            JButton editDataButton = new JButton("Edit data");
            editDataButton.setLocation(90, 255);
            editDataButton.setSize(300,30);
            add(editDataButton);

            JButton deleteDataButton = new JButton("Delete data");
            deleteDataButton.setLocation(90, 340);
            deleteDataButton.setSize(300,30);
            add(deleteDataButton);

        } else {
            JLabel title = new JLabel("PAGE NOT FOUND");
            title.setLocation(150, 10);
            title.setSize(300, 30);
            title.setFont(new Font("Serif", Font.BOLD, 20));
            add(title);
        }
    }
}
