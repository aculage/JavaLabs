package com.arseniculage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminForm {

    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JSpinner spinner1;
    private JComboBox comboBox1;
    private JSpinner spinner2;
    private JButton addButton1;
    private JFormattedTextField formattedTextField1;
    private JButton createPlanButton;
    private JPanel panel1;

    public adminForm() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void callWindow()
    {
        JFrame frame = new JFrame("Effective Production System");
        frame.setContentPane(new adminForm().panel1);
        frame.pack();
        frame.setVisible(true);

    }
}
