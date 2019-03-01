package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import voiture.*;

import voiture.Voiture;

public class VoitureObserveur implements ObserveurSwing {
	private Voiture voiture;

	
	public VoitureObserveur(Voiture voiture) {
		this.voiture=voiture;
	}
	
	
	private int getX() {
		return (int)voiture.getPosition().getX();
	}
	private int getY() {
		return (int)voiture.getPosition().getY();
	}
	
	public Color getColor() {
		if(voiture.getVitesse()<0.3) // vitesse faible -> cyan
            return new Color(0, (int)(voiture.getVitesse()*255*2), (int) (voiture.getVitesse()*255*2));

         if(voiture.getVitesse() == 0.9)
           return new Color((int)(voiture.getVitesse()*255),  (int) (voiture.getVitesse()*255), 0);

         return new Color((int)(voiture.getVitesse()*255), 0, (int) (voiture.getVitesse()*255));
	}

	 public void print(Graphics g){
         // Attention a l'inversion eventuelle des coordonnees
         g.setColor(this.getColor());
         g.drawRect(this.getX(), this.getY(), 2, 2);
         g.setColor(Color.red);
         g.drawLine(this.getX(),this.getY(),this.getX()+(int)voiture.getDirection().getX()*10,this.getY()+(int)voiture.getDirection().getY()*10);

         g.setColor(Color.black);
         g.drawString(String.format("v: %.2f d: (%6.2f, %6.2f) derap: ", voiture.getVitesse(),
                         voiture.getDirection().getX(), voiture.getDirection().getY()) + voiture.getDerapage(),
                         10, 10);


 }
	 

	

}
