package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Het menu van het start scherm.
 * Deze klasse zorgt dat het menu van het start scherm aangemaakt wordt.
 * @author Sem Postma
 */
public class StartFrameMenu extends JMenuBar {
    public StartFrameMenu(StartFrame startFrame) {

        JMenuItem logMenuItem = new JMenuItem("Logboek");
        logMenuItem.setMnemonic(KeyEvent.VK_L);
        logMenuItem.getAccessibleContext().setAccessibleDescription(
                "Bekijk het logboek");
        logMenuItem.addActionListener(e -> {
            LogOverviewFrame logOverviewFrame = new LogOverviewFrame();
            logOverviewFrame.setVisible(true);
        });
        logMenuItem.setMaximumSize(logMenuItem.getPreferredSize());
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
                startFrame,
                "https://esstudio.site/inzendopgaven_418U15/doc/com/company/package-summary.html"
        ));

        JMenuItem websiteMenuItem = new JMenuItem("Website");
        websiteMenuItem.getAccessibleContext().setAccessibleDescription(
                "Bekijk de website");
        helpMenu.add(websiteMenuItem);

        websiteMenuItem.addActionListener(new LinkClickActionListener(
                startFrame,
                "https://esstudio.site/inzendopgaven_418U15"
        ));
    }
}
