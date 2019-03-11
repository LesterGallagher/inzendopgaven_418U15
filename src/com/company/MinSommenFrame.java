package com.company;

/**
 * Een OefeningFrame voor minsommen.
 * @author Sem Postma
 */
public class AftrekkenFrame extends OefeningFrame {

    public AftrekkenFrame() {
        setIconImage(new AppIcoontje("/aftrekken-icon.png").getImage());

        operator = '-';
        operationName = "Aftrekken";
    }

    /**
     * Initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        initAftrekkenQuestion();
        renderQuestion();
    }
}
