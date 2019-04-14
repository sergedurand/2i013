package modele;

import algo.Dijkstra;
import algo.RadarImpl;
import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import controleur.*;
import strategy.StrategyRadarSimple;
import voiture.Voiture;
import voiture.VoitureFactory;
import vue.*;

public class TestModele {

	public static void main(String[] args) {
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");
		Voiture v = VoitureFactory.build(c);
		RadarImpl rad=new RadarImpl(v,c,6);
		StrategyRadarSimple strat = new StrategyRadarSimple(rad);
		Modele m=new Modele(rad, v, c, strat,"1_safe.trk","Simple",6,"Rouge",50,20,50);
		m.setSimu(false);
		m.openFenetre();
		//m.startSimu();
	}

}