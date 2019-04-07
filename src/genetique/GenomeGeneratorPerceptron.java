package genetique;

import java.util.ArrayList;
import perceptron.*;

public class GenomeGeneratorPerceptron implements GenomeGenerator {
	private ArrayList<Integer> structure;
	
	
	public GenomeGeneratorPerceptron(ArrayList<Integer> structure) {
		super();
		this.structure = structure;
	}


	public ArrayList<Integer> getStructure() {
		return structure;
	}

 
	
	@Override
	public Genome getRandomGenome(double min, double max) {
		ArrayList<ArrayList<ArrayList<Double>>> poids = new ArrayList<ArrayList<ArrayList<Double>>>();
		ArrayList<ArrayList<Double>> biais = new ArrayList<ArrayList<Double>>();
		for(int i = 0;i<this.structure.size();i++) {
			
			int taille_couche = this.structure.get(i);
			if(i<this.structure.size()-1) {//for the weights, no need to get output weights for last layer
				int taille_suivante = this.structure.get(i+1);
				ArrayList<ArrayList<Double>> l_poids_couche = PerceptronTools.getRandomListWeights(taille_couche, taille_suivante, min, max);
				poids.add(l_poids_couche);
			}
			if(i>0) {//for the bias, no need for bias on first layer
				biais.add(PerceptronTools.getRandomBias(taille_couche, min, max));
			}
		}
		
		return new Genome(poids,biais);
	}

}
