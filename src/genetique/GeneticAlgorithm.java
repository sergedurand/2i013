package genetique;

import java.util.List;
import java.util.ArrayList;

import algo.Dijkstra;
import circuit.Circuit;
import exceptions.NeuroneException;
import simulation.Simulation;

public class GeneticAlgorithm {
	
	private static final ComparatorGenome comp = new ComparatorGenome();
	private MutationOperator mop;
	private CrossOperator cop;
	private GenomeGenerator gen;
	private int pop;
	private ArrayList<Integer> struc;
	private Circuit c;
	private Dijkstra d;
	
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
	 * @return
	 * @throws NeuroneException
	 */
	public Genome optimize(int iteration, String nom_fichier,int part,boolean dijkstra, double target) throws NeuroneException {
		List<Genome> population = new ArrayList<Genome>();
		ArrayList<Double> best_score = new ArrayList<Double>();
		//initialisation : 
		for(int i = 0;i<this.getPop();i++) {
			Genome g = gen.getRandomGenome(-0.5, 1);
			population.add(g);
		}
		

		for(int i = 0;i<iteration;i++) {
			for(Genome g : population) {
				FitnessEvaluation fit = new FitnessEvaluation(c,d,g);
				fit.evaluate(dijkstra);			
			}
			
			population.sort(comp.reversed());
			if(Math.abs(population.get(0).getScore())<target) {
				Simulation.saveListeCommande(population.get(0).getCommandes(),nom_fichier + " target atteinte");
				GeneticTools.saveGenome(population.get(0),"genome " + nom_fichier + "target atteinte");
				return population.get(0);
			}
			best_score.add(population.get(0).getScore());
			System.out.println("generation " + i + ", best score : " + population.get(0).getScore());
			if(population.get(0).isFinish()) {
				String nom_bis = nom_fichier + " gen " + (i+1) +" score " + population.get(0).getScore();
				Simulation.saveListeCommande(population.get(0).getCommandes(),nom_bis);
			}
			//we keep the first 2 in the new population
			if(i<iteration-1) {
				List<Genome> parents_l = population.subList(0, population.size()/part);
				ArrayList<Genome> parents = GeneticTools.listToArrayList(parents_l);
				ArrayList<Genome> nv_population = cop.crossPop(parents,part);
				mop.mutatePop(nv_population, 1, -1, 1, 0.2);
				nv_population.set(0, population.get(0));
				nv_population.set(1,population.get(1));
				population = GeneticTools.arrayListtoList(nv_population);
					
			}
			
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
