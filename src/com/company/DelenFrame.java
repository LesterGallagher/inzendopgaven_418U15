package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class DelenFrame extends OefeningFrame {

    public DelenFrame() {
        setIconImage(new AppIcon("/delen-icon.png").getImage());

        operator = '\u00F7';
        operationName = "Delen";
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        initDelenQuestion();
        renderQuestion();
    }
}
