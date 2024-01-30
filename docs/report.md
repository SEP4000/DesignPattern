# L3 design pattern report

- **Firstname**: Sépande
- **Lastname**: Nivandy

**Pour le mardi 30 janvier :** 
J'ai décomposé le code, en voyant qu'il y avait beaucoup trop de "if" les uns dans les autres, j'ai décidé de les remplacé en créants des méthodes.
J'ai ajouter une méthode pour vérifier si le fichier est valide.
J'ai aussi créé deux constante qui ne peuvent pas être modifiée après son initialisation qui ont été servi  définir les extensions JSON et CSV qui vont servir dans des méthodes
que j'ai ajouter pour pouvoir comprendre comment les fichiers JSON et CSV sont gérer plus facilement.
J'ai donc par la suite créer différentes méthodes qui ont servi à gérer la commande "list" pour les listes de tâches qui redirigera vers une des deux méthodes si c'est un fichier CSV ou Json, j'ai fait la même chose pour la commande "insert".

