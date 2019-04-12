package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import geometrie.Vecteur;
import voiture.Voiture;

public class VoitureObserveur implements ObserveurSwing {
	private Voiture voiture;
	private Color coul;
	private String filename = "";

	
	public VoitureObserveur(Voiture voiture) {
		this.voiture=voiture;
		coul = Color.YELLOW;
	}
	
	public VoitureObserveur(Voiture v, String filename) {
		this(v);
		this.filename = filename;

		
		
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
		 g.setColor(Color.black);
         g.drawString(String.format("v: %.2f d: (%6.2f, %6.2f) derap: ", voiture.getVitesse(),
                         voiture.getDirection().getX(), voiture.getDirection().getY()) + voiture.getDerapage(),
                         10, 10);
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
			Vecteur vect = new Vecteur(0,1);
			double angle = Math.PI/2 + vect.angle(voiture.getDirection());
			AffineTransform transform = new AffineTransform();
			transform.rotate(angle,(carMod.getWidth() / 2),(carMod.getHeight() / 2));
			AffineTransformOp op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_BICUBIC);
			carMod = op.filter(carMod, null);
//			int dim1 = 100, dim2 = 78;
//			int delta = 25;
//			BufferedImage resizedImg = new BufferedImage(dim1,dim2,BufferedImage.TYPE_4BYTE_ABGR);
//			Graphics g2 = resizedImg.createGraphics();
//			g2.drawImage(carMod, delta, delta, dim2-2*delta, dim1-2*delta, null);
//			carMod = resizedImg;
			g.drawImage(carMod, getX()-20, getY()-20, null);
		 }

 }
	 

	

}
