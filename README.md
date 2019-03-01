# 2i013
Course de voiture
cf http://www-connex.lip6.fr/~guigue/wikihomepage/pmwiki.php?n=Course.CourseLI260 pour les détails du projet.

TODO :

- tout commenter en mode javadoc
- créer un UML du projet
- tester le fonctionnement de serializable : sauvegarde et chargement d'objet, après modifications de la classe.

Gestion du moteur physique :
- Dans drive (ou similaire) :
  - Gérer les tests de faisabilité de la commande, en modifiant la commande pour qu'elle passe les conditions dans drive
  - Gérer un attribut boolean derapage de voiture, qu'on met à true lorsqu'on envoie une commande qui fait déraper. On le repasse à False lorsque qu'une nouvelle commande ramène la voiture à un mouvement sans dérapage. 
  - dans les deux cas à gérer avec les tableaux tabTurn et tabVitesse : regarder dans quel intervalle la vitesse actuelle se trouve et regarder l'intervalle correspondant des rotation autorisée...

