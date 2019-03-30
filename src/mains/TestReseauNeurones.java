package mains;
import java.util.ArrayList;

import perceptron.*;

public class TestReseauNeurones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Neurone n1 = new Neurone(1);
		n1.setRandomPoidsSortant(5, 4, -10, 10);
		System.out.println(n1.toString());
		CoucheNeurone couche1 = new CoucheNeurone(5);
		System.out.println(couche1.getTaille());
		System.out.println(couche1.getNeurones().size());
		couche1.setRandomWeightsBias(5, 4, -10, 10);
		System.out.println(couche1.toString());
		
		ArrayList<Integer> structurebasique = new ArrayList<Integer>();
		structurebasique.add(3);
		structurebasique.add(2);
		
//		Perceptron perceptron = new Perceptron(structurebasique);
//		perceptron.setRandomWeightsBias(-10, 10);
//		System.out.println(perceptron.toString());
		ArrayList<ArrayList<Double>> l = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> lpasvide = new ArrayList<Double>();
		lpasvide.add(3.3);
		
		l.add(new ArrayList<Double>());
		l.add(lpasvide);
		System.out.println(l.size());
		System.out.println(l.get(1).toString());

	}

}
