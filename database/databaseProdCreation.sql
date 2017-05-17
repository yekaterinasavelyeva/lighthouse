SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `prod` DEFAULT CHARACTER SET utf8 ;
USE `prod` ;

-- -----------------------------------------------------
-- Table `prod`.`users`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `users`(
    `UserID` INT(11) NOT NULL AUTO_INCREMENT,
    `FirstName` CHAR(32) NOT NULL,
    `LastName` CHAR(32) NOT NULL,
    `AccountType` CHAR(32) NOT NULL,
     PRIMARY KEY (`UserID`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

--ALTER TABLE `users` AUTO_INCREMENT = 1;

mysql> INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
    -> VALUES (default, 'Yekaterina', 'Savelyeva', 'ADMIN');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Viktor', 'Barbashin', 'ADMIN');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Anna', 'Kalinina', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Oleg', 'Lapin', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Janis', 'Ozolins', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Andis', 'Liepa', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Ruta', 'Kalnina', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Julija', 'Starodubova', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Jekaterina', 'Galinina', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Anton', 'Grib', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Jevgenij', 'Zajcev', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Aleksej', 'Fedotov', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Jelizaveta', 'Saveljeva', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Laura', 'Jansone', 'VISITOR');

INSERT INTO `accounts` (AccountID, FirstName, LastName, Status)
VALUES (default, 'Arturs', 'Kurms', 'VISITOR');

