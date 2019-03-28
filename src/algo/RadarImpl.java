package algo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import circuit.Circuit;
import geometrie.Vecteur;
import voiture.Voiture;
import terrain.*;

public class RadarImpl implements Radar {

	public RadarImpl(Voiture voiture, Circuit circuit, int nb_angles) {
		super();
		this.voiture = voiture;
		this.circuit = circuit;
		int taille;
		if(nb_angles%2 != 0) { //pour etre sur que la taille est paire
			taille=nb_angles+1;
		}
		else {
			taille = nb_angles;
		}
		this.angles = new double[taille];
		double angle = Math.PI/taille;
		for(int i = 1;i<=taille/2;i++) {
			angles[i-1]=angle*i;
			angles[taille-i]=angle*-i;
		}
		angles[taille/2]=0;
		this.distPix = new double[angles.length];
		BestIndex = 0;
	}

	
	public RadarImpl() {
		//TODO;
	}
	protected Voiture voiture;
	protected Circuit circuit;
	protected double[] angles;
	protected double[] distPix;
	protected int BestIndex;
	


	@Override
	public double[] scores(double epsilon) {
		double[] scores;
		int taille=angles.length;
		//System.out.println(taille);
		scores=new double[taille];
		double max = 0;
		int imax = 0;
		for (int i=0;i<taille;i++) {
			scores[i]=calcScore(angles[i],epsilon);
			//System.out.println("itération");
			if(scores[i]>=max) {
				max = scores[i];
				imax = i;
			}
			distPix[i]=scores[i]*epsilon;
		}
		this.BestIndex = imax;
		return scores;
	}

	@Override
	public double[] getDistPix() {
		return distPix;
	}

	@Override
	public int getBestIndex() {
		return BestIndex;
	}

	@Override
	public double[] getAngles() {
		// TODO Auto-generated method stub
		return angles;
	}
	
	
	
	protected double calcScore(double angle,double epsilon) {
		Vecteur p = voiture.getPosition().clone();
		Vecteur direction = voiture.getDirection().rotation(angle);
		int cpt=0;
		//System.out.println("Vecteur courant: "+p.toString());
		while ((circuit.estDansCircuit(p)&&(TerrainTools.charFromTerrain(circuit.getTerrain(p)) !='g'))) {
			cpt++;
			p = p.addition(direction.multiplication(epsilon));
		}
		return cpt;
	}

	@Override
	public String toString() {
		return "RadarImpl [voiture=" + voiture + ", circuit=" + circuit + ", angles=" + Arrays.toString(angles)
				+ ", distPix=" + Arrays.toString(distPix) + ", BestIndex=" + BestIndex + "]";
	}
	
	public Voiture getVoiture() {
		return voiture;
	}

	@Override
	public void traceRadar() {
		BufferedImage im = TerrainTools.imageFromTerrain(circuit.getTerrain());
		Graphics g = im.getGraphics();
		g.setColor(new Color(0, 0, 255));
		Vecteur vdir;
		for (int i=0;i<this.getDistPix().length;i++) {
			System.out.println(this.getDistPix()[i]);
			vdir=this.getVoiture().getDirection().rotation(this.getAngles()[i]);
			vdir=vdir.normalisation();
			vdir=vdir.multiplication(this.getDistPix()[i]);
			System.out.println("vdir=" + vdir.toString());
			g.drawLine((int)voiture.getPosition().getX(),(int)voiture.getPosition().getY(), (int)vdir.getX()+(int)voiture.getPosition().getX(), (int)vdir.getY()+(int)voiture.getPosition().getY());
		}
		
		try {
            File outputfile = new File("apresradar.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }
	}

	@Override
	public void init(Voiture v,Circuit c) {
		// TODO Auto-generated method stub
		circuit=c;
		voiture=v;
		
	}
	
	@Override
	public double getDistMin() {
		double min=Double.POSITIVE_INFINITY;
		for (int i=0;i<distPix.length;i++) {
			if (distPix[i]<min && distPix[i]!=0) {
				min=distPix[i];
			}
		}
		return min;
	}

}
