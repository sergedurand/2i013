package algo;

import circuit.Circuit;
import geometrie.Vecteur;
import terrain.TerrainTools;
import voiture.Voiture;

public class RadarParabolique extends RadarImpl {

	public RadarParabolique(Voiture voiture, Circuit circuit, int nb_angles) {
		super(voiture, circuit, nb_angles);
		distPix2=new double[nb_angles];
		
	}

	public RadarParabolique() {
		// TODO Auto-generated constructor stub
	}
	
	protected double[] distPix2;
	
	@Override
	public double[] scores(double epsilon) {
		double[] scores1;
		double[] scores2;
		int taille=angles.length;
		//System.out.println(taille);
		scores1=new double[taille];
		scores2=new double[taille];
		
		int score1,score2;
		
		
		double max = 0;
		int imax = 0;
		for (int i=0;i<taille;i++) {
			score1=calcScore1(angles[i],epsilon)[0];
			score2=calcScore1(angles[i],epsilon)[1];
			
			//System.out.println("itération");
			if(scores2[i]>=max) {
				max = scores2[i];
				imax = i;
			}
			distPix[i]=score1*epsilon;
			distPix2[i]=score2*epsilon;
			
		}
		this.BestIndex = imax;
		return scores2;
	}
	
	
	protected int [] calcScore1(double angle,double epsilon) {
		Vecteur p = voiture.getPosition().clone();
		Vecteur direction = voiture.getDirection().rotation(0);
		int cpt=0;
		int cptfinal1,cptfinal2;
		//System.out.println("Vecteur courant: "+p.toString());
		do {
			cpt++;
			p = p.addition(direction.multiplication(epsilon));
		}while ((circuit.estDansCircuit(p.addition(direction.multiplication(epsilon)))&&(TerrainTools.charFromTerrain(circuit.getTerrain(p)) !='g'))&&cpt<100);
		cptfinal1=cpt;
		p=p.rotation(Math.PI+angle);
		cpt=0;
		while ((circuit.estDansCircuit(p)&&(TerrainTools.charFromTerrain(circuit.getTerrain(p)) !='g'))) {
			cpt++;
			p = p.addition(direction.multiplication(epsilon));
		}
		cptfinal2=cpt;
		int tabcpt[]=new int[2];
		tabcpt[0]=cptfinal1;
		tabcpt[1]=cptfinal2;
		return tabcpt;
		
	}

	
	public double[] getDistPix2() {
		return distPix2;
	}
	
	

}
