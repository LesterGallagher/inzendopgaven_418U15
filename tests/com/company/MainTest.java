package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() throws InterruptedException {
        Main.main(new String[0]);
        TimeUnit.SECONDS.sleep(1);
        Helpers.setTimeout(() -> {
            boolean loginSchermVisible = LoginScherm.getFrames()[0].isVisible();
            Assertions.assertEquals(true, loginSchermVisible);
        }, 1000);
    }
}