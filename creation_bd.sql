DROP DATABASE IF EXISTS voyage;

CREATE DATABASE voyage;
USE voyage;

CREATE TABLE Destination (
	id INT PRIMARY KEY AUTO_INCREMENT,
    ville VARCHAR(255),
    region VARCHAR(255),
    pays VARCHAR(255),
    CONSTRAINT UNIQUE(ville, region, pays));
    
CREATE TABLE MoyenTransport (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) UNIQUE,
    vitesse INT);
    
CREATE TABLE Itineraire(
	id INT PRIMARY KEY AUTO_INCREMENT,
    depart INT,
    FOREIGN KEY (depart) REFERENCES Destination (id)
);
    
CREATE TABLE Deplacement(
	id INT PRIMARY KEY AUTO_INCREMENT,
    destination INT,
    moyenTransport INT,
    ordre INT,
    itineraire INT,
    FOREIGN KEY (destination) REFERENCES Destination(id),
    FOREIGN KEY (moyenTransport) REFERENCES MoyenTransport(id),
    FOREIGN KEY (itineraire) REFERENCES Itineraire(id),
    CONSTRAINT UNIQUE(itineraire, ordre));
    


/**
 * FIXTURES
 */
 
INSERT INTO Destination (ville, region, pays) VALUES 
	("Victoriaville", "Quebec", "Canada"),
    ("Drummondville", "Quebec", "Canada"),
    ("Montreal", "Quebec", "Canada"),
    ("Toronto", "Ontario", "Canada"),
    ("New York", "New York", "United States"),
    ("San Francisco", "Californie", "United States"),
    ("Paris", "Ile-de-France", "France");
    
INSERT INTO MoyenTransport(nom, vitesse)  VALUES
	("Autobus", 80),
    ("Avion", 800),
    ("Tracteur a gazon", 12),
    ("Train", 45);
    