package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class VermenigvuldigenFrame extends OefeningFrame {
    public VermenigvuldigenFrame() {
        setIconImage(new AppIcon("/vermenigvuldigen-icon.png").getImage());

        operator = '*';
        operationName = "Vermenigvuldigen";
        newQuestion();
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 11);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 5);
        this.answer = firstArgument * secondArgument;

        renderQuestion();
    }
}
