package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class FinalStats extends JPanel {
    private JLabel afgerond = new JLabel();
    private JLabel resultaat = new JLabel("hieronder zie je wat je resultaat is:");
    private JPanel resultsInner = new JPanel();
    private JPanel buttonFooter = new JPanel();

    private JLabel aantalSommenGoed = new JLabel("Aantal opdrachten goed:");
    private JLabel aantalSommenFout = new JLabel("Aantal sommen fout:");
    private JLabel score = new JLabel("Score:");

    private JLabel aantalSommenGoedAmount = new JLabel("0");
    private JLabel aantalSommenFoutAmount = new JLabel("0");
    private JLabel scoreAmount = new JLabel("0");

    private JButton repeatBtn = new JButton("Nog een keer");
    private JButton stopBtn = new JButton("Stoppen");

    private ArrayList<ActionListener> onRepeat = new ArrayList<>();

    public FinalStats(JFrame frame) {
        add(Box.createVerticalStrut(10));
        afgerond.setFont(new Font("Dialog", Font.BOLD, 14));
        resultaat.setFont(new Font("Dialog", Font.BOLD, 14));
        afgerond.setText(
                MessageFormat.format(
                        "Je hebt de opdrachten afgerond {0}",
                        StartScreen.getUserName()
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

    public void init(StatsPanel stats) {
        var done = stats.getDone();
        var mistakes = stats.getMistakes();
        this.aantalSommenGoedAmount.setText("" + done);
        this.aantalSommenFoutAmount.setText("" + mistakes);
        this.scoreAmount.setText(Math.round((float)done / (done + mistakes) * 100) + "%");
    }

    private void fireOnRepeatEvent() {
        onRepeat.forEach(listener -> listener.actionPerformed(null));
    }

    public void addOnRepeatListener(ActionListener actionListener) {
        this.onRepeat.add(actionListener);
    }

    public void removeOnRepeatListener(ActionListener actionListener) {
        this.onRepeat.remove(actionListener);
    }
}
