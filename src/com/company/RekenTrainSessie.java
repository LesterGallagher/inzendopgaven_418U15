package com.company;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * De Rekentrain sessie klasse.
 * Houd informatie bij over een rekensessie.
 * Informatie zoals de tijd, het percentage van alle sommen die goed afgerond zijn.
 * @author Sem Postma
 */
public class RekenTrainSessie {
    /**
     * Het master bestand pad.
     * Dit bestand wordt aangemaakt in dezelfde folder als de folder waarin het programma gestart is.
     * Dit bestand houdt de bestandsnamen van alle xml bestanden bij,
     * waarin informatie over een specifieke opdracht staat opgeslagen.
     */
    private static String masterFilename = System.getProperty("user.dir") + "/FuglenRekenTrainerSessies.txt";
    /**
     * De folder voor alle opgeslagen sessies.
     */
    private static File userHomeFolder = new File(System.getProperty("user.home") + "/FuglenRekentrainer");
    /**
     * Een lijst met sessie opdracht items.
     * Elk item is het resultaat van een bepaalde vraag.
     */
    public ArrayList<RekenTrainSessieItem> items = new ArrayList<>();
    /**
     * De naam van de oefening.
     * Bijvoorbeeld: "Optellen".
     */
    public String operationName;

    /**
     * Het aantal afgeronde opdrachten.
     */
    public int afgerond;
    /**
     * Het aantal foute opdrachten.
     */
    public int fouten;
    /**
     * Het gemaakte en te maken opdrachten.
     */
    public int teMaken;
    /**
     * Het percentage goed afgeronde opdrachten.
     */
    public int percentage;
    /**
     * Het aantal secondes waarin de gebruiker de opdracht had afgerond.
     */
    public long secondes;
    /**
     * De tijdstempel van wanneer de opdracht is gestart.
     */
    public long tijdstempel;

    /**
     * Verkrijg de alle opdrachten vanaf een sessie object zonder items.
     * @param sessieWithoutItems Een sessie object zonder items.
     * @return Een rekentrainersessie object met opdracht items.
     */
    public static RekenTrainSessie fetchWithItems(RekenTrainSessie sessieWithoutItems) {
        long tijdstempel = sessieWithoutItems.tijdstempel;
        String bestandsNaam = userHomeFolder.getAbsolutePath() + '/' + sessieWithoutItems.getFileName();
        return fetch(bestandsNaam);
    }

    /**
     * Verkrijg de alle opdrachten vanaf een bestand met alle opdrachten in die sessie.
     * @param bestandsNaam De naam van het bestand.
     */
    public static RekenTrainSessie fetch(String bestandsNaam) {
        try {
            XMLDecoder decoder =
                    new XMLDecoder(new BufferedInputStream(
                            new FileInputStream(bestandsNaam)));
            RekenTrainSessie o = (RekenTrainSessie) decoder.readObject();
            decoder.close();
            return o;
        } catch (Exception ex) {
            System.out.print(ex);
            return null;
        }
    }

    /**
     * Verkrijg alle oefeningen zonder opdrachten items voor elke oefening.
     * Deze items kunnen gekregen worden door met een van de oefening sessies "fetchWIthItems" aan te roepen.
     * @return Een lijst met oefening sessies.
     */
    public static ArrayList<RekenTrainSessie> fetchListWithoutItems() {
        try {
            File tmpDir = new File(masterFilename);
            boolean exists = tmpDir.exists() && tmpDir.isFile();
            if (!exists) return new ArrayList<>(0);

            List<String> lines = Files.readAllLines(Paths.get(tmpDir.getAbsolutePath()));
            ArrayList<RekenTrainSessie> ret = new ArrayList<RekenTrainSessie>(lines.size());
            lines.forEach(line -> {
                String[] split = line.split(" ");
                RekenTrainSessie sessie = new RekenTrainSessie();
                sessie.tijdstempel = Long.parseLong(split[0]);
                sessie.operationName = split[1];
                sessie.teMaken = Integer.parseInt(split[3]);
                sessie.afgerond = Integer.parseInt(split[4]);
                sessie.fouten = Integer.parseInt(split[5]);
                sessie.percentage = Integer.parseInt(split[6]);
                sessie.secondes = Integer.parseInt(split[7]);
                sessie.items = null;
                ret.add(sessie);
            });
            return ret;
        } catch (Exception ex) {
            System.out.print(ex);
            return null;
        }
    }

    /**
     * Na het maken van een opdracht moet deze methode worden aangeroepen
     * zodat de resultaten van de opdracht kunnen worden opgeslagen.
     * @param frame
     */
    public void push(OefeningFrame frame) {
        RekenTrainSessieItem item = new RekenTrainSessieItem();
        item.firstArgument = frame.firstArgument;
        item.secondArgument = frame.secondArgument;
        item.answer = frame.answer;
        item.operator = frame.operator;
        item.userAnswer = frame.userAnswer;
        items.add(item);
    }

    /**
     * Na het afronden van de sessie moet deze methode worden aangeroepen.
     * @param afgerond Het aantal afgeronde opdrachten.
     * @param fouten Het aantal foute opdrachten.
     * @param teMaken Het gemaakte en te maken opdrachten.
     * @param percentage Het percentage goed afgeronde opdrachten.
     * @param secondes Het aantal secondes waarin de gebruiker de opdracht had afgerond.
     */
    public void stop(int afgerond, int fouten, int teMaken, int percentage, long secondes) {
        this.afgerond = afgerond;
        this.fouten = fouten;
        this.teMaken = teMaken;
        this.percentage = percentage;
        this.secondes = secondes;
        this.tijdstempel = new Date().getTime();

        persist();
    }

    /**
     * Met deze methode kun je de bestandsnaam voor een bepaalde sessie achterhalen.
     * @return De naam van het bestand.
     */
    protected String getFileName() {
        return "Sessie-" + tijdstempel + ".xml";
    }

    /**
     * Deze methode zorgt ervoor dat de sessie in een bestand wordt opgeslagen.
     */
    protected void persist() {

        userHomeFolder.mkdirs();

        String bestandsNaam = userHomeFolder.getAbsolutePath() + '/' + getFileName();

        try {
            XMLEncoder encoder =
                    new XMLEncoder(
                            new BufferedOutputStream(
                                    new FileOutputStream(bestandsNaam)));
            encoder.writeObject(this);
            encoder.close();


            System.out.print(masterFilename);

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(masterFilename, true));
            out.write(tijdstempel + " ");
            out.write(operationName + " ");
            out.write(bestandsNaam + " ");
            out.write(teMaken + " ");
            out.write(afgerond + " ");
            out.write(fouten + " ");
            out.write(percentage + " ");
            out.write(secondes + System.lineSeparator());
            out.close();
        } catch (Exception ex) {
            System.out.print(ex);
            return;
        }
    }


}
