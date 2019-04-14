package mains;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JFrame;

import algo.Dijkstra;
import algo.Radar;
import algo.RadarImpl;
import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import perceptron.*;
import simulation.Simulation;
import strategy.Strategy;
import strategy.StrategyLigneDroite;
import strategy.StrategyListeCommande;
import strategy.StrategyPerceptron;
import strategy.StrategyRadarSimple;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import voiture.VoitureImpl;
import genetique.*;
import simulation.*;
import observeurs.*;
import controleur.*;
import exceptions.ArriveeException;
import exceptions.NeuroneException;
import exceptions.NotMovingException;
import vue.*;

public class TestReseauNeurones {

	public static void main(String[] args) throws NeuroneException, IOException {

		/*
		ArrayList<String> circuits = new ArrayList<String>();
		ArrayList<Double> target = new ArrayList<Double>();
		circuits.add("1_safe.trk");
		target.add(2700.);
		circuits.add("2_safe.trk");
		target.add(3400.);
		circuits.add("3_safe.trk");
		target.add(4500.);
		circuits.add("4_safe.trk");
		target.add(4100.);
		circuits.add("5_safe.trk");
		target.add(2050.);
		circuits.add("6_safe.trk");
		target.add(2000.);
		circuits.add("7_safe.trk");
		target.add(1200.);
		circuits.add("8_safe.trk");
		target.add(2700.);
		circuits.add("aufeu.trk");
		target.add(3300.);
		circuits.add("bond_safe.trk");
		target.add(1700.);
		circuits.add("Een2.trk");
		target.add(2000.);
		circuits.add("labymod.trk");
		target.add(8000.);
		circuits.add("labyperso.trk");
		target.add(8500.);
		circuits.add("perso.trk");
		target.add(8600.);
		circuits.add("t2009.trk");
		target.add(7150.);
		circuits.add("t260_safe.trk");
		target.add(2400.);
		for(int i = 0;i<circuits.size();i++) {
			String s = circuits.get(i);
			Circuit c1 = CircuitFactoryFromFile.build(s);
			Dijkstra d1 = new Dijkstra(c1);
			d1.compute();
			ArrayList<Integer> struct = new ArrayList<Integer>();
			struct.add(8);
			struct.add(8);
			struct.add(8);
			struct.add(2);
			GenomeGeneratorPerceptron gen = new GenomeGeneratorPerceptron(struct);
			CrossOperator cop = new CrossOperator();
			MutationOperator mut = new MutationOperator();
			double objectif = target.get(i);
			if(i<6) {//circuits "facile"
				GeneticAlgorithm algo = new GeneticAlgorithm(mut,cop,gen,1000,struct,c1,d1);
				Genome best = algo.optimize(2,s,2,false,objectif,2);
				continue;
			}
			if(i<8) {
				GeneticAlgorithm algo = new GeneticAlgorithm(mut,cop,gen,120,struct,c1,d1);
				Genome best = algo.optimize(100,s+ " pop 120 gen 100",2,true,objectif,2);
			}
		}*/
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");

		Dijkstra d = new Dijkstra(c);
		d.compute();
		ArrayList<Integer> struct = new ArrayList<Integer>();
		struct.add(8);
		struct.add(8);
//		struct.add(6);
//		struct.add(6);
//		struct.add(6);
//		struct.add(6);
//		struct.add(6);

//		struct.add(4);
//		struct.add(4);
//		struct.add(8);
		struct.add(2);
		GenomeGeneratorPerceptron gen = new GenomeGeneratorPerceptron(struct);
		CrossOperator cop = new CrossOperator();
		MutationOperator mut = new MutationOperator();

		GeneticAlgorithm algo = new GeneticAlgorithm(mut,cop,gen,24,struct,c,d);
		Genome best = algo.optimize(32,"1 safe",4,false,100,2);
//		GeneticTools.saveGenome(best, "best test");
//		Genome g_load = GeneticTools.loadGenome("genome circuit 1 struct", "genome circuit 1 poids");
//		System.out.println(g_load.toString());
//		
//		Genome g_load2 = GeneticTools.loadGenome("best test struct", "best test poids");
//		System.out.println(g_load2.toString());
//		FitnessEvaluation fit = new FitnessEvaluation(c, d, g_load);
//		fit.evaluateWithDisplay(false, 2);
//		Voiture v = VoitureFactory.build(c);
//		Radar r = new RadarImpl(v,c,g_load.getStructure().get(0)-1);
//		Perceptron p = new Perceptron(g_load.getListe_poids(),g_load.getListe_biais());
//		Strategy strat = new StrategyPerceptron(p, r, g_load);
		ArrayList<Commande> best_commandes = null;
		try {
			best_commandes = Simulation.loadListeCommande("5 safe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Voiture v = VoitureFactory.build(c);
		Strategy strat = new StrategyListeCommande(best_commandes);
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
/*		IHMSwing ihm = new IHMSwing();
		//ihm.add(new VoitureObserveur(v));
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
			simu.setSleep(3);
		} catch (VoitureException | ArriveeException | NotMovingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}

}

