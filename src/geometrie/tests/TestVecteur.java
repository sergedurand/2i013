package geometrie.tests;

import geometrie.Vecteur;

public class TestVecteur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	Vecteur v1 = new Vecteur(0,1);
		System.out.println(v1.getX());
		System.out.println(v1);
		Vecteur v2 = new Vecteur(0,1);
		Vecteur v3 = new Vecteur(1,0);
		System.out.println(v1.equals(v2));
		System.out.println(v2.equals(v3));
		v1.setX(1);
		System.out.println(v1);
		
		Vecteur v4=v2.addition(v3);
		System.out.println("addition: v2+v3="+v4);
		Vecteur v5=v4.soustraction(v3);
		System.out.println("soustraction: v4-v3="+v5);
		
		System.out.println("Produit scalaire entre v3 et v2:"+v3.prodScal(v2));
		System.out.println("Produit vectoriel entre v3 et v2:"+v3.prodVect(v2));
		
		System.out.println("Angle entre v3 et v2:"+v3.angle(v2));
		System.out.println("Angle entre v1 et v2:"+v1.angle(v2));
		Vecteur v6=v5.clone();
		System.out.println("Norme de v6:"+v6.norme());
		v1.rotationVoid(Math.PI/2.0);
		System.out.println("v1 après rotation void : "+v1);
		System.out.println("norme v1 : "+ v1.norme());
		System.out.println("prodscal v1 et v2 : " + v1.prodScal(v2));
		System.out.println("Angle entre v1 et v2:"+v1.angle(v2));
		System.out.println("Angle entre v1 et v2:"+v2.angle(v1));
		System.out.println("prodvect v1 et v2 : "+v1.prodVect(v2));
		Vecteur v7 = v1.rotation(Math.PI/2.0);
		System.out.println("v1 après rotation : "+v7);*/
		Vecteur v1 = new Vecteur(0,1);
		Vecteur v2 = new Vecteur(1,1);
		System.out.println("v1="+v1);
		System.out.println("v2="+v2);
		System.out.println("Angle avant rotation entre v1 et v2:"+v1.angle(v2));
		v2.rotationVoid(Math.PI/2.0);
		System.out.println("v2="+v2);
		System.out.println("Angle après rotation entre v1 et v2:"+v1.angle(v2));
		Vecteur v3 = new Vecteur(1,0);
		System.out.println("angle entre v3 et v2 : " + v3.angle(v2));
		
	}

}
