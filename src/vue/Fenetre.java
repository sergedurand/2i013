package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

public class Fenetre extends JFrame{	
	public Fenetre(JPanel ihm, String s) {
		super(s);
		
		
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
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(0,0,0,20);
        but=new JButton("Start");
        panel2.add(but,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        
        but=new JButton("Stop");
        panel2.add(but,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,0,0,0);
        JLabel label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Choix de la stratégie:");
        panel2.add(label1,gbc);  
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(10,0,0,0);
        String[] choices = { "Simple","Dijkstra","Prudente","Point à point","Génétique"};

        JComboBox<String> cb = new JComboBox<String>(choices);
        panel2.add(cb,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,0,0,0);
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Choix du circuit:");
        panel2.add(label1,gbc);
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(10,0,0,0);
        String[] choices2 = { "1_safe.trk", "2_safe.trk", "3_safe.trk", "4_safe.trk", "5_safe.trk", "6_safe.trk","7_safe.trk","8_safe.trk","aufeu.trk","bond.trk"
        ,"Een2.trk","labymod.trk","labyperso.trk","perso.trk","t260_safe.trk"};

        cb = new JComboBox<String>(choices2);
        panel2.add(cb,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
       // gbc.ipadx = 40;  
        //gbc.weightx = 50000;
        but=new JButton("Chargement d'un génome");
        panel2.add(but,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
       // gbc.ipadx = 40;  
        //gbc.weightx = 50000;
        but=new JButton("Chargement d'une liste de commandes");
        panel2.add(but,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;  
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Nombre de faisceaux du radar:");
        panel2.add(label1,gbc);
        
        
        JTextField edit = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 5;
        //gbc.gridwidth = 2;
        panel2.add(edit,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,0,0,0);
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Couleur de la voiture:");
        panel2.add(label1,gbc);
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(10,0,0,0);
        String[] ch = { "Rouge","Vert","Bleu","Jaune","Noire","Blanche"};

        cb = new JComboBox<String>(ch);
        panel2.add(cb,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,0,0,0);
        label1 = new JLabel("Test",SwingConstants.CENTER);
        label1.setText("Activer la 3D:");
        panel2.add(label1,gbc);
        
        JCheckBox boite=new JCheckBox();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        boite.setBackground(new Color(162, 192, 202));
        panel2.add(boite,gbc);
        
        panel2.setBackground(new Color(162, 192, 202));
       /* gbc.gridx = 2;
        gbc.gridy = 3;
        JFileChooser fc = new JFileChooser();;
        panel2.add(fc,gbc);*/
        
        
        
        
        
        
        
        
        
        this.add(panel2,BorderLayout.EAST);
        
		this.getContentPane().add(ihm);
		this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
}
