package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Het startscherm van de applicatie.
 * @author Sem Postma
 */
public class StartFrame extends JFrame {

    /**
     * Het aantal rotaties voordat een sessie is afgerond.
     */
    private static int rotaties = 20;
    /**
     * Het paneel met de inhoud.
     */
    protected JPanel inhoudPaneel = new JPanel(new BorderLayout());
    /**
     * Het paneel met de knoppen.
     */
    protected JPanel knoppenPaneel = new JPanel();
    /**
     * De frame met de optelsommen.
     */
    protected OptellenFrame optellenFrame = new OptellenFrame();
    /**
     * De frame met de minsommen.
     */
    protected MinSommenFrame minSommenFrame = new MinSommenFrame();
    /**
     * De frame met de vermenigvuldiging opdrachten.
     */
    protected VermenigvuldigenFrame vermenigvuldigenFrame = new VermenigvuldigenFrame();
    /**
     * De frame met de deelsommen.
     */
    protected DelenFrame delenFrame = new DelenFrame();
    /**
     * De frame met de willekeurige opdrachten.
     */
    protected WillekeurigFrame willekeurigFrame = new WillekeurigFrame();
    /**
     * De label met het aaantal opdrachten.
     */
    protected JLabel aantalOpdrachten = new JLabel("Hoeveel sommen wil je maken?");
    /**
     * Het tekstveld met het aantal opdrachten.
     */
    protected JTextField aantalOpdrachtenVeld = new JTextField("20");

    public StartFrame() {
        setTitle("Rekentrainer Keuze scherm");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new AppIcoontje("/icon.png").getImage());
        setJMenuBar(new StartFrameMenu(this));

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        inhoudPaneel.setBorder(padding);

        JPanel boxPane = new JPanel();
        boxPane.setLayout(new BoxLayout(boxPane, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Rekentrainer jenaplanbasisschool De Fuglen.");
        label.setFont(label.getFont().deriveFont(28.0f));
        JLabel textExplanation = new JLabel("Klik op een van de knoppen hier onder om de opdracht te beginnen.");
        textExplanation.setFont(textExplanation.getFont().deriveFont(16.0f));
        boxPane.add(label);
        boxPane.add(textExplanation);
        boxPane.add(Box.createVerticalStrut(20));
        boxPane.add(aantalOpdrachten);
        aantalOpdrachten.setFont(aantalOpdrachten.getFont().deriveFont(20.0f));
        boxPane.add(aantalOpdrachtenVeld);
        aantalOpdrachtenVeld.setFont(aantalOpdrachtenVeld.getFont().deriveFont(14.0f));
        aantalOpdrachtenVeld.getDocument().addDocumentListener(new JTextFieldDocumentChangeListener(e -> {
            try {
                rotaties = Integer.parseInt(aantalOpdrachtenVeld.getText());
            } catch (NumberFormatException err) {
            }
        }));
        boxPane.add(Box.createVerticalStrut(20));
        inhoudPaneel.add(boxPane, BorderLayout.NORTH);

        knoppenPaneel.setLayout(new GridBagLayout());

        // add menu buttons
        addButton("/optellen.png", new StartFrameKnopKlikActionListener(optellenFrame));
        addButton("/aftrekken.png", new StartFrameKnopKlikActionListener(minSommenFrame));
        addButton("/vermenigvuldigen.png", new StartFrameKnopKlikActionListener(vermenigvuldigenFrame));
        addButton("/delen.png", new StartFrameKnopKlikActionListener(delenFrame));
        addButton("/willekeurig.png", new StartFrameKnopKlikActionListener(willekeurigFrame));

        inhoudPaneel.add(knoppenPaneel, BorderLayout.SOUTH);

        GroepPaneel groepPanel = new GroepPaneel();
        groepPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        inhoudPaneel.add(groepPanel, BorderLayout.CENTER);

        add(inhoudPaneel);
    }

    /**
     * Verkrijg het aantal rotaties.
     * @return Het aantal rotaties.
     */
    public static int getRotaties() {
        return rotaties;
    }

    /**
     * Voeg een knop toe aan het start scherm.
     * @param imageName De naam van de afbeelding in de resource folder.
     * @param listener De listener voor wanneer er op de knop geklikt wordt.
     */
    protected void addButton(String imageName, ActionListener listener) {
        JButton btn = new JButton();
        btn.setIcon(new AppIcoontje(imageName).getIcon());
        btn.setContentAreaFilled(false);
        btn.setFocusable(false);
        btn.addActionListener(listener);
        btn.setBorder(BorderFactory.createLineBorder(Color.red, 2)); // Line Border + Thickness of the Border
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        knoppenPaneel.add(btn, gbc);
    }
}
