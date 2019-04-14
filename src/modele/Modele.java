package modele;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import algo.*;
import circuit.*;
import controleur.IHMSwing;
import exceptions.ArriveeException;
import exceptions.NeuroneException;
import exceptions.NotMovingException;
import genetique.CrossOperator;
import genetique.GeneticAlgorithm;
import genetique.Genome;
import genetique.GenomeGeneratorPerceptron;
import genetique.MutationOperator;
import observeurs.*;
import simulation.*;
import strategy.*;
import voiture.*;
import vue.Fenetre;


public class Modele {
	private Radar rad;
	private Voiture v;
	private Circuit c;
	private Strategy strat;
	private IHMSwing ihm;
	private Simulation simu;
	private Fenetre fen;
	private String nomcircuit;
	private String nomstrat;
	private int nbfaisceaux;
	private String couleurvoiture;
	private int population;
	private int generation;
	private int mutation;
	//Controleur con;
	
	public Modele(Radar rad, Voiture v, Circuit c,Strategy strat,String nomcircuit, String nomstrat,int nbfaisceaux,String couleur,int p,int g,int m) {
		this.rad = rad;
		this.v = v;
		this.c = c;
		this.strat=strat;
		this.nomcircuit=nomcircuit;
		this.nomstrat=nomstrat;
		this.nbfaisceaux=nbfaisceaux;
		this.couleurvoiture=couleur;
		population=p;
		generation=g;
		mutation=m;
	}
	
	
	
	
	public Voiture getV() {
		return v;
	}




	public Circuit getC() {
		return c;
	}



	public void setSimu(boolean listecommande) {
		ihm = new IHMSwing(this);
		ihm.add(new VoitureObserveur(v,couleurvoiture));
		if (listecommande==false) {
			ihm.add(new RadarObserveur(rad));
		}
		ihm.add(new TrajectoireObserveur(v));
		ihm.addCircuit(c);
		//ihm.add(new CircuitObserveur(c));
		simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
		simu.add(ihm);
		ihm.add(simu);
	}
	
	public void openFenetre() {
		fen=new Fenetre(this.getIHM(),"test","test",this.getIHM(),nbfaisceaux,couleurvoiture);
	}
	
	public void closeFenetre() {
		if (fen!=null) {
			fen.dispose();
		}
	}
	
	
	public void setCircuit(String nom) {
		c = CircuitFactoryFromFile.build(nom);
		Voiture v = VoitureFactory.build(c);
		if (rad instanceof RadarImpl) {
		//	System.out.println(strat.getClass());
			rad=new RadarImpl(v,c,nbfaisceaux);
			strat = new StrategyRadarSimple(rad);
		}
		else {
			Voiture v2 = VoitureFactory.build(c);
			Dijkstra dijk = new Dijkstra(c);
			dijk.compute();
			Radar rad = new RadarDijkstra(v,c,nbfaisceaux,dijk);
			if (strat instanceof StrategyRadarSimple) {
				strat=new StrategyRadarSimple(rad);	
			}
			if (strat instanceof StrategyPrudente) {
				strat=new StrategyPrudente(rad,v2);	
			}
		}

		Modele m=new Modele(rad, v, c, strat,nom,nomstrat,nbfaisceaux,couleurvoiture,population,generation,mutation);
		m.setSimu(false);
		closeFenetre();
		nomcircuit=nom;
		fen=new Fenetre(m.getIHM(),nom,nomstrat,m.getIHM(),nbfaisceaux,couleurvoiture);
		//System.out.println("On a changé de circuit, nomcircuit: "+nomcircuit+" ,nomstrat: "+nomstrat);
	}
	
	public String getStrategie() {
		String s="";
		if (strat instanceof StrategyRadarSimple) {
			if (rad instanceof RadarDijkstra) {
				s="Dijkstra";
			}
			else {
				s="Simple";
			}
		}
		if (strat instanceof StrategyPrudente) {
				s="Prudente";
		}

		
		return s;
	}
	
	public Strategy setStrategie(String nom,boolean simu) {
		//System.out.println("Je veux changer de stratégie. Je suis sur le circuit "+nomcircuit);
		if (nom.compareTo("Simple")==0) {
			Voiture v = VoitureFactory.build(c);
			rad=new RadarImpl(v,c,nbfaisceaux);
			strat = new StrategyRadarSimple(rad);
			Modele m=new Modele(rad, v, c, strat,nomcircuit,nom,nbfaisceaux,couleurvoiture,population,generation,mutation);
			m.setSimu(false);
			closeFenetre();
			fen=new Fenetre(m.getIHM(),nomcircuit,nom,m.getIHM(),nbfaisceaux,couleurvoiture);
			return strat;
		}
		
		if (nom.compareTo("Dijkstra")==0) {
			Voiture v = VoitureFactory.build(c);
			Dijkstra dijk = new Dijkstra(c);
			dijk.compute();
			rad = new RadarDijkstra(v,c,nbfaisceaux,dijk);
			strat=new StrategyRadarSimple(rad);
			Modele m=new Modele(rad, v, c, strat,nomcircuit,nom,nbfaisceaux,couleurvoiture,population,generation,mutation);
			m.setSimu(false);
			closeFenetre();
			fen=new Fenetre(m.getIHM(),nomcircuit,nom,m.getIHM(),nbfaisceaux,couleurvoiture);	
			return strat;
		}
		
		if (nom.compareTo("Prudente")==0) {
			Voiture v = VoitureFactory.build(c);
			Dijkstra dijk = new Dijkstra(c);
			dijk.compute();
			rad = new RadarDijkstra(v,c,nbfaisceaux,dijk);
			strat=new StrategyPrudente(rad,v);
			Modele m=new Modele(rad, v, c, strat,nomcircuit,nom,nbfaisceaux,couleurvoiture,population,generation,mutation);
			m.setSimu(false);
			closeFenetre();
			fen=new Fenetre(m.getIHM(),nomcircuit,nom,m.getIHM(),nbfaisceaux,couleurvoiture);	
			return strat;
		}
		//nomstrat=nom;
		return null;
		
	}
	
	
	
