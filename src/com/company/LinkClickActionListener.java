package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;


public class LinkClickActionListener implements ActionListener {

    protected Home home;
    protected String url;

    public LinkClickActionListener(Home home, String url) {
        this.home = home;
        this.url = url;
    }

    /**
     * Handles the documentation menu item click event.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
