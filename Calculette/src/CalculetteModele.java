

public class CalculetteModele
{  
    private int   total;  //total en cours
    
    public  static  final   int RIEN=0;
    public  static  final   int DEBUT=3;
    public  static  final   int PLUS=1;
    public  static  final   int MOINS=2;
    public  static  final   int MULU=4;
    public  static  final   int DIV=5;
    
    private int operation=DEBUT;
    
    /**
     * Constructeur de  CalculetteModele
     */
    public CalculetteModele()
    {
        
    }
    
    /**
     * defini l'operation
     */
    public  void    setOperation(int o,int e)
    {
        if  (operation==DEBUT)
            total=e;
        else    if  (operation!=RIEN)
            doOperation(e);
            
       operation=o;
    }
    
    /**
     * fait l'operation
     */
    public  void    doOperation(int e)
    {
        switch(operation)
        {
            case    PLUS:
                add(e);
            break;
            case    MOINS:
                sub(e);
            break;
            case    MULU:
                mul(e);
            break;
            case    DIV:
                div(e);
            break;
        }
        operation=RIEN;
    }
    
    /**
     * remet a zero le total
     */
    public  void    resetTotal()
    {
        total=0;
        operation=DEBUT;
    }
    
    /**
     * donne le total
     */
    public  int    getTotal()
    {
        return  total;
    }
    
    /**
     * ajoute 
     */
    public  void    add(int a)
    {
        total+=a;
    }
    
    /**
     * soustrait 
     */
    public  void    sub(int a)
    {
        total-=a;
    }
    
    /**
     * multiplie
     */
    public  void    mul(int a)
    {
        total*=a;
    }
    
    /**
     * divise
     */
    public  void    div(int a)
    {
        if  (a==0)   return;
        total=(int) (total/a);
    }
    
    /**
     * point d'entr�
     */
    public  static  void    main(String args[])
    {
    	System.out.println("1");
        CalculetteModele    modele=new  CalculetteModele();
        CalculetteVue   vue=new CalculetteVue(modele);
    }
    
}
