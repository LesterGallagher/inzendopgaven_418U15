package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AppIcon {
    protected String imageName;

    public AppIcon(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Gets a buffered image from a resource name.
     *
     * @return      The buffered image
    */
    public BufferedImage getImage() {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(imageName));
            Graphics2D g2d = (Graphics2D) img.getGraphics();
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            return img;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * Gets a resized image from a resource name.
     *
     * @return      The buffered image
     */
    public Image getImage(int width, int height) {
        return getImage().getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;
    }

    /**
     * Gets an icon from a resource name.
     *
     * @return      The buffered image
    */
    public ImageIcon getIcon() {
        return new ImageIcon(getImage());
    }

    /**
     * Gets an icon with the specified width and height from a resource name.
     *
     * @return      The buffered image
     */
    public ImageIcon getIcon(int width, int height) {
        return new ImageIcon(getImage(width, height));
    }
}
