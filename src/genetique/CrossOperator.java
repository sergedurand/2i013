package genetique;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Serge
 *
 */
public class CrossOperator {
	
	/**
	 * Return a new Genome, genes from mere with a rate % chance and pere with a (1-rate) % chance
	 * pere and mere must share the same structure
	 * @param pere
	 * @param mere
	 * @param rate must be between 0 and 1
	 * @return
	 */
	public Genome cross(Genome pere, Genome mere, double rate) {
		ArrayList<ArrayList<ArrayList<Double>>> l_poids = new ArrayList<ArrayList<ArrayList<Double>>>(); 
		ArrayList<ArrayList<Double>> biais = new ArrayList<ArrayList<Double>>();
		
		for(int i = 0;i<pere.getListe_poids().size();i++) {
			ArrayList<ArrayList<Double>> poids_couche = new ArrayList<ArrayList<Double>>();
			int taille_couche = pere.getStructure().get(i);
			for(int j = 0; j < taille_couche; j++) {
				ArrayList<Double> poids_neurone = new ArrayList<Double>();
				int taille_neurone = pere.getListe_poids().get(i).get(j).size();
				for(int k = 0;k<taille_neurone;k++) {
					double r = Math.random();
					if(r < 0.5){
						poids_neurone.add(pere.getListe_poids().get(i).get(j).get(k));
					}else {
						poids_neurone.add(mere.getListe_poids().get(i).get(j).get(k));
					}
				}
				poids_couche.add(poids_neurone);
			
			}
			
			l_poids.add(poids_couche);
		}
		
		for(int i = 0; i<pere.getListe_biais().size();i++) {
			ArrayList<Double> biais_couche = new ArrayList<Double>();
			for(int j = 0;j<pere.getListe_biais().get(i).size();j++){
				double r = Math.random();
				if(r<0.5) {
					biais_couche.add(pere.getListe_biais().get(i).get(j));
				}else {
					biais_couche.add(mere.getListe_biais().get(i).get(j));
				}
			}
			biais.add(biais_couche);
		}		
		Genome res = new Genome(l_poids,biais);		
		return res;		
	}
	
	/**
	 * cross a population (list of genomes). The population has to be of an even size. 
	 * The parents are chosen step by step at random within the population until no more parents are available
	 * @param population
	 * @return
	 */
	public ArrayList<Genome> crossPop(ArrayList<Genome> population, int part){
		Random rand = new Random();
		ArrayList<Genome> res = new ArrayList<Genome>();
		ArrayList<Genome> parents = population;
		
		while(res.size()<(population.size()*part)) {
			Genome father = null;
			while(res.size()<population.size()/4) {
				father = population.get(0);
				int i = rand.nextInt(parents.size());
				while(i==0) {
					i=rand.nextInt(parents.size());
				}
				Genome mother = parents.get(i);
				Genome son1 = cross(father,mother,0.5);
				res.add(son1);
			}
			
			int i = rand.nextInt(parents.size());
			father =  parents.get(i);
			int j = rand.nextInt(parents.size());
			while(j==i) {//to make sure parents are different
				j = rand.nextInt(parents.size());
			}
			Genome mother =  parents.get(j);
			//to boost the best ones
			if((i < 5) && (j < 5) && father.isFinish() && mother.isFinish()) {
				Genome son1 = cross(father,mother,0.5);
				Genome son2 = cross(father,mother,0.5);
				Genome son3 = cross(father,mother,0.5);
//				Genome son4 = cross(father,mother,0.5);
//				res.add(son4);
				res.add(son2);
				res.add(son3);
				res.add(son1);
			}else {
				Genome son1 = cross(father,mother,0.5);
				res.add(son1);
			}
		}
		return res;
	}
}
