package com.company;

/**
 * Een OefeningFrame voor deelsommen.
 * @author Sem Postma
 */
public class DelenFrame extends OefeningFrame {

    public DelenFrame() {
        setIconImage(new AppIcoontje("/delen-icon.png").getImage());

        operator = '\u00F7';
        operationName = "Delen";
    }

    /**
     * Initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        initDelenQuestion();
        renderQuestion();
    }
}
