package com.company;

/**
 * De frame voor Vermenigvuldigings opdrachten.
 * @author Sem Postma
 */
public class VermenigvuldigenFrame extends OefeningFrame {
    public VermenigvuldigenFrame() {
        setIconImage(new AppIcoontje("/vermenigvuldigen-icon.png").getImage());

        operator = 'x';
        operationName = "Vermenigvuldigen";
    }

    /**
     * Initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        initVermenigvuldigenQuestion();
        renderQuestion();
    }
}
