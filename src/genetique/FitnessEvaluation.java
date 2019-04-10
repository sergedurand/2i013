package genetique;

import circuit.*;
import controleur.IHMSwing;
import exceptions.ArriveeException;
import exceptions.NeuroneException;
import exceptions.NotMovingException;
import geometrie.Vecteur;
import simulation.Simulation;
import observeurs.*;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import algo.*;
import strategy.*;
import voiture.*;
import vue.Fenetre;

public class FitnessEvaluation {
	private Circuit c;
	private Dijkstra d;
	private Genome g;
	private boolean finish;
	private Radar r;
	public FitnessEvaluation(Circuit c, Dijkstra d, Genome g) {
		super();
		this.c = c;
		this.d = d;
		this.g = g;
	}
	
	public Genome getG() {
		return g;
	}

	public void evaluate(boolean dijkstra,int nb_param) throws NeuroneException {
		Voiture v = VoitureFactory.build(c);
		int taille_entree = g.getListe_poids().get(0).size();
		if(dijkstra) {
			this.r = new RadarDijkstra(v,c,taille_entree-nb_param,d);
		}else {
			this.r = new RadarImpl(v,c,taille_entree-nb_param);
		}
					
		Strategy strat = new StrategyPerceptron(g,r,v,d);
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
		double score = 0;
		
		try {
			simu.play();
			score = simu.getCommandes().get(0).size();
			this.g.setCommandes(simu.getCommandes().get(0));
			this.getG().setFinish(true);
		} catch (VoitureException e) {
			// TODO Auto-generated catch block
			score = simu.getCommandes().get(0).size();
			Vecteur position = simu.getVoitures().get(0).getPosition();
			score += this.d.getDist()[(int) position.getX()][(int) position.getY()];
			score += 1000000;
			this.g.setFinish(false);
			//e.printStackTrace();
			
		}catch (ArriveeException e2) {
			this.g.setFinish(false);
			score = 10000000;
			//e2.printStackTrace();
		}catch(NotMovingException e3) {
			this.g.setFinish(false);
			score = 10000000;
		}
		
		g.setScore(-score);
	}

	public void evaluateWithDisplay() throws NeuroneException {
		Voiture v = VoitureFactory.build(c);
		int taille_entree = g.getListe_poids().get(0).size();
		if(taille_entree%2 == 0) {
			this.r = new RadarImpl(v,c,taille_entree);
		}else {
			this.r = new RadarImpl(v,c,taille_entree-1);
		}
		
		Strategy strat = new StrategyPerceptron(g,r,v,d);
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
		double score = 0;
		IHMSwing ihm = new IHMSwing();
		//ihm.add(new VoitureObserveur(v));
		ihm.add(new RadarObserveur(r));
		ihm.add(new TrajectoireObserveur(v));
		ihm.addCircuit(c);
		simu.add(ihm);
		Fenetre fen = new Fenetre(ihm, "test perceptron");
		ihm.setPreferredSize(new Dimension(768,1024));
		fen.getContentPane().add(ihm);
		fen.pack();
	            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		
		try {
			simu.play();
			score = simu.getCommandes().get(0).size();
			this.finish = true;
		} catch (VoitureException e) {
			// TODO Auto-generated catch block
			score = simu.getCommandes().get(0).size();
			Vecteur position = simu.getVoitures().get(0).getPosition();
			score += this.d.getDist()[(int) position.getX()][(int) position.getY()];
			score += 1000000;
			this.finish = false;
			e.printStackTrace();
			
		}catch (ArriveeException e2) {
			score = 10000000;
			this.finish = false;
			e2.printStackTrace();
		}catch(NotMovingException e3) {
			this.finish = false;
			score = 10000000;
		}
		
		g.setScore(-score);		
	}
	public boolean isFinish() {
		return finish;
	}
	
}
