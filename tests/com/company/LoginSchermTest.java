package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginSchermTest {

    @Test
    void getUserName() {
        String userName = new LoginScherm().getUserName();
        Assertions.assertEquals("", userName);
    }
}