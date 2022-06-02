package application.controleur;

import application.modele.Joueur;
import application.modele.Personnage;
import application.vue.JoueurVue;
import application.vue.DeplacementAnimation;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressed implements EventHandler<KeyEvent> {

	private Personnage joueur;
	private JoueurVue joueurVue;

	public KeyPressed(Personnage joueur2, JoueurVue joueurVue) {
		this.joueur = joueur2;
		this.joueurVue = joueurVue;
	}

	public void handle(KeyEvent event) {
			
		switch(event.getCode()) {
		
		case Q:
			DeplacementAnimation.allerGauche(joueur, joueurVue);
			break;
		case LEFT:	
			DeplacementAnimation.allerGauche(joueur, joueurVue);
			break;
		case D:
			DeplacementAnimation.allerDroite(joueur, joueurVue);
			break;
		case RIGHT:
			DeplacementAnimation.allerDroite(joueur, joueurVue);
			break;
		case Z:
			DeplacementAnimation.sauter(joueur, joueurVue);
			break;
		case UP:
			DeplacementAnimation.sauter(joueur, joueurVue);
			break;
		case SPACE:	
			DeplacementAnimation.sauter(joueur, joueurVue);
			break;
		case F1:	
			joueur.perdreVie(1);
			System.out.println("Le joueur perd un demi coeur.");
			break;
		case F2:	
			joueur.ajouterVie(1);
			System.out.println("Le joueur gagne un demi coeur.");
			break;
			
		default:
			break;
			
		}
		
	}

}
