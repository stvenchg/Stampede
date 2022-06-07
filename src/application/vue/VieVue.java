package application.vue;

import javafx.scene.image.ImageView;

public class VieVue extends ImageView {

	final static String CheminRelatifTilesMap = "../ressources/sprites/joueur/vie/";
	final static int DecalageDuBordBarreVie = 10;

	private Images images;
	private CarteVue map;

	public VieVue(CarteVue map) {
		this.map = map;
		images = new Images(CheminRelatifTilesMap);
		afficherVie();
	}

	public void afficherVie() {
		setImage(11);
		this.setLayoutX(
				-DecalageDuBordBarreVie + map.getLargeur() * map.getTailleBlock() - images.getImage(0).getWidth());
		this.setLayoutY(DecalageDuBordBarreVie);
	}

	public void setImage(int numero) {
		this.setImage(images.getImage(numero));
	}
}