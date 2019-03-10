package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public abstract class OefeningFrame extends JFrame {

    protected int firstArgument;
    protected int secondArgument;
    protected int answer;
    protected char operator;
    protected int userAnswer;
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
    protected RekenTrainSessie sessie = new RekenTrainSessie();

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

        finalStats.addOnRepeatListener(e -> restart());

        add(contentPane);

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                persist();
            }
        });
    }

    private void processAnswerToQuestion() {
        try {
            this.userAnswer = Integer.parseInt(answerTextField.getText());
        }   catch(NumberFormatException err) {
            JOptionPane.showMessageDialog(null, '"' + answerTextField.getText()
                    + "\" is geen geldig heel getal.");
            return;
        }
        statsPanel.decrementTodo();
        renderAnswer();
        sessie.push(this);
        if (statsPanel.getTodoAmount() <= 0) {
            renderFinalStats();
        }
    }

    private void restart() {
        persist();

        init();
    }

    private void persist() {
        if (statsPanel.getDone() + statsPanel.getMistakes() == 0) return;
        sessie.stop(
                statsPanel.getDone(),
                statsPanel.getMistakes(),
                statsPanel.getTodoAmount(),
                statsPanel.getPercentage(),
                statsPanel.getTimeInSeconds());
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

    protected void renderAnswer() {
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
                .setDate(new Date())
                .setTodo(Home.getRotations());
        contentPane.remove(finalStats);
        contentPane.add(statsPanel, BorderLayout.SOUTH);
        setTitle(operationName + " - Groep " + GroepPanel.group);
        sessie.operationName = operationName;
        newQuestion();
    }

    protected void initOptellenQuestion() {
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPanel.getGroup() -3) * 13);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 3 + (GroepPanel.getGroup() - 3) * 11);
        this.answer = this.firstArgument + this.secondArgument;
    }

    protected void initVermenigvuldigenQuestion() {
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 4 + (GroepPanel.getGroup() -3) * 4);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 3 + (GroepPanel.getGroup() -3) * 3);
        this.answer = this.firstArgument * this.secondArgument;
    }

    protected void initDelenQuestion() {
        int a = ThreadLocalRandom.current().nextInt(2, 1 + GroepPanel.getGroup());
        int b = ThreadLocalRandom.current().nextInt(2, 1 + GroepPanel.getGroup());
        int r = a * b;

        this.firstArgument = r;
        this.secondArgument = b;
        this.answer = a;
    }

    protected void initAftrekkenQuestion() {
        this.getRootPane().setDefaultButton(this.nextButton);
        this.nextButton.requestFocus();
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPanel.getGroup() -3) * 20);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPanel.getGroup() - 3) * 20);

        if (this.firstArgument < this.secondArgument) {
            // draai de waarden om zonder een extra variabel te gebruiken...
            this.firstArgument += this.secondArgument;
            this.secondArgument = this.firstArgument - this.secondArgument;
            this.firstArgument -= this.secondArgument;
        }
        // zorgt ervoor dat het resultaat nooit nul kan zijn.
        this.firstArgument += 1;

        this.answer = this.firstArgument - this.secondArgument;
    }
}
