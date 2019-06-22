package genetique;

import java.util.List;
import java.util.ArrayList;

import algo.Dijkstra;
import circuit.Circuit;
import exceptions.NeuroneException;
import simulation.Simulation;
import voiture.Commande;

public class GeneticAlgorithm {
	
	private static final ComparatorGenome comp = new ComparatorGenome();
	private MutationOperator mop;
	private CrossOperator cop;
	private GenomeGenerator gen;
	private int pop;
	private ArrayList<Integer> struc;
	private Circuit c;
	private Dijkstra d;
	
	/**
	 * Constructor for a GeneticAlgorithm
	 * @param mop
	 * @param cop
	 * @param gen
	 * @param pop
	 * @param struc
	 * @param c
	 * @param d
	 */
	public GeneticAlgorithm(MutationOperator mop, CrossOperator cop, GenomeGenerator gen, int pop,
			ArrayList<Integer> struc, Circuit c, Dijkstra d) {
		super();
		this.mop = mop;
		this.cop = cop;
		this.gen = new GenomeGeneratorPerceptron(struc);
		this.pop = pop;
		this.struc = struc;
		this.c = c;
		this.d = d;
		d.compute();
	}
	
	/**
	 * Launch the algorithm for specified number of iteration. Saves best of each gen in a file nom_fichier + 
	 * generation number. Takes the best population/part for parenting next gen
	 * ie if part = 2 the best half is kept to parent next gen, if part = 4 we take the best quarter...
	 * @param iteration
	 * @param nom_fichier
	 * @param part
	 * @param dijkstra true if you want to use a RadarDijsktra, false otherwise
	 * @param target sets a goal for the simulation to stop if reached
	 * @param nb_param number of additional input in the first layer (ie speed, maxTurn etc...). Make sure you changed StrategyPerceptron accordingly
	 * @return
	 * @throws NeuroneException
	 * 
	 */
	public Genome optimize(int iteration, String nom_fichier,int part,boolean dijkstra, double target, int nb_param) throws NeuroneException {
		List<Genome> population = new ArrayList<Genome>();
		ArrayList<Double> best_score = new ArrayList<Double>();
		//initialisation : 
		for(int i = 0;i<this.getPop();i++) {
			Genome g = gen.getRandomGenome(-1, 1);
			population.add(g);
		}
		
		ArrayList<ArrayList<Commande>> best_genomes = new ArrayList<ArrayList<Commande>>();
		for(int i = 0;i<iteration;i++) {
			for(Genome g : population) {
				FitnessEvaluation fit = new FitnessEvaluation(c,d,g);
				fit.evaluate(dijkstra,nb_param);			
			}
			population.sort(comp.reversed());
//			uncomment to visualize improvement while the algorithm is running
//			if(i%5==0 && Math.abs(population.get(0).getScore())<1200000) {
//				FitnessEvaluation fit = new FitnessEvaluation(c,d,population.get(0));
//				fit.evaluateWithDisplay(dijkstra,nb_param);
//			}
			
//			uncomment to stop algorithm if target is reached.
//			if(Math.abs(population.get(0).getScore())<target) {
//				Simulation.saveListeCommande(population.get(0).getCommandes(),nom_fichier + " target atteinte");
//				GeneticTools.saveGenome(population.get(0),"genome " + nom_fichier + "target atteinte");
//				return population.get(0);
//			}
			best_score.add(population.get(0).getScore());
			System.out.println("generation " + i + ", best score : " + population.get(0).getScore());
			if(population.get(0).isFinish()) {
				if(best_genomes.size()==0) {
					best_genomes.add(population.get(0).getCommandes());
					Simulation.saveListeCommande(population.get(0).getCommandes(),nom_fichier + " " + i + " " + (int)(population.get(0).getScore()));
				}else {
					int score_precedent = best_genomes.get(best_genomes.size()-1).size();
					int score_courant = (int) population.get(0).getScore();
					if(score_courant != score_precedent) {
						best_genomes.add(population.get(0).getCommandes());
					}
				}
				
			}
			if(i>70) {
				if(best_score.get(i).intValue()==best_score.get(i-50).intValue()) {
//					String nom_bis = nom_fichier + " gen " + (i+1) +" score " + population.get(0).getScore() + " stagnation score";
//					Simulation.saveListeCommande(population.get(0).getCommandes(),nom_bis);
//					return population.get(0);
					break;
				}
			}
			if(i<iteration-1) {
				List<Genome> parents_l = population.subList(0, population.size()/part);
				ArrayList<Genome> parents = GeneticTools.listToArrayList(parents_l);
				ArrayList<Genome> nv_population = cop.crossPop(parents,part);
				mop.mutatePop(nv_population, 1, -1, 1, 0.8);
				nv_population.set(0, population.get(0));
//				nv_population.set(1,population.get(1));
//				nv_population.set(2,population.get(2));
//				nv_population.set(3,population.get(3));
				population = GeneticTools.arrayListtoList(nv_population);
					
			}
			
		}
		
		
		for(ArrayList<Commande> l_com : best_genomes) {
			String nom = nom_fichier + " " + best_genomes.indexOf(l_com) + " " +(int)l_com.size();
			Simulation.saveListeCommande(l_com, nom);
		}
		Simulation.saveListeCommande(population.get(0).getCommandes(),nom_fichier);
		GeneticTools.saveGenome(population.get(0),"genome " + nom_fichier);
		return population.get(0);
	}

	public MutationOperator getMop() {
		return mop;
	}

	public void setMop(MutationOperator mop) {
		this.mop = mop;
	}

	public CrossOperator getCop() {
		return cop;
	}

	public void setCop(CrossOperator cop) {
		this.cop = cop;
	}

	public GenomeGenerator getGen() {
		return gen;
	}

	public void setGen(GenomeGenerator gen) {
		this.gen = gen;
	}

	public int getPop() {
		return pop;
	}

	public void setPop(int pop) {
		this.pop = pop;
	}

	public ArrayList<Integer> getStruc() {
		return struc;
	}

	public void setStruc(ArrayList<Integer> struc) {
		this.struc = struc;
	}

	public static ComparatorGenome getComp() {
		return comp;
	}
	

}
