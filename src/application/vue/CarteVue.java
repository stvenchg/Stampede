package application.vue;


import application.modele.Environnement;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class CarteVue {
	
	final static String CheminRelatifTilesMap = "../ressources/tiles/map/";
	
	
	private Images imagesTilesMap;
	private Environnement env;
	private int hauteur;
	private int largeur;
	private TilePane panneauJeu;
	
	public CarteVue(Environnement env, TilePane panneauJeu, int largeur, int hauteur) {
		this.panneauJeu = panneauJeu;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.env = env;
		creerMap();
	}
	
	private void creerMap() {
		imagesTilesMap = new Images(CheminRelatifTilesMap);
		for(int i=0; i<env.getMap().size(); i++)
			panneauJeu.getChildren().add(new ImageView(imagesTilesMap.getImage(env.getMap().get(i))));
	}
	
	public int getTailleBlock() {
		return ((int) imagesTilesMap.getImage(0).getWidth());
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}

}
