package mains;
import terrain.*;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import circuit.*;
import controleur.IHMSwing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import simulation.*;
import algo.*;
import observeurs.*;
import strategy.StrategyRadarSimple;

public class testMVC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");
		//BufferedImage im = TerrainTools.imageFromTerrain(c.getTerrain());
		//Graphics g = im.getGraphics();
		Voiture v = VoitureFactory.build(c);
		RadarImpl rad = new RadarImpl(v,c, new double[1]);
		rad.setAngles64();
		StrategyRadarSimple strat = new StrategyRadarSimple(rad);
		IHMSwing ihm = new IHMSwing();
		ihm.add(new VoitureObserveur(v));
		ihm.add(new RadarObserveur(rad));
		ihm.addCircuit(c);
		Simulation simu = new Simulation(v,strat,c);
		
		//System.out.println("fini");
		JFrame fen = new JFrame("test");
		simu.add(ihm);
		//ci-dessous : pour que la fenetre prenne en compte 
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
		
		
	}

}
