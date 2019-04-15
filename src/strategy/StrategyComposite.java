package strategy;

import java.util.ArrayList;

import algo.Radar;
import circuit.Circuit;
import exceptions.NeuroneException;
import geometrie.Vecteur;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;

public class StrategyComposite implements Strategy {
	
	/**
	 * Convention : strategies[0] = RadarSimple, 1 = Dijkstra, 2 = Point à Point, 3 = ligneDroite
	 * 
	 */
	private ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private Strategy strat_courante;
	private ArrayList<Vecteur> points;
	private ArrayList<Vecteur> zone_points;
	private Vecteur arrivee;
	private Circuit c;
	private Voiture v;
	private Radar r;
	
	
	public StrategyComposite(ArrayList<Strategy> strategies, ArrayList<Vecteur> points, ArrayList<Vecteur> zone_points,
			Voiture v) {
		super();
		this.strategies = strategies;
		this.points = points;
		this.zone_points = zone_points;
		this.v = v;
		this.strat_courante = strategies.get(1); //Dijkstra by default
	}

	@Override
	public Commande getCommande() throws VoitureException, NeuroneException {
		// TODO Auto-generated method stub
		for(Vecteur zone : zone_points) {
			if(v.getPosition().isInArea(20, zone)){
				zone_points.remove(zone);
				strat_courante = strategies.get(2);
				break;
			}
		}
		
		return strat_courante.getCommande();
	}

	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub
		
	}

}
