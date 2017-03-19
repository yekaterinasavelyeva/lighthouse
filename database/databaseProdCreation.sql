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

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Yekaterina', 'Savelyeva', 'admin');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Viktor', 'Barbashin', 'admin');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Anna', 'Kalinina', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Oleg', 'Lapin', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Janis', 'Ozolins', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Andis', 'Liepa', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Ruta', 'Kalnina', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Julija', 'Starodubova', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Jekaterina', 'Galinina', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Anton', 'Grib', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Jevgenij', 'Zajcev', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Aleksej', 'Fedotov', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Jelizaveta', 'Saveljeva', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Laura', 'Jansone', 'visitor');

mysql> INSERT INTO `users` (UserID, FirstName, LastName, AccountType)
    -> VALUES (default, 'Arturs', 'Kurms', 'visitor');

