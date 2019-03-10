package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogOverviewFrame extends JFrame {
    JTable table;

    public LogOverviewFrame() {
        setTitle("Logboek");
        var sessies = RekenTrainSessie.fetchListWithoutItems();
        String[] columnNames = {"Tijdstip",
                "Opdracht",
                "Bestandsnaam",
                "Niet gemaakt",
                "Goed",
                "Fout",
                "Totaal",
                "Percentage",
                "Tijd"};
        Object[][] columnData = new Object[sessies.size()][columnNames.length];
        Locale locale = new Locale("nl", "NL");
        DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
        for (int i = 0; i < sessies.size(); i++) {
            RekenTrainSessie sessie = sessies.get(i);
            columnData[i][0] =  dateTimeFormat.format(new Date(sessie.timestamp));
            columnData[i][1] = sessie.operationName;
            columnData[i][2] = sessie.getFileName();
            columnData[i][3] = "" + sessie.todo;
            columnData[i][4] = "" + sessie.done;
            columnData[i][5] = "" + sessie.mistakes;
            columnData[i][6] = "" + (sessie.mistakes + sessie.todo + sessie.done);
            columnData[i][7] = sessie.percentage + "%";
            columnData[i][8] = sessie.seconds / 60 + " min. " + sessie.seconds % 60 + " sec";
        }
        table = new JTable(columnData, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(columnData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    LogItemFrame frame = new LogItemFrame(
                            RekenTrainSessie.fetchWithItems(sessies.get(row)));
                    frame.setVisible(true);
                }
            }
        });
        setSize(900, 600);
        validate();
        repaint();
        setLocationRelativeTo(null);
    }
}
