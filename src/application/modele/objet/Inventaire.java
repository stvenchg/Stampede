package application.modele.objet;



import application.modele.objet.Outils.Pioche;
import application.modele.objet.armes.Epee;
import application.modele.objet.armes.Pistolet;
import application.modele.objet.materiaux.*;

import java.util.ArrayList;

public class Inventaire {

	private ArrayList<Objet> inventaire;
	
	public Inventaire() {
		inventaire = new ArrayList<>();
		initialisationInventaire();
	}

	private void initialisationInventaire(){
		inventaire.add(new Pioche());
		inventaire.add(new Epee());
		inventaire.add(new Terre());
		inventaire.add(new BlocDeMetal());
		inventaire.add(new Pistolet());
		inventaire.add(new PlaqueDeMetal());
		inventaire.add(new Balle());
		inventaire.add(new Vaisseau());
		inventaire.add(new Ressort());
		inventaire.add(new Engrenage());
		inventaire.add(new NoyauEnergie());
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

	@Override
	public String toString() {
		return "Inventaire [inventaire=" + inventaire + "]";
	}
	
	
	
}
