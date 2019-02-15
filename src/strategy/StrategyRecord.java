package strategy;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.io.*;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyRecord implements Strategy {
	private Strategy strat;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Commande getCommande() {
		return strat.getCommande();
	}

	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub

	}
	
}
