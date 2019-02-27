package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class DelenFrame extends OefeningFrame {

    public DelenFrame() {
        setIconImage(new AppIcon("/delen-icon.png").getImage());

        operator = 'x';
        operationName = "Delen";
        newQuestion();
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        int a = ThreadLocalRandom.current().nextInt(1, 6);
        int b = ThreadLocalRandom.current().nextInt(1, 3) + 1;
        int r = a * b;

        this.firstArgument = r;
        this.secondArgument = b;
        this.answer = a;

        renderQuestion();
    }
}
