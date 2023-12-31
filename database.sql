CREATE DATABASE IF NOT EXISTS database_JEE;

USE database_JEE;

DROP TABLE IF EXISTS ProductOrder;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS bankAccount;


CREATE TABLE IF NOT EXISTS bankAccount(
                                          bankId INT AUTO_INCREMENT PRIMARY KEY,
                                          bankCode BIGINT,
                                          bankDate VARCHAR(5),
                                          cvv INT,
                                          bankBalance INT
);
INSERT INTO bankAccount VALUES(1,1234,"04/25",123,1200);

CREATE TABLE IF NOT EXISTS Company(
                                      companyId INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100)
);

INSERT INTO Company (name)
VALUES ('Kove');
INSERT INTO Company (name)
VALUES ('Marquise');
INSERT INTO Company (name)
VALUES ('SonicYou');


CREATE TABLE IF NOT EXISTS users (
                                     userId INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(50),
                                     surname VARCHAR(50),
                                     username VARCHAR(50),
                                     email VARCHAR(100),
                                     birthdate DATE,
                                     gender VARCHAR(10),
                                     password VARCHAR(100),
                                     isAdmin BIT,
                                     isModerator BIT,
                                     isSeller BIT,
                                     loyaltyPoints INT,
                                     companyId INT,
                                     FOREIGN KEY (companyId) REFERENCES Company(companyId),
                                     bankId INT,
                                     FOREIGN KEY (bankId) REFERENCES bankAccount(bankId)
);
UPDATE Users SET companyId=null WHERE userId=5;
DELETE FROM Users WHERE userId = 4;
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator, isSeller,loyaltyPoints,companyId,bankId)
VALUES ('Admin', 'Admin', 'AdminUser', 'admin@example.com', '1980-05-15', 'Male', '$2a$10$ZqiOG5yYC.NT4wTzctHjv.66AUBVkDjR/hPMkP4H2FiN01Pyk9ePO', 1, 1, 1, 0, 1, 1);
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator, isSeller,loyaltyPoints)
VALUES ('Moderator', 'Moderator', 'ModUser', 'mod@example.com', '1980-05-15', 'Male', '$2a$10$rxMCN/KHgEH2awQ5..87H.u8NcFkqBYMrCU.IkIri2KwAcR0kN2Vm', 0, 1, 0,0);
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator, isSeller,loyaltyPoints)
VALUES ('User', 'User', 'User', 'user@example.com', '1980-05-15', 'Male', '$2a$10$zh.cQrTCZcTgt4jWtcV3UO9eqbaq1eH8/K7HHiwJyxitLXUxYIfkq', 0, 0, 0,0);

CREATE TABLE IF NOT EXISTS Product(
                                      productId INT AUTO_INCREMENT PRIMARY KEY,
                                      label VARCHAR(100),
                                      price INT,
                                      stock INT,
                                      description VARCHAR(1000),
                                      productImage VARCHAR(10000),
                                      companyId INT,
                                      FOREIGN KEY (companyId) REFERENCES Company(companyId)

);
INSERT INTO Product (label, price, stock, description,productImage, companyId) VALUES ("Sac à dos noir Kove",140,50," grand compartiment central avec fermeture éclair. Plaque logo IZAC en métal argenté sur le devant. Doublure en toile noire. Finition fermeture en argent brillant.",'https://i.postimg.cc/GpP5D0XT/image.png',1);
INSERT INTO Product (label, price, stock, description,productImage, companyId) VALUES ("Lit 2 place Marquise",400,10,"160x200 cm en tissu gris",'https://i.postimg.cc/SNsRtk8q/image.png',2);
INSERT INTO Product (label, price, stock, description,productImage, companyId) VALUES ("Brosse à dent SonicYou",50,1000,"Brosse à dents électrique sonique. Batterie fonctionnant jusqu'à 300 jours avec une seule charge",'https://i.postimg.cc/XqDCDJYG/image.png',3);


CREATE TABLE IF NOT EXISTS ProductOrder(
                                           orderId INT AUTO_INCREMENT PRIMARY KEY,
                                           orderDate DATE,
                                           orderPrice INT,
                                           userId INT,
                                           FOREIGN KEY (userId) REFERENCES users(userId)
);

CREATE TABLE IF NOT EXISTS Cart (
                                    cartId INT AUTO_INCREMENT PRIMARY KEY,
                                    quantity INT,
                                    userId INT,
                                    productId INT,
                                    FOREIGN KEY (userId) REFERENCES users(userId),
                                    FOREIGN KEY (productId) REFERENCES Product(productId)
);

