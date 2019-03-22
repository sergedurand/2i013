package observeurs;
import circuit.*;
import terrain.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CircuitObserveur implements ObserveurSwing {//dans cette classe on ne fait que charger l'image correspondant � un circuit, et on la peint
	
	private BufferedImage im;
	
	public CircuitObserveur(Circuit c) {
		super();
		this.im = TerrainTools.imageFromTerrain(c.getTerrain());
	}

	public BufferedImage getImage() {
		return im;
	}
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(im, 1, 1, null);

	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

}
