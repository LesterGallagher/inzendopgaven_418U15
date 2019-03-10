package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class AftrekkenFrame extends OefeningFrame {

    public AftrekkenFrame() {
        setIconImage(new AppIcon("/aftrekken-icon.png").getImage());

        operator = '-';
        operationName = "Aftrekken";
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        initAftrekkenQuestion();
        renderQuestion();
    }
}
