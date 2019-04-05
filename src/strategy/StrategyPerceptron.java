package strategy;

import circuit.Circuit;
import perceptron.*;

import java.util.ArrayList;

import algo.*;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;

public class StrategyPerceptron implements Strategy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Perceptron p;
	private Radar r;

	public StrategyPerceptron(Perceptron p, Radar r) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.r = r;
	}

	@Override
	public Commande getCommande() throws VoitureException {
		ArrayList<Double> input = new ArrayList<Double>();
		r.scores(0.1);
		for(int i = 0;i<r.getDistPix().length;i++) {
			input.add(r.getDistPix()[i]);
		}
		
		p.setEntreeValues(input);
		ArrayList<Double> param_commande = p.getResultat();
		// TODO Auto-generated method stub
		return new Commande(param_commande.get(0),param_commande.get(1));
	}

	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub

	}

}
