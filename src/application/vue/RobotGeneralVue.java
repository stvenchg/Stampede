package application.vue;

import javafx.scene.image.ImageView;

public class RobotGeneralVue extends ImageView {

    private final static String CheminRelatifImagesGeneral = "../ressources/sprites/robotGeneral/";
    private Images tableauImagesGeneral;

    public RobotGeneralVue() {
        super();
        this.tableauImagesGeneral = new Images(CheminRelatifImagesGeneral);
        this.setImage(tableauImagesGeneral.getImage(0));
    }

    public void setImage(int numero) {
        this.setImage(tableauImagesGeneral.getImage(numero));
    }

}
