package strategy;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyLigneDroite implements Strategy {

	@Override
	public Commande getCommande() {
		return new Commande(1,0);
	}

	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub
		
	}

}
