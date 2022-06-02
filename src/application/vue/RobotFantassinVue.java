package application.vue;

import javafx.scene.image.ImageView;

public class RobotFantassinVue extends ImageView {

	private final static String CheminRelatifImagesFantassin = "../ressources/sprites/robotFantassin/";
	private Images tableauImagesFantassin;

	public RobotFantassinVue() {
		super();
		this.tableauImagesFantassin = new Images(CheminRelatifImagesFantassin);
		this.setImage(tableauImagesFantassin.getImage(0));
	}

	public void setImage(int numero) {
		this.setImage(tableauImagesFantassin.getImage(numero));
	}

}
