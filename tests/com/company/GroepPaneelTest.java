package com.company;

import com.company.GroepPaneel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GroepPaneelTest {

    GroepPaneel instance;

    @BeforeEach
    void setUp() {
        instance = new GroepPaneel();
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void getGroup() {
        Assertions.assertEquals(3, GroepPaneel.getGroup());
    }
}