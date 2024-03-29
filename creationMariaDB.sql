create table typePlat(numTypePlat int primary key, nomTypePlat varchar(50));
create table commande(numcom numeric(4),numtab numeric(4),datcom date,nbpers numeric(2),datpaie datetime,modpaie varchar(15),montcom numeric(8,2),primary key (numcom));
create table plat(numplat numeric(4)primary key,libelle varchar(40),type varchar(15),prixunit numeric(6,2));
create table serveur(numserv numeric(2) primary key, nomserv varchar(25), grade varchar(20));
create table tabl(numtab numeric(4) primary key,nbplace numeric(2));
create table contient(numcom numeric(4),numplat numeric (4),quantite numeric(2),primary key(numcom,numplat));
create table affecter(numtab numeric(4),dataff date,numserv numeric(2),primary key(numtab,dataff));
create table auditer(numcom numeric(4),numtab numeric(4),datcom date,nbpers numeric(2),datpaie date,montcom numeric(8,2),primary key (numcom));


insert into typePlat values (1, 'Entree'), (2, 'Plat'), (3, 'Dessert');
insert into serveur values(1,'Tutus Peter','maitre d''hotel');
insert into serveur values(2,'Lilo Vito','serveur g1');
insert into serveur values(3,'Don Carl','serveur g2');
insert into serveur values(4,'Leo Jon','serveur g1');
insert into serveur values(5,'Dean Geak','chef serveur');

insert into plat values(1,'assiette de crudites','Entree',25);
insert into plat values(2,'tarte de saison','Dessert',25);
insert into plat values(3,'sorbet mirabelle','Dessert',35);
insert into plat values(4,'filet de boeuf','Viande',62);
insert into plat values(5,'salade verte','Entree',15);
insert into plat values(6,'chevre chaud','Entree',21);
insert into plat values(7,'pate lorrain','Entree',25);
insert into plat values(8,'saumon fume','Entree',30);
insert into plat values(9,'entrecote printaniere','Viande',58);
insert into plat values(10,'gratin dauphinois','Plat',42);
insert into plat values(11,'brochet a l''oseille','Poisson',68);
insert into plat values(12,'gigot d''agneau','Viande',56);
insert into plat values(13,'creme caramel','Dessert',15);
insert into plat values(14,'munster au cumin','Fromage',18);
insert into plat values(15,'filet de sole au beurre','Poisson',70);
insert into plat values(16,'fois gras de lorraine','Entree',61);

insert into tabl values(10,4);
insert into tabl values(11,6);
insert into tabl values(12,8);
insert into tabl values(13,4);
insert into tabl values(14,6);
insert into tabl values(15,4);
insert into tabl values(16,4);
insert into tabl values(17,6);
insert into tabl values(18,2);
insert into tabl values(19,4);

insert into commande values(100, 10, '2016-09-10', 2, '2016-09-10 20:50', 'Carte', null);
insert into commande values(101, 11, '2016-09-10', 4, '2016-09-10 21:20', 'Cheque', null);
insert into commande values(102, 17, '2016-09-10', 2, '2016-09-10 20:55', 'Carte', null);
insert into commande values(103, 12, '2016-09-10', 2, '2016-09-10 21:10', 'Especes', null);
insert into commande values(104, 18, '2016-09-10', 1, '2016-09-10 21:00', 'Cheque', null);
insert into commande values(105, 10, '2016-09-10', 2, '2016-09-10 20:45', 'Carte', null);
insert into commande values(106, 14, '2016-10-11', 2, '2016-10-11 22:45', 'Carte', null);

insert into affecter values(10,'2016-09-10',1);
insert into affecter values(11,'2016-09-10',1);
insert into affecter values(12,'2016-09-10',1);
insert into affecter values(17,'2016-09-10',2);
insert into affecter values(18,'2016-09-10',2);
insert into affecter values(15,'2016-09-10',3);
insert into affecter values(16,'2016-09-10',3);
insert into affecter values(10,'2016-09-11',1);

insert into contient values(100,4,2);
insert into contient values(100,5,2);
insert into contient values(100,13,1);
insert into contient values(100,3,1);
insert into contient values(101,7,2);
insert into contient values(101,16,2);
insert into contient values(101,12,2);
insert into contient values(101,15,2);
insert into contient values(101,2,2);
insert into contient values(101,3,2);
insert into contient values(102,1,2);
insert into contient values(102,10,2);
insert into contient values(102,14,2);
insert into contient values(102,2,1);
insert into contient values(102,3,1);
insert into contient values(103,9,2);
insert into contient values(103,14,2);
insert into contient values(103,2,1);
insert into contient values(103,3,1);
insert into contient values(104,7,1);
insert into contient values(104,11,1);
insert into contient values(104,14,1);
insert into contient values(104,3,1);
insert into contient values(105,3,2);
insert into contient values(106,3,2);


alter table commande add ( foreign key(numtab)references tabl(numtab));
alter table affecter add ( foreign key(numserv)references serveur(numserv));
alter table contient add ( foreign key(numcom)references commande(numcom));
alter table contient add ( foreign key(numplat)references plat(numplat));

DELIMITER $$
CREATE OR REPLACE PROCEDURE majMontant(p_numcom INT)
BEGIN
  DECLARE numComCourant INT;
  DECLARE montCourant DECIMAL;
  DECLARE sommeMont DECIMAL;
  DECLARE numC CURSOR FOR SELECT contient.numcom
                          FROM contient
                          INNER JOIN plat ON contient.numplat = plat.numplat
                          INNER JOIN commande ON contient.numcom=commande.numcom
                          WHERE commande.numcom= p_numcom;

  DECLARE montC CURSOR FOR SELECT plat.prixunit * contient.quantite
                          FROM contient
                          INNER JOIN plat ON contient.numplat = plat.numplat
                          INNER JOIN commande ON contient.numcom=commande.numcom
                          WHERE commande.numcom= p_numcom;

  OPEN numC;
  OPEN montC;

  SET sommeMont = 0;

  somme_loop : LOOP
	FETCH montC INTO montCourant;
	SET sommeMont = sommeMont + montCourant;

    FETCH numC INTO numComCourant;
    UPDATE commande SET montcom = sommeMont WHERE commande.numcom = numComCourant;
    insert into test values('test');
  END LOOP;

  CLOSE numC;
  CLOSE montC;
END $$
DELIMITER ;

create or replace
  TRIGGER enregistrementAuditer
  after update
  on commande
  for each row
  begin

declare  nbPersCour INT;
declare  montantCom INT;
declare  gServ      varchar(20);
declare  nb         INT;


  montantCom := :new.montcom;

  nbPersCour := :new.nbpers;

  select grade into gServ
  from serveur
         inner join affecter on serveur.numserv = affecter.numserv
  where date_format(:new.datpaie, '%Y-%m-%d') = date_format(affecter.dataff, '%Y-%m-%d')
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