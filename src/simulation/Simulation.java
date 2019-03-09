package simulation;
import voiture .*;
import strategy .*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import circuit.*;
import controleur.IHMSwing;
import controleur.UpdateEventListener;
import controleur.UpdateEventSender;
import terrain .*;

/**
 * @author Serge et Kevin
 *permet de gerer les simulations.
 *
 */
public class Simulation implements UpdateEventSender{
	private Voiture v;
	private Strategy strat;
	private Circuit c;
	private ArrayList<Commande> commandes;
	private ArrayList<UpdateEventListener> listeners;

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
		this.commandes = new ArrayList<Commande>();
		listeners = new ArrayList<UpdateEventListener>();
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
	 * Colorie un pixel sur l'image ï¿½ la position courante de la voiture
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
	}
	
	public ArrayList<Commande> getCommandes(){
		return this.commandes;
	}
	
	/*private void TraceSortie(BufferedImage im) {
		
		//on met une image et pas un circuit en parametre : on veut creer l'image une seule fois dans la simulation
		int x = (int)v.getPosition().getX();
		int y = (int)v.getPosition().getY();
		Color c = new Color(255,0,0);
		im.setRGB(x, y, c.getRGB());
		
	}*/
	/**
	 * lance une simulation
	 * @throws VoitureException 
	 */
	public static void saveListeCommande(ArrayList<Commande> liste, String filename){
        try {
                DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));
                for(Commande c:liste){
                        os.writeDouble(c.getAcc());
                        os.writeDouble(c.getTurn());
                }
                os.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
}

	public static ArrayList<Commande> loadListeCommande(  String filename) throws IOException{
        ArrayList<Commande> liste = null;

        try {
                DataInputStream os = new DataInputStream(new FileInputStream(filename));

                liste = new ArrayList<Commande>();
                double a,t;
                while(true){ // on attend la fin de fichier
                        a = os.readDouble();
                        t = os.readDouble();
                        liste.add(new Commande(a,t));
                }
                

        } catch (EOFException e){
                return liste;
        }

}

	
	public void update() {
		for(UpdateEventListener e: listeners) {
			if(e!=null) {
				e.manageUpdate();
			}
		}
	}
	
	public Color getColor() {
		if(v.getVitesse()<0.3) // vitesse faible -> cyan
            return new Color(0, (int)(v.getVitesse()*255*2), (int) (v.getVitesse()*255*2));

         if(v.getVitesse() == 0.9)
           return new Color((int)(v.getVitesse()*255),  (int) (v.getVitesse()*255), 0);

         return new Color((int)(v.getVitesse()*255), 0, (int) (v.getVitesse()*255));
	}
	
	public void play() throws VoitureException {
		BufferedImage im = TerrainTools.imageFromTerrain(c.getTerrain());
		Graphics g=im.getGraphics();
		//test orientation
		int i = 0;

		while(i<5000) {
			Commande com = strat.getCommande();
			if (TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
			commandes.add(com);
			this.v.drive(com);
			}
		
			
			else {
				//System.out.println("aaaaaaaaaaaaaaaaaaaa");
				//v.setDirection(v.getDirection().multiplication(-1));
				//v.setPosition(v.getPosition().addition(v.getDirection().multiplication(v.getVitesse())));
			}
				// j'enlÃ¨ve tout le traÃ§age : on le gÃ¨re via le MVC, cf ihm
				/*Trace(im);
				System.out.println(v.getPosition().toString());
				g.setColor(getColor());
				g.drawLine((int)this.v.getPosition().getX(),(int)this.v.getPosition().getY(),(int)this.v.getPosition().getX(),(int)this.v.getPosition().getY());*/
				
				this.update();
				if ((this.v.getPosition().getX()>=0)&&((this.v.getPosition().getX()<c.getWidth()))&&(this.v.getPosition().getY()>=0)&&((this.v.getPosition().getY()<c.getHeight()))&&TerrainTools.charFromTerrain(this.c.getTerrain(v.getPosition()))=='!' && (i!=0) && TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
					/*if ((c.getDirectionArrivee().prodScal(v.getPosition().soustraction(c.getPointDepart()))<=0)) {
						System.out.println("Mauvais côté de la ligne: x="+this.v.getPosition().getX()+" y="+this.v.getPosition().getX()+" prod scal: "+(c.getDirectionArrivee().prodScal(v.getPosition().soustraction(c.getPointDepart()))));
						v.setDirection(c.getDirectionDepart());
						g.setColor(new Color(0,0,0));
						g.drawLine((int)this.v.getPosition().getX(),(int)this.v.getPosition().getY(),(int)this.v.getPosition().getX(),(int)this.v.getPosition().getY());
						continue;
					}*/
					System.out.println("Ligne d'arrivee franchie: "+i+" itérations");
					break;
				} //A décommenter pour le circuit 2_safe.trk
				i++;
				//System.out.println("i: "+i);
			
		}
		System.out.println("nombre d'itération = " + i);
		
		/*try {
           File outputfile = new File("Fin simulation simple.png");
           ImageIO.write(im, "png", outputfile);
        } catch (IOException e) {
           System.out.println("Erreur lors de la sauvegarde");
        }*/
		return;
	}

	@Override
	public void add(UpdateEventListener listener) {
		// TODO Auto-generated method stub
		listeners.add(listener);
		
	}
	
	
	

}
