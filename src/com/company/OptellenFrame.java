package com.company;

/**
 * Een OefeningFrame voor optel sommen.
 * @author Sem Postma
 */
public class OptellenFrame extends OefeningFrame {
    public OptellenFrame() {
        setIconImage(new AppIcoontje("/optellen-icon.png").getImage());

        operator = '+';
        operationName = "Optellen";
    }

    /**
     * Initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        initOptellenQuestion();
        renderQuestion();
    }
}
