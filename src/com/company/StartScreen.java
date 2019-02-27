package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;

public class StartScreen extends JFrame {

    private static String name = "";

    private JLabel headerLabel = new JLabel("Vul hieronder je naam in om de rekentrainer te starten.");
    private JTextField naamField = new JTextField("Jouw naam...");
    private JButton startButton = new JButton("Start");

    public StartScreen() {
        setTitle("Start Scherm");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new AppIcon("/icon.png").getImage());
        setResizable(false);

        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(headerLabel);
        panel.add(Box.createVerticalStrut(10));
        naamField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(naamField);
        panel.add(Box.createVerticalStrut(10));
        startButton.addActionListener(e -> this.showMenu());
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(startButton);
        getContentPane().add(panel);
        rootPane.setDefaultButton(startButton);
        startButton.requestFocus();

        validate();
        repaint();
        pack();
        setLocationRelativeTo(null);
    }

    public static String getUserName() { return name; }

    private void showMenu() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        name = naamField.getText();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        var home = new Home();
        home.setVisible(true);
    }
}
