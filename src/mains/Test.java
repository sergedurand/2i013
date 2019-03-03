package mains;
import geometrie.Vecteur;
import terrain.*;
import circuit.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import algo.*;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");


		
		Dijkstra dijk = new Dijkstra(c);
		dijk.compute();
		BufferedImage im = TerrainTools.imageFromTerrain(c.getTerrain());
		Graphics g = im.getGraphics();
		
		for(int i = 0;i<c.getWidth();i++) {
			for(int j = 0;j<c.getHeight();j++) {
				System.out.println(dijk.getDist()[i][j]);
				//System.out.println("couleur rouge:"+dijk.getDist()[i][j]%255);
				g.setColor(new Color((int) (dijk.getDist()[i][j]%255),0,0));
				if(TerrainTools.isRunnable(c.getTerrain(i, j))) {
					g.drawLine(i, j, i, j);
				}
			}
		}
		
		try {
            File outputfile = new File("dijkstra.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }

	}
	
		

	

}
