package application.modele.objet;



import application.modele.objet.Outils.Pioche;
import application.modele.objet.armes.Epee;
import application.modele.objet.materiaux.MineraiDeFer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

	private ObservableList<Objet> inventaire;
	
	public Inventaire() {
		inventaire = FXCollections.observableArrayList();
		inventaire.add(new Pioche(0));
		inventaire.add(new Epee(0));
		inventaire.add(new MineraiDeFer(0));
	}
	
	public void ajouterObjet(int obj, int nombre) {
		inventaire.get(obj).addRessources(nombre);	
	}
	
	public Objet getObjet(int numero){
		return inventaire.get(numero);
	}
	
	public void ajouterObjet(int obj) {
		ajouterObjet(obj, 1);
	}
	
	public void supprimerObjet(int obj, int nombre) {
		ajouterObjet(obj, -nombre);
	}
	
	public void supprimerObjet(int obj) {
		ajouterObjet(obj, -1);
	}
	
	public ObservableList<Objet> getInventaireObservable(){
		return this.inventaire;
	}
	
	public int getSize() {
		return inventaire.size();
	}

	@Override
	public String toString() {
		return "Inventaire [inventaire=" + inventaire + "]";
	}
	
	
	
}
