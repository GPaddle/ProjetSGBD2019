/*
Restaurant 					(idRestaurant)
Salle								(idSalle, #idRestaurant)
TableRestau					(idTable, nbMax, #idSalle)
AssocTableServeur		(#idTable, #idServeur, Jour)
Commande						(idCommande, quantite, modePaiement, montantTotal, nbPerso,dateCom,dateEncaiss)
Plat								(typePlat, libelle prixUnit)
TypePlat						(nomTypePlat, numTypePlat);
Serveur							(idServeur, categorie, nom, prenom)

//		ClientRestau	(idClient, nom, prenom)

*/


DECLARE
  cnt NUMBER;
BEGIN
  SELECT COUNT(*) INTO cnt FROM user_tables WHERE table_name = 'COMMANDE';
  IF cnt <> 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE commande CASCADE CONSTRAINTS ';
  END IF;
  SELECT COUNT(*) INTO cnt FROM user_tables WHERE table_name = 'PLAT';
  IF cnt <> 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE plat CASCADE CONSTRAINTS';
  END IF;
  SELECT COUNT(*) INTO cnt FROM user_tables WHERE table_name = 'SERVEUR';
  IF cnt <> 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE serveur CASCADE CONSTRAINTS';
  END IF;
  SELECT COUNT(*) INTO cnt FROM user_tables WHERE table_name = 'TABL';
  IF cnt <> 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE tabl CASCADE CONSTRAINTS';
  END IF;
  SELECT COUNT(*) INTO cnt FROM user_tables WHERE table_name = 'CONTIENT';
  IF cnt <> 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE contient CASCADE CONSTRAINTS';
  END IF;
  SELECT COUNT(*) INTO cnt FROM user_tables WHERE table_name = 'AFFECTER';
  IF cnt <> 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE affecter CASCADE CONSTRAINTS';
  END IF;
  SELECT COUNT(*) INTO cnt FROM user_tables WHERE table_name = 'AUDITER';
  IF cnt <> 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE auditer CASCADE CONSTRAINTS';
  END IF;
END;

/*
CREATE TABLE TypePlat
(
  numTypePlat INT PRIMARY KEY,
  nomTypePlat VARCHAR(50)
);

CREATE TABLE Restaurant
(
  idRestaurant int primary key
);
CREATE TABLE Salle
(
  idSalle int primary key,
  idRestaurant int,
  foreign key (idRestaurant) references Restaurant (idRestaurant)
);
CREATE TABLE TableRestau
(
  idTable int primary key,
  nbMax number,
  #  idSalle
);
CREATE TABLE AssocTableServeur
(
  #
  idTable int , #
  idServeur,
  Jour
);
CREATE TABLE Commande
(
  idCommande,
  quantite,
  modePaiement,
  montantTotal,
  nbPerso,
  dateCom,
  dateEncaiss
);
CREATE TABLE Plat
(
  typePlat,
  libelle,
  prixUnit
);
CREATE TABLE TypePlat
(
  nomTypePlat,
  numTypePlat
);
CREATE TABLE Serveur
(
  idServeur,
  categorie,
  nom,
  prenom
);

INSERT INTO TypePlat
VALUES ('1', 'Entrée');
INSERT INTO TypePlat
VALUES ('2', 'Plat');
INSERT INTO TypePlat
VALUES ('3', 'Dessert');

*/
-- Structure des tables :
create table commande
(
  numcom  number(4),
  numtab  number(4),
  datcom  date,
  nbpers  number(2),
  datpaie date,
  modpaie varchar2(15),
  montcom number(8, 2),
  primary key (numcom)
);

create table plat
(
  numplat  number(4),
  libelle  varchar2(40),
  type     varchar2(15),
  prixunit number(6, 2),
  primary key (numplat)
);
create table serveur
(
  numserv number(2),
  nomserv varchar2(25),
  grade   varchar2(20),
  primary key (numserv)
);
create table tabl
(
  numtab  number(4),
  nbplace number(2),
  primary key (numtab)
);
create table contient
(
  numcom   number(4),
  numplat  number(4),
  quantite number(2),
  primary key (numcom, numplat)
);
create table affecter
(
  numtab  number(4),
  dataff  date,
  numserv number(2),
  primary key (numtab, dataff)
);

alter table commande
  add ( foreign key (numtab) references tabl (numtab));
alter table affecter
  add ( foreign key (numserv) references serveur (numserv));
alter table affecter
  add ( foreign key (numtab) references tabl (numtab));
alter table contient
  add ( foreign key (numcom) references commande (numcom));
alter table contient
  add ( foreign key (numplat) references plat (numplat));

create table auditer
(
  numcom  number(4),
  numtab  number(4),
  datcom  date,
  nbpers  number(2),
  datpaie date,
  montcom number(8, 2),
  primary key (numcom)
);


