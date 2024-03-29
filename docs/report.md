# L3 design pattern report

- **Firstname**: Sépande
- **Lastname**: Nivandy

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


# Pour le mardi 13 février : #
J'ai découpé le code en plusieurs fichier afin d'avoir une meilleur lisibilité des méthodes utilisés et que ce ne soit plus dans un seul fichier Java.

Voici un schéma qui montre les relations entre les différentes classes et interfaces du code:

![Schéma](Schéma.png)

Dans ce schéma :

- App est la classe principale qui contient la méthode main, elle initialise l'exécution de l'application.
- Command est une interface qui définit la méthode execute, qui est implémentée par InsertCommand et ListCommand.
- CommandExecutor est une classe qui exécute les commandes en fonction de leur nom.
- InsertCommand ,ListCommand ,sont des classes qui implémentent l'interface Command , elles contiennent la logique spécifique à chaque commande (insert, list, respectivement).
- Les flèches représentent les dépendances ou les relations entre les différentes parties du code. Par exemple, App utilise CommandExecutor, qui lui-même utilise des instances de Command (qui peuvent être InsertCommand ou ListCommand).

# Pour le lundi 26 février : # 
J'ai ajoutél'implémentation de la méthode Done ainsi que de la méthode migrate pour pouvoir faire la migration des fichiers.

Voici un schéma qui montre les relations entre les différentes classes et interfaces du code:

![Schéma](Schéma2.png)

Dans ce Schéma : 

- **App.java:** C'est la classe principale de l'application. Elle contient la méthode main qui parse les arguments de la ligne de commande et exécute les commandes appropriées en fonction des arguments fournis.
- **Command.java:** C'est une interface définissant la méthode execute que toutes les commandes doivent implémenter.
- **CommandExecutor.java:** Cette classe est responsable de l'exécution des commandes en fonction de leur nom. Elle utilise une carte (Map) pour associer les noms de commandes à leurs implémentations concrètes.
- **InsertCommand.java:** Cette classe implémente la logique pour insérer une nouvelle tâche dans un fichier, soit au format JSON, soit au format CSV, en fonction de l'extension du fichier spécifié.
- **ListCommand.java:** Cette classe implémente la logique pour lister les tâches contenues dans un fichier, soit au format JSON, soit au format CSV, en fonction de l'extension du fichier spécifié. Elle prend également en charge l'option de filtrage pour afficher uniquement les tâches terminées.
- **MigrateCommand.java:** Cette classe implémente la logique pour migrer les données d'un fichier source vers un fichier de sortie, en prenant en charge les fichiers JSON et CSV.

# Pour le samedi 23 mars : # 
- Ajouter une commande pour extraire un attribut spécifique d'une source de données
- Modification de InsertCommand pour prendre en charge une nouvelle source de données de fichier,
- Implémentation d'une nouvelle classe NonFileDataSource pour gérer une source de données non-fichier
- Intégration de NonFileDataSource dans le fichier App
- Modification de MigrateCommand pour prendre en charge la migration depuis la nouvelle source de données

  Voici un schéma qui montre les relations entre les différentes classes et interfaces de tous les fichiers:

  ![Schéma](Schéma3.png)

  Dans ce Schéma : 

- **App.java:** C'est la classe principale de l'application. Elle contient la méthode main qui parse les arguments de la ligne de commande et exécute les commandes appropriées en fonction des arguments fournis.
- **Command.java:** C'est une interface définissant la méthode execute que toutes les commandes doivent implémenter.
- **CommandExecutor.java:** Cette classe est responsable de l'exécution des commandes en fonction de leur nom. Elle utilise une carte (Map) pour associer les noms de commandes à leurs implémentations concrètes.
- **InsertCommand.java:** Cette classe implémente la logique pour insérer une nouvelle tâche dans un fichier, soit au format JSON, soit au format CSV, en fonction de l'extension du fichier spécifié.
- **ListCommand.java:** Cette classe implémente la logique pour lister les tâches contenues dans un fichier, soit au format JSON, soit au format CSV, en fonction de l'extension du fichier spécifié. Elle prend également en charge l'option de filtrage pour afficher uniquement les tâches terminées.
- **MigrateCommand.java:** Cette classe implémente la logique pour migrer les données d'un fichier source vers un fichier de sortie, en prenant en charge les fichiers JSON et CSV.
- **AttributeSourceCommand.java:** Cett classe ajoute une source d'un attribut.
- **NonFileDataSource.java:** Cette classe représente une source de données non-fichier à partir de laquelle les données peuvent être récupérées.
