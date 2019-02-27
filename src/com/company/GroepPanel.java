package com.company;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public class GroepPanel extends JPanel {

    static int group = 3;
    JLabel groepLabel = new JLabel("Kies je groep:");

    public GroepPanel() {
        groepLabel.setFont(groepLabel.getFont().deriveFont(20.0f));
        this.add(groepLabel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i = 3; i <= 8; i++) {
            int g = i;
            var radio = new JRadioButton(
                    MessageFormat.format("Groep {0}", g)
            );
            radio.addActionListener(e -> group = g);
            buttonGroup.add(radio);
            add(radio);
        }
        buttonGroup.setSelected(buttonGroup.getElements().nextElement().getModel(), true);
        revalidate();
        repaint();
    }

    public static int getGroup() {
        return group;
    }
}
