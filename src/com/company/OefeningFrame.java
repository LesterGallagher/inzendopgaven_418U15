package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Het oefening frame.
 * Deze abstracte klasse is de basis voor alle oefening frames.
 * In deze klasse zijn een aantal methodes te vinden die de logica voor alle oefening frames verzorgen.
 * @author Sem Postma
 */
public abstract class OefeningFrame extends JFrame {

    /**
     * De eerste term van de opdracht.
     * Bijvoorbeeld 1 in de som: "1 + 2 = 3".
     */
    protected int firstArgument;
    /**
     * De tweede term van de opdracht.
     * Bijvoorbeeld 2 in de som: "1 + 2 = 3".
     */
    protected int secondArgument;
    /**
     * Het antwoord van de opdracht.
     * Bijvoorbeeld 3 in de som: "1 + 2 = 3".
     */
    protected int answer;
    /**
     * De basisoperatie van de opdracht.
     * Bijvoorbeeld "+" in de som: "1 + 2 = 3".
     * Deze waarde is altijd meer dan nul.
     */
    protected char operator;
    /**
     * Het antwoord van de leerling.
     */
    protected int userAnswer;
    /**
     * De naam van de opdracht.
     * Bijvoorbeeld: "Optellen".
     */
    protected String operationName;

    /**
     * Het TextField waar de leerling
     */
    protected JTextField answerTextField = new JTextField("", 20);
    /**
     * De label met de opdracht.
     */
    protected JLabel questionLabel = new JLabel("Wat is X Y X");
    /**
     * De knop om het antwoord te checken.
     */
    protected JButton answerButton = new JButton("Check");
    /**
     * De "Volgende" knop.
     */
    protected JButton nextButton = new JButton("Volgende");
    /**
     * De label met het resultaat.
     */
    protected JLabel resultaatLabel = new JLabel();
    /**
     * Het paneel met de inhoud.
     */
    protected JPanel inhoudPaneel = new JPanel(new BorderLayout());
    /**
     * Het paneel met de vraag.
     */
    protected JPanel vraagPaneel = new JPanel();
    /**
     * Het paneel met het resultaat.
     */
    protected JPanel resultaatPaneel = new JPanel();
    /**
     * Het paneel met de statistieken.
     */
    protected StatsPaneel statsPaneel = new StatsPaneel();
    /**
     * De label met de naam van de opdracht.
     */
    protected JLabel headerLabel = new JLabel();
    /**
     * Het paneel met de uiteindelijke statistieken.
     */
    protected FinalStats finalStats = new FinalStats(this);
    /**
     * Een referentie naar het sessie object.
     * Dit object houdt de score en de tijd bij en slaat deze op zodat de leerling deze later terug kan bekijken.
     */
    protected RekenTrainSessie sessie = new RekenTrainSessie();

