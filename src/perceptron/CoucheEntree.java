package perceptron;

import java.util.ArrayList;

public class CoucheEntree extends CoucheNeurone {

	public CoucheEntree(int taille) {
		super(taille);
		// TODO Auto-generated constructor stub
	}
	
	public void initAleatoire(int output, double min,  double max) {
		super.initAleatoire(0, output, min, max);
	}
	
	public void setInput(ArrayList<Double> inputs) {
		if(inputs.size()!=this.getTaille()) {
			System.out.println("Liste ne correspondant pas au nombre de neurone");
			return;
		}
		
		int i = 0;
		for(Double input : inputs) {
			this.getNeurones().get(i).setValeur(input);
			i++;
		}
	}
	
	public void feedForward() {
	}

}
