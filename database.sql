CREATE DATABASE IF NOT EXISTS database_JEE;

USE database_JEE;
DROP TABLE IF EXISTS ProductOrder;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS ProductCategory;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS Company;

CREATE TABLE IF NOT EXISTS Company(
                                      companyId INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100)
);

INSERT INTO Company (name)
VALUES ('Cramptoux');

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
                                     companyId INT,
                                     FOREIGN KEY (companyId) REFERENCES Company(companyId)
);
UPDATE Users SET companyId=null WHERE userId=5;
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator, isSeller)
VALUES ('Admin', 'Admin', 'AdminUser', 'admin@example.com', '1980-05-15', 'Male', 'admin', 1, 1, 0);
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator, isSeller)
VALUES ('Moderator', 'Moderator', 'ModUser', 'mod@example.com', '1980-05-15', 'Male', 'moderator', 0, 1, 0);
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator, isSeller)
VALUES ('User', 'User', 'User', 'user@example.com', '1980-05-15', 'Male', 'user', 0, 0, 0);

CREATE TABLE IF NOT EXISTS ProductCategory(
									categoryId INT AUTO_INCREMENT PRIMARY KEY,
                                    categoryName VARCHAR(100)
);
INSERT INTO ProductCategory VALUES(1,"Guitares");
INSERT INTO ProductCategory VALUES(2,"Basses");
INSERT INTO ProductCategory VALUES(3,"Piano");
INSERT INTO ProductCategory VALUES(4,"Batterie");

CREATE TABLE IF NOT EXISTS Product(
                                      productId INT AUTO_INCREMENT PRIMARY KEY,
                                      label VARCHAR(100),
                                      price INT,
                                      stock INT,
                                      description VARCHAR(100),
                                      companyId INT,
                                      FOREIGN KEY (companyId) REFERENCES Company(companyId),
                                      categoryId INT,
                                      FOREIGN KEY (categoryId) REFERENCES ProductCategory(categoryId)
                                      
);
INSERT INTO Product VALUES (1,"Ibanez",400,5,"Super guitare Ibanez",1,1);
INSERT INTO Product VALUES (2,"Yamaha",500,7,"Incroyable guitare de pro",1,1);
INSERT INTO Product VALUES (3,"Fender",250,4,"Super basse Fender",1,2);
INSERT INTO Product VALUES (4,"Bechstein",400,5,"Super piano",1,3);
INSERT INTO Product VALUES (5,"Gretsch",1200,2,"Super batterie",1,4);

CREATE TABLE IF NOT EXISTS ProductOrder(
                                           orderId INT AUTO_INCREMENT PRIMARY KEY,
                                           orderDate DATE,
                                           orderPrice INT,
                                           userId INT,
                                           FOREIGN KEY (userId) REFERENCES users(userId)
);