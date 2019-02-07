package simulation;
import voiture .*;
import strategy .*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import circuit.*;
import geometrie .*;
import terrain .*;

/**
 * @author Serge et Kevin
 *permet de gerer les simulations.
 *
 */
public class Simulation {
	private Voiture v;
	private Strategy strat;
	private Circuit c;

	/**
	 * Instancie un objet Simulation
	 * @param v 
	 * @param strat
	 * @param c
	 */
	public Simulation(Voiture v, Strategy strat, Circuit c) {
		super();
		this.v = v;
		this.strat = strat;
		this.c = c;
	}
	
	public Voiture getV() {
		return v;
	}
	public Strategy getStrat() {
		return strat;
	}
	public Circuit getC() {
		return c;
	}
	@Override
	public String toString() {
		return "Simulation [v=" + v + ", strat=" + strat + ", c=" + c + "]";
	}

	/**
	 * Colorie un pixel sur l'image à la position courante de la voiture
	 * @param im
	 */
	private void Trace(BufferedImage im) {
		//on met une image et pas un circuit en parametre : on veut creer l'image une seule fois dans la simulation
		int x = (int)v.getPosition().getX();
		int y = (int)v.getPosition().getY();
		Graphics g = im.getGraphics();
		g.setColor(new Color(255, 165, 0));
		g.drawLine(x, y, x, y);
		
	}
	
	/**
	 * lance une simulation
	 */
	public void play(int iteration) {
		BufferedImage im = TerrainTools.imageFromTerrain(c.getTerrain());
		for(int i=0;i<iteration;i++) {
			v.drive(strat.getCommande());
			//System.out.println("Position voiture : "+v.getPosition().toString());
			Trace(im);
		}
		try {
           File outputfile = new File("FinSimu");
           ImageIO.write(im, "png", outputfile);
        } catch (IOException e) {
           System.out.println("Erreur lors de la sauvegarde");
        }		
	}
	

}
