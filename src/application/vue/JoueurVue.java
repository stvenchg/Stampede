package application.vue;

import javafx.scene.image.ImageView;

public class JoueurVue extends ImageView {

	private Images imageJoueur;
	private final static String CheminRelatifTileJoueur = "../ressources/sprites/joueur/";

	public JoueurVue() {
		super();
		imageJoueur = new Images(CheminRelatifTileJoueur);
		setImage(imageJoueur.getImage(0));
	}

	public Images getImages() {
		return this.imageJoueur;
	}

	public void setImage(int nombre) {
		this.setImage(imageJoueur.getImage(nombre));
	}

}
