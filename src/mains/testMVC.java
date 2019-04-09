package mains;
import terrain.*;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import vue.Fenetre;
import circuit.*;
import controleur.IHMSwing;
import exceptions.ArriveeException;
import exceptions.NeuroneException;
import exceptions.NotMovingException;
import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import simulation.*;
import algo.*;
import observeurs.*;
import strategy.Strategy;
import strategy.StrategyListeCommande;
import strategy.StrategyPoint;
import strategy.StrategyPrudente;
import strategy.StrategyRadarSimple;

public class testMVC {

	public static void main(String[] args) throws IOException, NeuroneException {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");
		Voiture v = VoitureFactory.build(c);

		Dijkstra dijk = new Dijkstra(c);
		dijk.compute();
		Radar rad = new RadarDijkstra(v,c,12,dijk);
		//RadarImpl rad=new RadarImpl(v,c,12);
		//StrategyRadarSimple strat = new StrategyRadarSimple(rad);
		StrategyPoint strat = new StrategyPoint(rad,v);
		
		
		strat.addPoint(new Vecteur(200,455));
		strat.addPoint(new Vecteur(400,455));
		strat.addPoint(new Vecteur(600,520));
		strat.addPoint(new Vecteur(530,650));
		strat.addPoint(new Vecteur(400,400));
		strat.addPoint(new Vecteur(400,350));
		
		 //test du fonctionnement de la stratégie point à point 
		
		IHMSwing ihm = new IHMSwing();
		ihm.add(new VoitureObserveur(v));
		ihm.add(new RadarObserveur(rad));
		ihm.add(new TrajectoireObserveur(v));
		ihm.addCircuit(c);
		Simulation simu = new Simulation(c);
		simu.addVoitureStrategies(v, strat);
		//simu.add(ihm);
		
		//System.out.println("fini");
		//simu.add(ihm);
		//ci-dessous : pour que la fenetre prenne en compte 
//
//		Voiture v2 = VoitureFactory.build(c);
//		v2.setPosition(new Vecteur(v2.getPosition().getX()+5,v2.getPosition().getY()));
//		Radar rad2 = new RadarDijkstra(v2,c,64,dijk);
//		StrategyRadarSimple strat2 = new StrategyRadarSimple(rad2);
		//ArrayList<Commande> liste = Simulation.loadListeCommande(filename)
		//Strategy strat2 = new StrategyRadarSimple(rad2);
		//TrajectoireObserveur trajobs2 = new TrajectoireObserveur(v2);
		//ihm.add(new RadarObserveur(rad2));
		//trajobs2.setColor(Color.BLUE);
		//VoitureObserveur voitobs2 = new VoitureObserveur(v2);
		//voitobs2.setColor(Color.BLUE);
		//simu.addVoitureStrategies(v2, strat2);
		//ihm.add(trajobs2);
		
//		simu.addVoitureStrategies(v2, strat2);
//		Voiture v3 = VoitureFactory.build(c);
//		Radar rad3 = new RadarImpl(v3,c,12);
//		StrategyRadarSimple strat3 = new StrategyRadarSimple(rad3);
//		simu.addVoitureStrategies(v3, strat3);
//		Fenetre fen = new Fenetre(ihm,"test");
//		ihm.setPreferredSize(new Dimension(768,1024));
//		fen.getContentPane().add(ihm);
//		fen.pack();
//                fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		fen.setVisible(true);
		try {
			simu.play();
		} catch (VoitureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ArriveeException e2) {
			e2.printStackTrace();
		} catch (NotMovingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Simulation.saveListeCommande(simu.getCommandes().get(0), "listecommandes0.txt");
//		
//		Simulation.saveListeCommande(simu.getCommandes().get(0), "listecommandes0.txt");
////		Simulation.saveListeCommande(simu.getCommandes().get(1), "listecommandes1.txt");
////		Simulation.saveListeCommande(simu.getCommandes().get(2), "listecommandes2.txt");
//		
//		Voiture v4 = VoitureFactory.build(c);
////		Voiture v5 = VoitureFactory.build(c);
////		Voiture v6 = VoitureFactory.build(c);
//		
//		ArrayList<Commande> l1 = null;
////		ArrayList<Commande> l2 = null;
////		ArrayList<Commande> l3 = null;
//		try {
//			l1 = Simulation.loadListeCommande("listecommandes0.txt");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
////		try {
////			l2 = Simulation.loadListeCommande("listecommande1.txt");
////		} catch (IOException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
////		try {
////			l3 = Simulation.loadListeCommande("listecommande2.txt");
////		} catch (IOException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
//		StrategyListeCommande st1 = new StrategyListeCommande(l1);
//		StrategyListeCommande st2 = new StrategyListeCommande(l2);
//		StrategyListeCommande st3 = new StrategyListeCommande(l3);
//		
//		Simulation simu2 = new Simulation(c);
//		simu2.addVoitureStrategies(v4, st1);
////		simu2.addVoitureStrategies(v5, st2);
////		simu2.addVoitureStrategies(v6, st3);
//		
//		TrajectoireObserveur traj1 = new TrajectoireObserveur(v4);
////		TrajectoireObserveur traj2 = new TrajectoireObserveur(v5);
////		TrajectoireObserveur traj3 = new TrajectoireObserveur(v6);
//		ihm.add(traj1);
//		ihm.add(new VoitureObserveur(v4));
////		ihm.add(traj2);
////		ihm.add(traj3);
//		simu2.add(ihm);
//		ihm.add(simu2);
//		

//		try {
//			simu2.play();
//		} catch (VoitureException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
					
		
	}

}
