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
	private ArrayList<Voiture> voitures = new  ArrayList<Voiture>();
	private ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private Circuit c;
	private ArrayList<ArrayList<Commande>> commandes = new ArrayList<ArrayList<Commande>>();
	private ArrayList<UpdateEventListener> listeners = new ArrayList<UpdateEventListener>();

	/**
	 * Instancie un objet Simulation
	 * @param v 
	 * @param strat
	 * @param c
	 */

	public Simulation(Circuit c) {
		this.c = c;
	}

	public Simulation(ArrayList<Voiture> voitures, ArrayList<Strategy> strategies, Circuit c,
			ArrayList<ArrayList<Commande>> commandes, ArrayList<UpdateEventListener> listeners) {
		super();
		this.voitures = voitures;
		this.strategies = strategies;
		this.c = c;
		this.commandes = commandes;
		this.listeners = listeners;
	}
	
	public void addVoitureStrategies(Voiture v, Strategy s) {
		voitures.add(v);
		strategies.add(s);
	}
	public ArrayList<Voiture> getVoitures() {
		return voitures;
	}
	public void setVoitures(ArrayList<Voiture> voitures) {
		this.voitures = voitures;
	}
	public ArrayList<Strategy> getStrategies() {
		return strategies;
	}
	public void setStrategies(ArrayList<Strategy> strategies) {
		this.strategies = strategies;
	}
	public ArrayList<UpdateEventListener> getListeners() {
		return listeners;
	}
	public void setListeners(ArrayList<UpdateEventListener> listeners) {
		this.listeners = listeners;
	}
	public void setC(Circuit c) {
		this.c = c;
	}
	public void setCommandes(ArrayList<ArrayList<Commande>> commandes) {
		this.commandes = commandes;
	}
	public ArrayList<ArrayList<Commande>> getCommandes(){
		return commandes;
	}

	public Circuit getC() {
		return c;
	}
	public void setSimu(Voiture v, Strategy strat) {
		voitures.add(v);
		strategies.add(strat);
	}

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
	

	public void play() throws VoitureException {

		//test orientation
		int i = 0;
		ArrayList<Voiture> varrivee =  new ArrayList<Voiture>();
		while(i<5000) {
			for(Voiture v : voitures) {
				if(i==0){
					commandes.add(new ArrayList<Commande>());
				}
				if(varrivee.size()==voitures.size()) {
					return;
				}
				if(!(varrivee.contains(v))){
					int index = voitures.indexOf(v);
					if(index==0 && i<20) {
						continue;
					}
					Commande com = strategies.get(index).getCommande();
					if (TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
						commandes.get(index).add(com);
						v.drive(com);
					}
				
					
					
					this.update();
					
					if ((v.getPosition().getX()>=0)&&((v.getPosition().getX()<c.getWidth()))&&(v.getPosition().getY()>=0)&&((v.getPosition().getY()<c.getHeight()))&&TerrainTools.charFromTerrain(this.c.getTerrain(v.getPosition()))=='!' && (i!=0) && TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
						/*if ((c.getDirectionArrivee().prodScal(v.getPosition().soustraction(c.getPointDepart()))<=0)) {
						System.out.println("Mauvais côté de la ligne: x="+this.v.getPosition().getX()+" y="+this.v.getPosition().getX()+" prod scal: "+(c.getDirectionArrivee().prodScal(v.getPosition().soustraction(c.getPointDepart()))));
						v.setDirection(c.getDirectionDepart());
						g.setColor(new Color(0,0,0));
						g.drawLine((int)this.v.getPosition().getX(),(int)this.v.getPosition().getY(),(int)this.v.getPosition().getX(),(int)this.v.getPosition().getY());
						continue;
					}*/
						System.out.println("voiture " + index +" : ligne d'arrivee franchie: "+i+" it�rations");
						varrivee.add(v);
						break;
					} //A d�commenter pour le circuit 2_safe.trk
				
				}
			}i++;
				
			
		}
		System.out.println("nombre d'iteration = " + i);
		
		return;
	}
		
		
	public void play() throws VoitureException {
		BufferedImage im = TerrainTools.imageFromTerrain(c.getTerrain());
		Graphics g=im.getGraphics();
		//test orientation
		int i = 0;
		
		while(i<5000) {
			boolean bool=true;
			boolean bool2=false;
			Commande com = strat.getCommande();
			if (TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
				double turn=com.getTurn();
				
				if (((com.getTurn())>v.getMaxTurn()&&turn>=0)||((com.getTurn())<v.getMaxTurn()*-1&&turn<0)) {
					while(turn>=0&&(turn>v.getMaxTurn())) {
						if (com.getAcc()>0) {
			        		turn*=0.5;
			        		bool=false;
			        	}
			        	else {
			        		turn*=0.5;
			        		bool=false;
			        	}
					}
					if (bool==false) {
						if (com.getAcc()>0) {
							commandes.add(new Commande(-1,turn));
							this.v.drive(new Commande(-1,turn));
						}
						else {
							commandes.add(new Commande(com.getAcc(),turn));
							this.v.drive(new Commande(com.getAcc(),turn));
						}
					}
					
					while ((turn<0)&&(turn<v.getMaxTurn()*-1)) {
						//System.out.println("turn = "+turn+"   getMaxTurn= "+v.getMaxTurn());
						if (com.getAcc()>0) {
			        		turn*=0.5;
			        		//System.out.println("2eme version de turn:"+ turn);
			        		bool2=false;
			        		bool=false;
			        	}
			        	else {
			        		turn*=0.5;
			        		bool2=false;
			        		bool=false;
			        	}
					}
					if (bool2==false) {
						if (com.getAcc()>0) {
							commandes.add(new Commande(-1,turn));
							this.v.drive(new Commande(-1,turn));
						}
						else {
							commandes.add(new Commande(com.getAcc(),turn));
							this.v.drive(new Commande(com.getAcc(),turn));
						}
					}
					
			
		        }
				
				if (bool==true) {
					commandes.add(com);
					this.v.drive(com);
				}
				
			}
		
			
			else {
				//System.out.println("aaaaaaaaaaaaaaaaaaaa");
				//v.setDirection(v.getDirection().multiplication(-1));
				//v.setPosition(v.getPosition().addition(v.getDirection().multiplication(v.getVitesse())));
			}
				// j'enlève tout le traçage : on le gère via le MVC, cf ihm
				/*Trace(im);
				System.out.println(v.getPosition().toString());
				g.setColor(getColor());
				g.drawLine((int)this.v.getPosition().getX(),(int)this.v.getPosition().getY(),(int)this.v.getPosition().getX(),(int)this.v.getPosition().getY());*/
				
				this.update();
				if ((this.v.getPosition().getX()>=0)&&((this.v.getPosition().getX()<c.getWidth()))&&(this.v.getPosition().getY()>=0)&&((this.v.getPosition().getY()<c.getHeight()))&&TerrainTools.charFromTerrain(this.c.getTerrain(v.getPosition()))=='!' && (i!=0) && TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
						System.out.println("voiture " + index +" : ligne d'arrivee franchie: "+i+" it�rations");
						varrivee.add(v);
						break;
					}
				
				}
				
			}
			i++;
				
			
		}
		System.out.println("nombre d'iteration = " + i);
		
		return;
	}



	@Override
	public void add(UpdateEventListener listener) {
		// TODO Auto-generated method stub
		listeners.add(listener);
	}


	
	
	

}
