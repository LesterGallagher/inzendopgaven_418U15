package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sem Postma
 */
public class HomeButtonClickActionListener implements ActionListener {
    protected OefeningFrame frame;

    public HomeButtonClickActionListener(OefeningFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        frame.setVisible(true);
        frame.init();
    }
}
