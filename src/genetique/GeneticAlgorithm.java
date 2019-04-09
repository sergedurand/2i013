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
	
	public Genome optimize(int iteration, String nom_fichier) throws NeuroneException {
		List<Genome> population = new ArrayList<Genome>();
		//initialisation : 
		for(int i = 0;i<this.getPop();i++) {
			Genome g = gen.getRandomGenome(-1, 1);
			population.add(g);
		}
		

		for(int i = 0;i<iteration;i++) {
			for(Genome g : population) {
				FitnessEvaluation fit = new FitnessEvaluation(c,d,g);
				fit.evaluate();			
			}
			population.sort(comp.reversed());
			System.out.println("generation " + i + ", best score : " + population.get(0).getScore());
			if(population.get(0).isFinish()) {
				String nom_bis = nom_fichier + "generation " + (i+1);
				Simulation.saveListeCommande(population.get(0).getCommandes(),nom_bis);
			}
			//we keep the first 2 in the new population
			if(i<iteration-1) {
				List<Genome> parents_l = population.subList(0, population.size()/4);
				ArrayList<Genome> parents = GeneticTools.listToArrayList(parents_l);
				ArrayList<Genome> nv_population = cop.crossPop(parents);
				mop.mutatePop(nv_population, 0.1, -1, 1, 0.05);
				nv_population.set(0, population.get(0));
				nv_population.set(1,population.get(1));
				population = GeneticTools.arrayListtoList(nv_population);
			}
			
		}
		
		
		Simulation.saveListeCommande(population.get(0).getCommandes(),nom_fichier);

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
