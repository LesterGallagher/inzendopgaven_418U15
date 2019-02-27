package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class AftrekkenFrame extends OefeningFrame {

    public AftrekkenFrame() {
        setIconImage(new AppIcon("/aftrekken-icon.png").getImage());

        operator = '-';
        operationName = "Aftrekken";
    }

    /**
     * initialiseert een nieuwe vraag.
     */
    protected void newQuestion() {
        this.getRootPane().setDefaultButton(this.nextButton);
        this.nextButton.requestFocus();
        this.firstArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPanel.getGroup() -3) * 20);
        this.secondArgument = ThreadLocalRandom.current().nextInt(1, 6 + (GroepPanel.getGroup() - 3) * 20);

        if (firstArgument < secondArgument) {
            // draai de waarden om zonder een extra variabel te gebruiken...
            firstArgument += secondArgument;
            secondArgument = firstArgument - secondArgument;
            firstArgument -= secondArgument;
        }
        // zorgt ervoor dat het resultaat nooit nul kan zijn.
        firstArgument += 1;

        this.answer = firstArgument - secondArgument;

        renderQuestion();
    }
}
