package mains;
import simulation.Simulation;
import strategy.Strategy;
import strategy.StrategyRadarSimple;
import terrain.*;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import circuit.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import algo.*;


public class Test {

	public static void main(String[] args) throws VoitureException {
		// TODO Auto-generated method stub
		Circuit c = CircuitFactoryFromFile.build("aufeu.trk");
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
		Radar r=new RadarDijkstra(v,c,angles,dijk);
		Strategy strat = new StrategyRadarSimple(r);
		Simulation s1 = new Simulation(v,strat,c);
		BufferedImage im = TerrainTools.imageFromTerrain(c.getTerrain());
		Graphics g = im.getGraphics();
		/*for(int i = 0;i<c.getWidth();i++) {
			for(int j = 0;j<c.getHeight();j++) {
				//System.out.println(dijk.getDist()[i][j]);
				//System.out.println("couleur rouge:"+dijk.getDist()[i][j]%255);
				g.setColor(new Color((int) (dijk.getDist()[i][j]%255),0,0));
				if(TerrainTools.isRunnable(c.getTerrain(i, j))) {
					//g.drawLine(i, j, i, j);
				}
			}
		}*/
		r.scores(0.1);
		s1.play(5000);
		/*try {
            File outputfile = new File("dijkstraa.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }*/

	}
	
		

	

}
