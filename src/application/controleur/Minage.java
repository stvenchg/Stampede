package application.controleur;

import application.modele.Joueur;
import application.modele.SoundEffect;
import application.modele.objet.Outils.Pioche;
import application.modele.objet.materiaux.Ressource;
import application.vue.CarteVue;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Minage implements Runnable {

    private CarteVue mapVue;

    private Joueur joueur;
    private SoundEffect sonMinage;

    private ArrayList<Integer> map;

    public Minage(CarteVue mapVue, Joueur joueur, ArrayList<Integer> map){
        this.mapVue = mapVue;
        this.joueur = joueur;
        this.map = map;
    }

    @Override
    public void run() {
        miner();
    }

    private void miner(){
        if(joueur.getEnMain() instanceof Pioche){
            Ressource objetMiner = ((Ressource) joueur.getInventaire().getObjet(mapVue.getNumeroTileMiner()));

            int i=100; // +/- 10 miliseconde c'est le temps que il faut pour qu'une pression soit reconu
            while(i<objetMiner.getResistance()*1000 && i != -1){
                try {
                    Thread.sleep(9);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                if(mapVue.isEtatClik() == false){
                    i = -1;
                }else{
                    i+=10;
                }
            }
            if(i != -1){
                int position = mapVue.getPanneauJeu().getChildren().indexOf(mapVue.getTileMiner());
                map.remove(position);
                map.add(position, 0);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        mapVue.getPanneauJeu().getChildren().remove(position);
                        mapVue.getPanneauJeu().getChildren().add(position, new ImageView(mapVue.getImagesTilesMap().getImage(map.get(position))));
                        mapVue.ajouterEvents((ImageView) mapVue.getPanneauJeu().getChildren().get(position));
                        joueur.getInventaire().ajouterObjet(mapVue.getNumeroTileMiner());
                    }
                });

            }
        }
    }
}
