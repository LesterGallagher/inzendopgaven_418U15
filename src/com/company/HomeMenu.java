package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class HomeMenu extends JMenuBar {
    public HomeMenu(Home home) {

        JMenuItem logMenuItem = new JMenuItem("Logboek");
        logMenuItem.setMnemonic(KeyEvent.VK_L);
        logMenuItem.getAccessibleContext().setAccessibleDescription(
                "Bekijk het logboek");
        logMenuItem.addActionListener(e -> {
            LogOverviewFrame logOverviewFrame = new LogOverviewFrame();
            logOverviewFrame.setVisible(true);
        });
        logMenuItem.setMaximumSize( logMenuItem.getPreferredSize() );
        add(logMenuItem);

        add(Box.createHorizontalGlue());

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.getAccessibleContext().setAccessibleDescription(
            "Bekijk het help menu");
        add(helpMenu);

        JMenuItem documentationMenu = new JMenuItem("Documentatie");
        documentationMenu.getAccessibleContext().setAccessibleDescription(
            "Bekijk de documentatie");
        helpMenu.add(documentationMenu);

        documentationMenu.addActionListener(new LinkClickActionListener(
                home,
                "https://esstudio.site/inzendopgaven_418U15/java-doc/com/company/package-summary.html"
        ));

        JMenuItem websiteMenuItem = new JMenuItem("Website");
        websiteMenuItem.getAccessibleContext().setAccessibleDescription(
                "Bekijk de website");
        helpMenu.add(websiteMenuItem);

        websiteMenuItem.addActionListener(new LinkClickActionListener(
                home,
                "https://esstudio.site/inzendopgaven_418U15"
        ));


    }
}
