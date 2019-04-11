package com.company;

import com.company.LogItemFrame;
import com.company.RekenTrainSessie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogItemFrameTest {

    @Test
    void Test() {
        var item = new LogItemFrame(new RekenTrainSessie());
        Assertions.assertEquals("Log Item", item.getTitle());
    }

}