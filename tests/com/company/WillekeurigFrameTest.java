package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WillekeurigFrameTest {

    WillekeurigFrame instance;

    @BeforeEach
    void setUp() {
        instance = new WillekeurigFrame();
        instance.init();
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    int getAnswer() {
        return instance.operator == '+'
            ? instance.firstArgument + instance.secondArgument
            : instance.operator == '-'
            ? instance.firstArgument - instance.secondArgument
            : instance.operator == '÷'
            ? instance.firstArgument / instance.secondArgument
            : instance.firstArgument * instance.secondArgument;
    }

    @Test
    void newQuestion() {
        instance.statsPaneel.setTeMaken(1);
        instance.newQuestion();
        var answer = getAnswer();

        Assertions.assertEquals(answer, instance.answer);

    }

    @Test
    void newQuestionButtonAnswer() {
        instance.statsPaneel.setTeMaken(1);
        instance.newQuestion();
        var answer = getAnswer();

        Assertions.assertEquals(answer, instance.answer);

        instance.answerTextField.setText("" + answer);
        instance.answerButton.doClick();

        var hasFinalStats = Arrays.asList(instance.inhoudPaneel.getComponents())
                .contains(instance.finalStats);

        // Dit betekent dat de oefening afgerond is.
        Assertions.assertEquals(true, hasFinalStats);
    }
}