package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class OptellenFrame extends OefeningFrame {
    public OptellenFrame() {
        setIconImage(new AppIcon("/optellen-icon.png").getImage());

        operator = '+';
        operationName = "Optellen";
        newQuestion();
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 21);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 21);
        this.answer = firstArgument + secondArgument;

        renderQuestion();
    }
}
