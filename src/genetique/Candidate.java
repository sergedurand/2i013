package genetique;

import java.util.ArrayList;

import voiture.*;


/**
 * This class will be used to keep the best candidates with their list of commands
 * A candidate can they be used to instantiate a new strategy for new tracks to try 
 * or to visualize the best running through the list of commands.
 * @author Serge
 *
 */
public class Candidate {

	private Genome g;
	private ArrayList<Commande> commandes;
	private double score_fitness;
	public Candidate(Genome g, ArrayList<Commande> commandes, double score_fitness) {
		super();
		this.g = g;
		this.commandes = commandes;
		this.score_fitness = score_fitness;
	}
	public Genome getG() {
		return g;
	}
	public void setG(Genome g) {
		this.g = g;
	}
	public ArrayList<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(ArrayList<Commande> commandes) {
		this.commandes = commandes;
	}
	public double getScore_fitness() {
		return score_fitness;
	}
	public void setScore_fitness(double score_fitness) {
		this.score_fitness = score_fitness;
	}
	
	
	
}
