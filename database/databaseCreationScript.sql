SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2_test` DEFAULT CHARACTER SET utf8 ;
USE `java2_test` ;

-- -----------------------------------------------------
-- Table `Java2_test`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users`;

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  PRIMARY KEY (`UserID`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

-- -----------------------------------------------------
-- Table `Java2_test`.`accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accounts`;

CREATE TABLE IF NOT EXISTS `accounts` (
  `AccountID` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  `Status` ENUM('ADMIN','VISITOR') NOT NULL,
  PRIMARY KEY (`AccountID`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

-- -----------------------------------------------------
-- Table `Java2_test`.`resources`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resources`;

CREATE TABLE IF NOT EXISTS `resources` (
  `ResourceID` INT(11) NOT NULL AUTO_INCREMENT,
  `ResourceType` ENUM('BOOK', 'ARTICLE', 'MAGAZINE', 'NEWSPAPER', 'JOURNAL') NOT NULL,
  `Title` CHAR(32) NOT NULL,
  `Author` CHAR(32) NOT NULL,
  `ReleaseYear` INT(7) NOT NULL,
  PRIMARY KEY (`ResourceID`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;