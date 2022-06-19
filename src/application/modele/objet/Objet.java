package application.modele.objet;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Objet {

	private Integer objetNumero;
	private String nom;
	private IntegerProperty nbObjet;
	
	public Objet(int numero, String nom) {
		this.objetNumero = numero;
		this.nom = nom;
		this.nbObjet = new SimpleIntegerProperty(0);
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

	public boolean removeRessources(int nombre) {
		if(nbObjet.getValue() - nombre >= 0){
			nbObjet.setValue(nbObjet.getValue() - nombre);
			return true;
		}else{
			return false;
		}
	}
	
	public int getNbRessources() {
		return nbObjet.getValue();
	}
	
	public IntegerProperty objetProperty() {
		return nbObjet;
	}
	
}
