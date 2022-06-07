package application.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Environnement {
	
	
	private Carte carte;
	private int hauteur;
	private int largeur;
	private Personnage joueur;

	
	public Environnement(){
		this.carte = new Carte();
		this.hauteur = this.carte.getHauteur();
		this.largeur = this.carte.getLargeur();
		this.joueur = new Joueur();
	}
	
	public ArrayList<Integer> getMap() {
		return this.carte.getMap();
	}
	
	public int getHauteur(){
		return hauteur;
	}
	
	public int getLargeur(){
		return largeur;
	}
	
	public int getPosition(int x, int y) {
		if(y>=16)
			return (y/16 * 64 + x/16);
		else if(x>=16)
			return (x/16);
		else
			return x;
	}
	
	public Environnement getEnvironement() {
		return this;
	}	
}
