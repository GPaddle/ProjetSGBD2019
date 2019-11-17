# Projet GoodFood

L'ensemble des drives disponible est situé dans le dossier `src/Jars`

## SGBD couverts :

 - Oracle
 - MariaDB (sauf pour les trigers)

## Ajout de SGBD non définis

 - Ajout d'une classe `fabrique_SGBDName` héritant de la classe [FabriqueDB.java](https://github.com/GPaddle/ProjetSGBD2019/blob/master/src/Fabrique/FabriqueDB.java "FabriqueDB.java")
Puis redéfinir notamment la fonction `public String getDate();`
Pour d'autres ajouts spécifiques dans les fonctions : soumettre une requête
 
 - Ajouter le nom de son SGBD dans la liste 
	`String[] als = { "Oracle", "MariaDB" };`

- Ajouter une clause dans la méthode connect() de type :

		else if (typeBD.getValue().equals("_SGBDName")) {
		Main.fdb = new Fabrique_SGBDName();


## ClassPath

A inclure dans le classpath :

 - Driver JDBC
 - Librairie JFoenix
 - JavaFX

## Fonctionnalités :

 - Liste des plats servis sur une période
 - Liste des plats non servis sur une période
 - Liste des serveurs ayant servis sur une table sur une période
 - Le chiffre d'affaire  et nombre de commande des serveurs sur une période 
 - Liste des serveurs n'ayant pas fait de chiffre d'affaire sur une période
 - Mise à jour des montants sur une commande donnée 
 
 ---
 A envoyer :
thibault.tricard@inria.fr

Objet mail : projetsgbd
