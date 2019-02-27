package com.company;

import javax.swing.*;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> {
            StartScreen screen = new StartScreen();
            screen.setVisible(true);
        });
    }
}
