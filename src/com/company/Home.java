package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Home extends JFrame {

    protected JPanel contentPane = new JPanel(new BorderLayout());
    protected JPanel buttonPane = new JPanel();
    protected OptellenFrame optellenFrame = new OptellenFrame();
    protected AftrekkenFrame aftrekkenFrame = new AftrekkenFrame();
    protected VermenigvuldigenFrame vermenigvuldigenFrame = new VermenigvuldigenFrame();
    protected DelenFrame delenFrame = new DelenFrame();
    protected WillekeurigFrame willekeurigFrame = new WillekeurigFrame();
    protected JLabel amountOfSums = new JLabel("Hoeveel sommen wil je maken?");
    protected JTextField amountOfSumsField = new JTextField("20");
    private static int rotations = 20;

    public Home() {
        setTitle("Rekentrainer Keuze scherm");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new AppIcon("/icon.png").getImage());
        setJMenuBar(new HomeMenu(this));

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        contentPane.setBorder(padding);

        JPanel boxPane = new JPanel();
        boxPane.setLayout(new BoxLayout(boxPane, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Rekentrainer jenaplanbasisschool De Fuglen.");
        label.setFont(label.getFont().deriveFont(28.0f));
        JLabel textExplanation = new JLabel("Klik op een van de knoppen hier onder om de opdracht te beginnen.");
        textExplanation.setFont(textExplanation.getFont().deriveFont(16.0f));
        boxPane.add(label);
        boxPane.add(textExplanation);
        boxPane.add(Box.createVerticalStrut(20));
        boxPane.add(amountOfSums);
        amountOfSums.setFont(amountOfSums.getFont().deriveFont(20.0f));
        boxPane.add(amountOfSumsField);
        amountOfSumsField.setFont(amountOfSumsField.getFont().deriveFont(14.0f));
        amountOfSumsField.getDocument().addDocumentListener(new JTextFieldDocumentChangeListener(e -> {
            try {
                rotations = Integer.parseInt(amountOfSumsField.getText());
            } catch(NumberFormatException err) {}
        }));
        boxPane.add(Box.createVerticalStrut(20));
        contentPane.add(boxPane, BorderLayout.NORTH);

        buttonPane.setLayout(new GridBagLayout());

        // add menu buttons
        addButton("/optellen.png", new HomeButtonClickActionListener(optellenFrame));
        addButton("/aftrekken.png", new HomeButtonClickActionListener(aftrekkenFrame));
        addButton("/vermenigvuldigen.png", new HomeButtonClickActionListener(vermenigvuldigenFrame));
        addButton("/delen.png", new HomeButtonClickActionListener(delenFrame));
        addButton("/willekeurig.png", new HomeButtonClickActionListener(willekeurigFrame));

        contentPane.add(buttonPane, BorderLayout.SOUTH);

        GroepPanel groepPanel = new GroepPanel();
        groepPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPane.add(groepPanel, BorderLayout.CENTER);

        add(contentPane);
    }

    public static int getRotations() {
        return rotations;
    }

    protected void addButton(String imageName, ActionListener listener) {
        JButton btn = new JButton();
        btn.setIcon(new AppIcon(imageName).getIcon());
        btn.setContentAreaFilled(false);
        btn.setFocusable(false);
        btn.addActionListener(listener);
        btn.setBorder(BorderFactory.createLineBorder(Color.red, 2)); // Line Border + Thickness of the Border
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        buttonPane.add(btn, gbc);
    }
}
