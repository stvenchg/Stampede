package application.vue.inventaire;

import java.util.ArrayList;
import java.util.List;

import application.modele.objet.Inventaire;
import application.modele.objet.Objet;
import application.vue.Images;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InventaireVue extends Pane{
	
	private final static String CheminRelatifObjets = "../ressources/tiles/objets/";
	private final static String CheminRelatifFond = "../ressources/tiles/inventaire/";
	private final static String CheminPoliceMincraft = "/application/ressources/polices/minecraft.ttf";
	private final static String TexteInventaire = "Inventory";
	private final static int nbColonnesInventaire = 6;
	private Images imagesFondInventaire;
	private Images imagesObjetsInventaire;
	private VBox vBox;
	private Text texteInv;
	private Pane panneauDeFondInventaire;
	private TilePane panneauBordInventaire;
	private TilePane panneauImagesInventaire;
	private Font policeEcritureMincraft = Font.loadFont(getClass().getResourceAsStream(CheminPoliceMincraft), 42);

	
	public InventaireVue() {
		super(new Pane());
		imagesObjetsInventaire = new Images(CheminRelatifObjets);
		creerInventaireVue();
	}
	
	public Images getImagesObjets() {
		return imagesObjetsInventaire;
	}
	
	public Font getPoliceMincraft() {
		return policeEcritureMincraft;
	}
	
	public void ajouterObjet(Pane objet) {
		panneauImagesInventaire.getChildren().add(objet);
	}
	
	public void modifierQuantiterObjet(Pane pane, int nbAjouter) {
		int value = Integer.parseInt(((Text) ((Pane) panneauImagesInventaire.getChildren().get(panneauImagesInventaire.getChildren().indexOf(pane))).getChildren().get(1)).getText());
		((Text) ((Pane) panneauImagesInventaire.getChildren().get(panneauImagesInventaire.getChildren().indexOf(pane))).getChildren().get(1)).setText(String.valueOf(value + nbAjouter));
	}
	
	public void retirerObjet(int numero) {
		panneauImagesInventaire.getChildren().remove(numero);
	}
	
	public void retirerObjet(Pane pane) {
		panneauImagesInventaire.getChildren().remove(pane);
	}
	
	private void creerInventaireVue() {
		imagesFondInventaire = new Images(CheminRelatifFond);
		creerBox();
		//affichageInventaire();
	}
	
	private void insererFond() {
		this.getStyleClass().add("FondInventaire");
		//this.setBackground(new Background(new BackgroundImage(imagesFondInventaire.getImage(0), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		for(int i=0; i<18; i++) {
			panneauBordInventaire.getChildren().add(new ImageView(imagesFondInventaire.getImage(1)));
		}
	}
	
	public void changerOrdreListeInvetaire(Pane objDeplacer, Pane objetArriver) {
		int PosObjDeplacer = panneauImagesInventaire.getChildren().indexOf(objDeplacer);
		int PosObjArriver = panneauImagesInventaire.getChildren().indexOf(objetArriver);
		
		if(PosObjArriver < PosObjDeplacer) {
			panneauImagesInventaire.getChildren().remove(PosObjDeplacer);
			panneauImagesInventaire.getChildren().add(PosObjArriver, objDeplacer);
			
		}else {
			panneauImagesInventaire.getChildren().remove(PosObjDeplacer);
			panneauImagesInventaire.getChildren().add(PosObjArriver, objDeplacer);
		}
	}
	
	private void creerBox() {
		vBox = new VBox();
		texteInv = new Text(TexteInventaire);
		panneauDeFondInventaire = new Pane();
		panneauBordInventaire = new TilePane();
		panneauImagesInventaire = new TilePane();
		
		insererFond();
		gererPositionContenaire();
		
		this.getChildren().add(vBox);
		vBox.getChildren().add(texteInv);
		vBox.getChildren().add(panneauDeFondInventaire);

		panneauDeFondInventaire.getChildren().add(panneauBordInventaire);
		panneauDeFondInventaire.getChildren().add(panneauImagesInventaire);
	}
	
	public void affichageInventaire() {
		this.setVisible(!this.isVisible());
	}
	
	public TilePane getPaneObjetsInventaire() {
		return panneauImagesInventaire;
	}
	
	private void gererPositionContenaire() {
		this.setPrefWidth(nbColonnesInventaire*64+60);
		this.setPrefHeight(3*64+60+50);
		vBox.setPrefWidth(this.getPrefWidth());
		vBox.setPrefHeight(this.getPrefHeight());
		this.setLayoutX(60);
		this.setLayoutY(60);
		
		texteInv.setLayoutX(10);
		texteInv.setLayoutY(20);

		texteInv.getStyleClass().add("TexteMenuInventaire");
		texteInv.setFont(policeEcritureMincraft);
		
		this.setOpacity(0.92);
		
		//panneauDeFondInventaire.setLayoutX(60);
		//panneauDeFondInventaire.setLayoutY(60);

		panneauImagesInventaire.setLayoutX(30+3+4);
		panneauImagesInventaire.setLayoutY(3+4);
		vBox.setAlignment(Pos.CENTER);
		panneauBordInventaire.setLayoutX(30);
		//panneauBordInventaire.setLayoutY(30);
		panneauImagesInventaire.setHgap(14);
		panneauImagesInventaire.setVgap(14);
		panneauImagesInventaire.setPrefColumns(nbColonnesInventaire);
		
		panneauBordInventaire.setPrefColumns(nbColonnesInventaire);
		vBox.setSpacing(20);
	}
}
