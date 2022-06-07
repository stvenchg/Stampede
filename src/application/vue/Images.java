package application.vue;

import java.io.File;
import java.net.URL;

import javafx.scene.image.Image;

public class Images {

	private String CheminRelatifImages;
	private Image[] tableauImage;
	private int tailleDef;
	private int nbImage;

	public Images(String cheminRelatif) {
		this.CheminRelatifImages = cheminRelatif;
		creerTableauImage();
	}

	public Images(String cheminRelatif, int taille) {
		this.CheminRelatifImages = cheminRelatif;
		tailleDef = taille;
		creerTableauImageRedimentioner();
	}

	// Création d'un tableau de l'objet Image
	private void creerTableauImage() {
		URL chemin = getClass().getResource(CheminRelatifImages); // transformation d'un chemin String en URL
		nbImage = new File(chemin.getFile()).listFiles().length; // récupération du nombre d'images présentes dans le
																	// dossier
		this.tableauImage = new Image[nbImage]; // Création du tableau d'Image

		for (int i = 0; i < tableauImage.length; i++)
			tableauImage[i] = new Image(chemin.toString() + i + ".png"); // Ajout de chaque Image présente dans le
																			// dossier dans le tableau d'Image
	}

	private void creerTableauImageRedimentioner() {
		URL chemin = getClass().getResource(CheminRelatifImages);
		nbImage = new File(chemin.getFile()).listFiles().length;
		this.tableauImage = new Image[nbImage];

		for (int i = 0; i < tableauImage.length; i++)
			tableauImage[i] = new Image(chemin.toString() + i + ".png", tailleDef, tailleDef, true, false);
	}

	public Image[] gettableauImageObjets() {
		return tableauImage;
	}

	public Image getImage(int numero) {
		return tableauImage[numero];
	}
}
