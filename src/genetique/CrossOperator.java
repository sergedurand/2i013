package genetique;

import java.util.ArrayList;

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
}
