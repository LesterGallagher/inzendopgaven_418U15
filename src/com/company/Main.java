package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * De "Main" class.
 * @author Sem Postma
 */
public class Main {

    /**
     * De "main" methode.
     * @param args Programma argumenten.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> {
            LoginScherm screen = new LoginScherm();
            screen.setVisible(true);
        });
    }
}
