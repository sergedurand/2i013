package algo;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;
import circuit.Circuit;
import geometrie.Vecteur;
import terrain.Terrain;
import terrain.TerrainTools;
 
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
			//System.out.println("Arrive�s: "+p.toString());
			dist[(int)p.getX()][(int)p.getY()]=0;
			q.add(p);
		}	
		dist[(int)c.getPointDepart().getX()][(int)c.getPointDepart().getY()]=0;
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
				//System.out.println(temp.toString());
				if(c.estDansCircuit(v)&&(nx!=x|| ny!=y)&&(nx>=0)&&(nx<c.getWidth())&&(ny>=0)&&(ny<c.getHeight())) {
					res.add(temp);
				}
			}
			
		}
		
		return res;
	}
	
	
	public double[][] getDist() {
		return dist;
	}

	public void update3(Vecteur s) {
		double x = s.getX();
		double y = s.getY();
		int coef = 1; //poids pour alourdir la distance sur les bandes ou la boue
		ArrayList<Vecteur> voisins = getVoisin(s);
		
		for (Vecteur v: voisins) {
			double score= dist[(int)x][(int)y];
			int i=(int)v.getX();
			int j=(int)v.getY();
			if ((i) < 0 || (i) > dist.length || (j) < 0 || (j) > dist[0].length || !TerrainTools.isRunnable((c.getTerrain(i,j))))
						continue;
			if (c.getTerrain(s)==Terrain.EndLine) {
					//System.out.println(v.soustraction(c.getPointDepart()).toString());
					if ((c.getDirectionArrivee().prodScal(v.soustraction(c.getPointDepart()))<0)) {
						continue;
					}
			}
				//System.out.println(v.toString());
			if (c.getTerrain(v)==Terrain.Boue) {
				coef=1;
			}
			else if(c.getTerrain(v)== Terrain.BandeBlanche || c.getTerrain(v)==Terrain.BandeRouge) {
				coef=1;
			}
			else {
				coef=1;
			}
				
			if (s.getDistance(v)>1) {
				score += 14 * coef;
			}
			else {
				score += 10 * coef;
			}
			
			if (dist[i][j] > score) {
					dist[i][j] = score;
					q.add(v);
			}
		}		
	}

	public void compute() {

		while ((q.size()!=0)) {
			Vecteur s=q.poll();
			update3(s);
			q.remove(s);

		}
		for (int i=0;i<c.getWidth();i++) {
			for (int j=0;j<c.getHeight();j++) {
				dist[i][j]=dist[i][j];
			}
		}
		
	}
}