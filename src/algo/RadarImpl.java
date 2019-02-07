package algo;

import circuit.Circuit;
import geometrie.Vecteur;
import voiture.Voiture;
import terrain.*;

public class RadarImpl implements Radar {

	private Voiture voiture;
	private Circuit circuit;
	private double[] angles;
	
	public RadarImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double[] scores() {
		
		return null;
	}

	@Override
	public double[] distancesInPixels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBestIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] thetas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	private int calcScore(double epsilon) {
		Vecteur p = voiture.getPosition();
		Vecteur d = voiture.getDirection();
		int cpt=0;
		while ((TerrainTools.charFromTerrain(circuit.getTerrain(p)) !='g')&&(circuit.estDansCircuit(p))) {
			cpt++;
			p.addition(d.multiplication(epsilon));
		}
		return cpt;
	}

}
