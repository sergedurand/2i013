package strategy;

import circuit.Circuit;
import exceptions.*;
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
	private Voiture v;
	private Dijkstra d;

	public StrategyPerceptron(Perceptron p, Radar r, Genome g) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.r = r;
		this.g = g;
	}
	
	public StrategyPerceptron(Genome g, Radar r, Voiture v, Dijkstra d) {
		this.g = g;
		this.r = r;
		this.p = new Perceptron(g.getListe_poids(),g.getListe_biais());
		this.v = v;
		this.d = d;
	}

	@Override
	public Commande getCommande() throws VoitureException, NeuroneException {
		ArrayList<Double> input = new ArrayList<Double>();
//		input.add(this.getV().getVitesse());
		input.add(this.getV().getMaxTurn());
//		input.add(this.getV().getBraquage());
//		input.add(this.getD().getDist()[(int) this.getV().getPosition().getX()][(int) this.getV().getPosition().getY()]);
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

	public Perceptron getP() {
		return p;
	}

	public void setP(Perceptron p) {
		this.p = p;
	}
	
	public Dijkstra getD() {
		return d;
	}
	public Voiture getV() {
		return v;
	}

	public Genome getG() {
		return g;
	}

	public void setG(Genome g) {
		this.g = g;
	}

}
