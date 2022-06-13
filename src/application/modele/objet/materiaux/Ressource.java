package application.modele.objet.materiaux;

import application.modele.objet.Objet;

public abstract class Ressource extends Objet{

	private int resistance;
	private int distance;
	
	
	public Ressource(int resistance, String nom, int distance, int numero, int nbRessource) {
		super(numero, nom, nbRessource);
		this.distance = distance;
		this.resistance = resistance;
	}

	public int getResistance() {
		return resistance;
	}


	public int getDistance() {
		return distance;
	}
	


	public void lacher() {
		// TODO Auto-generated method stub
		
	}
	
}
