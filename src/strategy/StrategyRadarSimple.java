package strategy;

import circuit.Circuit;
import geometrie.*;
import voiture.Commande;
import voiture.Voiture;
import algo.*;

public class StrategyRadarSimple implements Strategy{
	private Radar radar;
	public StrategyRadarSimple(Radar radar) {
		super();
		this.radar = radar;
	}
	
	@Override
	public Commande getCommande() {
		radar.scores(0.1);
		double rot = radar.getAngles()[radar.getBestIndex()]*2/Math.PI;
		return new Commande(1,rot);
	}

	@Override
	public void init(Voiture v, Circuit c) {
		radar.init(v,c);
	}
	

}
