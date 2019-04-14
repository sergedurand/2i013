package observeurs;
import algo.*;
import geometrie.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ConcurrentModificationException;
import java.awt.BasicStroke;
import java.awt.Color;

public class RadarObserveur implements ObserveurSwing {
	private Radar rad;
	private Color coul;
	
	public RadarObserveur(Radar r) {
		super();
		this.rad = r;
		coul = Color.BLUE;
	}


	/*@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(coul);
		Vecteur vdir;
		for (int i=0;i<rad.getDistPix().length;i++) {
			vdir=rad.getVoiture().getDirection().rotation(rad.getAngles()[i]);
			vdir=vdir.normalisation();
			vdir=vdir.multiplication(rad.getDistPix()[i]);
			g.drawLine(this.getX(),this.getY(), (int)vdir.getX()+this.getX(), (int)vdir.getY()+this.getY());
		}

	}*/
	
	 //POUR LE RADAR PARABOLIQUE UNIQUEMENT

	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		try {
			g.setColor(coul);
			Vecteur vdir;
			Vecteur vfinal;
			for (int i=0;i<rad.getDistPix().length;i++) {
				if(i==rad.getBestIndex()) {
				    Graphics2D g2 = (Graphics2D) g;
				    g2.setStroke(new BasicStroke(3));
				    g2.setColor(Color.RED);
					vdir=rad.getVoiture().getDirection().rotation(rad.getAngles()[i]);
					vdir=vdir.normalisation();
					vdir=vdir.multiplication(rad.getDistPix()[i]);
					g2.drawLine(this.getX(),this.getY(), (int)vdir.getX()+this.getX(), (int)vdir.getY()+this.getY());
				}else {
					Graphics2D g2 = (Graphics2D) g;
				    g2.setStroke(new BasicStroke(1));
				    g2.setColor(Color.BLUE);
					vdir=rad.getVoiture().getDirection().rotation(rad.getAngles()[i]);
					vdir=vdir.normalisation();
					vdir=vdir.multiplication(rad.getDistPix()[i]);
					g2.drawLine(this.getX(),this.getY(), (int)vdir.getX()+this.getX(), (int)vdir.getY()+this.getY());
				}
			}
		}catch (ConcurrentModificationException e) {
			System.out.println("modification concurrente radar observeur");

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


	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		coul = c;
	}

}
