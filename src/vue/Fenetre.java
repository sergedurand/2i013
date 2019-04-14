package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.*;

import modele.Modele;
import controleur.IHMSwing;

public class Fenetre extends JFrame{	
	//Controleur con;
	
	
	


	public Fenetre(JPanel ihm, String circuit, String strategie,IHMSwing i,int slidervalue,String couleur) {
		//JPanel  panel=new   JPanel();
        //panel qui contiendra tout
        ihm.setLayout(new BorderLayout());
		
        JButton but=null;
        JPanel  panel2=new  JPanel();
        GridBagLayout g=new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel2.setLayout(g);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        //gbc.insets = new Insets(0,0,0,20);
        but=new JButton("Start");
        but.addActionListener(i);
        panel2.add(but,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        
        but=new JButton("Stop");
        but.addActionListener(i);
        panel2.add(but,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
       
        but=new JButton("Restart");
        but.addActionListener(i);
        panel2.add(but,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,0,0,0);
        JLabel label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Choix de la stratégie:");
        panel2.add(label1,gbc);  
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(10,0,0,0);
        String[] choices = { "Simple","Dijkstra","Prudente","Point à point","Genetique"};

        JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setSelectedItem(strategie);
        cb.addActionListener(i);
        panel2.add(cb,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,0,0,0);
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Choix du circuit:");
        panel2.add(label1,gbc);
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(10,0,0,0);
        String[] choices2 = { "1_safe.trk", "2_safe.trk", "3_safe.trk", "4_safe.trk", "5_safe.trk", "6_safe.trk","7_safe.trk","8_safe.trk","aufeu.trk","bond_safe.trk"
        ,"Een2.trk","labymod.trk","labyperso.trk","perso.trk","t260_safe.trk"};

        cb = new JComboBox<String>(choices2);
        cb.setSelectedItem(circuit);
        cb.addActionListener(i);
        panel2.add(cb,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
       // gbc.ipadx = 40;  
        //gbc.weightx = 50000;
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
       // gbc.ipadx = 40;  
        //gbc.weightx = 50000;
        but=new JButton("Chargement d'une liste de commandes");
        but.addActionListener(i);
        panel2.add(but,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;  
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Nombre de faisceaux du radar:");
        panel2.add(label1,gbc);
        
        
        JSlider slider=new JSlider(0,100,50);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(162, 192, 202));
        slider.setValue(slidervalue);
        slider.setName("faisceaux");
        gbc.gridx = 2;
        gbc.gridy = 7;
        //gbc.gridwidth = 2;
        panel2.add(slider,gbc);
        slider.addChangeListener(i);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,0,0,0);
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Couleur de la voiture:");
        panel2.add(label1,gbc);
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(10,0,0,0);
        String[] ch = { "Rouge","Vert","Bleu","Jaune","Cyan","Violet"};

        cb = new JComboBox<String>(ch);
        cb.setSelectedItem(couleur);
        cb.addActionListener(i);
        panel2.add(cb,gbc);
        
        //System.out.println("je veux la couleur "+couleur);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;  
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Population de l'algorithme génétique");
        panel2.add(label1,gbc);
        
        
        slider=new JSlider(0,200,50);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(50);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(162, 192, 202));
        slider.setValue(20);
        slider.setName("population");
        gbc.gridx = 2;
        gbc.gridy = 9;
        //gbc.gridwidth = 2;
        panel2.add(slider,gbc);
        slider.addChangeListener(i);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;  
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Générations de l'algorithme génétique");
        panel2.add(label1,gbc);
        
        
        slider=new JSlider(0,50,25);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(162, 192, 202));
        slider.setValue(10);
        slider.setName("generation");
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        panel2.add(slider,gbc);
        slider.addChangeListener(i);
        
        
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        JLabel label2 = new JLabel("Test",SwingConstants.CENTER);
        label2.setText("Pourcentage de mutation génétique");
        panel2.add(label2,gbc);
        
        
        slider=new JSlider(0,5,2);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(162, 192, 202));
        slider.setValue(2);
        slider.setName("mutation");
        gbc.gridx = 2;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        panel2.add(slider,gbc);
        slider.addChangeListener(i);
        
        
        panel2.setBackground(new Color(162, 192, 202));
        
        this.add(panel2,BorderLayout.EAST);
        this.setPreferredSize(new Dimension(1200,1024));
		this.getContentPane().add(ihm);
		this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//i.runSimu();
	}
	
	
	
	
}