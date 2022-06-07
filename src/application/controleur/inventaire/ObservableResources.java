package application.controleur.inventaire;

import java.util.ArrayList;

import application.modele.objet.Objet;
import application.vue.inventaire.InventaireVue;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ObservableResources extends ObservableObjet{
	
	private final static int NbMaxRessourcesBlok = 64;
	private ArrayList<Pane> ressourcesVue;
	
	public ObservableResources(Objet obj, InventaireVue inventaireVue) {
		super(obj, inventaireVue);
		ressourcesVue = super.getObjetsVue();
	}

	@Override
	public void actualiserVueInventaire(int ans, int nv){
		int nbModification = nv-ans;
		
		if(nbModification>0){
			if(!ressourcesVue.isEmpty()) {
				int nbRes = Integer.parseInt(((Text)((Pane)ressourcesVue.get(ressourcesVue.size()-1).getChildren().get(1)).getChildren().get(1)).getText());
				if( nbRes < NbMaxRessourcesBlok) {
					int nbResPossibleAjouter = NbMaxRessourcesBlok-nbRes;
					if(nbResPossibleAjouter >= nbModification) {
						((Text)((Pane)ressourcesVue.get(ressourcesVue.size()-1).getChildren().get(1)).getChildren().get(1)).setText(String.valueOf(nbRes+nbModification));
					}else{
						((Text)((Pane)ressourcesVue.get(ressourcesVue.size()-1).getChildren().get(1)).getChildren().get(1)).setText(String.valueOf(nbRes+nbResPossibleAjouter));
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
			super.getInventaireVue().ajouterObjet(objet);
		}
	}
	
	private Pane creerObjetInventaireVue(int nbRes){
		Text nombre = new Text(String.valueOf(nbRes));
		Pane objetVue = new Pane();
		objetVue.setPrefHeight(50);
		objetVue.setPrefWidth(50);
		super.ajouterEvent(objetVue);
		nombre.getStyleClass().add("NbRessources");
		nombre.setLayoutY(49);
		nombre.setFont(super.getInventaireVue().getPoliceMincraft());
		objetVue.getChildren().addAll(new ImageView(super.getInventaireVue().getImagesObjets().getImage(super.getnumeroObj())), nombre);
		return objetVue;
	}
}