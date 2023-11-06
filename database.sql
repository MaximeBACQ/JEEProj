CREATE DATABASE IF NOT EXISTS database_JEE;

USE database_JEE;
DROP TABLE users;
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
    isModerator BIT
);

INSERT INTO users (name, surname, username, email, birthdate, gender, password, isAdmin, isModerator)
VALUES ('Admin', 'Admin', 'AdminUser', 'admin@example.com', '1980-05-15', 'Male', 'admin', 1, 1);

CREATE TABLE IF NOT EXISTS Company(
	companyId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    FOREIGN KEY (userId) REFERENCES users(userId)
);

CREATE TABLE IF NOT EXISTS Product(
	productId INT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(100),
    price INT,
	stock INT,
    description VARCHAR(100),
	FOREIGN KEY (companyId) REFERENCES Company(companyId)
);

CREATE TABLE IF NOT EXISTS ProductOrder(
	orderId INT AUTO_INCREMENT PRIMARY KEY,
	orderDate DATE,
	orderPrice INT,
    FOREIGN KEY (userId) REFERENCES users(userId)
);