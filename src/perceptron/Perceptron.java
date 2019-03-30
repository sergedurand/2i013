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
	 * @param structure
	 */
	public Perceptron(ArrayList<Integer> structure) {
		if(structure.size()<2) {
			System.out.println("Structure trop petite : au moins deux couches nécessaires");
			return;
		}
		entree = new CoucheEntree(structure.get(0));
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
			//cas ou il y a des couches cachées
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
	
	public void setRandomWeightsBias(double min, double max) {//il suffit de randomiser les couches sortantes uniquement.
		int i = 0;
		int input = 0;
		int output = 0;
		if(this.getCoucheCachee().size() == 0) {//cas du perceptron simple couche
			output = sortie.getTaille();
			entree.setRandomPoidsSortant(min, max);
			sortie = new CoucheNeurone(entree,sortie.getBiais());
			sortie.setRandomBiais(min, max);
			return;
		}else {
			entree.setRandomPoidsSortant(min, max);
			while(i<this.getCoucheCachee().size()) {
				
			}
		}
		sortie.setInputWeights(entree.get);
		output = this.getCoucheCachee().get(i).getTaille();
		entree.setRandomWeightsBias(output, min, max);
		for(i=0;i<this.getCoucheCachee().size();i++) {
			CoucheNeurone couche_courante = this.getCoucheCachee().get(i);
			if(i==0) {
				input = entree.getTaille();
			}
			else {
				input = this.getCoucheCachee().get(i-1).getTaille();
			}
			if(i==this.getCoucheCachee().size()-1) {
				output = sortie.getTaille();
			}else {
				output = this.getCoucheCachee().get(i+1).getTaille();
			}
			couche_courante.setRandomWeightsBias(input, output, min, max);
		}
	}
	
	
	
	public Perceptron(int input, int output) {
		CoucheEntree entree = new CoucheEntree(input);
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
	 * @param couche1
	 * @param couche2
	 */
	public void calculValeur(CoucheNeurone couche1, CoucheNeurone couche2) {
		for(Neurone neuronearrivee : couche2.getNeurones()) {
			double res = 0;
			int i = 0;
			for(Neurone neuronedepart : couche1.getNeurones()) {
				double poid = neuronearrivee.getListePoidsEntrants().get(i);
				res += poid* neuronedepart.getValeur();
			}
			
			res+= neuronearrivee.getBiais();
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
		}
	}
	
	/**
	 * 
	 * @return Output computed by the neural network 
	 */
	public ArrayList<Double> resultat() {
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
