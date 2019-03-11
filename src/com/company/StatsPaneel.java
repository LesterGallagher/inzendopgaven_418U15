package com.company;

import javax.swing.*;
import java.util.Date;

/**
 * Het paneel met een aantal statistieken over de sessie.
 * @author Sem Postma
 */
public class StatsPaneel extends JPanel {
    /**
     * Te maken en afgeronde opdrachten.
     */
    private JLabel teMaken = new JLabel();
    /**
     * Afgeronde opdrachten.
     */
    private JLabel afgerond = new JLabel();
    /**
     * Foute opdrachten.
     */
    private JLabel fouten = new JLabel();

    /**
     * Te maken hoeveelheid.
     */
    private int teMakenHoeveelheid = -1;
    /**
     * Hoeveelheid afgeronde opdrachten.
     */
    private int afgerondHoeveelheid = -1;
    /**
     * Hoeveelheid foute opdrachten.
     */
    private int foutenHoeveelheid = -1;

    /**
     * De start datum.
     */
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

    /**
     * Een builder methode om het aantal te maken opdrachten met één te verminderen.
     */
    public StatsPaneel decrementTodo() {
        teMakenHoeveelheid--;
        setTeMaken(teMakenHoeveelheid);
        return this;
    }

    /**
     * Een builder methode om het aantal afgeronde opdrachten met één toe te nemen.
     */
    public StatsPaneel incrementDone() {
        afgerondHoeveelheid++;
        setAfgerond(afgerondHoeveelheid);
        return this;
    }

    /**
     * Een builder methode om het aantal foute opdrachten met één toe te nemen.
     */
    public StatsPaneel incrementMistakes() {
        foutenHoeveelheid++;
        setFouten(foutenHoeveelheid);
        return this;
    }

    /**
     * Een builder methode om het aantal te maken opdrachten te wijzigen.
     */
    public StatsPaneel setTeMaken(int value) {
        teMaken.setText("Nog " + value + " opdrachten te maken");
        teMakenHoeveelheid = value;
        return this;
    }

    /**
     * Een builder methode om de start datum te wijzigen.
     */
    public StatsPaneel setDate(Date date) {
        this.startDatum = date;
        return this;
    }

    /**
     * Een methode om de hoeveelheid te maken opdrachten te verkrijgen.
     */
    public int getTeMakenHoeveelheid() {
        return teMakenHoeveelheid;
    }

    /**
     * Een methode om de hoeveelheid foute opdrachten te verkrijgen.
     */
    public int getFouten() {
        return foutenHoeveelheid;
    }

    /**
     * Een methode om de hoeveelheid foute opdrachten te verkrijgen.
     */
    public StatsPaneel setFouten(int value) {
        fouten.setText("Aantal opdrachten tot nu toe fout: " + value);
        foutenHoeveelheid = value;
        return this;
    }

    /**
     * Een methode om de tijd in secondes te verkrijgen.
     */
    public long getTimeInSeconds() {
        return (new Date().getTime() - startDatum.getTime()) / 1000;
    }

    /**
     * Een methode om de hoeveelheid afgeronde opdrachten te verkrijgen.
     */
    public int getAfgerond() {
        return afgerondHoeveelheid;
    }

    /**
     * Een methode om de hoeveelheid afgeronde opdrachten te wijzigen.
     */
    public StatsPaneel setAfgerond(int value) {
        afgerond.setText("Aantal opdrachten tot nu toe goed: " + value);
        afgerondHoeveelheid = value;
        return this;
    }

    /**
     * Een methode om het percentage goede opdrachten te verkrijgen.
     */
    public int getPercentage() {
        return Math.round((float) afgerondHoeveelheid / (afgerondHoeveelheid + foutenHoeveelheid) * 100);
    }
}
