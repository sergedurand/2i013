package genetique;

import java.util.List;
import java.util.ArrayList;

import algo.Dijkstra;
import circuit.Circuit;

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
	
	public Genome GenomeOptimize(int iteration) {
		List<Genome> population = new ArrayList<Genome>();
		//initialisation : 
		for(int i = 0;i<this.getPop();i++) {
			Genome g = gen.getRandomGenome(-1, 1);
			population.add(g);
		}
		
		//first loop:
		for(int i = 0;i<iteration;i++) {
			for(Genome g : population) {
				FitnessEvaluation fit = new FitnessEvaluation(c,d,g);
				fit.evaluate();
				
			}
			population.sort(comp.reversed());
			//we keep the first 2 in the new population
			List<Genome> parents = population.subList(0, population.size()/2);
		}
		
		return null;
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
