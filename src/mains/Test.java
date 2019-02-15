package mains;
import geometrie.Vecteur;
import circuit.*;

import java.util.ArrayList;

import algo.*;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("1_safe.trk");


		
		Dijkstra dijk = new Dijkstra(c);
		dijk.compute();
		for (int i=0;i<dijk.getDist().length;i++) {
			for (int j=0;j<dijk.getDist()[0].length;j++) {
				System.out.print(dijk.getDist()[i][j]);
			}
			System.out.print("\n");
		}
		
		System.out.println(dijk.getDist()[(int) c.getPointDepart().getX()][(int) c.getPointDepart().getY()+1]);

	}

}
