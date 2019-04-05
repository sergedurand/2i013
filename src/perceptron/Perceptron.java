package perceptron;

import java.util.ArrayList;

public class Perceptron {
	private CoucheEntree entree;
	private ArrayList<CoucheNeurone> coucheCachee = new ArrayList<CoucheNeurone>();
	private CoucheNeurone sortie;
	
	
	public Perceptron(CoucheEntree entree, ArrayList<CoucheNeurone> coucheCachee, CoucheNeurone sortie) {
		super();
		this.entree = entree;
		this.coucheCachee = coucheCachee;
		this.sortie = sortie;
	}
	
	/**
	 * creates a full network from a basic structure : a list of sizes of each layer. 
	 * Has to be size 2 minimum as the minimal network has 2 layer
	 * initiate all values, weights and bias at 0.
	 * @param structure
	 */
	public Perceptron(ArrayList<Integer> structure) {
		if(structure.size()<2) {
			System.out.println("Structure trop petite : au moins deux couches n�cessaires");
			return;
		}
		entree = new CoucheEntree(structure.get(0));
		entree.setInputWeights(PerceptronTools.getListWeights(entree.getTaille(), 1));
		
		ArrayList<CoucheNeurone> cCachee = new ArrayList<CoucheNeurone>();
		for(int i = 1;i<structure.size();i++) {
			if(i<(structure.size()-1)) {
				CoucheNeurone couche_courante = new CoucheNeurone(structure.get(i));
				cCachee.add(couche_courante);
			}else {
				sortie = new CoucheNeurone(structure.get(i));
				}
		}
		this.coucheCachee = cCachee;
		if(structure.size() == 2) {
			entree.setOutputWeights(PerceptronTools.getListWeights(entree.getTaille(), sortie.getTaille()));
			sortie.setInputWeightsFromLayer(entree);
		}else {
			int taille_cachee = this.getCoucheCachee().size();
			CoucheNeurone couche_courante = this.getCoucheCachee().get(0);
			entree.setOutputWeights(PerceptronTools.getListWeights(entree.getTaille(), couche_courante.getTaille()));
			couche_courante.setInputWeightsFromLayer(entree);
			if(taille_cachee==1) {
				couche_courante.setOutputWeights(PerceptronTools.getListWeights(couche_courante.getTaille(), sortie.getTaille()));
				sortie.setInputWeightsFromLayer(couche_courante);
			}else {
				couche_courante.setOutputWeights(PerceptronTools.getListWeights(couche_courante.getTaille(),this.getCoucheCachee().get(1).getTaille()));
				for(int j = 1;j<taille_cachee;j++) {
					CoucheNeurone couche_precedente = this.getCoucheCachee().get(j-1);
					couche_courante = this.getCoucheCachee().get(j);
					couche_courante.setInputWeightsFromLayer(couche_precedente);
					if(j!=this.getCoucheCachee().size()-1) {
						couche_courante.setOutputWeights(PerceptronTools.getListWeights(couche_courante.getTaille(), this.getCoucheCachee().get(j+1).getTaille()));
					}
					
				}
				
				CoucheNeurone derniere_couche = this.getCoucheCachee().get(taille_cachee-1);
				int nb_neurone = derniere_couche.getTaille();
				int nb_sortie = sortie.getTaille();
				derniere_couche.setOutputWeights(PerceptronTools.getListWeights(nb_neurone, nb_sortie));
				sortie.setInputWeightsFromLayer(derniere_couche);
			}

		}
		
	}
	
	/**
	 * creates a network from a list of list of weights and bias. The list of weights must only have internal weights : 
	 * we can have outputs from previous layers inputs weights. if the list is of size 1, the network is a single layer perceptron.
	 * the only list is then a list of output weights from the point of vue of the entry layer, and input weights from the point of vue 
	 * of the output layer.
	 * @param listePoidsNeurones
	 * @param biais
	 */
	public Perceptron(ArrayList<ArrayList<ArrayList<Double>>> listePoidsNeurones, ArrayList<ArrayList<Double>> biais) {
		int taille = listePoidsNeurones.size();
		if(taille<1) {
			System.out.println("Probleme taille liste");
			return;
		}
		
		ArrayList<ArrayList<Double>> listePoids = listePoidsNeurones.get(0);
		int taille_entree = listePoids.size();
		entree = new CoucheEntree(taille_entree);
		for(int j = 0;j<taille_entree;j++) {
			Neurone neurone_courant = entree.getNeurones().get(j);
			ArrayList<Double> l_poids_courants = listePoids.get(j);
			neurone_courant.setListePoidsSortants(l_poids_courants);
			neurone_courant.setListePoidsEntrants(new ArrayList<Double>());
		}
		if(taille==1) { //cas perceptron simple couche
			sortie = new CoucheNeurone(entree,biais.get(0));
			sortie.setPoidsSortants(new ArrayList<ArrayList<Double>>());
			return;
			
		}else {
			//cas ou il y a des couches cach�es
			int taille_cachee = taille-1; 
			CoucheNeurone precedent = entree;
			int i = 0;
			while(i<taille_cachee) {
				ArrayList<ArrayList<Double>> l_poids_courants = listePoidsNeurones.get(i+1);
				CoucheNeurone couche_courante = new CoucheNeurone(precedent, biais.get(i));
				couche_courante.setPoidsSortants(l_poids_courants);
				this.getCoucheCachee().add(couche_courante);
				precedent = couche_courante;
				i++;
			}
			sortie = new CoucheNeurone(this.getCoucheCachee().get(this.getCoucheCachee().size()-1),biais.get(biais.size()-1));
			sortie.setPoidsSortants(new ArrayList<ArrayList<Double>>());
			

		}
	}
	
