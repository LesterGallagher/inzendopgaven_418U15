package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Het paneel wat het uitendelijke resultaat laat zien aan de leerling.
 * @author Sem Postma
 */
public class FinalStats extends JPanel {
    /**
     * De label voor het bericht: "Je hebt de opdrachten afgerond {leerling}".
     */
    private JLabel afgerond = new JLabel();
    /**
     * De label voor het resultaat kopje.
     */
    private JLabel resultaat = new JLabel("hieronder zie je wat je resultaat is:");
    /**
     * Het paneel met de resultaten, zoals de aantalSommenGoed label, de aantalSommenFout label, de score. etc.
     */
    private JPanel resultsInner = new JPanel();
    /**
     * Het paneel met knoppen: "Nog een keer" en "Stoppen".
     */
    private JPanel buttonFooter = new JPanel();

    /**
     * De label met de tekst: "Aantal opdrachten goed:".
     */
    private JLabel aantalSommenGoed = new JLabel("Aantal opdrachten goed:");
    /**
     * De label met de tekst: "Aantal sommen fout:".
     */
    private JLabel aantalSommenFout = new JLabel("Aantal sommen fout:");
    /**
     * De label met de tekst: "Score".
     */
    private JLabel score = new JLabel("Score:");
    /**
     * De label met de tekst: "Tijd:".
     */
    private JLabel tijd = new JLabel("Tijd:");

    /**
     * De label met het aantal opdrachten wat de leerling goed heeft gemaakt.
     */
    private JLabel aantalSommenGoedAmount = new JLabel("0");
    /**
     * De label met het aantal opdrachten wat de leerling fout heeft gemaakt.
     */
    private JLabel aantalSommenFoutAmount = new JLabel("0");
    /**
     * De label met het percentage van opdrachten wat de leerling goed heeft gemaakt.
     */
    private JLabel scoreAmount = new JLabel("0");
    /**
     * De label die aangeeft hoelang de leerling over de opdracht hebt gedaan.
     */
    private JLabel tijdHoeveelheid = new JLabel("0");

    /**
     * De knop waarmee de leerling de opdracht opnieuw kan beginnen.
     */
    private JButton repeatBtn = new JButton("Nog een keer");
    /**
     * De knop waarmee de leerling de opdracht kan stoppen.
     */
    private JButton stopBtn = new JButton("Stoppen");

    /**
     * Een lijst met listeners die worden aangeroepen waneer de leerling op de "Nog een keer" knop klikt.
     */
    private ArrayList<ActionListener> onRepeat = new ArrayList<>();

    public FinalStats(OefeningFrame frame) {
        add(Box.createVerticalStrut(10));
        afgerond.setFont(new Font("Dialog", Font.BOLD, 14));
        resultaat.setFont(new Font("Dialog", Font.BOLD, 14));
        afgerond.setText(
                MessageFormat.format(
                        "Je hebt de opdrachten afgerond {0}",
                        LoginScherm.getUserName()
                )
        );
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));
        text.add(afgerond);
        text.add(resultaat);
        add(text);
        add(Box.createVerticalStrut(10));
        resultsInner.setLayout(new GridLayout(0, 2));
        resultsInner.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aantalSommenFout.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        aantalSommenGoed.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        score.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        resultsInner.add(aantalSommenGoed);
        resultsInner.add(aantalSommenGoedAmount);
        resultsInner.add(aantalSommenFout);
        resultsInner.add(aantalSommenFoutAmount);
        resultsInner.add(score);
        resultsInner.add(scoreAmount);
        resultsInner.add(tijd);
        resultsInner.add(tijdHoeveelheid);
        add(resultsInner);
        buttonFooter.setLayout(new BoxLayout(buttonFooter, BoxLayout.LINE_AXIS));
        buttonFooter.add(repeatBtn);
        buttonFooter.add(Box.createHorizontalGlue());
        buttonFooter.add(stopBtn);
        add(Box.createVerticalStrut(10));
        add(buttonFooter);
        add(Box.createVerticalGlue());

        stopBtn.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
        repeatBtn.addActionListener(e -> fireOnRepeatEvent());
        frame.getRootPane().setDefaultButton(repeatBtn);
        repeatBtn.requestFocus();
    }

    /**
     * Deze methode moet worden aangeroepen wanneer dit paneel weergegeven wordt.
     * @param stats Het statistieken paneel waar informatie uitgehaald kan worden.
     */
    public void init(StatsPaneel stats) {
        var done = stats.getAfgerond();
        var mistakes = stats.getFouten();
        this.aantalSommenGoedAmount.setText("" + done);
        this.aantalSommenFoutAmount.setText("" + mistakes);
        this.scoreAmount.setText(stats.getPercentage() + "%");
        long sec = stats.getTimeInSeconds();
        this.tijdHoeveelheid.setText(sec / 60 + " min " + sec % 60 + " sec");
    }

    private void fireOnRepeatEvent() {
        onRepeat.forEach(listener -> listener.actionPerformed(null));
    }

    /**
     * Voeg een listener toe voor wanneer de opdracht herhaald wort.
     * @param actionListener De callback listener voor wanneer de opdracht herhaald wordt.
     */
    public void addOnRepeatListener(ActionListener actionListener) {
        this.onRepeat.add(actionListener);
    }

    /**
     * Verwijder een listener voor wanneer de opdracht herhaald wort.
     * @param actionListener Verwijder de callback listener voor wanneer de opdracht herhaald wordt.
     */
    public void removeOnRepeatListener(ActionListener actionListener) {
        this.onRepeat.remove(actionListener);
    }
}
