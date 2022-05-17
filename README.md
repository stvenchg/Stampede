# Stampede
Projet universitaire d'un jeu semblable à Terraria en Java.

Membres du groupe : Philippe SANTOS, Steven CHING, Yanis HAMANI.

Cahier d'initialisation: 



JEU : Stampede

Univers du jeu : Planète Désertée

Le jeu se déroule dans un monde post-apocalyptique après une guerre opposant 2 puissances de l’espace, sur une
planète dont le seul paysage est un désert de débris métalliques, le dernier être vivant : c’est vous. Votre
objectif est de quitter la planète en construisant un vaisseau spatial. Pour ce faire, vous devez réunir assez de
métal et surtout une source d’énergie pour faire fonctionner votre vaisseau.
Vous n’êtes cependant pas en sécurité. Des robots militaires encore actifs et programmés pour tirer sur tout ce
qu’ils voient vous mettront des bâtons dans les roues. 

Personnage:

Il possède une barre de vie limitée à 6 cœurs maximum, il récupère un demi-cœur toutes les trente secondes. Si le
personnage n’a plus de cœur, il redémarre de zéro.

Déplacement:

    Q - Aller à gauche
    D - Aller à droite
    Espace - Sauter 

Interactions:

    Clic Gauche sur un élément du monde - Casser (Pioche équipée) / Attaquer (Épée équipée) / Tirer (Flingue
    équipé)

    Clic Droit sur un élément du monde - Placer un bloc

Inventaire:

    L’ensemble des armes, des outils et des matériaux sont visibles depuis l’inventaire en appuyant sur E.

Craft:
    
    A partir des matériaux obtenus, on peut craft des objets depuis son inventaire.

Armes/Outils disponibles:

    Armes:
        Epée     (Utilisable dès le départ ; Usage infini ; Courte portée)
            Dégâts : 2 coeurs 
        Flingue  (Craft ; Usage limité au nombre de munitions ; Longue portée)
            Dégâts : 1.5 coeurs
Outils:
    Pioche   (Utilisable dès le départ ; Usage infini ; Courte portée)
    
Matériaux:
    
    Plaque de métal (Obtenable en cassant des débris)
        Objet de craft
    Engrenage (Obtenable en détruisant des robots)
        Objet de craft
    Ressort (Obtenable en détruisant des robots)
        Objet de craft
    Bloc de métal (Craft) 
        Bloc placable
    Noyau d’énergie (En battant le robot général)
        Objet de craft

Ennemis:

Robot arachnoïde :   Cet ennemi pourra se déplacer latéralement au sol légèrement moins vite que le joueur, il ne
peut attaquer le joueur que par combat très rapproché (Il fait des dégâts en étant en contact avec le joueur).
(Drop: 2 ressorts)
(Vie: 3 coeurs)
(Dégâts: 1.5 coeurs)

Robot fantassin : Cet ennemi pourra se déplacer latéralement au sol à la même vitesse que le joueur, il ne peut
attaquer le joueur que par combat rapproché (Il est armé d’une épée).
(Drop: 3 engrenages)
(Vie: 5 coeurs)
(Dégâts: 2 coeurs)

Drone sentinelle : Cet ennemi pourra se déplacer latéralement dans les airs légèrement plus vite que le joueur, il
ne peut attaquer le joueur qu’à distance en tirant des missiles. Uniquement le flingue peut venir à bout de cet
ennemi.
Cet ennemi n'apparaît pas tant que le joueur n’a pas le flingue.
(Drop: 1 ressorts et 2 engrenages)
(Vie: 3 coeurs)
(Dégâts: 1.5 coeurs)

Robot général : Cet ennemi est l’ennemi final, il apparaît une fois que tous les matériaux pour le vaisseau sont
réunis (hormis le noyau d’énergie). Il pourra se déplacer latéralement au sol et en sautant (comme le joueur) plus
vite que le joueur. Il peut attaquer le joueur au corps-à-corps avec une épée et à distance avec un flingue.
(Drop: Noyau d’énergie)
(Vie: 10 coeurs)
(Dégâts flingue: 1.5 coeurs)    
(Dégâts épée: 2 coeurs)




Recettes de craft:

    Bloc de métal : 5 plaques de métal
    Flingue : 3 plaque de métal + 5 ressorts + 6 engrenages
    Balles de flingue (6) : 1 plaque de métal
    Vaisseau : 20 plaques de métal + 20 engrenages + 10 ressorts + 1 noyau d’énergie

Fin du jeu: 

    Le jeu s’achève lorsque le joueur fabrique le vaisseau pour quitter la planète. Une fois ceci fait, l’écran de
    fin de jeu apparaît (éventuellement une cinématique (vraiment pas sûr)).




