package strategy;

import java.util.ArrayList;

import algo.Radar;
import circuit.Circuit;
import geometrie.Vecteur;
import voiture.Commande;
import voiture.Voiture;

public class StrategyPoint implements Strategy {
	private ArrayList<Vecteur> points;
	private Voiture v;
	private Radar r;
	public StrategyPoint(Radar radar,Voiture v) {
		super();
		r = radar;
		this.v=v;
		points=new ArrayList<Vecteur>();	
	}
	
	public void addPoint(Vecteur v) {
		points.add(v);
	}
	
	@Override
	public Commande getCommande() {
		boolean zonepoint=false;
		int index=0;
		for (Vecteur point:points) {
			if (v.getPosition().getDistance(point)<30) {
				zonepoint=true;
				index=points.indexOf(point);
			}
		}
		if (zonepoint==false) { //Si la voiture n'est pas dans un des points de l'ArrayList
			r.scores(0.1);
			double rot = r.getAngles()[r.getBestIndex()]*2/Math.PI;
			return new Commande(1,rot);
		}
		//Sinon
		if (zonepoint==true && index%2==0) {
			Vecteur arrivee = points.get(index+1);
			double min=Double.POSITIVE_INFINITY;
			Commande best=null;
			for (int i=0;i<r.getAngles().length;i++) {
				double rot = r.getAngles()[i]*2/Math.PI;
				Vecteur newposition = v.getPosition().addition(v.getDirection().rotation(rot * v.getBraquage()).multiplication(0.1));
				double distance=arrivee.getDistance(newposition);
				if (distance<min) {
					min=distance;
					best=new Commande(1,rot);
				}
			}
			return best;
		}
		double rot = r.getAngles()[r.getBestIndex()]*2/Math.PI;
		return new Commande(1,rot);
	}

	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub

	}
	
	public void setVoiture(Voiture v) {
		this.v=v;
	}
	
	

}
