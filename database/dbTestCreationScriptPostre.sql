CREATE SCHEMA IF NOT EXISTS java2_test;

COMMENT ON SCHEMA java2_test
    IS 'test environment';

SET search_path TO java2_test;

-- -----------------------------------------------------
-- Table Java2_test.users
-- -----------------------------------------------------
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS USERS(
   UserID  SERIAL PRIMARY KEY,
   FirstName           TEXT      NOT NULL,
   LastName            TEXT       NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);

-- -----------------------------------------------------
-- Table Java2_test.accounts
-- -----------------------------------------------------
DROP TABLE IF EXISTS accounts;
CREATE TYPE STATUS AS ENUM ('ADMIN', 'VISITOR');

CREATE TABLE IF NOT EXISTS accounts(
   AccountID  SERIAL PRIMARY KEY,
   FirstName      CHAR(50)      NOT NULL,
   LastName       CHAR(50)       NOT NULL,
   Status         STATUS 		NOT NULL
);

-- -----------------------------------------------------
-- Table Java2_test.resources
-- -----------------------------------------------------
DROP TABLE IF EXISTS resources;
CREATE TYPE RESOURCETYPE AS ENUM ('BOOK', 'ARTICLE', 'MAGAZINE', 'NEWSPAPER', 'JOURNAL');

CREATE TABLE IF NOT EXISTS resources(
  ResourceID SERIAL PRIMARY KEY,
  ResourceType RESOURCETYPE NOT NULL,
  Title CHAR(50) NOT NULL,
  Author CHAR(50) NOT NULL,
  ReleaseYear INT NOT NULL
);


-- -----------------------------------------------------
-- Table Java2_test.reservations
-- -----------------------------------------------------

DROP TABLE IF EXISTS reservations;
CREATE TYPE RESOURCESTATUS AS ENUM ('OPEN', 'CLOSED');


CREATE TABLE IF NOT EXISTS reservations (
    ReservationID SERIAL PRIMARY KEY,
    DateFrom DATE NOT NULL,
    DateTo DATE NOT NULL,
    AccountID INT NOT NULL,
    ResourceID INT NOT NULL,
    Status RESOURCESTATUS NOT NULL,
    FOREIGN KEY (ResourceID) REFERENCES resources(ResourceID)
       ON DELETE CASCADE
       ON UPDATE CASCADE,
    FOREIGN KEY (AccountID) REFERENCES accounts(AccountID)
       ON DELETE CASCADE
       ON UPDATE CASCADE
);

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Yekaterina', 'Savelyeva', 'ADMIN');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Viktor', 'Barbashin', 'ADMIN');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Anna', 'Kalinina', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Oleg', 'Lapin', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Janis', 'Ozolins', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Andis', 'Liepa', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Ruta', 'Kalnina', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Julija', 'Starodubova', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Jekaterina', 'Galinina', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Anton', 'Grib', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Jevgenij', 'Zajcev', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Aleksej', 'Fedotov', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Jelizaveta', 'Saveljeva', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Laura', 'Jansone', 'VISITOR');

INSERT INTO accounts (AccountID, FirstName, LastName, Status)
VALUES (default, 'Arturs', 'Kurms', 'VISITOR');

INSERT INTO resources (ResourceID, ResourceType, Title, Author, ReleaseYear)
VALUES (default, 'BOOK', 'Harry Potter', 'Joan Rowling', '1996');

INSERT INTO resources (ResourceID, ResourceType, Title, Author, ReleaseYear)
VALUES (default, 'BOOK', 'Anna Karenina', 'Lev Tolstoy', 1878);

INSERT INTO reservations (ReservationID, DateFrom, DateTo, AccountID, ResourceID, Status)
VALUES (default, '2018-08-10', '2018-08-24', 1, 1, 'OPEN');