package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Een link klik listener voor het start scherm.
 * @author Sem Postma
 */
public class LinkClickActionListener implements ActionListener {

    /**
     * Een verwijzing naar het start frame.
     */
    protected StartFrame startFrame;
    /**
     * De url van de link.
     */
    protected String url;

    public LinkClickActionListener(StartFrame startFrame, String url) {
        this.startFrame = startFrame;
        this.url = url;
    }

    /**
     * Deze methode wordt aangeroepen wanneer de leerling op het menu itm klikt.
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
