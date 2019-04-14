import  java.awt.event.*;
import  javax.swing.*;

public class CalculetteControleur   implements  ActionListener
{
    private CalculetteVue       vue;
    private CalculetteModele    modele;
    
   
    public CalculetteControleur(CalculetteVue vue,CalculetteModele modele)
    {
        this.vue=vue;
        this.modele=modele;
    }

    public void actionPerformed(ActionEvent e)
    {
        String  s=((JButton) e.getSource()).getText();
        
        if  (s.compareTo("+")==0)  //plus
        {
            if  (vue.entreeValide())
            {
                modele.setOperation(modele.PLUS, vue.getEntree());
                vue.efface();
            }
            else
                vue.erreur("Valeur invalide !");
        }else if (s.compareTo("-")==0)  // -
        {
            if  (vue.entreeValide())
            {
                modele.setOperation(modele.MOINS,vue.getEntree());
                vue.efface();
            }
            else
                vue.erreur("Valeur invalide !");
        }else if (s.compareTo("*")==0)  // multiplication
        {
            if  (vue.entreeValide())
            {
                modele.setOperation(modele.MULU,vue.getEntree());
                vue.efface();
            }
            else
                vue.erreur("Valeur invalide !");
        }else if (s.compareTo("/")==0)  // division
        {
            if  (vue.entreeValide())
            {
                modele.setOperation(modele.DIV,vue.getEntree());
                vue.efface();
            }
            else
                vue.erreur("Valeur invalide !");
        }else if (s.compareTo("")==0)  // rien : pour faire beau!
        {
        }
        else if (s.compareTo("=")==0)  // egal
        {
            if  (vue.entreeValide())
            {
                modele.doOperation(vue.getEntree());
                vue.afficheTotal();
                modele.resetTotal();
            }else
                vue.erreur("Valeur invalide !");
        }else
        {
            vue.ajouteSaisie(s);
        }
    }
    
}



























































