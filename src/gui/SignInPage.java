package gui;

import entities.User;

import javax.swing.*;
import java.io.IOException;

public class SignInPage extends JPanel {
    private GeneralFrame parent;
    private int currentUserIndex;

    public SignInPage(GeneralFrame parent) {
        this.parent = parent;

        setSize(500,500);
        setLayout(null);

        JLabel label = new JLabel("Sign In");
        label.setSize(300, 30);
        label.setLocation(90, 50);
        add(label);

        JTextField emailInputField = new JTextField();
        TextPrompt emailPlaceholder = new TextPrompt("Enter email", emailInputField);
        emailInputField.setLocation(90, 100);
        emailInputField.setSize(300,30);
        add(emailInputField);

        JPasswordField passwordInputField = new JPasswordField();
        TextPrompt passwordPlaceholder = new TextPrompt("Enter password", passwordInputField);
        passwordInputField.setEchoChar('*');
        passwordInputField.setLocation(90, 150);
        passwordInputField.setSize(300,30);
        add(passwordInputField);

        JButton loginButton = new JButton("Sign In");
        loginButton.setLocation(90, 200);
        loginButton.setSize(300,30);
        loginButton.addActionListener(click -> {
            if (emailInputField.getText().equals("") || passwordInputField.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Please, fill out all fields");
            else if (signIn(emailInputField.getText(), passwordInputField.getText()) == -1)
                JOptionPane.showMessageDialog(this, "Incorrect email or password",
                        "Incorrect input",
                        JOptionPane.ERROR_MESSAGE);
            else {
                parent.getLoginPage().setVisible(false);
                setCurrentUserIndex(signIn(emailInputField.getText(),
                        passwordInputField.getText()));
                parent.getMainPage().setVisible(true);
                parent.setMenuVisible(true);

                try {
                    parent.updateCurrentUserIndex();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        add(loginButton);

        JLabel signUpLabel = new JLabel("Don't have an account yet?");
        signUpLabel.setSize(200, 30);
        signUpLabel.setLocation(90, 250);
        add(signUpLabel);

        JButton signUpRedirectButton = new JButton("Sign Up");
        signUpRedirectButton.setSize(100, 30);
        signUpRedirectButton.setLocation(290, 250);
        signUpRedirectButton.addActionListener(click -> {
            parent.getLoginPage().setVisible(false);
            parent.getSignUpPage().setVisible(true);
        });
        add(signUpRedirectButton);
    }

    public int signIn(String email, String password) {
        int successfullySignedIn = -1;

        int i = 0;
        for (User user : parent.getDatabaseManager().getUsers())
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                successfullySignedIn = user.getUserID();
                break;
            }

        return successfullySignedIn;
    }

    public void setCurrentUserIndex(int newUserIndex) {
        currentUserIndex = newUserIndex;
    }

    public int getCurrentUserIndex() {
        return currentUserIndex;
    }
}