	/**
	 * Sets random weights and bias everywhere.
	 * Assumes the network has been initialiazed
	 * @param min
	 * @param max
	 */
	public void setRandomWeightsBias(double min, double max) {//il suffit de randomiser les couches sortantes uniquement.
		entree.setRandomBias(min, max);
		entree.setRandomPoidsSortant(min, max);
		int taille_cachee = this.getCoucheCachee().size();
		if(taille_cachee == 0) {
			sortie.setInputWeightsFromLayer(entree);
			sortie.setRandomBias(min, max);
		}
		
		else{

			for(int j = 0; j<taille_cachee;j++) {
				this.getCoucheCachee().get(j).setRandomBias(min, max);
				this.getCoucheCachee().get(j).setRandomPoidsSortant(min, max);

				this.getCoucheCachee().get(0);
				if(j==0) {
					this.getCoucheCachee().get(j).setInputWeightsFromLayer(entree);

				}else {
					this.getCoucheCachee().get(j).setInputWeightsFromLayer(this.getCoucheCachee().get(j-1));

				}
			}

			sortie.setInputWeightsFromLayer(this.getCoucheCachee().get(taille_cachee-1));
			sortie.setRandomBias(min, max);
		}
	}
	
	/**
	 * return the basic structure of the neural network
	 * @return
	 */
	public ArrayList<Integer> getStructure(){
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(entree.getTaille());
		for(CoucheNeurone couche : this.getCoucheCachee()) {
			res.add(couche.getTaille());
		}
		res.add(sortie.getTaille());
		return res;
	}

	public void setEntree(ArrayList<Double> inputs) {
		this.entree.setInput(inputs);
	}
	public CoucheEntree getEntree() {
		return entree;
	}
	public void setEntree(CoucheEntree entree) {
		this.entree = entree;
	}
	public ArrayList<CoucheNeurone> getCoucheCachee() {
		return coucheCachee;
	}
	public void setCoucheCachee(ArrayList<CoucheNeurone> coucheCachee) {
		this.coucheCachee = coucheCachee;
	}
	public CoucheNeurone getSortie() {
		return sortie;
	}
	public void setSortie(CoucheNeurone sortie) {
		this.sortie = sortie;
	}
	
	/**
	 * compute the feedforward between two specified layers. Uses tanh as the activation function
	 * @param couche1 Starting Layer
	 * @param couche2 Arrival Layer
	 */
	public void calculValeur(CoucheNeurone couche1, CoucheNeurone couche2) {
		for(Neurone neuronearrivee : couche2.getNeurones()) {
			double res = 0;
			int i = 0;
			for(Neurone neuronedepart : couche1.getNeurones()) {
				double poid = neuronearrivee.getListePoidsEntrants().get(i);
				res += poid* neuronedepart.getValeur();
				i++;
			}
			
			res += neuronearrivee.getBiais();
			res = Math.tanh(res);
			neuronearrivee.setValeur(res);
		}
	}
	
	/**
	 * compute the feedforward between all the layers.
	 */
	public void calculTotal() {
		if(coucheCachee.size()==0) {
			calculValeur(entree,sortie);
		}else {
			int nb_cachee = this.getCoucheCachee().size();
			calculValeur(entree,this.getCoucheCachee().get(0));
			for(int i = 0;i<nb_cachee;i++) {
				if(i==(nb_cachee-1)) {
					//last layer reached
					break;
				}else {
					calculValeur(this.getCoucheCachee().get(i),this.getCoucheCachee().get(i+1));
				}
			}
			calculValeur(this.getCoucheCachee().get(nb_cachee-1), this.sortie);
		}
	}
	
	/**
	 * 
	 * @return Output computed by the neural network 
	 */
	public ArrayList<Double> getResultat() {
		this.calculTotal();
		ArrayList<Double> res = new ArrayList<Double>();
		for(Neurone neurone : sortie.getNeurones()) {
			double temp = neurone.getValeur();
			res.add(temp);
		}
		return res;
	}
	
	public int getTaille() {
		return 2 + this.getCoucheCachee().size();
	}
	
	public String toString() {
		String res = "taille du reseau = " + this.getTaille() + "\n";
		res += "Entree : \n";
		res += entree.toString();
		res += "\n";
		res += "--------------------------------------------------------------------------------- \n";
				for(CoucheNeurone c : this.getCoucheCachee()) {
			res += c.toString();
			res += "\n";
			res += "--------------------------------------------------------------------------------- \n";
		}
		res+= "Sortie : \n";
		
		res += sortie.toString();
		return res;
	}

	
}
