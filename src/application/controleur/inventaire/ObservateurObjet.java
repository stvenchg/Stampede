package application.controleur.inventaire;

import java.util.ArrayList;

import application.modele.Joueur;
import application.modele.objet.Inventaire;
import application.modele.objet.Objet;
import application.vue.inventaire.InventaireVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class ObservateurObjet implements ChangeListener<Number>{
	
	private Objet obj;
	private int numeroObj;

	private boolean drag;
	private InventaireVue inventaireVue;
	private ArrayList<Pane> objetsVue;

	private Joueur joueur;
	private Text textNomObjetTemp;
	
	public ObservateurObjet(Objet obj, InventaireVue inventaireVue, Joueur joueur) {
		this.obj = obj;
		this.joueur = joueur;
		numeroObj = obj.getobjetNumero();
		creerTexteObjetTemp();
		objetsVue = new ArrayList<>();
		this.inventaireVue = inventaireVue;
	}

	private void creerTexteObjetTemp() {
		textNomObjetTemp = new Text();
		//textNomObjetTemp.setFont(inventaireVue.getPoliceMincraft());
		textNomObjetTemp.getStyleClass().add("TexteObjetInventaire");
		textNomObjetTemp.setLayoutX(5);
		textNomObjetTemp.setLayoutY(-10);
	}
	
	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number ans, Number nv){
		actualiserVueInventaire(ans.intValue(), nv.intValue());
		actualiserCraft();
	}
	
	public void actualiserVueInventaire(int ans, int nv){
		if(nv-ans > 0) {
			creerNObjetInventaireVue(nv-ans);		
		}else {
			supprimerNObjet(ans-nv);
		}
	}

	private void creerNObjetInventaireVue(int nb){
		for (int i=0; i<nb; i++) {
			Pane objetVue = new Pane();
			objetVue.setPrefHeight(50);
			objetVue.setPrefWidth(50);
			ajouterEvent(objetVue);
			objetVue.getChildren().add(new ImageView(inventaireVue.getImagesObjets().getImage(numeroObj)));
			objetsVue.add(objetVue);
			inventaireVue.ajouterObjet(objetVue);
		}
	}
	
	public void ajouterEvent(Pane objetVue) {
		
		objetVue.setOnMouseEntered(mouseEvent -> {
			objetVue.setScaleX(1.3);
			objetVue.setScaleY(1.3);
			textNomObjetTemp.setText(obj.getNom());
			objetVue.getChildren().add(textNomObjetTemp);
		});

		objetVue.setOnMouseExited(mouseEvent -> {
			objetVue.setScaleX(1);
			objetVue.setScaleY(1);
			objetVue.getChildren().remove(textNomObjetTemp);
		});

		objetVue.setOnMousePressed(mouseEvent -> {
			if(mouseEvent.getButton() == MouseButton.PRIMARY){
				joueur.prendreEnMain(obj);
			}
		});

		objetVue.setOnDragDetected(mouseEvent1 -> {
			drag = true;
		});

		objetVue.setOnMouseReleased(mouseEvent2 -> {
			if(mouseEvent2.getButton() == MouseButton.PRIMARY) {
				if (drag) {
					int numeroColonneInventaire = (((int) mouseEvent2.getSceneX()) - inventaireVue.getDecalageBordSceneXEtInventaireXETContenaireImagesX()) / inventaireVue.getTailleBoxContenaireImage();
					int numeroLigneInventaire = (((int) mouseEvent2.getSceneY()) - inventaireVue.getDecalageBordSceneInventaireYEtDecalageBordInventaireBoxY()) / inventaireVue.getTailleBoxContenaireImage();
					if (numeroColonneInventaire < 6 && numeroColonneInventaire >= 0 && numeroLigneInventaire < 3 && numeroLigneInventaire >= 0) {
						inventaireVue.changerOrdreListeInvetaire(objetVue, (numeroLigneInventaire * inventaireVue.getNbColonnesInventaire() + numeroColonneInventaire));
					} else {
						supprimerObjet(objetVue);
					}
					drag = false;
				}else{
					inventaireVue.affichageInventaire();
				}
			}
		});

	}

	public void supprimerObjet(Pane objetVue){
		inventaireVue.retirerObjet(objetVue);
		obj.addRessources(-1);
	}

	private void supprimerNObjet(int nb) {
		for (int i=0; i<nb; i++) {
			inventaireVue.retirerObjet(objetsVue.get(objetsVue.size()-1));
			objetsVue.remove(objetsVue.size()-1);
		}
	}
	
	protected int getnumeroObj() {
		return numeroObj;
	}
	
	public Objet getObjet() {
		return obj;
	}
	
	public ArrayList<Pane> getObjetsVue(){
		return objetsVue;
	}
	
	public InventaireVue getInventaireVue() {
		return inventaireVue;
	}

	private void actualiserCraft(){
		Inventaire inventaire = joueur.getInventaire();

		//Actualise Fligue
		System.out.println(inventaire.getObjet(4).getNbRessources());
		if(inventaire.getObjet(4).getNbRessources() < 1)
			if(inventaire.getObjet(5).getNbRessources() > 2 && inventaire.getObjet(8).getNbRessources() > 4 && inventaire.getObjet(9 ).getNbRessources() > 5)
				inventaireVue.getPanneauImagesCraft().getChildren().get(0).setOpacity(1);
			else {
				inventaireVue.getPanneauImagesCraft().getChildren().get(0).setOpacity(0.4);
				inventaireVue.getPanneauImagesCraft().getChildren().get(0).setScaleX(1);
				inventaireVue.getPanneauImagesCraft().getChildren().get(0).setScaleY(1);
			}
		else {
			inventaireVue.getPanneauImagesCraft().getChildren().get(0).setOpacity(0.4);
			inventaireVue.getPanneauImagesCraft().getChildren().get(0).setScaleX(1);
			inventaireVue.getPanneauImagesCraft().getChildren().get(0).setScaleY(1);
		}

		//Actualise Balle
		if(inventaire.getObjet(5).getNbRessources() > 0)
			inventaireVue.getPanneauImagesCraft().getChildren().get(1).setOpacity(1);
		else {
			inventaireVue.getPanneauImagesCraft().getChildren().get(1).setOpacity(0.4);
			inventaireVue.getPanneauImagesCraft().getChildren().get(1).setScaleX(1);
			inventaireVue.getPanneauImagesCraft().getChildren().get(1).setScaleY(1);
		}

		//Actualise Block de fer
		if(inventaire.getObjet(5).getNbRessources() > 4)
			inventaireVue.getPanneauImagesCraft().getChildren().get(2).setOpacity(1);
		else {
			inventaireVue.getPanneauImagesCraft().getChildren().get(2).setOpacity(0.4);
			inventaireVue.getPanneauImagesCraft().getChildren().get(2).setScaleX(1);
			inventaireVue.getPanneauImagesCraft().getChildren().get(2).setScaleY(1);
		}

		//Actualise Vaisseau
		if(inventaire.getObjet(5).getNbRessources() > 19 && inventaire.getObjet(9).getNbRessources() > 19 && inventaire.getObjet(8).getNbRessources() > 9 && inventaire.getObjet(10).getNbRessources() > 0)
			inventaireVue.getPanneauImagesCraft().getChildren().get(3).setOpacity(1);
		else {
			inventaireVue.getPanneauImagesCraft().getChildren().get(3).setOpacity(0.4);
			inventaireVue.getPanneauImagesCraft().getChildren().get(3).setScaleX(1);
			inventaireVue.getPanneauImagesCraft().getChildren().get(3).setScaleY(1);
		}

	}

}
