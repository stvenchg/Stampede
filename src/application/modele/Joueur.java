package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur extends Personnage {

	private SimpleIntegerProperty X;
	private SimpleIntegerProperty Y;
	private int vitesseD;
//	private SimpleIntegerProperty vieProperty;
	private static Environnement env;

	public Joueur() {
		super(env);
		this.X = new SimpleIntegerProperty();
		this.Y = new SimpleIntegerProperty();
//		this.vieProperty = new SimpleIntegerProperty(12);
		this.vitesseD = 8;
	}

	public int getVitesseD() {
		return this.vitesseD;
	}

}
