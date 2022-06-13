package application.vue;


import application.controleur.Controleur;
import application.modele.Environnement;
import application.modele.objet.Outils.Pioche;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class CarteVue {
	
	final static String CheminRelatifTilesMap = "../ressources/tiles/map/";
	
	
	private Images imagesTilesMap;
	private Environnement env;
	private int hauteur;

	private int tempsInit;
	private Controleur controleur;

	private BooleanProperty mine;
	private int largeur;
	private TilePane panneauJeu;
	
	public CarteVue(Controleur controleur, Environnement env, TilePane panneauJeu, int largeur, int hauteur) {
		this.controleur = controleur;
		this.panneauJeu = panneauJeu;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.env = env;
		this.mine = new SimpleBooleanProperty(false);
		creerMap();
	}
	
	private void creerMap() {
		imagesTilesMap = new Images(CheminRelatifTilesMap);
		for(int i=0; i<env.getMap().size(); i++) {
			panneauJeu.getChildren().add(new ImageView(imagesTilesMap.getImage(env.getMap().get(i))));
			ajouterEvents((ImageView) panneauJeu.getChildren().get(panneauJeu.getChildren().size()-1));

		}
	}
	
	private void ajouterEvents(ImageView tile){
		tile.setOnMouseEntered(mouseEvent -> {
			tile.setOpacity(0.8);
			tile.setScaleX(1.5);
			tile.setScaleY(1.5);
		});

		tile.setOnMouseExited(mouseEvent -> {
			tile.setOpacity(1);
			tile.setScaleX(1);
			tile.setScaleY(1);
		});

		tile.setOnMousePressed(mouseEvent -> {
			if(env.getJoueur().getEnMain() instanceof Pioche) {
				mine.setValue(true);
			}
		});

		tile.setOnMouseReleased(mouseEvent -> {
			if(!mine.getValue() && env.getJoueur().getEnMain() instanceof Pioche){
				piocher(tile);
			}
			mine.setValue(false);
		});


	}

	private void piocher(ImageView tile){
		int position = (panneauJeu.getChildren().indexOf(tile));
		int numeroRessource;
		String url = tile.getImage().impl_getUrl();

		if (url.charAt(url.length() - 6) != '/') {
			numeroRessource = Integer.parseInt(String.valueOf(url.charAt(url.length() - 6) + url.charAt(url.length() - 5)));


		} else {
			numeroRessource = Integer.parseInt(String.valueOf(url.charAt(url.length() - 5)));
		}

		env.getMap().remove(position);
		env.getMap().add(position, 0);
		panneauJeu.getChildren().remove(position);
		panneauJeu.getChildren().add(position, new ImageView(imagesTilesMap.getImage(env.getMap().get(position))));
		ajouterEvents((ImageView) panneauJeu.getChildren().get(panneauJeu.getChildren().size() - 1));
		env.getJoueur().getInventaire().ajouterObjet(numeroRessource);
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

	public Images getImagesTilesMap(){
		return imagesTilesMap;
	}

	public BooleanProperty mineProperty(){
		return mine;
	}
}