alter table auditer
  add ( foreign key (numcom) references commande (numcom));

--Données :
-- Tuples de Serveur
insert into serveur
values (1, 'Tutus Peter', 'maitre d''hotel');
insert into serveur
values (2, 'Lilo Vito', 'serveur g1');
insert into serveur
values (3, 'Don Carl', 'serveur g2');
insert into serveur
values (4, 'Leo Jon', 'serveur g1');
insert into serveur
values (5, 'Dean Geak', 'chef serveur');
-- Tuples de Plat
insert into plat
values (1, 'assiette de crudités', 'Entrée', 25);
insert into plat
values (2, 'tarte de saison', 'Dessert', 25);
insert into plat
values (3, 'sorbet mirabelle', 'Dessert', 35);
insert into plat
values (4, 'filet de boeuf', 'Viande', 62);
insert into plat
values (5, 'salade verte', 'Entrée', 15);
insert into plat
values (6, 'chevre chaud', 'Entrée', 21);
insert into plat
values (7, 'pate lorrain', 'Entrée', 25);
insert into plat
values (8, 'saumon fumé', 'Entrée', 30);
insert into plat
values (9, 'entrecote printaniere', 'Viande', 58);
insert into plat
values (10, 'gratin dauphinois', 'Plat', 42);
insert into plat
values (11, 'brochet à l''oseille', 'Poisson', 68);
insert into plat
values (12, 'gigot d''agneau', 'Viande', 56);
insert into plat
values (13, 'crème caramel', 'Dessert', 15);
insert into plat
values (14, 'munster au cumin', 'Fromage', 18);
insert into plat
values (15, 'filet de sole au beurre', 'Poisson', 70);
insert into plat
values (16, 'fois gras de lorraine', 'Entrée', 61);
-- Tuples de Tabl
insert into tabl
values (10, 4);
insert into tabl
values (11, 6);
insert into tabl
values (12, 8);
insert into tabl
values (13, 4);
insert into tabl
values (14, 6);
insert into tabl
values (15, 4);
insert into tabl
values (16, 4);
insert into tabl
values (17, 6);
insert into tabl
values (18, 2);
insert into tabl
values (19, 4);
-- Tuples de Commande
insert into commande
values (100, 10, '10/09/2016', 2, to_date('10/09/2016 20:50', 'dd/mm/yyyy hh24:mi'), 'Carte', null);
insert into commande
values (101, 11, '10/09/2016', 4, to_date('10/09/2016 21:20', 'dd/mm/yyyy hh24:mi'), 'Chèque', null);
insert into commande
values (102, 17, '10/09/2016', 2, to_date('10/09/2016 20:55', 'dd/mm/yyyy hh24:mi'), 'Carte', null);
insert into commande
values (103, 12, '10/09/2016', 2, to_date('10/09/2016 21:10', 'dd/mm/yyyy hh24:mi'), 'Espèces', null);
insert into commande
values (104, 18, '10/09/2016', 1, to_date('10/09/2016 21:00', 'dd/mm/yyyy hh24:mi'), 'Chèque', null);
insert into commande
values (105, 10, '10/09/2016', 2, to_date('10/09/2016 20:45', 'dd/mm/yyyy hh24:mi'), 'Carte', null);
insert into commande
values (106, 14, '11/10/2016', 2, to_date('11/10/2016 22:45', 'dd/mm/yyyy hh24:mi'), 'Carte', null);
-- Tuples de Affecter
insert into affecter
values (10, '10/09/2016', 1);
insert into affecter
values (11, '10/09/2016', 1);
insert into affecter
values (12, '10/09/2016', 1);
insert into affecter
values (17, '10/09/2016', 2);
insert into affecter
values (18, '10/09/2016', 2);
insert into affecter
values (15, '10/09/2016', 3);
insert into affecter
values (16, '10/09/2016', 3);
insert into affecter
values (10, '11/09/2016', 1);
-- Tuples de Contient
insert into contient
values (100, 4, 2);
insert into contient
values (100, 5, 2);
insert into contient
values (100, 13, 1);
insert into contient
values (100, 3, 1);
insert into contient
values (101, 7, 2);
insert into contient
values (101, 16, 2);
insert into contient
values (101, 12, 2);
insert into contient
values (101, 15, 2);
insert into contient
values (101, 2, 2);
insert into contient
values (101, 3, 2);
insert into contient
values (102, 1, 2);
insert into contient
values (102, 10, 2);
insert into contient
values (102, 14, 2);
insert into contient
values (102, 2, 1);
insert into contient
values (102, 3, 1);
insert into contient
values (103, 9, 2);
insert into contient
values (103, 14, 2);
insert into contient
values (103, 2, 1);
insert into contient
values (103, 3, 1);
insert into contient
values (104, 7, 1);
insert into contient
values (104, 11, 1);
insert into contient
values (104, 14, 1);
insert into contient
values (104, 3, 1);
insert into contient
values (105, 3, 2);
insert into contient
values (106, 3, 2);

