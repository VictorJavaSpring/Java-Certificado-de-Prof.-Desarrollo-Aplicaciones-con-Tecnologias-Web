INSERT INTO Rol (Id, Nom, Codi) VALUES (1, 'Ensinistrador','C1Ensi');
INSERT INTO Rol (Id, Nom, Codi) VALUES (2, 'Motard','C2Mot');


INSERT INTO RolUsuari (eMail,idRol) VALUES ('user4@user.com',1);
INSERT INTO RolUsuari (eMail,idRol) VALUES ('user4@user.com',2);


INSERT INTO Usuari (eMail, Password, Actiu) VALUES ('user5@user.com', 'user5', true);

INSERT INTO RolUsuari (eMail,idRol) VALUES ('user5@user.com',1);
INSERT INTO RolUsuari (eMail,idRol) VALUES ('user5@user.com',2);
