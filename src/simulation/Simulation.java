package simulation;
import voiture .*;
import strategy .*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
		int x = (int)v.getPosition().x;
		int y = (int)v.getPosition().getY();
		Graphics g = im.getGraphics();
		g.drawLine(x, y, x, y);
		
	}
	public void play() {
		TerrainTools terTool = new TerrainTools();
		BufferedImage im = terTool.imageFromTerrain(c.getTerrain());
		
		return;
	}
	

}
