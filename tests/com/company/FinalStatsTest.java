package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class FinalStatsTest {

    FinalStats instance;
    WillekeurigFrame frame;
    StatsPaneel stats;

    @BeforeEach
    void setUp() {
        stats = new StatsPaneel();
        frame = new WillekeurigFrame();
        frame.init();
        instance = new FinalStats(frame);
    }

    @AfterEach
    void tearDown() {
        frame = null;
        instance = null;
    }

    @Test
    void init() {
        final AtomicReference<Boolean> reference = new AtomicReference<>();
        stats.setTeMaken(2);
        stats.setDate(new Date());
        stats.setAfgerond(2);
        stats.setFouten(0);
        instance.init(stats);

        reference.set(false);
        instance.addOnRepeatListener(e -> {
            reference.set(true);
        });
        var jPanels = Arrays.asList(instance.getComponents())
                .stream()
                .filter(e -> e.getClass() == JPanel.class)
                .toArray();
        JPanel buttonPanel = (JPanel) jPanels[jPanels.length - 1];

        JButton button = (JButton) buttonPanel.getComponents()[0];
        button.doClick();

        Assertions.assertEquals(true, reference.get());
    }
}