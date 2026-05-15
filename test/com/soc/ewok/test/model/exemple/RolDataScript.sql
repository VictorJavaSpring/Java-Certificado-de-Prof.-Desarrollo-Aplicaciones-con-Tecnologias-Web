DROP TABLE IF EXISTS `RolData`;

CREATE TABLE IF NOT EXISTS `RolData` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NOT NULL,
  `DataCreacio` DATETIME NOT NULL,
  PRIMARY KEY (`Id`));

INSERT INTO RolData (Id, Nom, DataCreacio) VALUES (1, 'Administrador', '2015-04-01T21:00:00');
INSERT INTO RolData (Id, Nom, DataCreacio) VALUES (2, 'Cuiner', '2010-05-23T12:13:14');
INSERT INTO RolData (Id, Nom, DataCreacio) VALUES (3, 'Caixer', '2016-01-01T00:00:00');
