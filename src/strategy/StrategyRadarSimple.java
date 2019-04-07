package strategy;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;
import algo.*;

public class StrategyRadarSimple implements Strategy{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Radar radar;
	public StrategyRadarSimple(Radar radar) {
		super();
		this.radar = radar;
	}
	
	public Radar getRadar() {
		return radar;
	}
	
	@Override
	public Commande getCommande() {
		radar.scores(0.1);
		double rot = radar.getAngles()[radar.getBestIndex()]*2/Math.PI;
		System.out.println("taille radar : " + radar.getDistPix().length);
		return new Commande(1,rot);
	}

	@Override
	public void init(Voiture v, Circuit c) {
		radar.init(v,c);
	}
	

}
