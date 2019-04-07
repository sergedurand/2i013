package genetique;

import perceptron.PerceptronTools;

public class MutationOperator {
	
	/**
	 * mutates the Genome g with a rate% chance for each genes. Set the mutated genes to a 
	 * random double between min and max
	 * @param g
	 * @param rate
	 * @param min
	 * @param max
	 */
	public void mutate(Genome g,double rate, double min, double max) {
		
		for(int i = 0;i<g.getListe_poids().size();i++) {
			for(int j = 0;j<g.getListe_poids().get(i).size();j++) {
				for(int k = 0;k<g.getListe_poids().get(i).get(j).size();k++) {
					double r = Math.random();
					if(r<rate) {
						double p = PerceptronTools.getRandDouble(min, max);
						g.getListe_poids().get(i).get(j).set(k,p);
					}
				}
			}
		}
		
		for(int i = 0;i<g.getListe_biais().size();i++) {
			for(int j = 0;j<g.getListe_biais().get(i).size();j++) {
				double r = Math.random();
				if(r<rate) {
					double b = PerceptronTools.getRandDouble(min, max);
					g.getListe_biais().get(i).set(j, b);
				}
			}
		}
		
	}
	
}
