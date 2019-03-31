package observeurs;
import circuit.*;
import terrain.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
		Graphics gg=im.getGraphics();
		gg.setColor(new Color(0,0,0));
		gg.fillRect(200,455,10,10);
		gg.fillRect(400,455,10,10);
		gg.fillRect(610,520,10,10);
		gg.fillRect(530,650,10,10);  //Observation pour la stratégie point à point
		gg.fillRect(400,400,10,10);
		gg.fillRect(400,350,10,10);

		// TODO Auto-generated method stub
		g.drawImage(im, 1, 1, null);

	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

}
