package com.company;

import javax.swing.*;
import java.util.Date;

public class StatsPanel extends JPanel {
    private JLabel todo = new JLabel();
    private JLabel done = new JLabel();
    private JLabel mistakes = new JLabel();

    private int todoAmount = -1;
    private int doneAmount = -1;
    private int mistakesAmount = -1;

    private Date startDate;

    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(done);
        add(mistakes);
        add(todo);

        this
                .setMistakes(0)
                .setTodo(0)
                .setDone(Home.getRotations());

    }



    public StatsPanel decrementTodo() {
        todoAmount--;
        setTodo(todoAmount);
        return this;
    }

    public StatsPanel incrementDone() {
        doneAmount++;
        setDone(doneAmount);
        return this;
    }

    public StatsPanel incrementMistakes() {
        mistakesAmount++;
        setMistakes(mistakesAmount);
        return this;
    }

    public StatsPanel setTodo(int value) {
        todo.setText("Nog " + value + " opdrachten te maken");
        todoAmount = value;
        return this;
    }

    public StatsPanel setDone(int value) {
        done.setText("Aantal opdrachten tot nu toe goed: " + value);
        doneAmount = value;
        return this;
    }

    public StatsPanel setMistakes(int value) {
        mistakes.setText("Aantal opdrachten tot nu toe fout: " + value);
        mistakesAmount = value;
        return this;
    }

    public StatsPanel setDate(Date date) {
        this.startDate = date;
        return this;
    }

    public int getTodoAmount() {
        return todoAmount;
    }

    public int getMistakes() {
        return mistakesAmount;
    }

    public long getTimeInSeconds () {
        return (new Date().getTime() - startDate.getTime()) / 1000;
    }

    public int getDone() {
        return doneAmount;
    }

    public int getPercentage() {
        return Math.round((float)doneAmount / (doneAmount + mistakesAmount) * 100);
    }
}
