package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkClickActionListenerTest {

    @Test
    void Test() {
        var link = new LinkClickActionListener(new StartFrame(), "example.com");
        link.actionPerformed(null);
        Assertions.assertEquals("example.com", link.url);
    }
}