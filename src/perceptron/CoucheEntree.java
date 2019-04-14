package perceptron;

import java.util.ArrayList;

import exceptions.NeuroneException;

public class CoucheEntree extends CoucheNeurone {

	public CoucheEntree(int taille, ArrayList<Neurone> neurones) {
		super(taille, neurones);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiate a layer of given size and given values.
	 * @param taille
	 */
	public CoucheEntree(ArrayList<Double> values) {
		super(values.size());
		int taille = values.size();
		for(int i = 0;i<taille;i++) {
			this.getNeurones().set(i, new Neurone(values.get(i)));
		}
	}
	
	public CoucheEntree(int taille) {
		// TODO Auto-generated constructor stub
		super(taille);
	}

	public CoucheEntree(ArrayList<ArrayList<ArrayList<Double>>> listePoidsNeurones, ArrayList<Double> biais) throws NeuroneException {
		super(listePoidsNeurones,biais);
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
	

}
