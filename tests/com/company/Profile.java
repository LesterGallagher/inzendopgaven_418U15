package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Test
 */
class Profile {
    @Test
    void main() throws InterruptedException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> {
            LoginScherm screen = new LoginScherm();
            screen.setVisible(true);
        });

        Helpers.setTimeout(() -> {
            OptellenFrame optellenFrame = new OptellenFrame();
            optellenFrame.init();
            optellenFrame.dispatchEvent(new WindowEvent(optellenFrame, WindowEvent.WINDOW_CLOSING));
        }, 200).join();

        Helpers.setTimeout(() -> {
            MinSommenFrame minSommenFrame = new MinSommenFrame();
            minSommenFrame.init();
            minSommenFrame.dispatchEvent(new WindowEvent(minSommenFrame, WindowEvent.WINDOW_CLOSING));
        }, 400).join();

        Helpers.setTimeout(() -> {
            DelenFrame delenFrame = new DelenFrame();
            delenFrame.init();
            delenFrame.dispatchEvent(new WindowEvent(delenFrame, WindowEvent.WINDOW_CLOSING));
        }, 600).join();

        Helpers.setTimeout(() -> {
            VermenigvuldigenFrame vermenigvuldigenFrame = new VermenigvuldigenFrame();
            vermenigvuldigenFrame.init();
            vermenigvuldigenFrame.dispatchEvent(new WindowEvent(vermenigvuldigenFrame, WindowEvent.WINDOW_CLOSING));
        }, 800).join();

        Helpers.setTimeout(() -> {
            WillekeurigFrame willekeurigFrame = new WillekeurigFrame();
            willekeurigFrame.init();
            willekeurigFrame.dispatchEvent(new WindowEvent(willekeurigFrame, WindowEvent.WINDOW_CLOSING));
        }, 1000).join();

        Helpers.setTimeout(() -> {
            LogOverviewFrame logOverviewFrame = new LogOverviewFrame();
            logOverviewFrame.dispatchEvent(new WindowEvent(logOverviewFrame, WindowEvent.WINDOW_CLOSING));
        }, 1200).join();

        Helpers.setTimeout(() -> {
            long totalGarbageCollections = 0;
            long garbageCollectionTime = 0;

            for(GarbageCollectorMXBean gc :
                    ManagementFactory.getGarbageCollectorMXBeans()) {

                long count = gc.getCollectionCount();

                if(count >= 0) {
                    totalGarbageCollections += count;
                }

                long time = gc.getCollectionTime();

                if(time >= 0) {
                    garbageCollectionTime += time;
                }
            }

            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(System.getProperty("user.dir") + "/middelen_gebruik.txt")
                );

                writer.write("Total Garbage Collections: "
                        + totalGarbageCollections);
                writer.newLine();
                writer.write("Total Garbage Collection Time (ms): "
                        + garbageCollectionTime);
                writer.newLine();

                long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                writer.write("Total Memory usage: "
                        + Helpers.humanReadableByteCount(memory));
                writer.newLine();

                var f = Paths.get(System.getProperty("user.dir"),
                        "out/artifacts/FuglenRekentrainer_jar/FuglenRekentrainer.jar")
                        .toAbsolutePath()
                        .toFile();

                writer.write("Final executable jar file size: " + Helpers.humanReadableByteCount(f.length()));
                writer.newLine();

                writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }, 1400).join();

    }

}