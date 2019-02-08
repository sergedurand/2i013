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
		Color c = new Color(255,165,0);
		Graphics g = im.getGraphics();
		g.setColor(c);
		g.drawLine(x, y, x, y);
		
		//im.setRGB(x, y, c.getRGB());
		
	}
	
	private void TraceSortie(BufferedImage im) {
		
		//on met une image et pas un circuit en parametre : on veut creer l'image une seule fois dans la simulation
		int x = (int)v.getPosition().getX();
		int y = (int)v.getPosition().getY();
		Color c = new Color(255,0,0);
		im.setRGB(x, y, c.getRGB());
		
	}
	/**
	 * lance une simulation
	 * @throws VoitureException 
	 */
	public void play(int iteration) throws VoitureException {
		BufferedImage im = TerrainTools.imageFromTerrain(c.getTerrain());
		//test orientation
		Color c = new Color(255,0,0);
		Color c2 = new Color(0,0,255);

		im.setRGB(0, 0, c.getRGB());
		im.setRGB(767, 1023, c2.getRGB());
		im.setRGB(760, 800, c.getRGB());
		for(int i=0;i<iteration;i++) {
			int x = (int)this.v.getPosition().getX();
			int y = (int)this.v.getPosition().getY();
			//System.out.println(v.getPosition().toString());
			/*if(x<0 || x>=c.getHeight() || y<0 || y>= c.getWidth()) {
				System.out.println("Sortie de terrain");
				TraceSortie(im);
			}
			else{*/
				this.v.drive(strat.getCommande());
				Trace(im);
				/*for(Vecteur v: this.c.getArrivees()) {
					if (v.equals(this.v.getPosition()) && (iteration !=0)){
						System.out.println("Ligne d'arrivee franchie");
						return;
					}
				}*/
				if (TerrainTools.charFromTerrain(this.c.getTerrain(v.getPosition()))=='!' && (iteration !=0)) {
					System.out.println("Ligne d'arrivee franchie");
					break;
				}
			
		}
		try {
           File outputfile = new File("FinSimu");
           ImageIO.write(im, "png", outputfile);
        } catch (IOException e) {
           System.out.println("Erreur lors de la sauvegarde");
        }		
	}
	

}
