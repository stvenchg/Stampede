package application.vue.inventaire;

import application.modele.Joueur;
import application.vue.Images;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public class InventaireVue extends Pane{
	
	private final static String CheminRelatifObjets = "../ressources/tiles/objets/";
	private final static String CheminRelatifFond = "../ressources/tiles/inventaire/";
	private final static String CheminPoliceMincraft = "/application/ressources/polices/minecraft.ttf";
	private final static String TexteInventaire = "Inventory";
	public final static int NbColonnesInventaire = 6;
	public final static int DecalageBordSceneInventaireX = 60;

	public final static int DecalageBordSceneInventaireY = 60;
	public final static int DecalageBordInventaireBoxX = 30;
	public final static int DecalageBordInventaireBoxY = 80;
	public final static int DecalageBordBoxBox = 14;

	public final static int TailleBoxContenaireImage = 64;
	public final static int DecalageBoxImages = 3+4;
	private Images imagesFondInventaire;
	private Images imagesObjetsInventaire;
	private Text texteInv;
	private Pane panneauDeFondInventaire;

	private TilePane panneauBordCraft;

	private TilePane panneauImagesCraft;
	private TilePane panneauBordInventaire;
	private TilePane panneauImagesInventaire;

	private Joueur joueur;

	
	public InventaireVue(Joueur joueur) {
		super(new Pane());
		this.joueur = joueur;
		imagesObjetsInventaire = new Images(CheminRelatifObjets);
		creerInventaireVue();
	}
	
	public Images getImagesObjets() {
		return imagesObjetsInventaire;
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

		for(int i=0; i<4; i++){
			panneauBordCraft.getChildren().add(new ImageView(imagesFondInventaire.getImage(1)));
		}
		initialiserCraft();
	}

	private void initialiserCraft(){

		var ref = new Object() {
			ImageView img;
		};

		panneauImagesCraft.getChildren().add(new ImageView(imagesObjetsInventaire.getImage(4)));
		ImageView img = (ImageView) panneauImagesCraft.getChildren().get(0);
		img.setOpacity(0.4);
		ajouterEventCraft(img);
		img.setOnMouseClicked(mouseEvent -> {
			if(mouseEvent.getButton() == MouseButton.PRIMARY) {
				if (img.getOpacity() == 1) {
					joueur.getInventaire().ajouterObjet(4, 1);

					joueur.getInventaire().ajouterObjet(5, -3);
					joueur.getInventaire().ajouterObjet(8, -5);
					joueur.getInventaire().ajouterObjet(9, -6);
				}
			}
		});


		panneauImagesCraft.getChildren().add(new ImageView(imagesObjetsInventaire.getImage(6)));
		ImageView img1 = (ImageView) panneauImagesCraft.getChildren().get(1);
		img1.setOpacity(0.4);
		ajouterEventCraft(img1);
		img1.setOnMouseClicked(mouseEvent -> {
			if(mouseEvent.getButton() == MouseButton.PRIMARY) {
				System.out.println("opacité = " + img1.getOpacity());
				if (img1.getOpacity() == 1) {
					joueur.getInventaire().ajouterObjet(6, 1);

					joueur.getInventaire().ajouterObjet(5, -1);
				}
			}
		});

		panneauImagesCraft.getChildren().add(new ImageView(imagesObjetsInventaire.getImage(3)));
		ImageView img2 = (ImageView) panneauImagesCraft.getChildren().get(2);
		img2.setOpacity(0.4);
		ajouterEventCraft(img2);
		img2.setOnMouseClicked(mouseEvent -> {
			if(mouseEvent.getButton() == MouseButton.PRIMARY) {
				if (img2.getOpacity() == 1) {
					joueur.getInventaire().ajouterObjet(3, 1);

					joueur.getInventaire().ajouterObjet(5, -5);
				}
			}
		});

		panneauImagesCraft.getChildren().add(new ImageView(imagesObjetsInventaire.getImage(7)));
		ImageView img3 = (ImageView) panneauImagesCraft.getChildren().get(3);
		img3.setOpacity(0.4);
		ajouterEventCraft(img3);
		img3.setOnMouseClicked(mouseEvent -> {
			if(mouseEvent.getButton() == MouseButton.PRIMARY) {
				if (img3.getOpacity() == 1) {
					joueur.getInventaire().ajouterObjet(7, 1);

					joueur.getInventaire().ajouterObjet(5, -20);
					joueur.getInventaire().ajouterObjet(9, -20);
					joueur.getInventaire().ajouterObjet(8, -10);
					joueur.getInventaire().ajouterObjet(10, -1);
				}
			}
		});
	}

	private void ajouterEventCraft(ImageView img){
		img.setOnMouseEntered(mouseEvent -> {
			if(img.getOpacity() == 1){
				img.setScaleX(1.4);
				img.setScaleY(1.4);
			}
		});

		img.setOnMouseExited(mouseEvent -> {
			if(img.getOpacity() == 1){
				img.setScaleX(1);
				img.setScaleY(1);
			}
		});
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
		panneauBordCraft = new TilePane();
		panneauImagesCraft = new TilePane();
		
		insererFond();
		gererPositionContenaire();

		Text textCraft = new Text("Craft");
		textCraft.getStyleClass().add("TexteDeCraft");
		textCraft.setLayoutX(180);
		textCraft.setLayoutY(235);

		this.getChildren().addAll(texteInv, panneauDeFondInventaire);
		panneauDeFondInventaire.getChildren().add(textCraft);
		panneauDeFondInventaire.getChildren().add(panneauBordInventaire);
		panneauDeFondInventaire.getChildren().add(panneauImagesInventaire);
		panneauDeFondInventaire.getChildren().add(panneauBordCraft);
		panneauDeFondInventaire.getChildren().add(panneauImagesCraft);
	}
	
	public void affichageInventaire() {
		this.setVisible(!this.isVisible());
	}
	
	private void gererPositionContenaire() {
		this.setPrefWidth(NbColonnesInventaire *64+60);
		this.setPrefHeight(3*64+60+50+64+64);
		//vBox.setPrefWidth(this.getPrefWidth());
		//vBox.setPrefHeight(this.getPrefHeight());
		this.setLayoutX(DecalageBordSceneInventaireX);
		this.setLayoutY(DecalageBordSceneInventaireY);
		
		texteInv.setLayoutX(150);
		texteInv.setLayoutY(50);
		texteInv.getStyleClass().add("TexteMenuInventaire");
		
		this.setOpacity(0.92);
		


		panneauImagesInventaire.setLayoutX(DecalageBordInventaireBoxX+DecalageBoxImages);
		panneauImagesInventaire.setLayoutY(DecalageBoxImages);
		panneauBordInventaire.setLayoutX(DecalageBordInventaireBoxX);
		panneauDeFondInventaire.setLayoutY(DecalageBordInventaireBoxY);
		panneauImagesInventaire.setHgap(DecalageBordBoxBox);
		panneauImagesInventaire.setVgap(DecalageBordBoxBox);
		panneauImagesInventaire.setPrefColumns(NbColonnesInventaire);

		panneauBordCraft.setLayoutX(DecalageBordInventaireBoxX+7);
		panneauBordCraft.setLayoutY(260);
		panneauImagesCraft.setLayoutX(DecalageBordInventaireBoxX+DecalageBoxImages+7);
		panneauImagesCraft.setLayoutY(260+ 7);
		panneauBordCraft.setHgap(40);
		panneauImagesCraft.setHgap(40 + DecalageBordBoxBox);

		panneauBordInventaire.setPrefColumns(NbColonnesInventaire);
	}

	public int getDecalageBordSceneXEtInventaireXETContenaireImagesX(){
		return DecalageBordSceneInventaireX + DecalageBordInventaireBoxX;
	}

	public TilePane getPanneauImagesCraft(){
		return panneauImagesCraft;
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
