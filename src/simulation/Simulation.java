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
import exceptions.ArriveeException;
import exceptions.NeuroneException;
import exceptions.NotMovingException;
import geometrie.Vecteur;
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
	private int sleep = 2;

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

	
	
	
	
	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	public void update() {
		for(UpdateEventListener e: listeners) {
				if(e!=null) {
					e.manageUpdate(this.sleep);
				}
		}
			
	}
	

	public void play() throws VoitureException, ArriveeException, NotMovingException, NeuroneException {

		//test orientation
		Vecteur pos_depart = this.getC().getPointDepart();
		int i = 0;
		ArrayList<Voiture> varrivee =  new ArrayList<Voiture>();
		//to test if the car moved
		ArrayList<ArrayList<Vecteur>> positions = new ArrayList<ArrayList<Vecteur>>();
		for(Voiture voit : this.getVoitures()) {
			ArrayList<Vecteur> pos = new ArrayList<Vecteur>();
			pos.add(voit.getPosition());
			positions.add(pos);
		}
		
		while(i<100000) {
			boolean bool=true;
			boolean bool2=false;
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
					Strategy s_courant = this.getStrategies().get(index);
					Commande com = null;
					
					if(s_courant instanceof StrategyPerceptron) {
						if(i>1 && i%500==0) {
							ArrayList<Vecteur> pos_voit = positions.get(index);
							if(this.getVoitures().get(index).getPosition().getDistance(pos_voit.get(positions.size()-1)) < 50) {
								throw new NotMovingException("la voiture fait du surplace");
							}
							pos_voit.add(this.getVoitures().get(index).getPosition());
						}
					}
					
					if (TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
						if (strategies.get(index) instanceof StrategyPrudente) {
							StrategyPrudente strat=(StrategyPrudente)strategies.get(index);
							strat.setVoiture(v);
							com=strat.getCommande();
							v.drive(com);
							strat.setVoiture(v);
						}
						
						if (strategies.get(index) instanceof StrategyPoint) {
							StrategyPoint strat=(StrategyPoint)strategies.get(index);
							strat.setVoiture(v);
							com=strat.getCommande();
							v.tryToDrive(com,commandes.get(0),bool,bool2);
							strat.setVoiture(v);
						}
						
						
						else {
							v.tryToDrive(strategies.get(index).getCommande(),commandes.get(0),bool,bool2);
						}
						
					}else {
						throw new VoitureException("portion de terrain non roulable");
					}
				
					
					
					this.update();
					
					if ((v.getPosition().getX()>=0)&&((v.getPosition().getX()<c.getWidth()))&&(v.getPosition().getY()>=0)&&((v.getPosition().getY()<c.getHeight()))&&TerrainTools.charFromTerrain(this.c.getTerrain(v.getPosition()))=='!' && (i!=0) && TerrainTools.isRunnable(this.c.getTerrain(v.getPosition()))) {
//						if ((c.getDirectionArrivee().prodScal(v.getPosition().soustraction(c.getPointDepart()))<=0)) {
//							throw new ArriveeException("arrivee franchie dans le mauvais sens !");
//						}
						if((v.getDirection().angle(c.getDirectionArrivee()) < (Math.PI/2.) && v.getDirection().angle(c.getDirectionArrivee())> (Math.PI)/(-2.))) {
							//System.out.println("voiture " + index +" : ligne d'arrivee franchie: "+i+" it�rations");
							varrivee.add(v);
							break;
						}else {
//							saveListeCommande(getCommandes().get(index), "au feu");
							throw new ArriveeException("arrivee franchie dans le mauvais sens !");
						}
					}
					
				
				}
			}i++;
				
			
		}
		if(i>29900) {
			throw new VoitureException("la voiture a depasse 29900 iterations");

		}
		
		//System.out.println("nombre d'iteration = " + i);
		return;
	}
		
		
	



	@Override
	public void add(UpdateEventListener listener) {
		// TODO Auto-generated method stub
		listeners.add(listener);
	}



	
	
	

}
