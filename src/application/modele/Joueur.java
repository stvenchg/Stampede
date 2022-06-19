package application.modele;

import application.modele.objet.Inventaire;
import application.modele.objet.Objet;

public class Joueur extends Personnage {

	private Inventaire inventaire;
	private Objet enMain;

	public Joueur() {
		super(12);
		this.setX(100);
		this.setY(310);
		this.inventaire = new Inventaire();
		this.enMain = null;
	}

	public void ajouterRessourceInitial(){
		inventaire.ajouterObjet(0, 1);
		inventaire.ajouterObjet(1, 1);
		inventaire.ajouterObjet(5, 30);
		inventaire.ajouterObjet(8, 9);
		inventaire.ajouterObjet(9, 30);
		//inventaire.ajouterObjet(10, 1);
		this.enMain = inventaire.getObjet(1);
	}

	@Override
	public void attaque(Personnage perso) {
		perso.perdreVie(4);
	}

	@Override
	public void respawn() {
		super.vie.setValue(12);
	}

	public void prendreEnMain(Objet objet){
		this.enMain = objet;
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public Object getEnMain(){
		return enMain;
	}

}
