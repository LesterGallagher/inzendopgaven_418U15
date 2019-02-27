package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Logger;

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
