package mains;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import algo.Dijkstra;
import genetique.*;
import observeurs.RadarObserveur;
import observeurs.TrajectoireObserveur;
import simulation.Simulation;
import strategy.Strategy;
import strategy.StrategyListeCommande;
import strategy.StrategyPrudente;
import strategy.StrategyRadarSimple;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import vue.Fenetre;
import circuit.*;
import controleur.IHMSwing;
import exceptions.ArriveeException;
import exceptions.NeuroneException;
import exceptions.NotMovingException;
import algo.*;
public class TestDivers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("aufeu_modifie.trk");
		Dijkstra d = new Dijkstra(c);
		d.compute();
		Voiture v = VoitureFactory.build(c);
		Radar rad = new RadarDijkstra(v,c,12,d);
		Strategy strat = new StrategyPrudente(rad,v);
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
		IHMSwing ihm = new IHMSwing();
		//ihm.add(new VoitureObserveur(v));
		ihm.add(new TrajectoireObserveur(v));
		ihm.add(new RadarObserveur(rad));
		ihm.addCircuit(c);
		simu.add(ihm);
		Fenetre fen = new Fenetre(ihm, "test affichages");
		ihm.setPreferredSize(new Dimension(768,1024));
		fen.getContentPane().add(ihm);
		fen.pack();
	            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		try {
			simu.play();
			Simulation.saveListeCommande(simu.getCommandes().get(0), "au feu modif");
		} catch (VoitureException | ArriveeException | NotMovingException e) {
			// TODO Auto-generated catch block
			Simulation.saveListeCommande(simu.getCommandes().get(0), "au feu modif");
			e.printStackTrace();
		} catch (NeuroneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fen.dispose();
		fen = new Fenetre(ihm, "test affichages");
		ihm.setPreferredSize(new Dimension(768,1024));
		fen.getContentPane().add(ihm);
		fen.pack();
	            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		Strategy strat2 = null;
		try {
			strat2 = new StrategyListeCommande(Simulation.loadListeCommande("au feu modif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Simulation simu2 = new Simulation(c);
		simu2.addVoitureStrategies(v, strat2);
		simu2.add(ihm);
		ihm.add(simu2);
		try {
			simu2.setSleep(3);
			simu2.play();
		
		} catch (VoitureException | ArriveeException | NotMovingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NeuroneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
