package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class OptellenFrame extends OefeningFrame {
    public OptellenFrame() {
        setIconImage(new AppIcon("/optellen-icon.png").getImage());

        operator = '+';
        operationName = "Optellen";
    }
    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPanel.getGroup() -3) * 13);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 3 + (GroepPanel.getGroup() - 3) * 11);
        this.answer = firstArgument + secondArgument;

        renderQuestion();
    }
}
