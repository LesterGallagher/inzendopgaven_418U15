package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class VermenigvuldigenFrame extends OefeningFrame {
    public VermenigvuldigenFrame() {
        setIconImage(new AppIcon("/vermenigvuldigen-icon.png").getImage());

        operator = 'x';
        operationName = "Vermenigvuldigen";
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        initVermenigvuldigenQuestion();
        renderQuestion();
    }
}
