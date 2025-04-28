CREATE DATABASE IF NOT EXISTS mana_db;

USE mana_db;

CREATE TABLE IF NOT EXISTS auth_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(75) UNIQUE,
    password VARCHAR(255),
    phone TEXT,
    address TEXT,
    role ENUM('USER', 'WORKER', 'ADMIN') NOT NULL
);
