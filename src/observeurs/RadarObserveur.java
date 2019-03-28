package observeurs;
import algo.*;
import geometrie.*;
import java.awt.Graphics;

import java.awt.Color;

public class RadarObserveur implements ObserveurSwing {
	
	public RadarObserveur(Radar r) {
		super();
		this.rad = r;
	}



	private Radar rad;
	
	

	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.blue);
		Vecteur vdir;
		for (int i=0;i<rad.getDistPix().length;i++) {
			vdir=rad.getVoiture().getDirection().rotation(rad.getAngles()[i]);
			vdir=vdir.normalisation();
			vdir=vdir.multiplication(rad.getDistPix()[i]);
			g.drawLine(this.getX(),this.getY(), (int)vdir.getX()+this.getX(), (int)vdir.getY()+this.getY());
		}

	}



	private int getX() {
		// TODO Auto-generated method stub
		return (int) rad.getVoiture().getPosition().getX();
	}



	private int getY() {
		// TODO Auto-generated method stub
		return (int) rad.getVoiture().getPosition().getY();
	}

}
