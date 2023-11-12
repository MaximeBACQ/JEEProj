CREATE DATABASE IF NOT EXISTS database_JEE;

USE database_JEE;
DROP TABLE USERS;
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
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator)
VALUES ('Admin', 'Admin', 'AdminUser', 'admin@example.com', '1980-05-15', 'Male', 'admin', 1, 1);
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator)
VALUES ('Moderator', 'Moderator', 'ModUser', 'mod@example.com', '1980-05-15', 'Male', 'moderator', 0, 1);
INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator)
VALUES ('User', 'User', 'User', 'user@example.com', '1980-05-15', 'Male', 'user', 0, 0);

DROP TABLE Company;
CREATE TABLE IF NOT EXISTS Company(
                                      companyId INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100)
);

INSERT INTO Company (name)
VALUES ('Cramptoux');

DROP TABLE Product;
CREATE TABLE IF NOT EXISTS Product(
                                      productId INT AUTO_INCREMENT PRIMARY KEY,
                                      label VARCHAR(100),
                                      price INT,
                                      stock INT,
                                      description VARCHAR(100),
                                      companyId INT,
                                      FOREIGN KEY (companyId) REFERENCES Company(companyId)
);

DROP TABLE ProductOrder;
CREATE TABLE IF NOT EXISTS ProductOrder(
                                           orderId INT AUTO_INCREMENT PRIMARY KEY,
                                           orderDate DATE,
                                           orderPrice INT,
                                           userId INT,
                                           FOREIGN KEY (userId) REFERENCES users(userId)
);