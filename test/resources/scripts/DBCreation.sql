DROP TABLE IF EXISTS `Rol`;

CREATE TABLE IF NOT EXISTS `Rol` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NOT NULL,
  `Codi` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`Id`));
  
-- -----------------------------------------------------
-- Table `Producte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Producte`;

CREATE TABLE IF NOT EXISTS `Producte` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(50) NOT NULL,
  `DescripcioCurta` VARCHAR(100) NULL,
  `Descripcio` TEXT NULL,
  `IniciVigencia` DATETIME NULL,
  `FiVigencia` DATETIME NULL,
  `IdUnitat` INT UNSIGNED NOT NULL,
  `IdTipusProducte` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Id`));
  
  -- -----------------------------------------------------
-- Table `Unitat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Unitat` ;

CREATE TABLE IF NOT EXISTS `Unitat` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(50) NOT NULL,
  `Acronim` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`Id`));

  
  -- -----------------------------------------------------
-- Table `TipusProducte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TipusProducte` ;

CREATE TABLE IF NOT EXISTS `TipusProducte` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NOT NULL,
  `Descripcio` VARCHAR(45) NULL,
  `Codi` VARCHAR(10) NULL COMMENT 'Camp destinat a codificar subcategories de productes. Obert a la codificaci� que es necessiti en cada cas.\n\nIndicarem:\n* Si el plat es cuinat o no per enviar-lo a la cuina',
  PRIMARY KEY (`Id`));

  
  -- -----------------------------------------------------
-- Table `OfertaComanda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OfertaComanda` ;

CREATE TABLE IF NOT EXISTS `OfertaComanda` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `LimitInferior` DECIMAL(6,2) NOT NULL,
  `PctDescompte` DECIMAL(5,2) UNSIGNED NOT NULL,
  `IniciVigencia` DATETIME NOT NULL,
  `FiVigencia` DATETIME NOT NULL,
  PRIMARY KEY (`Id`));
  
  -- -----------------------------------------------------
-- Table `Comanda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Comanda`;
CREATE TABLE IF NOT EXISTS `Comanda` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `IdClient` INT UNSIGNED NULL,
  `PuntsGenerats` TINYINT UNSIGNED NOT NULL,
  `PctDescompte` DECIMAL(5,2) UNSIGNED NULL,
  `Data` DATETIME NOT NULL,
  `AdrecaEntrega` INT UNSIGNED NULL,
  `Comentaris` TEXT NULL,
  `Estat` CHAR(1) NOT NULL,
  `PreuFinal` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`Id`));
-- -----------------------------------------------------

   -- --------------------------------------------------------
  -- Table `eWok` . `LiniaComanda`
  -- --------------------------------------------------------
  
  DROP TABLE IF EXISTS `LiniaComanda`;
CREATE TABLE IF NOT EXISTS `LiniaComanda` (
  `IdComanda` INT UNSIGNED NOT NULL,
  `NumLinia` SMALLINT UNSIGNED NOT NULL,
  `IdProducte` TINYINT UNSIGNED NOT NULL,
  `Quantitat` SMALLINT NOT NULL,
  `PreuBrut` DECIMAL(6,2) NOT NULL,
  `PreuVenda` DECIMAL(6,2) NOT NULL,
  `Estat` CHAR(1) NOT NULL,
   PRIMARY KEY (`IdComanda`,`NumLinia`));
   
-- -------------------------------------------------------------
  
    -- -----------------------------------------------------
  -- Table `Client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Client`;

CREATE TABLE IF NOT EXISTS `Client` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NOT NULL,
  `Cognoms` VARCHAR(45) NULL,
  `Telefon` VARCHAR(15) NULL,
  `eMail`  VARCHAR(45) NULL,
  `DataAlta` DATETIME NULL,
  `DNI`  VARCHAR(20) NULL,
  `IdUsuari`  VARCHAR(50) NULL,
  PRIMARY KEY (`Id`));
  
  -- -----------------------------------------------------
-- Table `Xec`
-- -----------------------------------------------------
  DROP TABLE IF EXISTS `Xec`;

CREATE TABLE IF NOT EXISTS `Xec` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `NumPunts` TINYINT NOT NULL,
  `DataCaducitat` DATE NOT NULL,
  `IdComanda` INT NOT NULL,
  `IdClient` INT NOT NULL,
  PRIMARY KEY (`Id`));
  
    -- -----------------------------------------------------
-- Table `PuntsPendents`
-- -----------------------------------------------------

  DROP TABLE IF EXISTS `PuntsPendents`;

CREATE TABLE IF NOT EXISTS `PuntsPendents` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `NumPunts` TINYINT NOT NULL,
  `DataCaducitat` DATE NOT NULL,
  `IdClient` INT NOT NULL,
  PRIMARY KEY (`Id`));
  
  
  -- -----------------------------------------------------
-- Table `eWok`.`FormaPagament`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FormaPagament` ;

