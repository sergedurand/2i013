package mains;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {
	public static void main(String[] args) {
		BufferedImage im;
		im=new BufferedImage(768,1024,BufferedImage.TYPE_INT_ARGB);
		Graphics g=im.getGraphics();
		g.fillRect(320, 717, 25, 25);
		try {
        File outputfile = new File("testaffichage.png");
        ImageIO.write(im, "png", outputfile);
     } catch (IOException e) {
        System.out.println("Erreur lors de la sauvegarde");
     }
		System.out.println("réussi");
		
	}
	
	
}
