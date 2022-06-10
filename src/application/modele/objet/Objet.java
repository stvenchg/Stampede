package application.modele.objet;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Objet {
	
	private Integer objetNumero;
	private String nom;
	private IntegerProperty nbObjet;
	
	public Objet(int numero, String nom, int nbRes) {
		this.objetNumero = numero;
		this.nom = nom;
		this.nbObjet = new SimpleIntegerProperty(nbRes);
	}
	
	public abstract void lacher();
	
	public abstract void utiliser();
	
	public String toString() {
		return String.valueOf(objetNumero);
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getobjetNumero() {
		return this.objetNumero;
	}
	
	public void addRessources(int nombre) {
		nbObjet.setValue(nbObjet.getValue() + nombre);
	}
	
	public int getNbRessources() {
		return nbObjet.getValue();
	}
	
	public IntegerProperty objetProperty() {
		return nbObjet;
	}
	
}
