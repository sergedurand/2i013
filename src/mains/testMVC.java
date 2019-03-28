package mains;
import terrain.*;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import vue.Fenetre;
import circuit.*;
import controleur.IHMSwing;
import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import simulation.*;
import algo.*;
import observeurs.*;
import strategy.Strategy;
import strategy.StrategyRadarSimple;

public class testMVC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("2_safe.trk");
		Voiture v = VoitureFactory.build(c);

		Dijkstra dijk = new Dijkstra(c);
		Radar rad = new RadarDijkstra(v,c,12,dijk);
		StrategyRadarSimple strat = new StrategyRadarSimple(rad);
		IHMSwing ihm = new IHMSwing();
		//ihm.add(new VoitureObserveur(v));
		ihm.add(new RadarObserveur(rad));

		ihm.add(new TrajectoireObserveur(v));
		ihm.addCircuit(c);
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
		
		//System.out.println("fini");
		simu.add(ihm);
		//ci-dessous : pour que la fenetre prenne en compte 

		Voiture v2 = VoitureFactory.build(c);
		v2.setPosition(new Vecteur(v2.getPosition().getX()+5,v2.getPosition().getY()));
		Radar rad2 = new RadarImpl(v2,c,64);
		//ArrayList<Commande> liste = Simulation.loadListeCommande(filename)
		Strategy strat2 = new StrategyRadarSimple(rad2);
		TrajectoireObserveur trajobs2 = new TrajectoireObserveur(v2);
		ihm.add(new RadarObserveur(rad2));
		trajobs2.setColor(Color.BLUE);
		VoitureObserveur voitobs2 = new VoitureObserveur(v2);
		voitobs2.setColor(Color.BLUE);
		simu.addVoitureStrategies(v2, strat2);
		ihm.add(trajobs2);
		
		//simu.addVoitureStrategies(v2, strat2);;
		Fenetre fen = new Fenetre(ihm,"test");
		ihm.setPreferredSize(new Dimension(768,1024));
		fen.getContentPane().add(ihm);
		fen.pack();
                fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		try {
			simu.play();
		} catch (VoitureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Simulation.saveListeCommande(simu.getCommandes(), "listecommandes.txt");


	
		
		
	}

}
