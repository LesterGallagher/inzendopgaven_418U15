package com.company;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RekenTrainSessie {
    public ArrayList<RekenTrainSessieItem> items = new ArrayList<>();
    public String operationName;
    public int done;
    public int mistakes;
    public int todo;
    public int percentage;
    public long seconds;
    public long timestamp;

    private static String masterFilename = System.getProperty("user.dir") + "/FuglenRekenTrainerSessies.txt";
    private static File userHomeFolder = new File(System.getProperty("user.home") + "/FuglenRekentrainer");

    public static RekenTrainSessie fetchWithItems(RekenTrainSessie sessieWithoutItems) {
        long timestamp = sessieWithoutItems.timestamp;
        String filename = userHomeFolder.getAbsolutePath() + '/' + sessieWithoutItems.getFileName();
        return fetch(filename);
    }

    public static RekenTrainSessie fetch(String filename) {
        try {
            XMLDecoder decoder =
                    new XMLDecoder(new BufferedInputStream(
                            new FileInputStream(filename)));
            RekenTrainSessie o = (RekenTrainSessie) decoder.readObject();
            decoder.close();
            return o;
        }
        catch(Exception ex) {
            System.out.print(ex);
            return null;
        }
    }

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
                sessie.timestamp = Long.parseLong(split[0]);
                sessie.operationName = split[1];
                sessie.todo = Integer.parseInt(split[3]);
                sessie.done = Integer.parseInt(split[4]);
                sessie.mistakes = Integer.parseInt(split[5]);
                sessie.percentage = Integer.parseInt(split[6]);
                sessie.seconds = Integer.parseInt(split[7]);
                sessie.items = null;
                ret.add(sessie);
            });
            return ret;
        }
        catch(Exception ex) {
            System.out.print(ex);
            return null;
        }
    }

    public void push(OefeningFrame frame) {
        RekenTrainSessieItem item = new RekenTrainSessieItem();
        item.firstArgument = frame.firstArgument;
        item.secondArgument = frame.secondArgument;
        item.answer = frame.answer;
        item.operator = frame.operator;
        item.userAnswer = frame.userAnswer;
        items.add(item);
    }

    public void stop(int done, int mistakes, int todo, int percentage, long seconds) {
        this.done = done;
        this.mistakes = mistakes;
        this.todo = todo;
        this.percentage = percentage;
        this.seconds = seconds;
        this.timestamp = new Date().getTime();

        persist();
    }

    protected String getFileName() {
        return "Sessie-" + timestamp + ".xml";
    }

    protected void persist() {

        userHomeFolder.mkdirs();

        String filename = userHomeFolder.getAbsolutePath() + '/' + getFileName();

        try {
            XMLEncoder encoder =
                    new XMLEncoder(
                            new BufferedOutputStream(
                                    new FileOutputStream(filename)));
            encoder.writeObject(this);
            encoder.close();



            System.out.print(masterFilename);

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(masterFilename, true));
            out.write(timestamp + " ");
            out.write(operationName + " ");
            out.write(filename + " ");
            out.write(todo + " ");
            out.write(done + " ");
            out.write(mistakes + " ");
            out.write(percentage + " ");
            out.write(seconds + System.lineSeparator());
            out.close();
        } catch(Exception ex) {
            System.out.print(ex);
            return;
        }
    }


}
