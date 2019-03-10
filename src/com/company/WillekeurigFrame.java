package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class WillekeurigFrame extends OefeningFrame {

    public WillekeurigFrame() {
        setIconImage(new AppIcon("/willekeurig-icon.png").getImage());

        operator = 'w';
        operationName = "Willekeurig";
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        int rnd = ThreadLocalRandom.current().nextInt(0, 3);
        switch (rnd) {
            case 0:
                initAftrekkenQuestion();
                operator = '-';
                break;
            case 1:
                initOptellenQuestion();
                operator = '+';
                break;
            case 2:
                initDelenQuestion();
                operator = '\u00F7';
                break;
            case 3:
                initVermenigvuldigenQuestion();
                operator = 'x';
                break;
        }
        renderQuestion();
    }
}