	public IHMSwing getIHM() {
		return ihm;
	}
	
	public void listecommandes(File f) {
		//System.out.println("Je tente la stratégie listecommandes avec le fichier: "+f.getPath());
		Voiture v = VoitureFactory.build(c);
		StrategyListeCommande st1=null;
		try {
			st1 = new StrategyListeCommande(Simulation.loadListeCommande(f.getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Modele m=new Modele(rad, v, c, st1,nomcircuit,nomstrat,nbfaisceaux,couleurvoiture,population,generation,mutation);
		m.setSimu(true);
		closeFenetre();
		fen=new Fenetre(m.getIHM(),nomcircuit,nomstrat,m.getIHM(),nbfaisceaux,couleurvoiture);
		
	}
	
	public void setNbfaisceaux(int nb) {
		this.nbfaisceaux=nb;
		//System.out.println("La value de nbfaisceaux a été set a "+nbfaisceaux+ " alors que nb vaut "+nb);
		setStrategie(nomstrat,false);
	}
	
	public void setCouleurVoiture(String nom) {
		this.couleurvoiture=nom;
		setSimu(false);
		setStrategie(nomstrat,false);
		//System.out.println("Stratégie actuelle: "+nomstrat);
	}
	
	public void restart() {
		setCircuit(nomcircuit);
	}
	
	public void startSimu() {
		//System.out.println(rad.getClass());
	//	System.out.println("Je commence la simulation avec "+nbfaisceaux+" faisceaux");
		/*c = CircuitFactoryFromFile.build(nomcircuit);
		Voiture v = VoitureFactory.build(c);
		RadarImpl rad=new RadarImpl(v,c,nbfaisceaux);
		StrategyRadarSimple strat = new StrategyRadarSimple(rad);
		Modele m=new Modele(rad, v, c, strat,nomcircuit,nomstrat,nbfaisceaux);
		m.setSimu(false);*/
		//closeFenetre();
		//fen=new Fenetre(m.getIHM(),nomcircuit,nomstrat,m.getIHM(),nbfaisceaux);
		
		try {
		//	System.out.println("try");
			simu.isRunning=true;
			simu.play();
		}
		catch (NeuroneException e) {
			e.printStackTrace();
		}
		catch (VoitureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ArriveeException e2) {
			e2.printStackTrace();
		} catch (NotMovingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    catch (ConcurrentModificationException e) {
		
	}
	}
	
	public void genetique() {
		genetiqueInformation();
		Voiture v = VoitureFactory.build(c);
		Dijkstra dijk = new Dijkstra(c);
		dijk.compute();
		ArrayList<Integer> struct = new ArrayList<Integer>();
		struct.add(8);
		struct.add(8);
		struct.add(2);
		GenomeGeneratorPerceptron gen = new GenomeGeneratorPerceptron(struct);
		CrossOperator cop = new CrossOperator();
		MutationOperator mut = new MutationOperator();

		GeneticAlgorithm algo = new GeneticAlgorithm(mut,cop,gen,population,struct,c,dijk);
		try {
			Genome best = algo.optimize(generation,"Resultat genetique",mutation,false,50000,2);
		} catch (NeuroneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StrategyListeCommande st1=null;
		try {
			st1 = new StrategyListeCommande(Simulation.loadListeCommande("Resultat genetique"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Modele m=new Modele(rad, v, c, st1,nomcircuit,nomstrat,nbfaisceaux,couleurvoiture,population,generation,mutation);
		m.setSimu(true);
		closeFenetre();
		genetiqueCharge();
		fen=new Fenetre(m.getIHM(),nomcircuit,nomstrat,m.getIHM(),nbfaisceaux,couleurvoiture);
		
		
		
		
	}
	
	public void setPopulation(int k) {
		population=k;
	}
	
	public void setGeneration(int k) {
		generation=k;
	}
	
	public void setMutation(int k) {
		mutation=k;
	}
	
	
	
	public void stopSimu() {
		simu.isRunning=false;
	}
	
	public void circuitCharge() {
		Object[] options = {"Ok"};
		JOptionPane.showOptionDialog(fen,
		"La course a ete chargee. Veuillez appuyer sur Start pour lancer la simulation.",
		"Information",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,     //do not use a custom Icon
		options,  //the titles of buttons
		options[0]); //default button title
	}
	
	public void genetiqueCharge() {
		Object[] options = {"Ok"};
		JOptionPane.showOptionDialog(fen,
		"L'algorithme génétique s'est terminé. Veuillez appuyer sur Start pour afficher le résultat.",
		"Information",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,     //do not use a custom Icon
		options,  //the titles of buttons
		options[0]); //default button title
	}
	
	public void genetiqueInformation() {
		Object[] options = {"Ok"};
		JOptionPane.showOptionDialog(fen,
		"L'algorithme génétique est un algorithme relativement long.\nLe MVC va se mettre en pause jusqu'à la fin de l'exécution de l'algorithme.\nVous serez ensuite invitée à voir le résultat.\nEn attendant, vous pouvez suivre l'évolution de l'algorithme sur la console.",
		"Attention!",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,     //do not use a custom Icon
		options,  //the titles of buttons
		options[0]); //default button title
	}
	
}