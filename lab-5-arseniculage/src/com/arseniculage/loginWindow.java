package com.arseniculage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class loginWindow {
    private JTextField textField1;
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton loginButton;
    private JButton registerButton;

    private static List<User> users = new ArrayList<>();
    String name, login, password, repeation;
    private static User currentUser;

    private static void addUser(String name, String login, String password, String repeation) throws Exception {
        for (User user : users)
            if (user.getLogin().equals(login))
                throw new Exception("Login already exists");
        if (!password.equals(repeation))
            throw new Exception("Password mismatch");
        if (users.isEmpty())
            users.add(new Admin(name, login, password));
        else
            users.add(new Client(name, login, password));
    }

    private static User findUser(String login) throws Exception {
        for (User user : users)
            if (user.getLogin().equals(login))
                return user;
        throw new Exception("Login not found");
    }
    public loginWindow() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                try {
                    User temp = findUser(login);
                    if (temp.enter(login = textField1.getText(), password = textField3.getText())) {
                        currentUser = temp;
                        if (currentUser instanceof Admin) {
                            //Admin
                        }
                        else {
                            //User
                        }
                    }
                } catch (InterruptedException z) {

                } catch (Exception z) {
                    System.out.println(e.toString());

                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    addUser(name = textField1.getText(), login = textField2.getText(), password = textField3.getText(), repeation = textField4.getText());
                    currentUser = findUser(login);

                    if (currentUser instanceof Admin) {
                        //Admincall
                    }
                    else {
                       //Usercall
                    }
                } catch (InterruptedException z) {

                } catch (Exception z) {
                    System.out.println(e.toString());
                }


            }
        });
    }

    public static void callWindow()
    {
        JFrame frame = new JFrame("Effective Production System");
        frame.setContentPane(new loginWindow().panel1);
        frame.pack();
        frame.setVisible(true);

    }
}
