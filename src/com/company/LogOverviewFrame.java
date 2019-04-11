package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Het logboek frame.
 * Deze frame laat een tabel zien met alle resultaten van de afgelopen sessies.
 * @author Sem Postma
 */
public class LogOverviewFrame extends JFrame {
    /**
     * De verwijzing naar het tabel.
     */
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
            columnData[i][0] = dateTimeFormat.format(new Date(sessie.tijdstempel));
            columnData[i][1] = sessie.operationName;
            columnData[i][2] = sessie.getFileName();
            columnData[i][3] = "" + sessie.teMaken;
            columnData[i][4] = "" + sessie.afgerond;
            columnData[i][5] = "" + sessie.fouten;
            columnData[i][6] = "" + (sessie.fouten + sessie.teMaken + sessie.afgerond);
            columnData[i][7] = sessie.percentage + "%";
            columnData[i][8] = sessie.secondes / 60 + " min. " + sessie.secondes % 60 + " sec";
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
                    var sessie = RekenTrainSessie.fetchWithItems(sessies.get(row));
                    if (sessie == null) {
                        JOptionPane.showMessageDialog(null,
                                "Het is niet gelukt om het bestand '" + sessies.get(row).getFileName() + "' te openen...");
                        return;
                    }
                    LogItemFrame frame = new LogItemFrame(sessie);
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
