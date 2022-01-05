package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageResizer {

    // Metoda która zmienia rozmiar obrazka i go zwraca
    public static Image getScaledInstance(Image srcImage, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = resizedImg.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2D.drawImage(srcImage, 0, 0, w, h, null);
        g2D.dispose();
        return resizedImg;
    }

}
