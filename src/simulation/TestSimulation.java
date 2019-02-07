package simulation;
import circuit.*;
import geometrie.*;
import terrain.*;
import voiture.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestSimulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");
		Voiture v = VoitureFactory.build(c);
		Commande[] AccSansRot = new Commande[100];
		Commande[] AccPlusRotDroit = new Commande[200];
		Commande[] AccPlusRotGauche = new Commande[200];
		for(int i=0;i<100;i++) {
			Random r = new Random();
			double acc1=r.nextDouble()*2-1;
			double acc2 = r.nextDouble()*2-1;
			double acc3 = r.nextDouble()*2-1;
			AccSansRot[i] = new Commande(acc1,0.);
			AccPlusRotDroit[i] = new Commande(acc2,0.);
			AccPlusRotGauche[i] = new Commande(acc3,0.);
		}
		for(int i=0;i<100;i++) {
			Random r = new Random();
			double acc1=r.nextDouble()*2-1;
			double acc2 = r.nextDouble()*2-1;
			double rot1 = r.nextDouble()*2-1;
			double rot2 = r.nextDouble();
			AccPlusRotDroit[100+i] = new Commande(acc1,rot1);
			AccPlusRotGauche[100+i] = new Commande(acc2,rot2);
		}
		for(int i = 0;i<200;i++) {
			System.out.println(AccPlusRotDroit[i].toString());
		}
		BufferedImage im = TerrainTools.imageFromCircuit(c.getTerrain());
		Graphics g = im.getGraphics();
		g.setColor(new Color(255, 165, 0));
		for(int i = 0;i<200;i++) {
			if(i<100) {
				v.drive(AccSansRot[i]);
				Vecteur position = v.getPosition();
				g.drawLine((int)position.getX(),(int)position.getY(),(int)position.getX(),(int)position.getY());

			}
			v.drive(AccPlusRotDroit[i]);
			v.drive(AccPlusRotGauche[i]);
			Vecteur position = v.getPosition();
			System.out.println("Position : "+position.toString());
			g.drawLine((int)position.getX(),(int)position.getY(),(int)position.getX(),(int)position.getY());

		}
		try {
            File outputfile = new File("aprescourse.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }
		
		
		

	}

}