    public OefeningFrame() {
        setSize(600, 460);
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        inhoudPaneel.setBorder(padding);
        setLocationRelativeTo(null);
        resultaatPaneel.setLayout(new BoxLayout(resultaatPaneel, BoxLayout.PAGE_AXIS));
        vraagPaneel.setLayout(new BoxLayout(vraagPaneel, BoxLayout.X_AXIS));
        answerTextField.setMaximumSize(answerTextField.getPreferredSize());
        answerTextField.addActionListener(e -> processAnswerToQuestion());
        vraagPaneel.add(questionLabel);
        vraagPaneel.add(Box.createHorizontalStrut(10));
        vraagPaneel.add(answerTextField);
        vraagPaneel.add(Box.createHorizontalStrut(10));
        vraagPaneel.add(answerButton);
        answerButton.addActionListener(e -> processAnswerToQuestion());

        resultaatPaneel.add(resultaatLabel);
        resultaatPaneel.add(nextButton);
        nextButton.addActionListener(e -> newQuestion());

        headerLabel.setFont(headerLabel.getFont().deriveFont(28.0f));
        inhoudPaneel.add(headerLabel, BorderLayout.NORTH);
        inhoudPaneel.add(statsPaneel, BorderLayout.SOUTH);

        this.getRootPane().setDefaultButton(answerButton);
        answerButton.requestFocus();

        finalStats.addOnRepeatListener(e -> restart());

        add(inhoudPaneel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                persist();
            }
        });
    }

    private void processAnswerToQuestion() {
        try {
            this.userAnswer = Integer.parseInt(answerTextField.getText());
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(null, '"' + answerTextField.getText()
                    + "\" is geen geldig heel getal.");
            return;
        }
        statsPaneel.decrementTodo();
        renderAnswer();
        sessie.push(this);
        if (statsPaneel.getTeMakenHoeveelheid() <= 0) {
            renderFinalStats();
        }
    }

    private void restart() {
        persist();

        init();
    }

    private void persist() {
        if (statsPaneel.getAfgerond() + statsPaneel.getFouten() == 0) return;
        sessie.stop(
                statsPaneel.getAfgerond(),
                statsPaneel.getFouten(),
                statsPaneel.getTeMakenHoeveelheid(),
                statsPaneel.getPercentage(),
                statsPaneel.getTimeInSeconds());
    }

    protected void renderFinalStats() {
        inhoudPaneel.remove(vraagPaneel);
        inhoudPaneel.remove(resultaatPaneel);
        inhoudPaneel.remove(statsPaneel);
        finalStats.init(statsPaneel);
        inhoudPaneel.add(finalStats);
        setTitle("Rekentrainer - Resultaat " + LoginScherm.getUserName());
        revalidate();
        repaint();
    }

    protected void renderQuestion() {
        headerLabel.setText("Reken opdracht: " + operationName);
        String question = String.format("Wat is %d %c %d?", firstArgument, operator, secondArgument);
        questionLabel.setText(question);
        answerTextField.setText("");
        inhoudPaneel.remove(resultaatPaneel);
        inhoudPaneel.add(vraagPaneel, BorderLayout.CENTER);
        answerTextField.requestFocus();
        revalidate();
        repaint();
    }

    protected void renderAnswer() {
        boolean correct = userAnswer == answer;
        if (correct) {
            resultaatLabel.setText("Goed gedaan!");
            statsPaneel.incrementDone();
        } else {
            resultaatLabel.setText("Helaas het antwoord was \"" + answer + "\".");
            statsPaneel.incrementMistakes();
        }
        inhoudPaneel.remove(vraagPaneel);
        inhoudPaneel.add(resultaatPaneel, BorderLayout.CENTER);
        this.getRootPane().setDefaultButton(nextButton);
        nextButton.requestFocus();
        revalidate();
        repaint();
    }

    abstract void newQuestion();

    public void init() {
        statsPaneel
                .setAfgerond(0)
                .setFouten(0)
                .setDate(new Date())
                .setTeMaken(StartFrame.getRotaties());
        inhoudPaneel.remove(finalStats);
        inhoudPaneel.add(statsPaneel, BorderLayout.SOUTH);
        setTitle(operationName + " - Groep " + GroepPaneel.getGroup());
        sessie.operationName = operationName;
        newQuestion();
    }

    protected void initOptellenQuestion() {
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPaneel.getGroup() - 3) * 13);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 3 + (GroepPaneel.getGroup() - 3) * 11);
        this.answer = this.firstArgument + this.secondArgument;
    }

    protected void initVermenigvuldigenQuestion() {
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 4 + (GroepPaneel.getGroup() - 3) * 4);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 3 + (GroepPaneel.getGroup() - 3) * 3);
        this.answer = this.firstArgument * this.secondArgument;
    }

    protected void initDelenQuestion() {
        int a = ThreadLocalRandom.current().nextInt(2, 1 + GroepPaneel.getGroup());
        int b = ThreadLocalRandom.current().nextInt(2, 1 + GroepPaneel.getGroup());
        int r = a * b;

        this.firstArgument = r;
        this.secondArgument = b;
        this.answer = a;
    }

    protected void initAftrekkenQuestion() {
        this.getRootPane().setDefaultButton(this.nextButton);
        this.nextButton.requestFocus();
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPaneel.getGroup() - 3) * 20);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPaneel.getGroup() - 3) * 20);

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
