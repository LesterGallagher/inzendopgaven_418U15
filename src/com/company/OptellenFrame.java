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
        initOptellenQuestion();
        renderQuestion();
    }
}
