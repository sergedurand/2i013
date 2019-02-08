package algo;

import circuit.Circuit;
import geometrie.Vecteur;
import voiture.Voiture;
import terrain.*;

public class RadarImpl implements Radar {

	private Voiture voiture;
	private Circuit circuit;
	private double[] angles;
	private double[] distPix;
	private int BestIndex;
	
	public RadarImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] scores(double epsilon) {
		int[] scores;
		int taille=angles.length;
		scores=new int[taille];
		double max = 0;
		int imax = 0;

		for (int i=0;i<taille;i++) {
			scores[i]=calcScore(angles[i],epsilon);
			if(scores[i]>=max) {
				max = scores[i];
				imax = i;
			}
			distPix[i]=scores[i]*epsilon;
		}
		this.BestIndex = imax;
		return scores;
	}

	@Override
	public double[] distancesInPixels() {
		return distPix;
	}

	@Override
	public int getBestIndex() {
		return BestIndex;
	}

	@Override
	public double[] thetas() {
		// TODO Auto-generated method stub
		return angles;
	}
	
	
	
	private int calcScore(double angle,double epsilon) {
		Vecteur p = voiture.getPosition();
		Vecteur direction = voiture.getDirection().rotation(angle);
		int cpt=0;
		while ((TerrainTools.charFromTerrain(circuit.getTerrain(p)) !='g')&&(circuit.estDansCircuit(p))) {
			cpt++;
			p.addition(direction.multiplication(epsilon));
		}
		return cpt;
	}

}