-- 1

select datcom, plat.numplat, plat.libelle
from PLAT
       inner join contient on plat.numplat = contient.numplat
       inner join commande on contient.numcom = commande.numcom
where datcom between to_date('2016-09-01', 'YYYY-MM-DD') and to_date('2017-01-01', 'YYYY-MM-DD');

-- Changer les dates sur le where


-- 2

select numplat, libelle
from PLAT

minus

select plat.numplat, plat.libelle
from PLAT
       inner join contient on plat.numplat = contient.numplat
       inner join commande on contient.numcom = commande.numcom
where datcom between to_date('2016-09-01', 'YYYY-MM-DD') and to_date('2016-09-20', 'YYYY-MM-DD');
-- Changer les dates sur le where


-- 3

select distinct nomserv, to_char(dataff, 'DD/MM/YYYY')
from serveur
       inner join affecter on serveur.numserv = affecter.numserv
where numtab = 10
  and dataff between to_date('2015-01-01', 'YYYY-MM-DD') and to_date('2019-09-20', 'YYYY-MM-DD');
-- Changer les dates sur le where et le numtab


-- 4

select serveur.nomserv, sum(montcom), count(*)
from serveur
       inner join affecter on serveur.numserv = affecter.numserv
       inner join tabl on tabl.numtab = affecter.numtab
       inner join commande on commande.numtab = tabl.numtab
where datcom between to_date('2016-09-01', 'YYYY-MM-DD') and to_date('2016-09-20', 'YYYY-MM-DD')
group by serveur.nomserv, serveur.numserv
order by sum(montcom) DESC;


----------------------------------------------

-- 5

select nomserv
from serveur

minus

select serveur.nomserv
from serveur
       inner join affecter on serveur.numserv = affecter.numserv
       inner join tabl on tabl.numtab = affecter.numtab
       inner join commande on commande.numtab = tabl.numtab
where datcom between to_date('2016-09-01', 'YYYY-MM-DD') and to_date('2016-09-20', 'YYYY-MM-DD')
group by serveur.nomserv, serveur.numserv;


-- 6

create or replace procedure majMontant is
  cursor numC is (select contient.numcom, plat.prixunit * contient.quantite as mont
                  from contient
                         inner join plat on contient.numplat = plat.numplat);
begin

  for commandeCourante in numC
    loop
      update commande set montcom = commandeCourante.mont where commandeCourante.numcom = commande.numcom;
      --dbms_output.put_line(commandeCourante.mont);
    end loop;

end;


-- 7
select *
from serveur;

select *
from commande;

select *
from affecter;


create or replace
  TRIGGER enregistrementAuditer
  after update of montcom
  on commande
  for each row
declare

  nbPersCour number(2);
  montantCom number(3);
  gServ      varchar2(20);
  nb         number(1);

begin
  montantCom := :new.montcom;

  nbPersCour := :new.nbpers;

  select grade into gServ
  from serveur
         inner join affecter on serveur.numserv = affecter.numserv
  where to_char(:new.datpaie, 'DD/MM/YYYY') = to_char(affecter.dataff, 'DD/MM/YYYY')
    and affecter.numtab = :new.numtab;

  if (gServ = 'maitre d''hotel' and montantCom / nbPersCour < 15) then

    select COUNT(*) into nb
    from auditer
    where :new.numcom = auditer.numcom;

    if (nb = 0) then
      insert
      into auditer
      values (:new.numcom, :new.numtab, :new.datcom, :new.nbpers, :new.datpaie, :new.montcom);
    end if;

  end if;

exception
  When NO_DATA_FOUND Then
    null;

end;

select *
from auditer;

insert into commande
values (108, 14, '11/10/2016', 2, to_date('11/10/2016 22:45', 'dd/mm/yyyy hh24:mi'), 'Carte', null);

insert into contient
values (108, 1, 1);

begin
  majMontant();
end;

-- 7b
/*
select *
from contient;
select *
from commande;
*/
create or replace
  TRIGGER verifQte
  before insert
  on contient
  for each row

declare

  nbP   number(2);
  maxNb number(2);

begin

  select max(quantite) into maxNb
  from contient
  where :new.numcom = contient.numcom;

  if (:new.quantite > maxNb) then
    maxNb := :new.quantite;
  end if;

  select nbpers into nbP
  from commande
  where :new.numcom = commande.numcom;


  if (maxNb > nbP) then
    RAISE_APPLICATION_ERROR(-20501, 'Insertion impossible, trop de plats/nb client');
  end if;

end;


insert into contient
values (104, 1, 2);





