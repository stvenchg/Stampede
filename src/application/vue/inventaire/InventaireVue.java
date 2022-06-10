package application.vue.inventaire;

import application.vue.Images;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InventaireVue extends Pane{
	
	private final static String CheminRelatifObjets = "../ressources/tiles/objets/";
	private final static String CheminRelatifFond = "../ressources/tiles/inventaire/";
	private final static String CheminPoliceMincraft = "/application/ressources/polices/minecraft.ttf";
	private final static String TexteInventaire = "Inventory";
	private final static int NbColonnesInventaire = 6;
	private final static int DecalageBordSceneInventaireX = 60;

	private final static int DecalageBordSceneInventaireY = 60;
	private final static int DecalageBordInventaireBoxX = 30;
	private final static int DecalageBordInventaireBoxY = 80;
	private final static int DecalageBordBoxBox = 14;

	private final static int TailleBoxContenaireImage = 64;
	private final static int DecalageBoxImages = 3+4;
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
	
	public void changerOrdreListeInvetaire(Pane ObjDeplacer, int posObjArriver) {
		ObservableList<Node> inventaireVue = panneauImagesInventaire.getChildren();
		int posObjDeplacer = inventaireVue.indexOf(ObjDeplacer);
		Pane objet1 = (Pane) inventaireVue.get(posObjDeplacer);
		Pane objet2 = (Pane) inventaireVue.get(posObjArriver);

		if (posObjDeplacer < posObjArriver) {
			inventaireVue.remove(posObjArriver);
			inventaireVue.remove(posObjDeplacer);
			inventaireVue.add(posObjArriver-1, objet1);
			inventaireVue.add(posObjDeplacer, (Node) objet2);
		} else if (posObjDeplacer > posObjArriver) {
			inventaireVue.remove(posObjDeplacer);
			inventaireVue.remove(posObjArriver);
			inventaireVue.add(posObjArriver, objet1);
			inventaireVue.add(posObjDeplacer, (Node) objet2);
		}
	}
	
	private void creerBox() {
		//vBox = new VBox();
		texteInv = new Text(TexteInventaire);
		panneauDeFondInventaire = new Pane();
		panneauBordInventaire = new TilePane();
		panneauImagesInventaire = new TilePane();
		
		insererFond();
		gererPositionContenaire();

		this.getChildren().addAll(texteInv,panneauDeFondInventaire);
		//this.getChildren().add(vBox);
		//vBox.getChildren().add(texteInv);
		//vBox.getChildren().add(panneauDeFondInventaire);

		panneauDeFondInventaire.getChildren().add(panneauBordInventaire);
		panneauDeFondInventaire.getChildren().add(panneauImagesInventaire);
	}
	
	public void affichageInventaire() {
		this.setVisible(!this.isVisible());
	}
	
	private void gererPositionContenaire() {
		this.setPrefWidth(NbColonnesInventaire *64+60);
		this.setPrefHeight(3*64+60+50);
		//vBox.setPrefWidth(this.getPrefWidth());
		//vBox.setPrefHeight(this.getPrefHeight());
		this.setLayoutX(DecalageBordSceneInventaireX);
		this.setLayoutY(DecalageBordSceneInventaireY);
		
		texteInv.setLayoutX(150);
		texteInv.setLayoutY(50);
		texteInv.getStyleClass().add("TexteMenuInventaire");
		texteInv.setFont(policeEcritureMincraft);
		
		this.setOpacity(0.92);
		
		//panneauDeFondInventaire.setLayoutX(60);
		//panneauDeFondInventaire.setLayoutY(60);

		panneauImagesInventaire.setLayoutX(DecalageBordInventaireBoxX+DecalageBoxImages);
		panneauImagesInventaire.setLayoutY(DecalageBoxImages);
		//vBox.setAlignment(Pos.CENTER);
		panneauBordInventaire.setLayoutX(DecalageBordInventaireBoxX);
		panneauDeFondInventaire.setLayoutY(DecalageBordInventaireBoxY);
		panneauImagesInventaire.setHgap(DecalageBordBoxBox);
		panneauImagesInventaire.setVgap(DecalageBordBoxBox);
		panneauImagesInventaire.setPrefColumns(NbColonnesInventaire);
		
		panneauBordInventaire.setPrefColumns(NbColonnesInventaire);

		//vBox.setSpacing(20);
	}

	public int getDecalageBordSceneXEtInventaireXETContenaireImagesX(){
		return DecalageBordSceneInventaireX + DecalageBordInventaireBoxX;
	}

	public int getDecalageBordBoxBox(){
		return DecalageBordBoxBox;
	}

	public int getNbColonnesInventaire(){
		return NbColonnesInventaire;
	}

	public int getDecalageBordSceneInventaireYEtDecalageBordInventaireBoxY(){
		return DecalageBordSceneInventaireY + DecalageBordInventaireBoxY;
	}

	public int getTailleBoxContenaireImage(){
		return TailleBoxContenaireImage;
	}
}
