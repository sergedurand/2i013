package strategy;

import java.util.ArrayList;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyListeCommande implements Strategy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Commande> liste;
	private int index;
	
	public StrategyListeCommande(ArrayList<Commande> liste) {
		super();
		this.liste = liste;
		index = 0;
	}


	@Override
	public Commande getCommande() {
		if(index==liste.size()) {
			index=0;
			return liste.get(index++);
		}
		return liste.get(index++);
	}

	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub

	}

}
