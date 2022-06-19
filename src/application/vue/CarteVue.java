package application.vue;


import application.controleur.Minage;
import application.modele.Environnement;
import application.modele.objet.materiaux.Ressource;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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

	private int imageOriginal;

	private int positionMiner;
	private TilePane panneauJeu;



	private Minage lancerMinage;
	
	public CarteVue(Environnement env, TilePane panneauJeu, int largeur, int hauteur) {
		this.panneauJeu = panneauJeu;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.env = env;
		this.lancerMinage = new Minage(this, env.getJoueur(), env.mapProperty());
		creerMap();
	}
	
	private void creerMap() {
		imagesTilesMap = new Images(CheminRelatifTilesMap);
		for(int i = 0; i<env.mapProperty().size(); i++) {
			panneauJeu.getChildren().add(new ImageView(imagesTilesMap.getImage(env.mapProperty().get(i))));
			ajouterEvents((ImageView) panneauJeu.getChildren().get(panneauJeu.getChildren().size()-1));
			if(env.mapProperty().get(i) == 0)
				((ImageView) panneauJeu.getChildren().get(panneauJeu.getChildren().size()-1)).setOpacity(0);

		}
	}
	
	public void ajouterEvents(ImageView tile){
		tile.setOnMouseEntered(mouseEvent -> {
			if (getNumeroTile(tile) != 0) {
				tile.setOpacity(0.8);
				tile.setScaleX(1.5);
				tile.setScaleY(1.5);
			}

		});

		tile.setOnMouseExited(mouseEvent -> {
			if(getNumeroTile(tile) != 0){
				tile.setOpacity(1);
				tile.setScaleX(1);
				tile.setScaleY(1);
			}
		});

		tile.setOnMouseClicked(mouseEvent -> {
			if(mouseEvent.getButton() == MouseButton.SECONDARY) {
				if (env.getJoueur().getEnMain() instanceof Ressource) {
					if (getNumeroTile(tile) == 0) {
						int numeroObjet = ((Ressource) env.getJoueur().getEnMain()).getobjetNumero();
						if (env.getJoueur().getInventaire().getObjet(numeroObjet).removeRessources(1)) {
							int position = panneauJeu.getChildren().indexOf(tile);
							env.mapProperty().set(position, numeroObjet);
						} else {
							env.getJoueur().prendreEnMain(null);
						}
					}
				}
			}
		});

		tile.setOnMousePressed(mouseEvent -> {
			etatClik = true;
			tileMiner = tile;
			positionMiner = panneauJeu.getChildren().indexOf(tile);
			new Thread(lancerMinage).start();
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

	public int getNumeroTile(ImageView tile){
		String url = tile.getImage().getUrl();

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

	public int getPositionTileMiner() {
		return positionMiner;
	}
}
