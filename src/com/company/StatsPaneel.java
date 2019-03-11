package com.company;

import javax.swing.*;
import java.util.Date;

/**
 * Het paneel met een aantal statistieken over de sessie.
 * @author Sem Postma
 */
public class StatsPaneel extends JPanel {
    private JLabel teMaken = new JLabel();
    private JLabel afgerond = new JLabel();
    private JLabel fouten = new JLabel();

    private int teMakenHoeveelheid = -1;
    private int afgerondHoeveelheid = -1;
    private int foutenHoeveelheid = -1;

    private Date startDatum;

    public StatsPaneel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(afgerond);
        add(fouten);
        add(teMaken);

        this
                .setFouten(0)
                .setTeMaken(0)
                .setAfgerond(StartFrame.getRotaties());

    }


    public StatsPaneel decrementTodo() {
        teMakenHoeveelheid--;
        setTeMaken(teMakenHoeveelheid);
        return this;
    }

    public StatsPaneel incrementDone() {
        afgerondHoeveelheid++;
        setAfgerond(afgerondHoeveelheid);
        return this;
    }

    public StatsPaneel incrementMistakes() {
        foutenHoeveelheid++;
        setFouten(foutenHoeveelheid);
        return this;
    }

    public StatsPaneel setTeMaken(int value) {
        teMaken.setText("Nog " + value + " opdrachten te maken");
        teMakenHoeveelheid = value;
        return this;
    }

    public StatsPaneel setDate(Date date) {
        this.startDatum = date;
        return this;
    }

    public int getTeMakenHoeveelheid() {
        return teMakenHoeveelheid;
    }

    public int getFouten() {
        return foutenHoeveelheid;
    }

    public StatsPaneel setFouten(int value) {
        fouten.setText("Aantal opdrachten tot nu toe fout: " + value);
        foutenHoeveelheid = value;
        return this;
    }

    public long getTimeInSeconds() {
        return (new Date().getTime() - startDatum.getTime()) / 1000;
    }

    public int getAfgerond() {
        return afgerondHoeveelheid;
    }

    public StatsPaneel setAfgerond(int value) {
        afgerond.setText("Aantal opdrachten tot nu toe goed: " + value);
        afgerondHoeveelheid = value;
        return this;
    }

    public int getPercentage() {
        return Math.round((float) afgerondHoeveelheid / (afgerondHoeveelheid + foutenHoeveelheid) * 100);
    }
}
