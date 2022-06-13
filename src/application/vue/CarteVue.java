package application.vue;


import application.controleur.Minage;
import application.modele.Environnement;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class CarteVue extends TilePane{
	
	final static String CheminRelatifTilesMap = "../ressources/tiles/map/";
	private Images imagesTilesMap;
	private Environnement env;
	private int hauteur;
	private ImageView tileMiner;

	private int tempsInit;
	private int tempInitialMinage;
	private boolean etatClik;
	private int largeur;
	private TilePane panneauJeu;

	private int[] cordonneesSouris;

	private Minage lancerMinage;
	
	public CarteVue(Environnement env, TilePane panneauJeu, int largeur, int hauteur) {
		this.panneauJeu = panneauJeu;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.env = env;
		this.cordonneesSouris = new int[2];
		this.lancerMinage = new Minage(this, env.getJoueur(), env.getMap());
		creerMap();
	}
	
	private void creerMap() {
		imagesTilesMap = new Images(CheminRelatifTilesMap);
		for(int i=0; i<env.getMap().size(); i++) {
			panneauJeu.getChildren().add(new ImageView(imagesTilesMap.getImage(env.getMap().get(i))));
			if(env.getMap().get(i) != 0)
				ajouterEvents((ImageView) panneauJeu.getChildren().get(panneauJeu.getChildren().size()-1));

		}
	}
	
	public void ajouterEvents(ImageView tile){
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
			etatClik = true;
			tileMiner = tile;
			cordonneesSouris[0] = (int) mouseEvent.getX();
			cordonneesSouris[1] = (int) mouseEvent.getY();
			new Thread(lancerMinage).start();

			//new Thread(new Minage(this, env.getJoueur(), env.getMap())).start();
		});

		tile.setOnMouseReleased(mouseEvent -> {
			if(etatClik) {
				etatClik = false;
				tileMiner = null;
			}
		});


	}
	
	public int getTailleBlock() {
		return ((int) imagesTilesMap.getImage(0).getWidth());
	}

	public int getNumeroTileMiner(){
		String url = tileMiner.getImage().getUrl();

		if (url.charAt(url.length() - 6) != '/')
			return Integer.parseInt(String.valueOf(url.charAt(url.length() - 6) + url.charAt(url.length() - 5)));
		else
			return Integer.parseInt(String.valueOf(url.charAt(url.length() - 5)));

	}

	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}

	public int[] getCordonneesSouris() {
		return cordonneesSouris;
	}

	public Images getImagesTilesMap(){
		return imagesTilesMap;
	}

	public boolean isEtatClik() {
		return etatClik;
	}

	public void setTempInitialMinage(int tempInitialMinage) {
		this.tempInitialMinage = tempInitialMinage;
	}

	public int getTempInitialMinage() {
		return tempInitialMinage;
	}

	public ImageView getTileMiner() {
		return tileMiner;
	}

	public TilePane getPanneauJeu() {
		return panneauJeu;
	}
}
