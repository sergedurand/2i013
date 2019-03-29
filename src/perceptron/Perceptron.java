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
	
	public void calculTotal() {
		if(coucheCachee.size()==0) {
			calculValeur(entree,sortie);
		}
	}
	public ArrayList<Double> resultat() {
		ArrayList<Double> res = new ArrayList<Double>();
		for(Neurone neurone : sortie.getNeurones()) {
			double temp = neurone.getValeur();
			temp = temp*neurone.getListePoidsSortants().get(0) + neurone.getBiais();
			temp = Math.tanh(temp);
			res.add(temp);
		}
		return res;
	}
	
	
	public void setReseauMin(int nb_input,int nb_output) {
		
	}
	
}
