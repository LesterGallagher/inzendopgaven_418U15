package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class HomeMenu extends JMenuBar {
    private JMenuItem menuItem;
    private JRadioButtonMenuItem rbMenuItem;
    private JCheckBoxMenuItem cbMenuItem;

    public HomeMenu(Home home) {

        add(Box.createHorizontalGlue());
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.getAccessibleContext().setAccessibleDescription(
            "Bekijk het help menu");
        helpMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(helpMenu);

        JMenuItem documentationMenu = new JMenuItem("Documentatie");
        documentationMenu.getAccessibleContext().setAccessibleDescription(
            "Bekijk de documentatie");
        helpMenu.add(documentationMenu);

        documentationMenu.addActionListener(new DocumentationClickActionListener(home));
    }
}
