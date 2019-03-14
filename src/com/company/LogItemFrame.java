package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * De frame die extra informatie over een sessie weergeeft zoals de antwoorden op de individuele sommen van elke opdarcht.
 * @author Sem Postma
 */
public class LogItemFrame extends JFrame {
    public LogItemFrame(RekenTrainSessie sessie) {
        setTitle("Log Item");

        Locale locale = new Locale("nl", "NL");
        DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        getContentPane().add(scrollPane);

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        contentPane.setBorder(padding);

        JLabel title = new JLabel("Opdracht:");
        title.setFont(title.getFont().deriveFont(30f));
        contentPane.add(title);
        contentPane.add(Box.createVerticalStrut(15));
        JLabel tijdstipLabel = new JLabel("Tijdstip: " + dateTimeFormat.format(new Date(sessie.tijdstempel)));
        contentPane.add(tijdstipLabel);
        JLabel operationLabel = new JLabel("Opdracht: " + sessie.operationName);
        contentPane.add(operationLabel);
        JLabel fileNameLabel = new JLabel("Bestandsnaam: " + sessie.getFileName());
        contentPane.add(fileNameLabel);
        JLabel todoLabel = new JLabel("Niet gemaakt: " + sessie.teMaken);
        contentPane.add(todoLabel);
        JLabel doneLabel = new JLabel("Goed: " + sessie.afgerond);
        contentPane.add(doneLabel);
        JLabel mistakesLabel = new JLabel("Fout: " + sessie.fouten);
        contentPane.add(mistakesLabel);
        JLabel totaalLabel = new JLabel("Totaal: " + (sessie.teMaken + sessie.fouten + sessie.afgerond));
        contentPane.add(totaalLabel);
        JLabel percentageLabel = new JLabel("Percentage: " + sessie.percentage + "%");
        contentPane.add(percentageLabel);
        JLabel tijdLabel = new JLabel("Tijd: " + sessie.secondes / 60 + " min " + sessie.secondes % 60 + " sec");
        contentPane.add(tijdLabel);

        contentPane.add(Box.createVerticalStrut(15));

        for (RekenTrainSessieItem item : sessie.items) {
            contentPane.add(Box.createVerticalStrut(15));
            JLabel vraag = new JLabel("Wat is "
                    + item.firstArgument + " "
                    + item.operator + " "
                    + item.secondArgument + "?");
            vraag.setFont(vraag.getFont().deriveFont(18f));
            contentPane.add(vraag);
            boolean isCorrect = item.userAnswer == item.answer;
            String text = isCorrect
                    ? "Goed! het antwoord was: " + item.answer
                    : "Fout... jouw antwoord was: " + item.userAnswer + " maar het goede antwoord was: " + item.answer;
            JLabel antwoordLabel = new JLabel(text);
            contentPane.add(antwoordLabel);
        }

        setSize(800, 500);
        validate();
        repaint();
        setLocationRelativeTo(null);
    }
}
