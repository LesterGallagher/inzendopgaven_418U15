package com.company;

import javax.swing.*;
import java.text.MessageFormat;

/**
 * Het eerste paneel van de applicatie.
 * Hier kan de leerling de groep aankiezen.
 * @author Sem Postma
 */
public class GroepPaneel extends JPanel {

    /**
     * Het statische variabel voor de groep van de leerling.
     */
    private static int group = 3;
    /**
     * De label "Kies je groep:" label.
     */
    private JLabel groepLabel = new JLabel("Kies je groep:");

    public GroepPaneel() {
        groepLabel.setFont(groepLabel.getFont().deriveFont(20.0f));
        this.add(groepLabel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 3; i <= 8; i++) {
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

    /**
     * Krijgt de groep van de leerling. De standaard waarde is 3.
     * @return De groep van de leerling
     */
    public static int getGroup() {
        return group;
    }
}
