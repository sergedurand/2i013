package perceptron;

import java.util.ArrayList;

public class PerceptronTools {

	/**
	 * Return a list of list of weights all at 0. Used to instantiate a "empty" network.
	 * @param nb_neurone
	 * @param nb_poids
	 * @return
	 */
	
	public PerceptronTools() {
		super();
	}
	
	public static ArrayList<ArrayList<Double>> getListWeights(int nb_neurone, int nb_poids){
		ArrayList<ArrayList<Double>> res = new ArrayList<ArrayList<Double>>();
		for(int i = 0;i<nb_neurone;i++) {
			ArrayList<Double> temp = new ArrayList<Double>();
			for(int j = 0;j<nb_poids;j++) {
				temp.add(0.0);
			}
			res.add(temp);
		}
		return res;
		
	}
}
