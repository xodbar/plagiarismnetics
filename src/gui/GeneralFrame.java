package gui;

import entities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;

public class GeneralFrame extends JFrame {
    private SignInPage signInPage;
    private SignUpPage signUpPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private SubscriptionsPage subscriptionsPage;
    private AdminPanelPage adminPanelPage;

    private JMenuBar menuBar;
    private Database database;

    public GeneralFrame() {
        database = new Database();
        database.getUsers().add(new User(
                0,
                "Admin",
                "+7777777777",
                "admin@iitusolutions.kz",
                "Admin123$",
                "Kazakhstan",
                "IITU"
        ));

        HashMap<String, String> users = new HashMap<>();
        users.put("29316@iitu.edu.kz", "100702Zh$");
        database.getOrganizations().add(new Organization(
                "IITU",
                "@iitu.edu.kz",
                15,
                1,
                users
        ));

        database.getSubscriptions().add(new Subscription(1, "Basic | Only documents", 15.0));
        database.getSubscriptions().add(new Subscription(2, "Middle | Documents, OCR", 20.0));
        database.getSubscriptions().add(new Subscription(3, "Pro | Documents, OCR & Archives", 30.0));
        database.getSubscriptions().add(new Subscription(4, "Personal | Personal functions", 22.0));
        database.getSubscriptions().add(new Subscription(5, "Enterprise | All functionality", 100.0));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,540);
        setTitle("Plagiarismnetics 0.1");
        setLayout(null);

        signInPage = new SignInPage(this);
        signInPage.setVisible(true);
        add(signInPage);

        signUpPage = new SignUpPage(this);
        signUpPage.setVisible(false);
        add(signUpPage);

        mainPage = new MainPage(this);
        mainPage.setVisible(false);
        add(mainPage);

        profilePage = new ProfilePage(this);
        profilePage.setVisible(false);
        add(profilePage);

        subscriptionsPage = new SubscriptionsPage(this);
        subscriptionsPage.setVisible(false);
        add(subscriptionsPage);

        adminPanelPage = new AdminPanelPage(this);
        adminPanelPage.setVisible(false);
        add(adminPanelPage);

        //ssss

        menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenuItem profile = new JMenuItem(new AbstractAction("Profile") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getLoginPage().getCurrentUserIndex() >= 0) {
                    getMainPage().setVisible(false);
                    getSubscriptionsPage().setVisible(false);
                    getProfilePage().setVisible(true);
                    getAdminPanelPage().setVisible(false);
                } else JOptionPane.showMessageDialog(null, "No current user!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }), subscriptions = new JMenuItem(new AbstractAction("Subscriptions") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getLoginPage().getCurrentUserIndex() >= 0) {
                    getMainPage().setVisible(false);
                    getSubscriptionsPage().setVisible(true);
                    getProfilePage().setVisible(false);
                    getAdminPanelPage().setVisible(false);
                } else JOptionPane.showMessageDialog(null, "No current user!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }), main = new JMenuItem(new AbstractAction("Main") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getLoginPage().getCurrentUserIndex() >= 0) {
                    getMainPage().setVisible(true);
                    getSubscriptionsPage().setVisible(false);
                    getProfilePage().setVisible(false);
                    getAdminPanelPage().setVisible(false);
                } else JOptionPane.showMessageDialog(null, "No current user!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        menu.add(main);
        menu.addSeparator();
        menu.add(profile);
        menu.addSeparator();
        menu.add(subscriptions);

        JMenu about = new JMenu("About");
        JMenuItem aboutProject = new JMenuItem("About Plagiarismnetics"),
                aboutUs = new JMenuItem("About IITU Solutions");
        about.add(aboutProject);
        about.addSeparator();
        about.add(aboutUs);

        menuBar.add(menu);
        menuBar.add(about);
    }

    public SignInPage getLoginPage() {
        return signInPage;
    }

    public void setLoginPage(SignInPage signInPage) {
        this.signInPage = signInPage;
    }

    public SignUpPage getSignUpPage() {
        return signUpPage;
    }

    public void setSignUpPage(SignUpPage signUpPage) {
        this.signUpPage = signUpPage;
    }


    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setMenuVisible(boolean value) {
        if (value) {
            this.setJMenuBar(menuBar);
        } else this.setJMenuBar(null);
    }

    public JMenuBar getGeneralMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public ProfilePage getProfilePage() {
        return profilePage;
    }

    public void setProfilePage(ProfilePage profilePage) {
        this.profilePage = profilePage;
    }

    public void updateCurrentUserIndex() throws IOException {
        int current = this.getLoginPage().getCurrentUserIndex();
        this.getProfilePage().setCurrentUserIndex(current);
        this.getMainPage().setCurrentUserIndex(current);
        this.getSubscriptionsPage().setCurrentUserIndex(current);
    }

    public SubscriptionsPage getSubscriptionsPage() {
        return subscriptionsPage;
    }

    public void setSubscriptionsPage(SubscriptionsPage subscriptionsPage) {
        this.subscriptionsPage = subscriptionsPage;
    }

    public AdminPanelPage getAdminPanelPage() {
        return adminPanelPage;
    }

    public void setAdminPanelPage(AdminPanelPage adminPanelPage) {
        this.adminPanelPage = adminPanelPage;
    }
}
