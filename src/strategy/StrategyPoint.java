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
	public boolean zonepoint=false;
	public int index=0;
	@Override
	public Commande getCommande() {
		
		
		for (Vecteur point:points) {
			if (v.getPosition().getDistance(point)<20) {
				
				index=points.indexOf(point);
				System.out.println("point: "+point.toString()+" index: "+index);
				if (index%2==0) {
					zonepoint=true;
				}
				else {
					zonepoint=false;
				}
			}
		}
		//System.out.println("zonepoint: "+zonepoint+" index: "+index);
		if (zonepoint==false) { //Si la voiture n'est pas dans un des points de l'ArrayList
			r.scores(0.1);
			double rot = r.getAngles()[r.getBestIndex()]*2/Math.PI;
			return new Commande(1,rot);
		}
		
		if (zonepoint==false&&index%2==1) {
			
		}
		
		//Sinon
		if (zonepoint==true && index%2==0) {
			System.out.println("je cherche la sortie!");
			Vecteur arrivee = points.get(index+1);
			System.out.println("voici l'arrivée: "+arrivee.toString());
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
