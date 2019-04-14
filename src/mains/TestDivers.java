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
		Circuit c = CircuitFactoryFromFile.build("2_safe.trk");
		Dijkstra d = new Dijkstra(c);
		d.compute();
		Voiture v = VoitureFactory.build(c);
		Radar rad = new RadarDijkstra(v,c,12,d);
		Strategy strat = new StrategyRadarSimple(rad);
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
		simu.setIsRunning(true);

		try {
			//simu.setSleep(0);
			simu.play();
			System.out.println(simu.getCommandes().get(0).size());
			Simulation.saveListeCommande(simu.getCommandes().get(0), "circuit 2 dijkstra");
		} catch (VoitureException | ArriveeException | NotMovingException e) {
			// TODO Auto-generated catch block
			//Simulation.saveListeCommande(simu.getCommandes().get(0), "circuit 1 dijkstra");
			e.printStackTrace();
		} catch (NeuroneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
