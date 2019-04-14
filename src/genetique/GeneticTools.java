package genetique;

import java.awt.Color;
import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import circuit.*;
import controleur.IHMSwing;
import exceptions.ArriveeException;
import exceptions.NeuroneException;
import exceptions.NotMovingException;
import simulation.Simulation;
import strategy.*;
import voiture.*;
import vue.Fenetre;
import observeurs.*;

public class GeneticTools {
	
	public static ArrayList<Genome> listToArrayList(List<Genome> list){
		ArrayList<Genome> res = new ArrayList<Genome>();
		for(Genome g : list) {
			res.add(g);
		}
		return res;
	}
	
	public static List<Genome> arrayListtoList(ArrayList<Genome> list){
		List<Genome> res = new ArrayList<Genome>();
		for(Genome g : list) {
			res.add(g);
		}
		return res;
	}
	
	public static void saveGenome(Genome g, String filename){
        try {
        		String struct = filename + " struct";
        		String poids = filename + " poids";
                DataOutputStream os1 = new DataOutputStream(new FileOutputStream(struct));
                DataOutputStream os2 = new DataOutputStream(new FileOutputStream(poids));
                for(int i : g.getStructure()){
                        os1.writeInt(i);
                }
                for(int i = 0;i<g.getListe_poids().size();i++) {
                	for(int j = 0; j<g.getListe_poids().get(i).size();j++) {
                		for(int k = 0;k<g.getListe_poids().get(i).get(j).size();k++) {
                			os2.writeDouble(g.getListe_poids().get(i).get(j).get(k));
                		}
                	}
                }
                
                for(int i = 0;i<g.getListe_biais().size();i++) {
                	for(int j= 0;j<g.getListe_biais().get(i).size();j++) {
                		os2.writeDouble(g.getListe_biais().get(i).get(j));
                	}
                }
                os1.close();
                os2.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
	}
	
	public static void batchVisualization(ArrayList<ArrayList<Commande>> com, Circuit c) {
		/*Simulation simu = new Simulation(c);
		IHMSwing ihm = new IHMSwing();
		ihm.add(simu);
		ihm.addCircuit(c);
		for(ArrayList<Commande> l_com : com) {
			Voiture v = VoitureFactory.build(c);
			Strategy s = new StrategyListeCommande(l_com);
			simu.addVoitureStrategies(v, s);
			simu.setSleep(2);
			TrajectoireObserveur t = new TrajectoireObserveur(v);
			VoitureObserveur vobs = new VoitureObserveur(v,"voiture verte.png");
			int r = (int) (Math.random()*254);
			int b = (int) (Math.random()*254);
			int g = (int) (Math.random()*254);
			Color col = new Color(r,g,b);
//			t.setColor(col);
			ihm.add(t);
			ihm.add(vobs);
			
		}
		
		simu.add(ihm);
		
		Fenetre fen = new Fenetre(ihm, "test perceptron");
		ihm.setPreferredSize(new Dimension(768,1024));
		fen.getContentPane().add(ihm);
		fen.pack();
	            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);

		try {
			simu.play();
		} catch (VoitureException | ArriveeException | NotMovingException | NeuroneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static void saveTest(ArrayList<Integer> entiers, ArrayList<Double> reels, String filename) {
		try {
            DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));
            for(int n : entiers) {
            	os.writeInt(n);
            }
            for(double d : reels) {
            	os.writeDouble(d);
            }os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	
	public static ArrayList<Double> loadTest(String filename) throws IOException{
        ArrayList<Double> struct = new ArrayList<Double>();

		try {
			DataInputStream os = new DataInputStream(new FileInputStream(filename));
	        double d;
	        while(true){ // on attend la fin de fichier
	        	  d = os.readDouble();
	        	  struct.add(d);
	          }
          
		}catch (EOFException e) {
			e.printStackTrace();
            return struct;
        }
		
	}
	
	public static Genome loadGenome(String n_struct, String n_poids) throws IOException {
		Genome g = null;
		ArrayList<Integer> struct = new ArrayList<Integer>();
        try {
                DataInputStream os = new DataInputStream(new FileInputStream(n_struct));
                int i;
                while(true) {
                	i = os.readInt();
                	struct.add(i);
                }

        } catch (EOFException e){
                try {
                	DataInputStream os = new DataInputStream(new FileInputStream(n_poids));
                	ArrayList<ArrayList<ArrayList<Double>>> poids = new ArrayList<ArrayList<ArrayList<Double>>>();
                	for(int i = 0;i<struct.size()-1;i++) {
                		ArrayList<ArrayList<Double>> poids_couche = new ArrayList<ArrayList<Double>>();
                		for(int j = 0;j<struct.get(i);j++) {
                			ArrayList<Double> poids_neurone = new ArrayList<Double>();
                			for(int k = 0;k<struct.get(i+1);k++) {
                				double d = os.readDouble();
                				poids_neurone.add(d);
                			}
                			poids_couche.add(poids_neurone);
                		}
                		poids.add(poids_couche);
                	}
                	
                	ArrayList<ArrayList<Double>> biais = new ArrayList<ArrayList<Double>>();
                	for(int i = 1;i<struct.size();i++) {
                		ArrayList<Double> biais_couche = new ArrayList<Double>();
                		for(int j = 0;j<struct.get(i);j++) {
                			double d = os.readDouble();
                			biais_couche.add(d);
                		}
                		biais.add(biais_couche);
                	}
                	
                	g = new Genome(poids,biais);
                	
                }catch(EOFException e2){
                	return g;
                }
        }return g;
	}
}
