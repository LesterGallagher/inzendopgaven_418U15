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
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 4 + (GroepPanel.getGroup() -3) * 4);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 3 + (GroepPanel.getGroup() -3) * 3);
        this.answer = firstArgument * secondArgument;

        renderQuestion();
    }
}
