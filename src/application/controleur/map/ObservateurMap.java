package application.controleur.map;


import application.vue.CarteVue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;


import java.util.ArrayList;

public class ObservateurMap implements ListChangeListener<Integer> {
    private CarteVue carteVue;
    private int positionTileMiner;
    private ObservableList mapVue;

    private ObservableList<Integer> map;

    public ObservateurMap(CarteVue carteVue, ObservableList<Integer> map){
        this.carteVue = carteVue;
        this.map = map;
        this.mapVue = carteVue.getPanneauJeu().getChildren();
    }


    @Override
    public void onChanged(Change<? extends Integer> change) {
        int i = 0;
        while (change.next()){
            if(change.wasReplaced()) {
                positionTileMiner = carteVue.getPositionTileMiner();
                ((ImageView) mapVue.get(positionTileMiner)).setImage(carteVue.getImagesTilesMap().getImage(map.get(positionTileMiner)));
                if(change.getAddedSubList().get(i) == 0){
                    ((ImageView) mapVue.get(positionTileMiner)).setOpacity(0);
                }else {
                    ((ImageView) mapVue.get(positionTileMiner)).setOpacity(1);
                }
            }
            i++;
        }
    }
}
