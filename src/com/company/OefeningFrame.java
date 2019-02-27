package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class OefeningFrame extends JFrame {

    protected int firstArgument;
    protected int secondArgument;
    protected int answer;
    protected char operator;
    protected String operationName;

    protected JTextField answerTextField = new JTextField("", 20);
    protected JLabel questionLabel = new JLabel("Wat is X Y X");
    protected JButton answerButton = new JButton("Check");
    protected JButton nextButton = new JButton("Volgende");
    protected JLabel resultLabel = new JLabel();
    protected JPanel contentPane = new JPanel(new BorderLayout());
    protected JPanel questionPane = new JPanel();
    protected JPanel resultPane = new JPanel();
    protected StatsPanel statsPanel = new StatsPanel();
    protected JLabel headerLabel = new JLabel();
    protected FinalStats finalStats = new FinalStats(this);

    public OefeningFrame() {
        setSize(600, 460);
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        contentPane.setBorder(padding);
        setLocationRelativeTo(null);
        resultPane.setLayout(new BoxLayout(resultPane, BoxLayout.PAGE_AXIS));
        questionPane.setLayout(new BoxLayout(questionPane, BoxLayout.X_AXIS));
        answerTextField.setMaximumSize( answerTextField.getPreferredSize() );
        answerTextField.addActionListener(e -> processAnswerToQuestion());
        questionPane.add(questionLabel);
        questionPane.add(Box.createHorizontalStrut(10));
        questionPane.add(answerTextField);
        questionPane.add(Box.createHorizontalStrut(10));
        questionPane.add(answerButton);
        answerButton.addActionListener(e -> processAnswerToQuestion());

        resultPane.add(resultLabel);
        resultPane.add(nextButton);
        nextButton.addActionListener(e -> newQuestion());

        headerLabel = new JLabel();
        headerLabel.setFont(headerLabel.getFont().deriveFont(28.0f));
        contentPane.add(headerLabel, BorderLayout.NORTH);
        contentPane.add(statsPanel, BorderLayout.SOUTH);

        this.getRootPane().setDefaultButton(answerButton);
        answerButton.requestFocus();

        finalStats.addOnRepeatListener(e -> init());

        add(contentPane);
    }

    private void processAnswerToQuestion() {
        try {
            int userAnswer = Integer.parseInt(answerTextField.getText());
            statsPanel.decrementTodo();
            if (statsPanel.getTodoAmount() <= 0) {
                renderFinalStats();
            } else {
                renderAnswer(userAnswer);
            }
        } catch(NumberFormatException err) {}
    }

    protected void renderFinalStats() {
        contentPane.remove(questionPane);
        contentPane.remove(resultPane);
        contentPane.remove(statsPanel);
        finalStats.init(statsPanel);
        contentPane.add(finalStats);
        setTitle("Rekentrainer - Resultaat " + StartScreen.getUserName());
        revalidate();
        repaint();
    }

    protected void renderQuestion() {
        headerLabel.setText("Reken opdracht: " + operationName);
        String question = String.format("Wat is %d %c %d?", firstArgument, operator, secondArgument);
        questionLabel.setText(question);
        answerTextField.setText("");
        contentPane.remove(resultPane);
        contentPane.add(questionPane, BorderLayout.CENTER);
        answerTextField.requestFocus();
        revalidate();
        repaint();
    }

    protected void renderAnswer(int userAnswer) {
        boolean correct = userAnswer == answer;
        if (correct) {
            resultLabel.setText("Goed gedaan!");
            statsPanel.incrementDone();
        } else {
            resultLabel.setText("Helaas het antwoord was \"" + answer + "\".");
            statsPanel.incrementMistakes();
        }
        contentPane.remove(questionPane);
        contentPane.add(resultPane, BorderLayout.CENTER);
        this.getRootPane().setDefaultButton(nextButton);
        nextButton.requestFocus();
        revalidate();
        repaint();
    }

    abstract void newQuestion();

    public void init() {
        statsPanel
                .setDone(0)
                .setMistakes(0)
                .setTodo(Home.getRotations());
        contentPane.remove(finalStats);
        contentPane.add(statsPanel, BorderLayout.SOUTH);
        setTitle(operationName + " - Groep " + GroepPanel.group);

        newQuestion();
    }
}
