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
	
	public Environnement getEnvironement() {
		return this;
	}	
}
