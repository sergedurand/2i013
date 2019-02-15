package circuit;

import java.util.ArrayList;

import geometrie.Vecteur;
import terrain.Terrain;
import terrain.TerrainTools;

public class CircuitImpl implements Circuit {
	private Terrain[][] terrain;
	private Vecteur dirDepart;
	private Vecteur dirArrivee;
	
	public CircuitImpl(Terrain t[][],Vecteur dirDepart,Vecteur dirArrivee) {
		this.terrain = t;
		this.dirDepart=dirDepart;
		this.dirArrivee=dirArrivee;
	}

	@Override
	public Terrain getTerrain(int i, int j) {
		// TODO gérer les erreurs avec des exceptions
		if(i < 0 || i> this.getWidth() || j < 0 || j>this.getHeight()) {
			System.out.println("Coordonnees hors terrain");
			return null;
		}
		return this.terrain[i][j];
	}

	@Override
	public Terrain getTerrain(Vecteur v) {
		return this.getTerrain((int)v.getX(), (int)v.getY());
	}

	@Override
	public Vecteur getPointDepart() {
		// TODO Auto-generated method stub
		Vecteur depart = null;
		for(int i =0;i<this.getWidth();i++) {
			for(int j = 0;j<this.getHeight();j++) {
				if(TerrainTools.charFromTerrain(this.terrain[i][j]) == '*') {
					depart = new Vecteur(i,j);
				}
			}
		}
		return depart;
	}

	@Override
	public Vecteur getDirectionDepart() {
		// TODO Auto-generated method stub
		return dirDepart;
	}

	@Override
	public Vecteur getDirectionArrivee() {
		// TODO Auto-generated method stub
		return dirArrivee;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.terrain.length;
	}

	@Override
	public int getHeight() {
		if(this.getWidth()==0) {
			return 0;
		}
		return this.terrain[1].length ;
	}

	@Override
	public ArrayList<Vecteur> getArrivees() {
		ArrayList<Vecteur> a = new ArrayList();
		for(int i =0;i<this.getHeight();i++) {
			for(int j = 0;j<this.getWidth();j++) {
				//System.out.println("x = " + i +"y = "+ j);
				if(TerrainTools.charFromTerrain(this.terrain[j][i]) == '!') {
					Vecteur cour = new Vecteur(i,j);
					a.add(cour);
				}
			}
		}
		
		return a;
	}

	@Override
	public double getDist(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Terrain[][] getTerrain() {
		return terrain;
	}
	
	public boolean estDansCircuit(Vecteur v) {
		if (v.getX()<0 || v.getX()>=this.getWidth()) {
			return false;
		}
		if (v.getY()<0 || v.getY()>=this.getHeight()) {
			return false;
		}
		return true;
	}


}
