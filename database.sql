CREATE DATABASE IF NOT EXISTS database1;

USE database1;

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
