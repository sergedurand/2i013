package algo;

import java.util.ArrayList;

import circuit.Circuit;
import geometrie.Vecteur;
import terrain.Terrain;
import voiture.Voiture;

public class RadarDijkstra extends RadarImpl {
	Dijkstra dij;
	Vecteur arr;
	Vecteur dep;
	ArrayList<Vecteur> arrivees;
	
	public RadarDijkstra(Voiture voiture, Circuit circuit, int n,Dijkstra dij) {
		super(voiture, circuit, n);
		this.dij=dij;
		arr=circuit.getDirectionArrivee();
		dep=circuit.getDirectionDepart();
		arrivees=circuit.getArrivees();
		// TODO Auto-generated constructor stub
	}

	public double oneScore(int k) {
		int indicemax=0,cpt=0;
		double n = Double.POSITIVE_INFINITY;
		Vecteur pos = voiture.getPosition().clone();
		Vecteur dir = voiture.getDirection().clone();
		dir=dir.rotation(angles[k]);
		while ((circuit.estDansCircuit(pos)&&(circuit.getTerrain(pos)!=Terrain.Herbe))) {
			if (arrivees.contains(pos.addition(dir.multiplication(0.1)))) {
				throw new RuntimeException("non.");
				/*if ((arr.prodScal(pos.soustraction(dep))>0)) {
					//throw new RuntimeException("non.");
					System.out.println("boum");
					continue;
				}*/
			}
			
			pos=pos.addition(dir.multiplication(0.1));
			int i = (int) (dij.getDist()[(int) pos.getX()][(int) pos.getY()]);
			if (n > i) {
				n = i;
				indicemax=cpt;
			}
			cpt++;
		}
		//distPix[indicemax] = (int) (cpt * 0.1);
		return n; //On renvoie la distance minimale trouv�e dans le tableau dist parmi les vecteurs explor�s
	}
	
	public double[] scores(double epsilon) {
		double[] scores;
		int taille=angles.length;
		//System.out.println(taille);
		scores=new double[taille];
		double[] scores2 = new double[taille];
		double max = 0;
		int imax = 0;
		double scorecroise=0;
		for (int i=0;i<taille;i++) {
			scores2[i]=1/Math.log(oneScore(i)+500000); //Impl�mentation pour les circuits simples
			//scores2[i]=1/(oneScore(i)+2); //Impl�mentation pour les circuits compliqu�s
			scores[i]=calcScore(angles[i],epsilon);
			scorecroise=Math.log(scores[i])*scores2[i];
			//System.out.println("itération");
			if(scorecroise>max) {
				max = scorecroise;
				imax = i;
				
			}
			/*if(scores[i]>=max) {
				max = scores[i];
				imax = i;
			}*/
			distPix[i]=scores[i]*epsilon;
			
		}
		this.BestIndex = imax;
		return scores;
	}
	

}
