package application.controleur.inventaire;

import java.util.ArrayList;

import application.modele.Joueur;
import application.modele.objet.Objet;
import application.vue.inventaire.InventaireVue;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ObservateurResources extends ObservateurObjet {
	
	private final static int NbMaxRessourcesBlok = 64;
	private ArrayList<Pane> ressourcesVue;
	
	public ObservateurResources(Objet obj, InventaireVue inventaireVue, Joueur joueur) {
		super(obj, inventaireVue, joueur);
		ressourcesVue = super.getObjetsVue();
	}

	@Override
	public void actualiserVueInventaire(int ans, int nv){
		int nbModification = nv-ans;

		if(nbModification>0){
			if(!ressourcesVue.isEmpty()) {
				int nbRes = Integer.parseInt( ((Text)ressourcesVue.get(ressourcesVue.size()-1).getChildren().get(1)).getText() );
				if( nbRes < NbMaxRessourcesBlok) {
					int nbResPossibleAjouter = NbMaxRessourcesBlok-nbRes;
					if(nbResPossibleAjouter >= nbModification) {
						((Text)ressourcesVue.get(ressourcesVue.size()-1).getChildren().get(1)).setText(String.valueOf(nbRes+nbModification));
					}else{
						((Text) ressourcesVue.get(ressourcesVue.size()-1).getChildren().get(1)).setText(String.valueOf(nbRes+nbResPossibleAjouter));
						nbModification-=nbResPossibleAjouter;					
						creerNObjetInventaireVue(nbModification);
					}
				}else{
					creerNObjetInventaireVue(nbModification);
				}
			}else{
				creerNObjetInventaireVue(nbModification);
			}
		}else{
			nbModification = Math.abs(nbModification);
			if(ressourcesVue.isEmpty()) {
				System.out.println("T'essayes de supprimer une ressource qui n'existe pas\nFaudra pensser a mettre une Exception...");
			}else{
				int nbRes = Integer.parseInt(((Text)((Pane)ressourcesVue.get(ressourcesVue.size()-1)).getChildren().get(1)).getText());
				if( nbModification >= nbRes) {
					super.getInventaireVue().retirerObjet(ressourcesVue.get(ressourcesVue.size()-1));
					ressourcesVue.remove(ressourcesVue.size()-1);
					nbModification-=nbRes;
					supprimerNobjetInventaire(nbModification);
				}else {
					super.getInventaireVue().modifierQuantiterObjet(ressourcesVue.get(ressourcesVue.size()-1), -nbModification);
				}
			}
		}
	}

	public void supprimerObjet(Pane objetVue){
		super.getInventaireVue().retirerObjet(objetVue);
		super.getObjet().addRessources(-Integer.parseInt(((Text)objetVue.getChildren().get(1)).getText()));
	}
	
	private void supprimerNobjetInventaire(int nbSupression) {
		for (int i=0; i<nbSupression/NbMaxRessourcesBlok; i++) {
			super.getInventaireVue().retirerObjet(ressourcesVue.get(ressourcesVue.size()-1));
			ressourcesVue.remove(ressourcesVue.size()-1);
			
		}
		if(nbSupression%NbMaxRessourcesBlok != 0) {
			int quantiter = Integer.parseInt(((Text)((Pane)ressourcesVue.get(ressourcesVue.size()-1)).getChildren().get(1)).getText());
			((Text)((Pane)ressourcesVue.get(ressourcesVue.size()-1)).getChildren().get(1)).setText(String.valueOf(quantiter-nbSupression%NbMaxRessourcesBlok));
		}
	}
	
	private void creerNObjetInventaireVue(int nbAjouts){
		for (int i=0; i<nbAjouts/NbMaxRessourcesBlok; i++) {
			Pane objet = creerObjetInventaireVue(NbMaxRessourcesBlok);
			ressourcesVue.add(objet);
			super.getInventaireVue().ajouterObjet(objet);
		}
		if(nbAjouts%NbMaxRessourcesBlok != 0) {
			Pane objet = creerObjetInventaireVue(nbAjouts%NbMaxRessourcesBlok);
			ressourcesVue.add(objet);
			Platform.runLater(
					() -> {
						super.getInventaireVue().ajouterObjet(objet);
					}
			);
		}
	}
	
	private Pane creerObjetInventaireVue(int nbRes){
		Text nombre = new Text(String.valueOf(nbRes));
		Pane objetVue = new Pane();
		objetVue.setPrefHeight(50);
		objetVue.setPrefWidth(50);
		nombre.getStyleClass().add("NbRessources");
		nombre.setLayoutY(49);
		nombre.setFont(super.getInventaireVue().getPoliceMincraft());
		ImageView imageObjet = new ImageView(super.getInventaireVue().getImagesObjets().getImage(super.getnumeroObj()));
		objetVue.getChildren().addAll(imageObjet, nombre);
		super.ajouterEvent(objetVue);
		return objetVue;
	}
}