package mains;
import java.awt.Dimension;
import java.util.ArrayList;

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
import strategy.StrategyPerceptron;
import strategy.StrategyRadarSimple;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import genetique.*;
import simulation.*;
import observeurs.*;
import controleur.*;
import exceptions.ArriveeException;
import vue.*;

public class TestReseauNeurones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Neurone n1 = new Neurone(1);
		n1.setRandomPoidsSortant(5, -10, 10);
		//System.out.println(n1.toString());
		
		ArrayList<Integer> structurebasique = new ArrayList<Integer>();
		structurebasique.add(4);
		structurebasique.add(3);
		structurebasique.add(3);
		structurebasique.add(3);
		structurebasique.add(2);
		
		Perceptron perceptron = new Perceptron(structurebasique);
//		//System.out.println(perceptron.toString());
		perceptron.setRandomWeightsBias(-1, 1);
//		//System.out.println(perceptron.toString());
		ArrayList<Double> valeur_entree = new ArrayList<Double>();
		valeur_entree.add(20.);
		valeur_entree.add(150.2);
		valeur_entree.add(50.4);
		perceptron.getEntree().setValues(valeur_entree);
//		
//		ArrayList<Double> res = perceptron.getResultat();
//		//System.out.println(res.toString());
//		ArrayList<ArrayList<ArrayList<Double>>> lpoids = new ArrayList<ArrayList<ArrayList<Double>>>();
//		ArrayList<ArrayList<Double>> biais = new ArrayList<ArrayList<Double>>();
//		//Premiere couche de neurone:
//		ArrayList<ArrayList<Double>> couche1 = new ArrayList<ArrayList<Double>>();
//		for(int i = 0;i<3;i++) {
//			ArrayList<Double> neurone = new ArrayList<Double>();
//			for(int j = 0;j<4;j++) {
//				neurone.add(0.5);
//			}
//			couche1.add(neurone);
//		}
//		lpoids.add(couche1);
//		//2e couche
//		ArrayList<ArrayList<Double>> couche2 = new ArrayList<ArrayList<Double>>();
//		ArrayList<Double> biais2 = new ArrayList<Double>();
//		for(int i = 0;i<4;i++) {
//			ArrayList<Double> neurone = new ArrayList<Double>();
//			biais2.add(0.8);
//			for(int j = 0;j<2;j++) {
//				neurone.add(0.8);
//			}
//			couche2.add(neurone);
//		}
//		lpoids.add(couche2);
//		biais.add(biais2);
//		ArrayList<Double> biais_sortie = new ArrayList<Double>();
//		biais_sortie.add(0.3);
//		biais_sortie.add(0.3);

//		Perceptron p2 = new Perceptron(lpoids,biais);
		
		GenomeGeneratorPerceptron gen = new GenomeGeneratorPerceptron(structurebasique);
		Genome g1 = gen.getRandomGenome(-1, 1);
		Perceptron p2 = new Perceptron(g1.getListe_poids(),g1.getListe_biais());
		
		Genome g2 = gen.getRandomGenome(-1, 1);
		CrossOperator cOp = new CrossOperator();
		
		
		Genome g3 = cOp.cross(g1, g2, 0.5);
		
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");
		Voiture v = VoitureFactory.build(c);
		Dijkstra d = new Dijkstra(c);
		d.compute();
		Radar r = new RadarImpl(v,c,3);
		StrategyPerceptron strat = new StrategyPerceptron(g3,r);
		Strategy strat2 = new StrategyLigneDroite();
		Strategy strat3 = new StrategyRadarSimple(r);
		//System.out.println(strat.getP());
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat3);


//	IHMSwing ihm = new IHMSwing();
////		//ihm.add(new VoitureObserveur(v));
//		ihm.add(new RadarObserveur(r));
//		ihm.add(new TrajectoireObserveur(v));
//		ihm.addCircuit(c);
//		simu.add(ihm);
//		Fenetre fen = new Fenetre(ihm, "test perceptron");
//		ihm.setPreferredSize(new Dimension(768,1024));
//		fen.getContentPane().add(ihm);
//		fen.pack();
//                fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		fen.setVisible(true);
		
		FitnessEvaluation fit = new FitnessEvaluation(c,d,g3);
		fit.evaluate();
//		
//		try {
//			simu.play();
//		} catch (VoitureException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (ArriveeException e2) {
//			e2.printStackTrace();
//		}
//		
		System.out.println("on continue ?");
		


	}

}
