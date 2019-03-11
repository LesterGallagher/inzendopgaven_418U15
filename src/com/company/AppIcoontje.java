package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Een hulp klasse voor het laden van afbeeldingen en icoontjes.
 * @author Sem Postma
 */
public class AppIcoontje {
    /**
     * De naam van de afbeelding in de resources folder.
     */
    protected String imageName;

    public AppIcoontje(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Krijgt een buffered afbeelding van een resource naam.
     *
     * @return De buffered afbeelding
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
     * Krijgt een verkleinde afbeelding van een resource naam.
     *
     * @return De buffered afbeelding
     */
    public Image getImage(int width, int height) {
        return getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }

    /**
     * Krijgt een icoontje van een resource naam.
     *
     * @return Het icoontje
     */
    public ImageIcon getIcon() {
        return new ImageIcon(getImage());
    }

    /**
     * Krijgt een icoontje met de gespecificeerde breedte en hoogte van een resource naam.
     *
     * @return Het icoontje
     */
    public ImageIcon getIcon(int width, int height) {
        return new ImageIcon(getImage(width, height));
    }
}
