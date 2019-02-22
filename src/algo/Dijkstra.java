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
		dist=new double[c.getWidth()][c.getHeight()];
		comp = new ComparatorDijk(dist);
		this.c = c;
		arrivees = c.getArrivees();
		q=new PriorityBlockingQueue<Vecteur>(arrivees.size(),comp);
		this.c = c;
		for (int i=0;i<c.getWidth();i++) {
			for (int j=0;j<c.getHeight();j++) {
				dist[i][j]= Double.POSITIVE_INFINITY;
			}
		}
		
		for (Vecteur p:arrivees) {
			dist[(int)p.getX()][(int)p.getY()]=0;
		}
		q.add(c.getPointDepart());
		
	}
	
	public ArrayList<Vecteur> getVoisin(Vecteur v){
		ArrayList<Vecteur> res = new ArrayList<Vecteur>();
		int x,y;
		x = (int) v.getX();
		y = (int) v.getY();
		for(int i = -1;i<=1;i++) {
			for(int j = -1;j<=1;j++) {
				int nx = x+i;
				int ny = y+j;
				Vecteur temp = new Vecteur(nx,ny);
				if(c.estDansCircuit(v) && (nx!=x|| ny!=y)) {
					res.add(temp);
				}
			}
			
		}
		
		return res;
	}
	
	
	public double[][] getDist() {
		return dist;
	}




	public void update(Vecteur s) {
		double x = s.getX();
		double y = s.getY();
		int poids = 1; //poids pour alourdir la distance sur les bandes ou la boue
		ArrayList<Vecteur> voisins = getVoisin(s);
		for(Vecteur v : voisins) {
			Vecteur ov = new Vecteur(v,s);
			double prodscal = ov.prodScal(c.getDirectionArrivee());
			if(arrivees.contains(s)) {
				if ((prodscal <= 0) && (TerrainTools.isRunnable(c.getTerrain(v)))){
					int i = (int) v.getX();
					int j = (int) v.getY();
					if (dist[i][j]==Double.POSITIVE_INFINITY) {
						if(dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)){
						if (s.getDistance(v)==1) {
								if(c.getTerrain(v) == Terrain.Boue) {
									dist[i][j]=10*2*poids;
								}
								else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
									dist[i][j]=10*1.5*poids;
								}
								else {
									dist[i][j]=10*poids;
								}
							}
							if (s.getDistance(v)>1) {
								if(c.getTerrain(v) == Terrain.Boue) {
									dist[i][j]=14*2*poids;
								}
								else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
									dist[i][j]=14*1.5*poids;
								}
								else {
									dist[i][j]=14*poids;
								}
							}
						q.add(v);
						}
						
					//	}
					else {
						//double score=dist[(int)x][(int)y];
						if(q.contains(v)) {
							q.remove(v);
							if(dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)){
								if (s.getDistance(v)==1) {
									if(c.getTerrain(v) == Terrain.Boue) {
										//score+=10*2*poids;
										dist[i][j]+=10*2*poids;
									}
									else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
										//score+=10*1.5*poids;
										dist[i][j]+=10*1.5*poids;
									}
									else {
										//score+=10*poids;
										dist[i][j]+=10*poids;
									}
								}
								if (s.getDistance(v)>1) {
									if(c.getTerrain(v) == Terrain.Boue) {
										//score+=14*2*poids;
										dist[i][j]+=14*2*poids;
									}
									else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
										//score+=14*1.5*poids;
										dist[i][j]+=14*1.5*poids;
									}
									else {
										//score+=14*poids;
										dist[i][j]+=14*poids;
									}
								}
							
							}
							q.add(v);
						}else {
							if(dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)){
								if (s.getDistance(v)==1) {
									if(c.getTerrain(v) == Terrain.Boue) {
										//score+=10*2*poids;
										dist[i][j]+=10*2*poids;
									}
									else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
										//score+=10*1.5*poids;
										dist[i][j]+=10*1.5*poids;
									}
									else {
										//score+=10*poids;
										dist[i][j]+=10*poids;
									}
								}
								if (s.getDistance(v)>1) {
									if(c.getTerrain(v) == Terrain.Boue) {
										//score+=14*2*poids;
										dist[i][j]+=14*2*poids;
									}
									else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
										//score+=14*1.5*poids;
										dist[i][j]+=14*1.5*poids;
									}
									else {
										//score+=14*poids;
										dist[i][j]+=14*poids;
									}
								}
							
							}
						q.add(v);
						}
							
					}
						
				}
			}
			}else {
				if ((TerrainTools.isRunnable(c.getTerrain(v)))){
					int i = (int) v.getX();
					int j = (int) v.getY();
					if (dist[i][j]==Double.POSITIVE_INFINITY) {
						if (s.getDistance(v)>1) {
							if(c.getTerrain(v) == Terrain.Boue) {
								dist[i][j]=14*poids;
							}
							else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
								dist[i][j]=14*1.5*poids;
							}
							else {
								dist[i][j]=14*poids;
							}
							
						}if (s.getDistance(v)==1){
							if(c.getTerrain(v) == Terrain.Boue && dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)) {

								dist[i][j]=10*2*poids;
							}
							else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {

								dist[i][j]=10*1.5*poids;
							}
							else {

								dist[i][j]=10*poids;
							}
							
							
						}
						q.add(v);
					}
					else {
						if(q.contains(v)) {
							q.remove(v);
							if(dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)){
								double score= dist[(int)x][(int)y];
								//if(dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)){
									if (s.getDistance(v)==1) {
										if(c.getTerrain(v) == Terrain.Boue) {
											//score+=10*2*poids;
											dist[i][j]+=10*2*poids;
										}
										else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
											//score+=10*1.5*poids;
											dist[i][j]+=10*1.5*poids;
										}
										else {
											//score+=10*poids;
											dist[i][j]+=10*poids;
										}
									}
									if (s.getDistance(v)>1) {
										if(c.getTerrain(v) == Terrain.Boue) {
											//score+=14*2*poids;
											dist[i][j]+=14*2*poids;
										}
										else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
											//score+=14*1.5*poids;
											dist[i][j]+=14*1.5*poids;
										}
										else {
											//score+=14*poids;
											dist[i][j]+=14*poids;
										}
									}
				
								}
							q.add(v);
						}else {
							if(dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)){
								double score= dist[(int)x][(int)y];
								//if(dist[i][j]>dist[(int)x][(int)y]+s.getDistance(v)){
									if (s.getDistance(v)==1) {
										if(c.getTerrain(v) == Terrain.Boue) {
											//score+=10*2*poids;
											dist[i][j]+=10*2*poids;
										}
										else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
											//score+=10*1.5*poids;
											dist[i][j]+=10*1.5*poids;
										}
										else {
											//score+=10*poids;
											dist[i][j]+=10*poids;
										}
									}
									if (s.getDistance(v)>1) {
										if(c.getTerrain(v) == Terrain.Boue) {
											//score+=14*2*poids;
											dist[i][j]+=14*2*poids;
										}
										else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
											//score+=14*1.5*poids;
											dist[i][j]+=14*1.5*poids;
										}
										else {
											//score+=14*poids;
											dist[i][j]+=14*poids;
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

	
	public void update2(Vecteur s) {
		double x = s.getX();
		double y = s.getY();
		int poids = 1; //poids pour alourdir la distance sur les bandes ou la boue
		ArrayList<Vecteur> voisins = getVoisin(s);
		double score=dist[(int)x][(int)y];
		for(Vecteur v : voisins) {
			Vecteur ov = new Vecteur(v,s);
			double prodscal = ov.prodScal(c.getDirectionArrivee());
			int i = (int) v.getX();
			int j = (int) v.getY();
			if ((TerrainTools.isRunnable(c.getTerrain(v)))){
				if (dist[i][j]==Double.POSITIVE_INFINITY) {
					if (s.getDistance(v)>1) {
						if(c.getTerrain(v) == Terrain.Boue) {
							dist[i][j]=14*2*poids;
						}
						else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
							dist[i][j]=14*1.5*poids;
						}
						else {
							dist[i][j]=14*poids;
						}
					}
					if (s.getDistance(v)==1) {
						if(c.getTerrain(v) == Terrain.Boue) {
							dist[i][j]=10*2*poids;
						}
						else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
							dist[i][j]=10*1.5*poids;
						}
						else {
							dist[i][j]=10*poids;
						}
					}
				}
				else {
					if (s.getDistance(v)>1) {
						if(c.getTerrain(v) == Terrain.Boue) {
							score+=14*2*poids;
						}
						else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
							score+=14*1.5*poids;
						}
						else {
							score+=14*poids;
						}
					}
					if (s.getDistance(v)==1) {
						if(c.getTerrain(v) == Terrain.Boue) {
							score+=10*2*poids;
						}
						else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
							score+=10*1.5*poids;
						}
						else {
							score+=10*poids;
						}
					}
					if (dist[i][j]+score>score) {
						dist[i][j]=score;
					}
					
					
					
				}
				
				
				
			}
			
			
		}
	}
	

	public void compute() {
		Vecteur s=q.poll();
		int i=0;
		while ((q.size()!=0)||(i==0)) {
			update(s);
			q.remove(s);
			System.out.println("update");
			s = q.peek();
			i++;
			}
		
	}
	
	

}
