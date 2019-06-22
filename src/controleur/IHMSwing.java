package controleur;
import observeurs.*;
import simulation.*;
import strategy.*;
import terrain.TerrainTools;
import circuit.*;
import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modele.Modele;


public class IHMSwing extends JPanel implements UpdateEventListener,ActionListener,ChangeListener,MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ObserveurSwing> lobs;
	private BufferedImage im;
	private CircuitObserveur circuit; 
	private Simulation simu;
	private Modele m;
	private File f;
	private int nbfaisceaux=6;
	private ArrayList<Point> listepoints;
	private boolean StratPoint=false;
	
	public IHMSwing(Modele m) {
		super();
		lobs = new ArrayList<ObserveurSwing>(0);
		this.im = null;
		this.m=m;
		listepoints=new ArrayList<Point>();
	}
	
	public IHMSwing(BufferedImage im){
		super();
		this.im=im;
		
	}
	
	public void clean() {
		lobs=new ArrayList<ObserveurSwing>();
	}
	
	public void addCircuit(Circuit c) {//construction a partir d'un circuit
		circuit = new CircuitObserveur(c);
		im = TerrainTools.imageFromTerrain(c.getTerrain());
		
		}
	public void init(String nom) {

	}
	
	public void add(Simulation simu) {
		this.simu = simu;
	}
	
	public void add(ObserveurSwing o) {
		lobs.add(o);
	}
	
	public void setImage(BufferedImage im) {
		this.im = im;
	}
	
	public BufferedImage getImage() {
		return im;
	}
	
	public void paint(Graphics g){
		//System.out.println("1.5 - Je suis dans paint!!!");
		super.paint(g);
		circuit.print(g);
        for(ObserveurSwing o: lobs) {
        	/* nécessite de gérer les exceptions dans les observeurs*
        	 * try {
        	 
        		o.print(g);
        	}catch(IOException e) {
        		e.printStackTrace();
        	}*/
        	if(o!=null) {
        		if (o instanceof VoitureObserveur) {
        			for (Point p: listepoints) {
        				((VoitureObserveur)o).add(p);
        			}
            		
        		}
        		try {
        		o.print(g);
        		}catch(ConcurrentModificationException e) {
        			
        		}
        	}

        }
	}
	//@Override
	public void manageUpdate(int sleep) {
		repaint();
		/*try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                repaint();
            }
        });*/
		Graphics g=im.getGraphics();
		//circuit.print(g);
        for(ObserveurSwing o: lobs) {
        	/* nécessite de gérer les exceptions dans les observeurs*
        	 * try {
        	 
        		o.print(g);
        	}catch(IOException e) {
        		e.printStackTrace();
        	}*/
        	if(o!=null) {
        		int score=simu.getScore();
        		if (o instanceof VoitureObserveur) {
        			((VoitureObserveur) o).setScore(score);
        		}
        		o.print(g);
        	}
        }   
        //System.out.println("Fini de repaint");

	}
	

	
	public void virage() {
		Graphics g=im.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(50,50, 200, 200);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//
			//
				if (e.getSource() instanceof JButton) {
					new Thread(new Runnable() {
						public void run() {
							String  s=((JButton) e.getSource()).getText();
						//	System.out.println(s);
							if  (s.compareTo("Start")==0) {					
								runSimu();
								/*SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										
										System.out.println("Je lance la simulation");
									}
								});*/
								
								
							}
							if (s.compareTo("Stop")==0) {
								//System.out.println("J'arrête la simulation");
								stopSimu();
							}
							
							if (s.compareTo("Chargement d'une liste de commandes")==0) {
								//System.out.println("Je veux un fichier");
								if (selectFile()) {
									m.listecommandes(f);
									
								}
								
							}
							
							if (s.compareTo("Restart")==0) {
								stopSimu();
								m.restart();
							}
							
							
						}
						
						
						
						
						}).start();
				}
				
			//}
		
		//}).start();
		
		if (e.getSource() instanceof JComboBox) {
			JComboBox cb=(JComboBox) e.getSource();
			String etiquette=(String)cb.getSelectedItem();
			//System.out.println(etiquette);
			String fin=etiquette.substring(etiquette.length()-3, etiquette.length());
			if (fin.compareTo("trk")==0) {
				m.setCircuit(etiquette);
			}
			if (etiquette.compareTo("Simple")==0 || etiquette.compareTo("Dijkstra")==0 ||
			etiquette.compareTo("Prudente")==0 ) {
				m.setStrategie(etiquette,true);
			}
			
			if (etiquette.compareTo("Rouge")==0||etiquette.compareTo("Vert")==0||etiquette.compareTo("Jaune")==0
					||etiquette.compareTo("Bleu")==0||etiquette.compareTo("Cyan")==0||etiquette.compareTo("Violet")==0){
				String nomvoiture=etiquette;
				m.setCouleurVoiture(nomvoiture);
			}
			
			if (etiquette.compareTo("Genetique")==0) {
				m.genetique();
			}
			
			if (etiquette.compareTo("Point à point")==0) {
				m.pointapoint();
			}
			
		}
		
	}
	
	public void stopSimu() {
		m.stopSimu();
	}
	
	public void runSimu() {
		m.startSimu();
	}

	
	public boolean selectFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            this.f=f;
           // System.out.println(f.getPath());
        } else {
        	return false;
        }
        m.circuitCharge();
        return true;
    }

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		int valeur = (int)source.getValue();
		if (source.getName().compareTo("faisceaux")==0) {
			if (!source.getValueIsAdjusting()) {
				m.setNbfaisceaux(valeur);
			}
		}
		if (source.getName().compareTo("population")==0) {
			if (!source.getValueIsAdjusting()) {
				m.setPopulation(valeur);
			}
		}
		if (source.getName().compareTo("generation")==0) {
			if (!source.getValueIsAdjusting()) {
				m.setGeneration(valeur);
			}	
		}
		if (source.getName().compareTo("mutation")==0) {
			if (!source.getValueIsAdjusting()) {
				m.setMutation(valeur);
			}
		}
		
		
		
		//System.out.println("Je set le nombre de faisceaux à "+faisceaux);
		
	}
	
	public void setStratPoint(boolean b) {
		StratPoint=b;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (StratPoint) {
			Point p=new Point((int)arg0.getPoint().getX()-12,(int)arg0.getPoint().getY()-35);
			listepoints.add(p);
			System.out.println(arg0.getPoint()+" ajouté à la liste de points");
			
			if (m.getStrategy() instanceof StrategyPoint) {
				((StrategyPoint)m.getStrategy()).addPoint(new Vecteur(p.getX(),p.getY()));
			}
		}		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}