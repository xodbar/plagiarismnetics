package gui;

import entities.ListItem;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class MainPage extends JPanel {
    private GeneralFrame parent;
    private int currentUserIndex;

    public MainPage(GeneralFrame parent) {
        this.parent = parent;

        setSize(500, 700);
        setLayout(null);

    }

    public void setCurrentUserIndex(int currentUserIndex) throws IOException {
        this.currentUserIndex = currentUserIndex;

        JLabel title = new JLabel("Plagiarismnetics");
        title.setLocation(170, 5);
        title.setSize(300, 30);
        title.setFont(new Font("Serif", Font.BOLD, 17));
        add(title);

        JLabel inputBlockLabel = new JLabel("Input:");
        inputBlockLabel.setLocation(30, 30);
        inputBlockLabel.setSize(300, 30);
        add(inputBlockLabel);

        JTextArea textInputAreaS = new JTextArea();
        TextPrompt textInputAreaPlaceholder = new TextPrompt("\t\tEnter text here...", textInputAreaS);
        JScrollPane textInputArea = new JScrollPane(textInputAreaS);
        textInputArea.setSize(175, 240);
        textInputArea.setLocation(30, 60);
        textInputAreaS.setLineWrap(true);
        add(textInputArea);

        JLabel justLabel = new JLabel("or");
        justLabel.setLocation(30, 294);
        justLabel.setSize(50, 30);
        add(justLabel);

        JButton uploadFileButton = new JButton("Upload file");
        uploadFileButton.setLocation(30, 320);
        uploadFileButton.setSize(175, 30);
        uploadFileButton.addActionListener(click -> {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Documents & Archives",
                    "docx", "pdf","jpeg", "zip");
            fileOpen.addChoosableFileFilter(filter);
            fileOpen.showOpenDialog(null);

            int ret = fileOpen.showDialog(null, "Open file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileOpen.getSelectedFile();
                justLabel.setText(file.getName());
            }
        });
        add(uploadFileButton);

        JLabel statusLog = new JLabel("Press button to check");
        statusLog.setLocation(30,400);
        statusLog.setSize(175, 30);
        add(statusLog);

        JTextArea possibleSources = new JTextArea();
        TextPrompt possibleSourcesPlaceholder = new TextPrompt("\t\tPossible sources will be there", possibleSources);
        JScrollPane possibleSourcesS = new JScrollPane(possibleSources);
        possibleSourcesS.setSize(175, 240);
        possibleSourcesS.setLocation(250, 60);
        possibleSources.setLineWrap(true);
        add(possibleSourcesS);

        JLabel resultLog = new JLabel();
        resultLog.setText("Result");
        resultLog.setLocation(250,320);
        resultLog.setSize(175, 30);
        resultLog.setFont(new Font("Serif", Font.BOLD, 24));
        add(resultLog);


        JButton startCheckingButton = new JButton("Check plagiarism");
        startCheckingButton.setLocation(30, 370);
        startCheckingButton.setSize(175, 30);
        startCheckingButton.addActionListener(new ActionListener() {
                                                  public void actionPerformed(ActionEvent ae) {
                                                      if (subscriptionId() > 0) {
                                                          double rand = 0;
                                                          for (int i = 0; i < 100; i++) {
                                                              rand = (double) (Math.random() * 100);
                                                          }
                                                          resultLog.setText("Result: " + new DecimalFormat("##.##").format(rand) + "%");
                                                          resultLog.setLocation(250,320);
                                                          resultLog.setFont(new Font("Serif", Font.BOLD, 24));

                                                          String[] listOfSources = new String[] {
                                                                  "lingua.com", "EnRuText.com", "learnenglish.org",
                                                                  "langformula.ru", "memorysecrets.ru", "yandex.ru",
                                                                  "dundeeclub.ru", "esolcourses.com", "delo-angl.ru",
                                                                  "kpfu.ru", "oneminuteenglish.org", "lengish.com"
                                                          };

                                                          int randChoose = 0;
                                                          for (int j = 0; j < listOfSources.length; j++) {
                                                              randChoose = (int) (Math.random() * listOfSources.length);
                                                              possibleSources.setText(listOfSources[randChoose]);
                                                          }


                                                      } else {
                                                          resultLog.setText("You have no subscription");
                                                          resultLog.setLocation(250,320);
                                                          resultLog.setFont(new Font("Serif", Font.BOLD, 16));
                                                          startCheckingButton.setEnabled(false);
                                                      }
                                                  }
                                              }
        );
        add(startCheckingButton);

        JButton subCheckingButton = new JButton("Check subscription");
        subCheckingButton.setLocation(250, 370);
        subCheckingButton.setSize(175, 30);
        subCheckingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (subscriptionId() < 0) {
                    resultLog.setText("You have no subscription");
                    resultLog.setLocation(250,320);
                    resultLog.setFont(new Font("Serif", Font.BOLD, 16));
                    startCheckingButton.setEnabled(false);
                    subCheckingButton.addActionListener(click -> {
                        if (subscriptionId() < 0) {
                            parent.getMainPage().setVisible(false);
                            parent.getSubscriptionsPage().setVisible(true);
                            remove(resultLog);
                        } else {
                            resultLog.setText("Result");
                            resultLog.setLocation(250, 320);
                            resultLog.setFont(new Font("Serif", Font.BOLD, 24));
                            parent.getMainPage().setVisible(true);
                            parent.getSubscriptionsPage().setVisible(false);
                            add(resultLog);
                        }
                    });
                } else if (subscriptionId() > 0){
                    startCheckingButton.setEnabled(true);
                }
            }
        });
        add(subCheckingButton);

        JLabel outputBlockLabel = new JLabel("Output:");
        outputBlockLabel.setLocation(250, 30);
        outputBlockLabel.setSize(300, 30);
        add(outputBlockLabel);


    }

    public int subscriptionId() {
        int result = -1;

        for (ListItem item : parent.getDatabase().getListItems()) {
            if (Objects.equals(item.getUser().getUserID(), parent.getDatabase().getUsers().
                    get(currentUserIndex).getUserID()))
                if (item.getPurchase().isActive()) {
                    result = item.getSubscription().getSubscriptionID();
                    break;
                }
        }

        return result;
    }
}
