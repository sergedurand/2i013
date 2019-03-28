package mains;
import simulation.Simulation;
import strategy.Strategy;
import strategy.StrategyRadarSimple;
import terrain.*;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import circuit.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import algo.*;


public class Test {

	public static void main(String[] args) throws VoitureException {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("bond_safe.trk");
		Voiture v = VoitureFactory.build(c);
	
		int taille = 64;
		double angle = Math.PI/64;
		double[] angles = new double[taille+1];
		for(int i=1;i<=taille/2;i++) {
			angles[i-1]=angle*i;
			angles[angles.length-i]=angle*-i;
		}
		angles[32]=0;
		Dijkstra dijk = new Dijkstra(c);
		dijk.compute();
		Radar r=new RadarDijkstra(v,c,64,dijk);
		//Radar r=new RadarImpl(v,c,angles);
		Strategy strat = new StrategyRadarSimple(r);
		//Simulation s1 = new Simulation(v,strat,c);
		ArrayList<Integer> l1 = new ArrayList<Integer>();
        l1.add(1);
        l1.add(2);
        ArrayList<Integer> l2 = l1;
        for(int i : l1){
            System.out.println("l1 " + i);
        }
        l2.remove(1);
       System.out.println("l2");
       for(int i : l2){
            System.out.println("l2 " + i);
        }
       for(int i : l1){
           System.out.println("l1 " + i);
       }

	}
	
		

	

}
