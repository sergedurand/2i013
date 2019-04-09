package genetique;

import java.util.ArrayList;

import voiture.Commande;

public class Genome {
	private ArrayList<ArrayList<ArrayList<Double>>> weight_lists;
	private ArrayList<ArrayList<Double>> liste_biais;
	private final ArrayList<Integer> structure;
	private double score = Double.NEGATIVE_INFINITY;
	private boolean finish = false;
	private ArrayList<Commande> commandes = new ArrayList<Commande>();

	

	/**
	 * Classic constructor. The structure is deduced from the list of weights
	 * @param weight_lists
	 * @param liste_biais
	 */
	public Genome(ArrayList<ArrayList<ArrayList<Double>>> weight_lists, ArrayList<ArrayList<Double>> liste_biais) {
		super();
		this.weight_lists = weight_lists;
		this.liste_biais = liste_biais;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(ArrayList<ArrayList<Double>> l : weight_lists) {
			temp.add(l.size());
		}
		this.structure = temp;
	}
	public ArrayList<ArrayList<ArrayList<Double>>> getListe_poids() {
		return weight_lists;
	}
	public void setListe_poids(ArrayList<ArrayList<ArrayList<Double>>> liste_poids) {
		this.weight_lists = liste_poids;
	}
	public ArrayList<ArrayList<Double>> getListe_biais() {
		return liste_biais;
	}
	public void setListe_biais(ArrayList<ArrayList<Double>> liste_biais) {
		this.liste_biais = liste_biais;
	}
	public ArrayList<Integer> getStructure() {
		return structure;
	}
	
	@Override
	public String toString() {
		String str = " Liste Poids :  \n";
		str += this.getListe_poids().toString();
		str += "\n";
		str += "List biais : \n" + this.getListe_biais().toString() + "\n";
		return str;
	}
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public ArrayList<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(ArrayList<Commande> commandes) {
		this.commandes = commandes;
	}


	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
//	@Override
//	public Genome clone() {
//		ArrayList<ArrayList<ArrayList<Double>>> weight_lists_bis = this.getListe_poids().clone();
//		ArrayList<ArrayList<Double>> liste_biais_bis;
//		ArrayList<Integer> structure;
//	}
}
