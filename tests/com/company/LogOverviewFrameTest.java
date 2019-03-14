package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogOverviewFrameTest {

    @Test
    void Test() {
        var logOverviewFrame = new LogOverviewFrame();
        Assertions.assertEquals("Logboek", logOverviewFrame.getTitle());
    }

}