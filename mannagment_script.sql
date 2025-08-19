
CREATE DATABASE IF NOT EXISTS mana_db;


USE mana_db;

CREATE TABLE IF NOT EXISTS auth_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(75) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- for storing hashed passwords
    phone VARCHAR(20),
    address TEXT,
    role ENUM('USER', 'WORKER', 'ADMIN') NOT NULL DEFAULT 'USER'
);

CREATE TABLE IF NOT exists hospital_data(
id INT auto_increment PRIMARY KEY,
hospital_name VARCHAR(50),
address VARCHAR(50),
latitude DECIMAL,
longitude DECIMAL,
contact_number INT,
create_at TIMESTAMP,
updated_at TIMESTAMP
);

SELECT * FROM hospital_data;

CREATE TABLE IF NOT exists ambulance_data(
id int auto_increment PRIMARY KEY,
hospital_id INT,
number_plate VARCHAR(50),
status ENUM('available' , 'busy' , 'service' ) NOT NULL DEFAULT 'available',
driver_name VARCHAR(50),
driver_contact INT,
latitude INT,
longitude INT,
last_loction_updated_time TIMESTAMP,

FOREIGN KEY(hospital_id) references hospital_data(id)
);

SELECT * FROM ambulance_data;

SELECT * FROM auth_table;

