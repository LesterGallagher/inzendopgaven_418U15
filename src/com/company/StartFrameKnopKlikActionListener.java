package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Start scherm knop klik listener.
 * Deze listener wordt aangeroepen wanneer de gebruiker op een
 * van de oefening knoppen op het start scherm klikt.
 * @author Sem Postma
 */
public class StartFrameKnopKlikActionListener implements ActionListener {
    protected OefeningFrame frame;

    public StartFrameKnopKlikActionListener(OefeningFrame frame) {
        this.frame = frame;
    }

    /**
     * Deze methode zorgt ervoor dat de oefening frame zichtbaar en geinititeerd wordt.
     * @param e De event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        frame.setVisible(true);
        frame.init();
    }
}
