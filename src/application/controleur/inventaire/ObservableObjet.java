package application.controleur.inventaire;

import java.util.ArrayList;

import application.modele.objet.Objet;
import application.vue.inventaire.InventaireVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ObservableObjet implements ChangeListener<Number>{
	
	private Objet obj;
	private int numeroObj;
	private InventaireVue inventaireVue;
	private ArrayList<Pane> objetsVue;
	private Text textNomObjetTemp;
	
	public ObservableObjet(Objet obj, InventaireVue inventaireVue) {
		this.obj = obj;
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
	}
	
	public void actualiserVueInventaire(int ans, int nv){
		if(nv-ans > 0) {
			creerNObjetInventaireVue(nv-ans);		
		}else {
			suprimerNObjet(ans-nv);
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
		
		objetVue.setOnMousePressed(mouseEvent ->{
			if(mouseEvent.getButton() == MouseButton.PRIMARY) {
				System.out.println("fsq");
			}
			});
		
	//	objetVue.seton
	}
	
	private void suprimerNObjet(int nb) {
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
}
