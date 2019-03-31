package mains;
import java.util.ArrayList;

import perceptron.*;

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
		System.out.println(perceptron.toString());
		perceptron.setRandomWeightsBias(-10, 10);
		System.out.println(perceptron.toString());
		

	}

}
