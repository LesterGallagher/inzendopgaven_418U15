package com.company;

import com.company.AppIcoontje;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Test
 */
class AppIcoontjeTest {

    AppIcoontje instance;
    ImageObserver observer;

    @BeforeEach
    void setUp() {
        instance = new AppIcoontje("/delen.png");
        observer = (Image img, int infoflags, int x, int y, int width, int height) -> true;
    }

    @AfterEach
    void tearDown() {
        instance = null;
        observer = null;
    }


    @Test
    void getImageExceptionTest() {
        var img = new AppIcoontje("doesnt-exist.png").getImage();
        Assertions.assertEquals(null, img);
    }

    @Test
    void getImage() {
        var img = instance.getImage();
        var width = img.getWidth();
        var height = img.getHeight();
        Assertions.assertEquals(width, 150);
        Assertions.assertEquals(height, 150);
    }

    @Test
    void getImage1() {
        var img = instance.getImage(100, 50);
        var width = img.getWidth(observer);
        var height = img.getHeight(observer);
        Assertions.assertEquals(width, 100);
        Assertions.assertEquals(height, 50);
    }

    @Test
    void getIcon() {
        var img = instance.getIcon();
        var width = img.getImage().getWidth(observer);
        var height = img.getImage().getHeight(observer);
        Assertions.assertEquals(width, 150);
        Assertions.assertEquals(height, 150);
    }

    @Test
    void getIcon1() {
        var img = instance.getIcon(100, 50);
        var width = img.getImage().getWidth(observer);
        var height = img.getImage().getHeight(observer);
        Assertions.assertEquals(width, 100);
        Assertions.assertEquals(height, 50);
    }
}