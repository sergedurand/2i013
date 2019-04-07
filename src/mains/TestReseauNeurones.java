package mains;
import java.util.ArrayList;

import perceptron.*;
import genetique.*;

public class TestReseauNeurones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Neurone n1 = new Neurone(1);
		n1.setRandomPoidsSortant(5, -10, 10);
		//System.out.println(n1.toString());
		
		ArrayList<Integer> structurebasique = new ArrayList<Integer>();
		structurebasique.add(3);
		structurebasique.add(3);
		structurebasique.add(3);
		structurebasique.add(3);
		structurebasique.add(2);
		
		Perceptron perceptron = new Perceptron(structurebasique);
		//System.out.println(perceptron.toString());
		perceptron.setRandomWeightsBias(-1, 1);
		//System.out.println(perceptron.toString());
		ArrayList<Double> valeur_entree = new ArrayList<Double>();
		valeur_entree.add(20.);
		valeur_entree.add(150.2);
		valeur_entree.add(50.4);
		perceptron.getEntree().setValues(valeur_entree);
		
		ArrayList<Double> res = perceptron.getResultat();
		//System.out.println(res.toString());
		ArrayList<ArrayList<ArrayList<Double>>> lpoids = new ArrayList<ArrayList<ArrayList<Double>>>();
		ArrayList<ArrayList<Double>> biais = new ArrayList<ArrayList<Double>>();
		//Premiere couche de neurone:
		ArrayList<ArrayList<Double>> couche1 = new ArrayList<ArrayList<Double>>();
		for(int i = 0;i<3;i++) {
			ArrayList<Double> neurone = new ArrayList<Double>();
			for(int j = 0;j<4;j++) {
				neurone.add(0.5);
			}
			couche1.add(neurone);
		}
		lpoids.add(couche1);
		//2e couche
		ArrayList<ArrayList<Double>> couche2 = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> biais2 = new ArrayList<Double>();
		for(int i = 0;i<4;i++) {
			ArrayList<Double> neurone = new ArrayList<Double>();
			biais2.add(0.8);
			for(int j = 0;j<2;j++) {
				neurone.add(0.8);
			}
			couche2.add(neurone);
		}
		lpoids.add(couche2);
		biais.add(biais2);
		ArrayList<Double> biais_sortie = new ArrayList<Double>();
		biais_sortie.add(0.3);
		biais_sortie.add(0.3);

		Perceptron p2 = new Perceptron(lpoids,biais);
		
		GenomeGeneratorPerceptron gen = new GenomeGeneratorPerceptron(structurebasique);
		Genome g1 = gen.getRandomGenome(-1, 1);
		p2 = new Perceptron(g1.getListe_poids(),g1.getListe_biais());
		System.out.println(p2.toString());
		
		Genome g2 = gen.getRandomGenome(-1, 1);
		
		CrossOperator cOp = new CrossOperator();
		
		Genome g3 = cOp.cross(g1, g2, 0.5);
		System.out.println(g3.toString());

	}

}
