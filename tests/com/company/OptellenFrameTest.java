package com.company;

import com.company.OptellenFrame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptellenFrameTest {

    OptellenFrame instance;

    @BeforeEach
    void setUp() {
        instance = new OptellenFrame();
        instance.init();
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void newQuestion() {
        instance.newQuestion();

        var answer = instance.firstArgument + instance.secondArgument;
        Assertions.assertEquals(answer, instance.answer);
    }

    @Test
    void newQuestionButtonAnswer() {
        instance.statsPaneel.setTeMaken(1);
        instance.newQuestion();
        var answer = instance.firstArgument + instance.secondArgument;
        Assertions.assertEquals(answer, instance.answer);

        instance.answerTextField.setText("" + answer);
        instance.answerButton.doClick();

        var hasFinalStats = Arrays.asList(instance.inhoudPaneel.getComponents())
                .contains(instance.finalStats);

        // Dit betekent dat de oefening afgerond is.
        Assertions.assertEquals(true, hasFinalStats);
    }
}