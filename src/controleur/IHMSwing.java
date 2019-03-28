package controleur;
import observeurs.*;
import simulation.*;
import terrain.TerrainTools;
import circuit.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class IHMSwing extends JPanel implements UpdateEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ObserveurSwing> lobs;
	private BufferedImage im;
	private CircuitObserveur circuit; 
	private Simulation simu;
	
	public IHMSwing() {
		super();
		lobs = new ArrayList<ObserveurSwing>(0);
		this.im = null;
	}
	
	public IHMSwing(BufferedImage im){
		super();
		this.im=im;
	}
	
	public void addCircuit(Circuit c) {//construction a partir d'un circuit
		circuit = new CircuitObserveur(c);
		im = TerrainTools.imageFromTerrain(c.getTerrain());
		}
	public void init(String nom) {

	}
	
	public void add(Simulation simu) {
		this.simu = simu;
	}
	
	public void add(ObserveurSwing o) {
		lobs.add(o);
	}
	
	public void setImage(BufferedImage im) {
		this.im = im;
	}
	
	public BufferedImage getImage() {
		return im;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		circuit.print(g);
        for(ObserveurSwing o: lobs) {
        	/* nécessite de gérer les exceptions dans les observeurs*
        	 * try {
        	 
        		o.print(g);
        	}catch(IOException e) {
        		e.printStackTrace();
        	}*/
        	if(o!=null) {
        		o.print(g);
        	}
        }
	}
	@Override
	public void manageUpdate() {
		repaint();
		try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                repaint();
            }
        });

	}
	
	public void virage() {
		Graphics g=im.getGraphics();
		g.fillRect(514, 864, 20, 20);
	}

}
