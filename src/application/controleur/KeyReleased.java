package application.controleur;

import application.modele.Personnage;
import application.vue.JoueurVue;
import application.vue.RobotFantassinVue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyReleased implements EventHandler<KeyEvent>{

	private Personnage joueur;
	private Personnage robotFantassin;
	private JoueurVue joueurVue;
	private RobotFantassinVue robotFantassinVue;

	public KeyReleased(Personnage joueur2, JoueurVue joueurVue) {
		this.joueur = joueur2;
		this.joueurVue = joueurVue;
	}
	
	@Override
	public void handle(KeyEvent event) {

		if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.Q || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
			joueurVue.setImage(joueurVue.getImages().getImage(0));
		}	
	}

}
