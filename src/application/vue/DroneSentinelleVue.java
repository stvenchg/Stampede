package application.vue;

import javafx.scene.image.ImageView;

public class DroneSentinelleVue extends ImageView {

	private final static String CheminRelatifImagesSentinelle = "../ressources/sprites/droneSentinelle/";
	private Images tableauImagesSentinelle;

	public DroneSentinelleVue() {
		super();
		this.tableauImagesSentinelle = new Images(CheminRelatifImagesSentinelle);
		this.setImage(tableauImagesSentinelle.getImage(0));
	}

	public void setImage(int numero) {
		this.setImage(tableauImagesSentinelle.getImage(numero));
	}

}