CREATE TABLE IF NOT EXISTS `FormaPagament` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id`));



-- -----------------------------------------------------
-- Table `eWok`.`Pagament`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Pagament` ;

CREATE TABLE IF NOT EXISTS `Pagament` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `IdComanda` INT UNSIGNED NOT NULL,
  `Quantitat` DECIMAL(6,2) NOT NULL,
  `IdFormaPagament` INT UNSIGNED NOT NULL,
  `Data` DATETIME NOT NULL,
  PRIMARY KEY (`Id`));


  -- -----------------------------------------------------
-- Table `Adreca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Adreca`;

CREATE TABLE IF NOT EXISTS `Adreca` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Linia1` VARCHAR(100) NOT NULL,
  `Linia2` VARCHAR(100) NULL,
  `CP` VARCHAR(5) NOT NULL,
  `Ciutat` VARCHAR(50) NOT NULL,
  `Notes` TEXT NULL,
  `Telefon` VARCHAR(15) NULL,
  `IdClient` INT UNSIGNED NOT NULL,
  `Alias` VARCHAR(45),
  PRIMARY KEY (`Id`));
  -- -----------------------------------------------------

  -- -----------------------------------------------------
-- Table `eWok`.`Components`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Components` ;

CREATE TABLE IF NOT EXISTS `Components` (
  `IdProducte` INT UNSIGNED NOT NULL COMMENT 'Id del producte compost',
  `IdComponent` INT UNSIGNED NOT NULL COMMENT 'Id del component',
  `Quantitat` SMALLINT UNSIGNED NOT NULL,
  `Ordre` TINYINT UNSIGNED NOT NULL COMMENT 'Ordre de presentacio a la web del component dins el producte (per poder posar els components principals al principi)',
  PRIMARY KEY (`IdProducte`, `IdComponent`));
  
  -- -----------------------------------------------------
-- Table `eWok`.`Preu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Preu` ;

CREATE TABLE IF NOT EXISTS `Preu` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Preu` DECIMAL(6,2) NOT NULL,
  `IniciVigencia` DATETIME NULL,
  `FinalVigencia` DATETIME NULL,
  `IdProducte` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Id`));
-------------------------------------------------------
-- Table `Usuari`
-------------------------------------------------------
DROP TABLE IF EXISTS `Usuari`;

CREATE TABLE IF NOT EXISTS `Usuari` (
  `eMail` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(50) NOT NULL,
  `Actiu` BOOL,
  PRIMARY KEY (`eMail`));
 
-------------------------------------------------------
-- Table `ComentariClient`
-------------------------------------------------------
DROP TABLE IF EXISTS `ComentariClient`;

CREATE TABLE IF NOT EXISTS `ComentariClient` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `IdClient` INT UNSIGNED NOT NULL,
  `Comentari` TEXT NOT NULL,
  `Data` DATETIME NOT NULL,
  `IdAutor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id`));
  
  -- -----------------------------------------------------
-- Table `eWok`.`RolUsuari`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RolUsuari` ;

CREATE TABLE IF NOT EXISTS `RolUsuari` (
  `eMail` VARCHAR(50) NOT NULL,
  `idRol` INT NOT NULL,
  PRIMARY KEY (`eMail`, `idRol`));
  
 -- -----------------------------------------------------
-- Table `OfertaProducte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OfertaProducte` ;

CREATE TABLE IF NOT EXISTS `OfertaProducte` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `PctDescompte` DECIMAL(5,2) NOT NULL,
  `Nom` VARCHAR(50) NULL,
  `IniciVigencia` DATETIME NULL,
  `FiVigencia` DATETIME NULL,
  `Descripcio` TEXT NULL,
  `IdProducte` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Id`));
  
 -- -----------------------------------------------------
-- Table `OfertaPunts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OfertaPunts` ;

CREATE TABLE IF NOT EXISTS `OfertaPunts` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `EurosPerPunt` DECIMAL(6,2) UNSIGNED NOT NULL,
  `PuntsPerXec` DECIMAL(6,2) UNSIGNED NOT NULL,
  `DiesVigenciaPunts` INT UNSIGNED NOT NULL,
  `DiesVigenciaXecs` INT UNSIGNED NOT NULL,
  `IniciVigencia` DATETIME NOT NULL,
  `FiVigencia` DATETIME NOT NULL,
  PRIMARY KEY (`Id`));