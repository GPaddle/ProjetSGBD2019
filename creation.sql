/*
Restaurant 					(idRestaurant)
Salle								(idSalle, #idRestaurant)
TableRestau					(idTable, nbMax, #idSalle)
AssocTableServeur		(#idTable, #idServeur, Jour)
Commande						(idCommande, quantite, modePaiement, montantTotal, nbPerso,dateCom,dateEncaiss)
Plat								(typePlat, libelle prixUnit)
typePlat						(nomTypePlat, numTypePlat);
Serveur							(idServeur, categorie, nom, prenom)

//		ClientRestau	(idClient, nom, prenom)

*/




DROP TABLE IF EXISTS TypePlat;
CREATE TABLE TypePlat(
    numTypePlat INT PRIMARY KEY,
    nomTypePlat VARCHAR(50)
    );

INSERT INTO TypePlat VALUES
	('1','Entrée'),
	('2','Plat'),
	('3','Dessert');