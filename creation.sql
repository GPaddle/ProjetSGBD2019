DROP TABLE IF EXISTS TypePlat;
CREATE TABLE TypePlat(
    numType INT PRIMARY KEY,
    nomType VARCHAR(50)
    );

INSERT INTO TypePlat VALUES
	('1','Entrée'),
	('2','Plat'),
	('3','Dessert');