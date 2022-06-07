package application.controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyReleased implements EventHandler<KeyEvent> {

	private Controleur controleur;
	
	public KeyReleased(Controleur controleur) {
		this.controleur = controleur;
	}
	
	@Override
	public void handle(KeyEvent event) {
		switch(event.getCode()) {
			case TAB:
				//controleur.getInventaireVue().affichageInventaire();
				break;
		}
		
	}

}
