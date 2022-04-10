package gui;

import entities.Organization;
import entities.User;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPage extends JPanel {
    private GeneralFrame parent;

    public SignUpPage(GeneralFrame parent) {
        this.parent = parent;

        String[] countries = new String[9];
        countries[0] = ("Armenia");
        countries[1] = ("Azerbaijan");
        countries[2] = ("Belarus");
        countries[3] = ("Kazakhstan");
        countries[4] = ("Kyrgyzstan");
        countries[5] = ("Moldova");
        countries[6] = ("Russia");
        countries[7] = ("Tajikistan");
        countries[8] = ("Uzbekistan");

        setSize(500,500);
        setLayout(null);

        JLabel label = new JLabel("Sign Up");
        label.setSize(300, 30);
        label.setLocation(90, 15);
        add(label);

        JTextField fullNameInputField = new JTextField();
        TextPrompt fullNamePlaceholder = new TextPrompt("Enter full name", fullNameInputField);
        fullNameInputField.setLocation(90, 65);
        fullNameInputField.setSize(300,30);
        add(fullNameInputField);

        JTextField phoneNumberInputField = new JTextField();
        TextPrompt phoneNumberPlaceholder = new TextPrompt("Enter phone number", phoneNumberInputField);
        phoneNumberInputField.setLocation(90, 115);
        phoneNumberInputField.setSize(300,30);
        add(phoneNumberInputField);

        JTextField emailInputField = new JTextField();
        TextPrompt emailPlaceholder = new TextPrompt("Enter email", emailInputField);
        emailInputField.setLocation(90, 165);
        emailInputField.setSize(300,30);
        add(emailInputField);

        JPasswordField passwordInputField = new JPasswordField();
        TextPrompt passwordPlaceholder = new TextPrompt("Enter password", passwordInputField);
        passwordInputField.setEchoChar('*');
        passwordInputField.setLocation(90, 215);
        passwordInputField.setSize(300,30);
        add(passwordInputField);

        JPasswordField passwordRepeatInputField = new JPasswordField();
        TextPrompt passwordRepeatPlaceholder = new TextPrompt("Repeat password", passwordRepeatInputField);
        passwordRepeatInputField.setEchoChar('*');
        passwordRepeatInputField.setLocation(90, 265);
        passwordRepeatInputField.setSize(300,30);
        add(passwordRepeatInputField);

        JLabel countryBoxLabel = new JLabel("Your country:");
        countryBoxLabel.setSize(100, 30);
        countryBoxLabel.setLocation(90, 315);
        add(countryBoxLabel);

        JComboBox<String> countriesSet = new JComboBox<>(countries);
        countriesSet.setSize(209, 30);
        countriesSet.setLocation(180, 315);
        add(countriesSet);

        JButton loginButton = new JButton("Create an account");
        loginButton.setLocation(90, 365);
        loginButton.setSize(300,30);
        loginButton.addActionListener(click -> {
            if (emailInputField.getText().equals("") || passwordInputField.getText().equals("")
                    || passwordInputField.getText().equals("") || fullNameInputField.getText().equals("")
                    || phoneNumberInputField.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Please, fill out all fields",
                        "Empty field", JOptionPane.ERROR_MESSAGE);
            else if (!passwordInputField.getText().equals(passwordRepeatInputField.getText()))
                JOptionPane.showMessageDialog(this, ("Passwords aren't same!\n" +
                        "Please, repeat the password correctly"), "Password mismatch", JOptionPane.ERROR_MESSAGE);
            else if (!fullNameIsValid(fullNameInputField.getText()))
                JOptionPane.showMessageDialog(this, """
                        Full name is invalid!
                        Please, enter correct full name
                        
                        Tip: only latin characters, min 6 max 29 symbols""", "Invalid full name",
                        JOptionPane.ERROR_MESSAGE);
            else if (!emailIsValid(emailInputField.getText()))
                JOptionPane.showMessageDialog(this, "Email is invalid!\n" +
                        "Please, enter correct email", "Invalid email", JOptionPane.ERROR_MESSAGE);
            else if (!passwordIsValid(passwordInputField.getText()))
                JOptionPane.showMessageDialog(this, """
                                Password is invalid!
                                Please, enter correct password

                                Tips:
                                    • Password must be less than 20 and more than 8 characters in length.
                                    • Password must have at least one uppercase character
                                    • Password must have at least one lowercase character
                                    • Password must have at least one number
                                    • Password must have at least one special character among @#$%""",
                        "Invalid password", JOptionPane.ERROR_MESSAGE);
            else if (setOrganization(emailInputField.getText(), passwordInputField.getText()).equals("no_such_account"))
                JOptionPane.showMessageDialog(this, """
                        It looks like your organization doesn't have an account with this email.
                        Try contacting your manager to get detailed information.""", "No such account",
                        JOptionPane.ERROR_MESSAGE);
            else if (setOrganization(emailInputField.getText(), passwordInputField.getText()).
                    equals("incorrect_password"))
                JOptionPane.showMessageDialog(this, """
                        We've found your email in organization's members list, but password is incorrect.
                        Try contacting your manager to get detailed information.""", "Incorrect password",
                        JOptionPane.ERROR_MESSAGE);
            else {
                parent.getDatabase().getUsers().add(new User(
                        parent.getDatabase().getUsers().size()+1,
                        fullNameInputField.getText(),
                        phoneNumberInputField.getText(),
                        emailInputField.getText(),
                        passwordInputField.getText(),
                        countries[countriesSet.getSelectedIndex()],
                        setOrganization(emailInputField.getText(), passwordInputField.getText())
                ));
                JOptionPane.showMessageDialog(this, "Success!\n" +
                        "Now you can sign in with this data");
                parent.getSignUpPage().setVisible(false);
                parent.getLoginPage().setVisible(true);
            }
        });
        add(loginButton);

        JLabel signUpLabel = new JLabel("Already have an account?");
        signUpLabel.setSize(200, 30);
        signUpLabel.setLocation(90, 415);
        add(signUpLabel);

        JButton signInRedirectButton = new JButton("Sign In");
        signInRedirectButton.setSize(100, 30);
        signInRedirectButton.setLocation(290, 415);
        signInRedirectButton.addActionListener(click -> {
            parent.getSignUpPage().setVisible(false);
            parent.getLoginPage().setVisible(true);
        });
        add(signInRedirectButton);
    }

    public boolean emailIsValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean passwordIsValid(String password) {
        return (password.length() <= 20 && password.length() >= 8) && (password.matches("(.*[A-Z].*)")) &&
                (password.matches("(.*[a-z].*)")) && (password.matches("(.*[0-9].*)")) &&
                (password.matches("(.*[@,#,$,%].*$)"));
    }

    public boolean fullNameIsValid(String fullName) {
        String[] words = fullName.split(" ");
        boolean isValid = true;

        for (String word : words)
            if (!word.matches("^[a-zA-Z]*$")) {
                isValid = false;
                break;
            }

        return isValid;
    }

    public String setOrganization(String email, String password) {
        String result = "None";

        for (Organization organization : parent.getDatabase().getOrganizations()) {
            if (email.contains(organization.getCorporateDomain())) {
                if (organization.getMembers().containsKey(email)) {
                    if (organization.getMembers().get(email).contains(password))
                        result = organization.getOrganizationName();
                    else result = "incorrect_password";
                } else result = "no_such_account";
            }
        }

        return result;
    }
}
