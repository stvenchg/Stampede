# Stampede
Projet universitaire d'un jeu semblable à Terraria en Java.

Membres du groupe : Philippe SANTOS, Steven CHING, Yanis HAMANI

A faire :

Aller dans la classe CarteVue situé dans le package Vue et modifier la ligne 110 {

      si vous être sur une vertion de java > 1.8 alors remplacer par {
            String url = tile.getImage().getUrl();
            
      }sinon (exemple PC de l'IUT de Montreuil) {
            String url = tile.getImage().impl_getUrl();
      }
}
