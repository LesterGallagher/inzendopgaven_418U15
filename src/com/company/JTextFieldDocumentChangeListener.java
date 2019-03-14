package com.company;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;

/**
* Een DocumentListener voor een JTextField.
* @author Sem Postma
*/
public class JTextFieldDocumentChangeListener implements DocumentListener {
    private ActionListener listener;

    public JTextFieldDocumentChangeListener(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }

    public void update() {
        listener.actionPerformed(null);
    }
}
