package algo;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import circuit.Circuit;
import geometrie.Vecteur;
import strategy.Strategy;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Commande;
import voiture.Voiture;

public class Dijkstra{	
	private PriorityBlockingQueue<Vecteur> q= new PriorityBlockingQueue<Vecteur>();
	private double[][] dist;
	private Circuit c;
	private ComparatorDijk comp;
	private ArrayList<Vecteur> arrivees;
	
	
	public Dijkstra(Circuit c) {
		super();
		dist=new double[c.getHeight()][c.getWidth()];
		comp = new ComparatorDijk(dist);
		this.c = c;
		arrivees = c.getArrivees();
		q=new PriorityBlockingQueue<Vecteur>(arrivees.size(),comp);
		this.c = c;
		for (int i=0;i<c.getHeight();i++) {
			for (int j=0;j<c.getWidth();j++) {
				dist[i][j]= Double.POSITIVE_INFINITY;
			}
		}
		
		for (Vecteur p:arrivees) {
			dist[(int)p.getX()][(int)p.getY()]=0;
			q.add(p);
		}
		
	}
	
	
	
	public double[][] getDist() {
		return dist;
	}




	public void update(Vecteur s) {
		double x = s.getX();
		double y = s.getY();
		int poids = 1; //poids pour alourdir la distance sur les bandes ou la boue
		for (int i=-1;i<2;i++) { //exploration des voisins
			for (int j=-1;j<2;j++) {
				Vecteur v; //voisin courant
				if ((i!=0)&&(j!=0)) { //on elimine le cas v = s
					v=new Vecteur(x+i,y+j);
					Vecteur ov=new Vecteur(s,v);
					double prodscal=ov.prodScal(c.getDirectionArrivee());
					if ((prodscal<=0)&&c.estDansCircuit(v)&&(TerrainTools.isRunnable(c.getTerrain(v)))){ //on ne considere que les voisins dans le bon sens, 
						//dans un terrain roulable et dans le terrain
						if (dist[(int)x+i][(int)y+j]==Double.POSITIVE_INFINITY) {
							if (s.getDistance(v)==1) {
								if(c.getTerrain(v) == Terrain.Boue && dist[(int)x+i][(int)y+j]>dist[(int)x][(int)y]+s.getDistance(v)) {
									dist[(int)x+i][(int)y+j]=10*2*poids;
								}
								else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
									dist[(int)x+i][(int)y+j]=10*1.5*poids;
								}
								else {
									dist[(int)x+i][(int)y+j]=10*poids;
								}
								
							}
							if (s.getDistance(v)>1) {
								if(c.getTerrain(v) == Terrain.Boue) {
									dist[(int)x+i][(int)y+j]=14*poids;
								}
								else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
									dist[(int)x+i][(int)y+j]=14*1.5*poids;
								}
								else {
									dist[(int)x+i][(int)y+j]=14*poids;
								}
							}
						}
						else {
							if(dist[(int)x+i][(int)y+j]>dist[(int)x][(int)y]+s.getDistance(v)){
								if (s.getDistance(v)==1) {
									if(c.getTerrain(v) == Terrain.Boue) {
										dist[(int)x+i][(int)y+j]+=10*2*poids;
									}
									else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
										dist[(int)x+i][(int)y+j]+=10*1.5*poids;
									}
									else {
										dist[(int)x+i][(int)y+j]+=10*poids;
									}
								}
								if (s.getDistance(v)>1) {
									if(c.getTerrain(v) == Terrain.Boue) {
										dist[(int)x+i][(int)y+j]+=14*2*poids;
									}
									else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
										dist[(int)x+i][(int)y+j]+=14*1.5*poids;
									}
									else {
										dist[(int)x+i][(int)y+j]+=14*poids;
									}
								}
							}
						q.add(v);
						}
					}
				}
			}
		}
	}
	
	
	public void compute() {
		Vecteur s=q.poll();
		double x = s.getX();
		double y = s.getY();
		int poids = 1; //poids pour alourdir la distance sur les bandes ou la boue
		for (int i=-1;i<2;i++) { //exploration des voisins
			for (int j=-1;j<2;j++) {
				Vecteur v; //voisin courant
				if ((i!=0)&&(j!=0)) { //on elimine le cas v = s
					System.out.println("itération");
					v=new Vecteur(x+i,y+j);
					Vecteur ov=new Vecteur(s,v);
					double prodscal=ov.prodScal(c.getDirectionArrivee());
					System.out.println("Terrain : " + c.getTerrain(v).toString());
					System.out.println("prodscal : "+ (prodscal<=0) + ", runnable : " + (TerrainTools.isRunnable(c.getTerrain(v))+ ", est dans circuit : " + c.estDansCircuit(v) ));
					if ((prodscal<=0)&&c.estDansCircuit(v)&&(TerrainTools.isRunnable(c.getTerrain(v)))){ //on ne considere que les voisins dans le bon sens, 
						//dans un terrain roulable et dans le terrain
						System.out.println("itération");
							if (s.getDistance(v)==1) {
								if(c.getTerrain(v) == Terrain.Boue && dist[(int)x+i][(int)y+j]>dist[(int)x][(int)y]+s.getDistance(v)) {
									dist[(int)x+i][(int)y+j]=10*2*poids;
								}
								else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
									dist[(int)x+i][(int)y+j]=10*1.5*poids;
								}
								else {
									dist[(int)x+i][(int)y+j]=10*poids;
								}
								
							}
							if (s.getDistance(v)>1) {
								if(c.getTerrain(v) == Terrain.Boue) {
									dist[(int)x+i][(int)y+j]=14*poids;
								}
								else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
									dist[(int)x+i][(int)y+j]=14*1.5*poids;
								}
								else {
									dist[(int)x+i][(int)y+j]=14*poids;
								}
							}
						}
						q.add(v);
						
					}
				}
			}
		int i = 1;
		while (q.size()!=0) {
			update(s);
			System.out.println(i);
			i++;
			s = q.poll();
			System.out.println("taille" + q.size());
		}
		
	}
	
	

}
