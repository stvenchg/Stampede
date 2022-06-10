package application.modele;

import java.util.ArrayList;

import application.modele.objet.Inventaire;
import application.modele.objet.Objet;
import application.modele.objet.Outils.Pioche;
import application.modele.objet.armes.Epee;
import application.modele.objet.materiaux.MineraiDeFer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Personnage {

	private SimpleIntegerProperty X;
	private SimpleIntegerProperty Y;
	private int vitesseD;
//	private SimpleIntegerProperty vieProperty;
	private static Environnement env;
	private Inventaire inventaire;
	private Objet enMain;

	public Joueur() {
		super(env);
		this.enMain = null;
		this.X = new SimpleIntegerProperty();
		this.Y = new SimpleIntegerProperty();
		this.inventaire = new Inventaire();
		//this.inventaire.add(new MineraiDeFer());

//		this.vieProperty = new SimpleIntegerProperty(12);
		this.vitesseD = 8;
	}

	public int getVitesseD() {
		return this.vitesseD;
	}
	
	public Inventaire getInventaire(){
		 return this.inventaire; 
	}

	public void prendreEnMain(Objet objet){
		this.enMain = objet;
	}

	public Objet getEnMain(){
		return enMain;
	}
}
