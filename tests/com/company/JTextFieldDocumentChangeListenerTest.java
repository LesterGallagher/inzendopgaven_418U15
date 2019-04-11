package com.company;

import com.company.JTextFieldDocumentChangeListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class JTextFieldDocumentChangeListenerTest {

    @Test
    void TestListener() {
        AtomicReference<Boolean> reference = new AtomicReference<>();
        reference.set(false);
        var field = new JTextFieldDocumentChangeListener(e -> {
            reference.set(true);
        });
        field.update();
        field.changedUpdate(null);
        field.insertUpdate(null);
        field.removeUpdate(null);
        Assertions.assertEquals(true, reference.get());
    }
}