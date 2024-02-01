# Explication de ce que j'ai fait sur le TP semaine par semaine

# Pour le mardi 30 janvier : # 
J'ai décomposé le code, en voyant qu'il y avait beaucoup trop de "if" les uns dans les autres, j'ai décidé de les remplacé en créants des méthodes.
J'ai ajouter une méthode pour vérifier si le fichier est valide.
J'ai aussi créé deux constante qui ne peuvent pas être modifiée après son initialisation qui ont été servi  définir les extensions JSON et CSV qui vont servir dans des méthodes
que j'ai ajouter pour pouvoir comprendre comment les fichiers JSON et CSV sont gérer plus facilement.
J'ai donc par la suite créer différentes méthodes qui ont servi à gérer la commande "list" pour les listes de tâches qui redirigera vers une des deux méthodes si c'est un fichier CSV ou Json, j'ai fait la même chose pour la commande "insert".

# Pour le mardi 6 février : #
J'ai essayer d'implémenter la méthode SOLID pour ce code, ainsi, j'ai constaté qu'il y avait encore des if imbriqué les uns dans les autres, ainsi j'ai décidé d'utiliser "Map" afin d'implémenter les deux commandes "insert" et "list".

Les commandes sont implémentées en utilisant l'interface "Command". Deux implémentations concrètes sont fournies : "InsertCommand" pour ajouter une tâche et "ListCommand" pour afficher la liste.

La classe "CommandExecutor" est responsable de l'exécution de la commande spécifiée en utilisant une map associant le nom de la commande à son implémentation. Cela suit le principe de substitution de l'inversion de contrôle (IoC) du principe SOLID.

Les méthodes "handleJsonList", "handleCsvList", "handleJsonInsert", et "handleCsvInsert" effectuent le traitement spécifique du format du fichier en fonction de l'extension du fichier spécifié.

Utilisation des Principes SOLID :

**S (Single Responsibility Principle) :** Chaque classe a une responsabilité unique, par exemple, la classe "CommandExecutor" a pour responsabilité l'exécution de commandes.

**O (Open/Closed Principle) :** L'application peut être étendue pour prendre en charge de nouvelles commandes sans modifier le code existant, grâce à l'utilisation d'interfaces et de classes spécialisées.

**L (Liskov Substitution Principle) :** Les différentes implémentations de la commande ("InsertCommand" et "ListCommand") peuvent être utilisées de manière interchangeable via l'interface "Command".

**I (Interface Segregation Principle) :** L'utilisation d'interfaces ("Command") assure que chaque implémentation n'a que les méthodes nécessaires à sa fonctionnalité spécifique.

**D (Dependency Inversion Principle) :** L'application suit le principe d'inversion de contrôle en utilisant une map pour associer des noms de commandes à leurs implémentations.
