package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

import geometrie.Vecteur;
import voiture.Voiture;

public class VoitureObserveur implements ObserveurSwing {
	private Voiture voiture;
	private Color coul;
	private String filename;
	private int score;
	
	public VoitureObserveur(Voiture voiture) {
		this.voiture=voiture;
		coul = Color.YELLOW;
	}
	
	public VoitureObserveur(Voiture v, String filename) {
		this(v);
		this.filename = filename+".png";	
		
	}
	
	public void setScore(int k) {
		score=k;
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
	
	public void setColor(Color c) {
		coul = c;
	}
	 public void print(Graphics g){
         // Attention a l'inversion eventuelle des coordonnees
		 //System.out.println("filename : "+filename);
		 try {
			 g.setColor(Color.black);
	         g.drawString(String.format("v: %.2f d: (%6.2f, %6.2f)", voiture.getVitesse(),
	                         voiture.getDirection().getX(), voiture.getDirection().getY()),
	                         10, 10);
	         g.drawString(String.format("Score: "+score),10, 30);

     
			 if(filename.length()==0) {
				 g.setColor(this.getColor());
		         g.fillRect(this.getX(), this.getY(), 5, 5);
		         
		         g.drawLine(this.getX(),this.getY(),this.getX()+(int)voiture.getDirection().getX()*10,this.getY()+(int)voiture.getDirection().getY()*10);
			 }else {
				 g.setColor(getColor());
				BufferedImage carMod =null;
				try {
					carMod = ImageIO.read(new File(filename));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.setColor(getColor());
				 // calcul de l'angle à appliquer sur l'image de la voiture pour la rendre
				  // cohérente avec la simulation à chaque instant
				  // -> dépend de l'image et de l'affichage de l'interface (horizontale ou vertical)
				  double angle = Math.PI/2 - (voiture.getDirection().angle( new Vecteur(0, 1)));

				  // construction d'une transformation
				  AffineTransform transform = new AffineTransform();
				  // rotation par rapport à un centre à définir (cf javadoc)
				  transform.rotate(angle, (carMod.getWidth() / 2), (carMod.getHeight() / 2));
				  // transformation => transformation d'image
				  AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

				  // image finale
				  carMod = op.filter(carMod, null);
				 g.drawImage(carMod, getX()-13, getY(), null);
				
				
				
				
				
				
				
				
				
				
				
				
				
			 } 
		 }catch (ConcurrentModificationException e) {
				System.out.println("modification concurrente voiture observeur");
		}
		 
		 


 }
	 

	

}
