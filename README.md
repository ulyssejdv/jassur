
#1. Utilisation de Ant

Pour utiliser les commandes qui suivent, il est nécessaire d'installer Ant sur son OS !
On peut utiliser [la documentation officielle](http://ant.apache.org)

###Création du repertoire bin :
```
$ ant init
```
###Supprimer le répertoire bin :
```
$ ant clean
```
###Compilation du projet :
```
$ ant compile
```
###Démarer le serveur :
```
$ ant startServer
```
###Créer le JAR distribuable du client :
```
$ ant distclient
```



#2. Utilisation de git

### Fonctionnement des branches
**Develop :** tous les développements en local, sont propagés sur cette branche

**Release :** cette branche est utilisée pour tester le code en condition de production sur un environnement de test. Une fois tous les tests réalisés, la branche peut être propagée sur le branches master et develop.

**Master :** branche de « production » le code qu’il contient est du code mature, et contenant aucun bug provenant de la branche « release ».

**Hotfixes :** Une instance de cette branche pour corriger rapidement un petit pb bloquant sur du code de la branche master / ! \ ne doit être utilisé uniquement en cas d’urgence / ! \ après correction du problème, le code est propager sur les branches master et develop afin de continuer de travailler sur du code sain. 


Voir [ce schéma (plus complexe)](https://media.licdn.com/mpr/mpr/shrinknp_800_800/AAEAAQAAAAAAAAKMAAAAJDM2NjY0OWE4LTc0NDAtNDdkMS1hMDdiLWU3MzkwM2FjYWExNw.png) qui illustre bien la gestion des branches du projet

###Récupérer (cloner) le projet :
```
$ git clone https://github.com/ulyssejdv/jassur
```
###Partager ses modifications :
```
$ git add README.md # prise en compte des fichier modifiés
$ git commit README.md -m "description de ant dans le readme" # sauvegarde local des fichiers modifés
$ git push origin develop # propagation des modifications sur la remote origin
```
###Gérer les tags : 
```
git tag –a X.X <checksum> -m <message>
git push origin X.X
```

###Gérer les branches
```
$ git branch <nom de la branche> # Créer une branche
$ git checkout <nom de la branche> # Changer de branche
$ git merge <src> <dst> # Merger deux branches
$ git branch –d <nom de la branche> # Supprimer une branche
```
