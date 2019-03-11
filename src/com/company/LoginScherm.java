package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Het login scherm van de applicatie.
 * Hier vult de leerling zijn of haar naam in.
 * @author Sem Postma
 */
public class LoginScherm extends JFrame {

    /**
     * De naam van de leerling.
     */
    private static String name = "";

    /**
     * De label met het bericht om de naam in te vullen.
     */
    private JLabel headerLabel = new JLabel("Vul hieronder je naam in om de rekentrainer te starten.");
    /**
     * Het tekst veld met de naam van de leerling.
     */
    private JTextField naamField = new JTextField("Jouw naam...");
    /**
     * De knop om de rekentrainer te starten.
     */
    private JButton startButton = new JButton("Start");

    public LoginScherm() {
        setTitle("Start Scherm");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new AppIcoontje("/icon.png").getImage());
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
        startButton.addActionListener(e -> this.openRekentrainer());
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

    /**
     * Deze methode verkrijgt de naam van de leerling.
     * @return De naam van de leerling
     */
    public static String getUserName() {
        return name;
    }

    /**
     * Open de rekentrainer.
     */
    private void openRekentrainer() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        name = naamField.getText();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        var home = new StartFrame();
        home.setVisible(true);
    }
}
