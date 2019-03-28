package strategy;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;
import algo.*;

public class StrategyPrudente implements Strategy{
	/**
	 * 
	 */
	public int i=0;
	private static final long serialVersionUID = 1L;
	private Voiture v;
	private Radar radar;
	public StrategyPrudente(Radar radar) {
		super();
		this.radar = radar;
	}
	
	public Radar getRadar() {
		return radar;
	}
	
	public void setVoiture(Voiture v) {
		this.v=v;
	}
	
	@Override
	public Commande getCommande() {
		i++;
		radar.scores(0.1);
		double rot = radar.getAngles()[radar.getBestIndex()]*2/Math.PI;
		//System.out.println(radar.getDistMin());
		//if (radar.getDistPix()[radar.getBestIndex()]<=250 && i>150) {
		if (radar.getDistMin()<100 && i>150) {
			return new Commande (1,rot);
		}else {
			return new Commande(1,rot);
		}
		
	}

	@Override
	public void init(Voiture v, Circuit c) {
		radar.init(v,c);
	}
	

}