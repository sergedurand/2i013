package observeurs;
import circuit.*;
import terrain.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

public class CircuitObserveur implements ObserveurSwing {//dans cette classe on ne fait que charger l'image correspondant ï¿½ un circuit, et on la peint
	
	private BufferedImage im;
	
	public CircuitObserveur(Circuit c) {
		super();
		this.im = TerrainTools.imageFromTerrain(c.getTerrain());
	}

	public BufferedImage getImage() {
		return im;
	}
	public void print(Graphics g) {
		try {
			Graphics gg=im.getGraphics();
			gg.setColor(new Color(0,0,0));
	//		gg.fillRect(200,455,10,10);
	//		gg.fillRect(400,455,10,10);
	//		gg.fillRect(610,520,10,10);
	//		gg.fillRect(530,650,10,10);  //Observation pour la stratégie point à point
	//		gg.fillRect(400,400,10,10);
	//		gg.fillRect(400,350,10,10);
			//printmodif();
			// TODO Auto-generated method stub
			g.drawImage(im, 1, 1, null);
		}catch (ConcurrentModificationException e) {
			System.out.println("modification concurrente circuit observeur");
		}

	}
	public int i=0;
	public void printmodif() {
		Graphics gg=im.getGraphics();
		gg.setColor(new Color(0,0,0));
		gg.fillRect(200+i,455+i,10,10);
		i++;
		System.out.println(i);
		
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

}
