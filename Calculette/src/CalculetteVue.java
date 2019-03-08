

import  javax.swing.*;
import  javax.swing.border.*;
import  java.awt.*;


public class CalculetteVue  extends JFrame
{
    private CalculetteModele        modele; 
    private CalculetteControleur    controleur;
    
    //la zone d'edition et de total
    private JTextField  edit;   
    
    private boolean total_affiche=false;

    /**
     * Constructeur de  CalculetteVue
     */
    public CalculetteVue(CalculetteModele modele)
    {
        this.modele=modele;
        
        controleur=new  CalculetteControleur(this,modele);
        
        JPanel  panel=new   JPanel();
        //panel qui contiendra tout
        panel.setLayout(new BorderLayout());    
        
        //La zone d'edition et de total
        edit=new    JTextField();
        panel.add(edit,BorderLayout.NORTH);
        
        //les boutons
        JButton but=null;
        JPanel  panel2=new  JPanel();
        
        //1
        but=new JButton("1");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //2
        but=new JButton("2");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //3
        but=new JButton("3");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //+
        but=new JButton("+");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //4
        but=new JButton("4");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //5
        but=new JButton("5");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //6
        but=new JButton("6");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //-
        but=new JButton("-");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //7
        but=new JButton("7");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //8
        but=new JButton("8");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //9
        but=new JButton("9");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //=
        but=new JButton("=");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //0
        but=new JButton("0");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //*
        but=new JButton("*");
        but.addActionListener(controleur);
        panel2.add(but);
        
        ///
        but=new JButton("/");
        but.addActionListener(controleur);
        panel2.add(but);
        
        ///
        but=new JButton("");
        but.addActionListener(controleur);
        panel2.add(but);
        
        //ajout du panel
        panel.add(panel2,BorderLayout.CENTER);
        
        //divers
        setContentPane(panel);
        pack();
        setTitle("Calculette");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    /**
     * ajoute un caractere a la saisie
     */
    public  void    ajouteSaisie(String t)
    {
        if  (total_affiche)
        {
            total_affiche=false;
            edit.setText("");
        }
        edit.setText(edit.getText()+t);
    }
    
    /**
     * efface 
     */
    public  void    efface()
    {
        edit.setText("");
    }
    
    /**
     * affiche le total
     */
    public  void    afficheTotal()
    {
        total_affiche=true;
        edit.setText(""+modele.getTotal());
    }
    
    /**
     * teste la validit� de l'entr�e
     */
    public  boolean entreeValide()
    {
        try{
            int n=Integer.parseInt(edit.getText());
            return  true;   //valide
        }catch(Exception e)
        {
            return  false;
        }
               
    }
    
    /**
     * affiche une erreur
     */
    public  void    erreur(String a)
    {
        JOptionPane.showMessageDialog(this,a,"Erreur !",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * donne le nombre entr�
     */
    public  int   getEntree()
    {
        return  Integer.parseInt(edit.getText());
    }
    
}
