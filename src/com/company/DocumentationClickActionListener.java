package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;


public class DocumentationClickActionListener implements ActionListener {

    protected Home home;

    public DocumentationClickActionListener(Home home) {
        this.home = home;
    }

    /**
     * Handles the documentation menu item click event.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(java.net.URI.create("https://esstudio.site/FuglenRekentrainer"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
