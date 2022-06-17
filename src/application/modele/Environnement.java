package application.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class Environnement {

	private Carte carte;
	private int hauteur;
	private int largeur;
	private ArrayList<Personnage> listePersonnages;
	private Personnage joueurPersonnage;
	private Joueur joueur;
	private Personnage robotFantassin;
	private Personnage droneSentinelle;

	public Environnement() {
		this.carte = new Carte();
		this.hauteur = this.carte.getHauteur();
		this.largeur = this.carte.getLargeur();
		this.listePersonnages = new ArrayList<Personnage>();
		this.joueur = new Joueur();
		this.joueurPersonnage = joueur;
		this.ajouterPersonnage(joueurPersonnage);
		this.robotFantassin = new RobotFantassin();
		this.ajouterPersonnage(robotFantassin);
		this.droneSentinelle = new DroneSentinelle();
		this.ajouterPersonnage(droneSentinelle);
	}

	public void update() {
		for (int perso = 0; perso < this.listePersonnages.size(); perso++) {
			if (!this.listePersonnages.get(perso).estVivant()) {
				this.listePersonnages.remove(perso);
			}
		}
	}

	public void ajouterPersonnage(Personnage perso) {
		this.listePersonnages.add(perso);
	}

	public void supprimerPersonnage(Personnage perso) {
		this.listePersonnages.remove(perso);
	}

	public Personnage getJoueurPersonnage() {
		return this.joueurPersonnage;
	}

	public Joueur getJoueur(){
		return joueur;
	}

	public Personnage getRobotFantassin() {
		return this.robotFantassin;
	}

	public Personnage getDroneSentinelle() {
		return this.droneSentinelle;
	}

	public ArrayList<Integer> getMap() {
		return this.carte.getMap();

	public ObservableList<Integer> mapProperty() {
			return this.carte.mapProperty();
	}

	public ArrayList<Personnage> getListePersonnages() {
		return this.listePersonnages;
	}

	public ArrayList<Personnage> getEnnemis() {
		ArrayList<Personnage> listeEnnemis = new ArrayList<Personnage>();
		for (int perso = 0; perso < this.listePersonnages.size(); perso++) {
			if (!(this.listePersonnages.get(perso) instanceof Joueur)) {
				listeEnnemis.add(this.listePersonnages.get(perso));
			}
		}
		return listeEnnemis;
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getTileBas(int xJoueur, int yJoueur) {

		int xTile = (xJoueur) / 16;
		int yTile = (yJoueur + 46) / 16;
		int tileBas;

		tileBas = (xTile) + (yTile * 95);

		return tileBas;
	}

	public int getTileBasDroite(int xJoueur, int yJoueur) {

		int xTile = (xJoueur + 34) / 16;
		int yTile = (yJoueur + 46) / 16;
		int tileBasDroite;

		tileBasDroite = (xTile) + (yTile * 95);

		return tileBasDroite;
	}

	public int getTileBasGauche(int xJoueur, int yJoueur) {

		int xTile = (xJoueur - 6) / 16;
		int yTile = (yJoueur + 46) / 16;
		int tileBasGauche;

		tileBasGauche = (xTile) + (yTile * 95);

		return tileBasGauche;
	}

	public int getTileHautGauche(int xJoueur, int yJoueur) {

		int xTile = (xJoueur - 4) / 16;
		int yTile = (yJoueur + 2) / 16;
		int tileHautGauche;

		tileHautGauche = (xTile) + (yTile * 95);

		return tileHautGauche;
	}

	public int getTileHautDroite(int xJoueur, int yJoueur) {

		int xTile = (xJoueur + 25) / 16;
		int yTile = (yJoueur + 2) / 16;
		int getTileHautDroite;

		getTileHautDroite = (xTile) + (yTile * 95);

		return getTileHautDroite;
	}

	public Environnement getEnvironement() {
		return this;
	}	
}
