package application.modele.objet.armes;

import application.modele.objet.Objet;

public abstract class Arme extends Objet{

	private int degat;
	private int distance;
	
	public Arme(int degat, String nom, int distance, int numeroObjet) {
		super(numeroObjet, nom);
		this.degat = degat;
		this.distance = distance;
	}

	public int getDegat() {
		return degat;
	}

	public int getDistance() {
		return distance;
	}
	
	public abstract void faireDegat();
	
	
}
