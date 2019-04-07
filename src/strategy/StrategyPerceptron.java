package strategy;

import circuit.Circuit;
import genetique.*;
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
	private Genome g;

	public StrategyPerceptron(Perceptron p, Radar r, Genome g) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.r = r;
		this.g = g;
	}
	
	public StrategyPerceptron(Genome g, Radar r) {
		this.g = g;
		this.r = r;
		this.p = new Perceptron(g.getListe_poids(),g.getListe_biais());
	}

	@Override
	public Commande getCommande() throws VoitureException {
		ArrayList<Double> input = new ArrayList<Double>();
		r.scores(0.1);
		System.out.println("taille radar : " + r.getDistPix().length);
		System.out.println(" taille neurone : " + p.getEntree().getTaille());
		if(r.getDistPix().length!=p.getEntree().getTaille()) {
			throw new VoitureException("problème taille radar et nb neurone");
		}
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

	public Perceptron getP() {
		return p;
	}

	public void setP(Perceptron p) {
		this.p = p;
	}

	public Genome getG() {
		return g;
	}

	public void setG(Genome g) {
		this.g = g;
	}

}
