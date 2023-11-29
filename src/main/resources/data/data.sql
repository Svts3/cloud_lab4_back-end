drop database if exists lab6;
create database if not exists lab6;
use lab6;

DROP TABLE IF EXISTS health_info;
DROP TABLE IF EXISTS watch_location;
DROP TABLE IF EXISTS watch_battery;
DROP TABLE IF EXISTS emergency_phone_number;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS property_info;
DROP TABLE IF EXISTS watch;
DROP TABLE IF EXISTS street;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS region;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS continent;
drop table if exists continent_delete_logger;


create table continent_delete_logger
(
    id            int primary key auto_increment,
    continent     varchar(40) not null,
    deletion_time timestamp   not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE continent
(
    name VARCHAR(25) PRIMARY KEY
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_0900_AI_CI;

INSERT INTO continent
VALUES ("Europe");
INSERT INTO continent
VALUES ("North America");
INSERT INTO continent
VALUES ("South America");
INSERT INTO continent
VALUES ("Africa");
INSERT INTO continent
VALUES ("Asia");



CREATE TABLE country
(
    `name`           varchar(30) NOT NULL,
    `continent_name` varchar(25) not null,
    PRIMARY KEY (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO country (name)
VALUES ('Czech Republic');
INSERT INTO country (name)
VALUES ('Finland');
INSERT INTO country (name)
VALUES ('France');
INSERT INTO country (name)
VALUES ('Germany');
INSERT INTO country (name)
VALUES ('Greece');
INSERT INTO country (name)
VALUES ('Italy');
INSERT INTO country (name)
VALUES ('Latvia');
INSERT INTO country (name)
VALUES ('Poland');
INSERT INTO country (name)
VALUES ('Ukraine');
INSERT INTO country (name)
VALUES ('USA');

CREATE TABLE region
(
    `name`       varchar(30) NOT NULL,
    country_name varchar(30) NOT NULL,
    PRIMARY KEY (`name`),
    CONSTRAINT region_country FOREIGN KEY (country_name) REFERENCES country (`name`) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;



INSERT INTO region (name, country_name)
VALUES ('Central Finland', 'Finland');
INSERT INTO region (name, country_name)
VALUES ('Ostrobothnia', 'Finland');
INSERT INTO region (name, country_name)
VALUES ('Bavaria', 'Germany');
INSERT INTO region (name, country_name)
VALUES ('Central Greece', 'Greece');
INSERT INTO region (name, country_name)
VALUES ('Lazio', 'Italy');
INSERT INTO region (name, country_name)
VALUES ('Sicily', 'Italy');
INSERT INTO region (name, country_name)
VALUES ('Masovia', 'Poland');
INSERT INTO region (name, country_name)
VALUES ('Silesia', 'Poland');
INSERT INTO region (name, country_name)
VALUES ('Kyiv region', 'Ukraine');
INSERT INTO region (name, country_name)
VALUES ('Lviv region', 'Ukraine');



CREATE TABLE city
(
    `name`      varchar(30) NOT NULL,
    region_name varchar(30) NOT NULL,
    PRIMARY KEY (`name`),
    CONSTRAINT city_region FOREIGN KEY (region_name) REFERENCES region (`name`) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO city (name, region_name)
VALUES ('München ', 'Bavaria');
INSERT INTO city (name, region_name)
VALUES ('Länsi-Suomen lääni', 'Central Finland');
INSERT INTO city (name, region_name)
VALUES ('Laukaa', 'Central Finland');
INSERT INTO city (name, region_name)
VALUES ('Irpin', 'Kyiv region');
INSERT INTO city (name, region_name)
VALUES ('Kyiv', 'Kyiv region');
INSERT INTO city (name, region_name)
VALUES ('Lviv', 'Lviv region');
INSERT INTO city (name, region_name)
VALUES ('Warshaw', 'Masovia');
INSERT INTO city (name, region_name)
VALUES ('Palermu', 'Sicily');
INSERT INTO city (name, region_name)
VALUES ('Katowice', 'Silesia');
INSERT INTO city (name, region_name)
VALUES ('Wrocław', 'Silesia');

CREATE TABLE street
(
    `name`    varchar(30) NOT NULL,
    city_name varchar(30) NOT NULL,
    PRIMARY KEY (`name`),
    CONSTRAINT street_city FOREIGN KEY (city_name) REFERENCES city (`name`) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO street (name, city_name)
VALUES ('Warszawska Street', 'Katowice');
INSERT INTO street (name, city_name)
VALUES ('Alexander Dovzhenko Str', 'Kyiv');
INSERT INTO street (name, city_name)
VALUES ('Bogdan Khmelnitsky Street', 'Kyiv');
INSERT INTO street (name, city_name)
VALUES ('Koulutie', 'Laukaa');
INSERT INTO street (name, city_name)
VALUES ('Koshova', 'Lviv');
INSERT INTO street (name, city_name)
VALUES ('Balanstraße', 'München ');
INSERT INTO street (name, city_name)
VALUES ('Isarring', 'München ');
INSERT INTO street (name, city_name)
VALUES ('Sonnenstraße', 'München ');
INSERT INTO street (name, city_name)
VALUES ('Roku Pl.', 'Warshaw');
INSERT INTO street (name, city_name)
VALUES ('Sierpnia', 'Warshaw');



CREATE TABLE watch
(
    serial_number varchar(40) NOT NULL,
    street_name   varchar(30) NOT NULL,
    PRIMARY KEY (serial_number),
    CONSTRAINT watch_street FOREIGN KEY (street_name) REFERENCES street (`name`) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO watch (serial_number, street_name)
VALUES ('4161362634', 'Alexander Dovzhenko Str');
INSERT INTO watch (serial_number, street_name)
VALUES ('9757255899', 'Balanstraße');
INSERT INTO watch (serial_number, street_name)
VALUES ('9665882612', 'Bogdan Khmelnitsky Street');
INSERT INTO watch (serial_number, street_name)
VALUES ('7254178278', 'Isarring');
INSERT INTO watch (serial_number, street_name)
VALUES ('3604317998', 'Koshova');
INSERT INTO watch (serial_number, street_name)
VALUES ('1447113540', 'Koulutie');
INSERT INTO watch (serial_number, street_name)
VALUES ('5967279811', 'Roku Pl.');
INSERT INTO watch (serial_number, street_name)
VALUES ('5943247042', 'Sierpnia');
INSERT INTO watch (serial_number, street_name)
VALUES ('1379749449', 'Sonnenstraße');
INSERT INTO watch (serial_number, street_name)
VALUES ('8758758066', 'Warszawska Street');


CREATE TABLE `owner`
(
    id            int        NOT NULL AUTO_INCREMENT,
    first_name    varchar(45) DEFAULT NULL,
    last_name     varchar(45) DEFAULT NULL,
    date_of_birth date       NOT NULL,
    gender        varchar(7) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (11, 'Peter', 'Anderson', '2001-04-22', 'male');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (12, 'Korben', 'Shepherd', '1980-05-20', 'male');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (13, 'Jeff', 'Frye', '1990-11-05', 'male');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (14, 'Rome', 'Derrick', '1995-06-12', 'male');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (15, 'Jamie', 'Rennie', '1975-07-25', 'male');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (16, 'Geraldine', 'Welsh', '2000-07-10', 'female');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (17, 'Vickie', 'Osborne', '1997-02-14', 'female');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (18, 'Ann', 'Chandler', '2003-10-10', 'female');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (19, 'Charlotte', 'Sweeney', '1999-04-21', 'female');
INSERT INTO owner (id, first_name, last_name, date_of_birth, gender)
VALUES (20, 'Samera', 'Carter', '1998-09-15', 'female');



CREATE TABLE property_info
(
    id                  int         NOT NULL AUTO_INCREMENT,
    owner_id            int         NOT NULL,
    watch_serial_number varchar(40) NOT NULL,
    PRIMARY KEY (id),
    INDEX Table_5_owner (owner_id),
    INDEX Table_5_watch (watch_serial_number),
    CONSTRAINT Table_5_owner FOREIGN KEY (owner_id) REFERENCES `owner` (id) on update cascade on delete cascade,
    CONSTRAINT Table_5_watch FOREIGN KEY (watch_serial_number) REFERENCES watch (serial_number) on update cascade on delete cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (11, 11, '9757255899');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (12, 12, '9665882612');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (13, 13, '8758758066');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (14, 14, '7254178278');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (15, 15, '5967279811');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (16, 16, '5943247042');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (17, 17, '4161362634');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (18, 18, '3604317998');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (19, 19, '1447113540');
INSERT INTO property_info (id, owner_id, watch_serial_number)
VALUES (20, 20, '1379749449');


CREATE TABLE `user`
(
    id               int NOT NULL AUTO_INCREMENT,
    first_name       varchar(45) DEFAULT NULL,
    last_name        varchar(45) DEFAULT NULL,
    date_of_birth    date        DEFAULT NULL,
    gender           varchar(7)  DEFAULT NULL,
    property_info_id int NOT NULL,
    PRIMARY KEY (id),
    INDEX user_property_info (property_info_id),
    INDEX first_name_index (first_name),
    INDEX date_of_birth_index (date_of_birth),
    CONSTRAINT user_property_info FOREIGN KEY (property_info_id) REFERENCES property_info (id) on update cascade on delete cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (1, 'Peter', 'Anderson', '2001-04-22', 'male', 11);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (2, 'Geraldine', 'Welsh', '2000-07-10', 'female', 16);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (3, 'Maciej', 'Dale', '1975-06-21', 'male', 12);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (4, 'Ebrahim', 'Lacey', '1960-09-10', 'male', 13);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (5, 'Lucca', 'Mcintosh', '1975-12-17', 'male', 14);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (6, 'Adriana', 'Rubio', '2003-07-12', 'female', 15);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (7, 'Lottie', 'Levy', '1990-04-20', 'female', 17);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (8, 'Eden', 'Walton', '1999-01-24', 'female', 18);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (9, 'Teddie', 'Whitley', '1999-05-23', 'female', 19);
INSERT INTO user (id, first_name, last_name, date_of_birth, gender, property_info_id)
VALUES (10, 'Victor', 'Hardy', '2000-02-03', 'male', 20);


CREATE TABLE emergency_phone_number
(
    id                  int         NOT NULL AUTO_INCREMENT,
    phone_number        varchar(12) NOT NULL,
    watch_serial_number varchar(40) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX phone_number_UNIQUE (phone_number),
    CONSTRAINT emergency_phone_number_watch FOREIGN KEY (watch_serial_number) REFERENCES watch (serial_number) on update cascade on delete cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (11, '0634012412', '9757255899');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (12, '0964212532', '9757255899');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (13, '0631521533', '9757255899');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (14, '0953212453', '4161362634');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (15, '0956435434', '9665882612');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (16, '0633252532', '9665882612');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (17, '0632546346', '1447113540');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (18, '0965436434', '1447113540');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (19, '0954364362', '1379749449');
INSERT INTO emergency_phone_number (id, phone_number, watch_serial_number)
VALUES (20, '0635326434', '8758758066');



CREATE TABLE health_info
(
    id                  int         NOT NULL AUTO_INCREMENT,
    heartbeat_rate      int         NOT NULL,
    watch_serial_number varchar(40) NOT NULL,
    PRIMARY KEY (id),
    INDEX health_info_watch (watch_serial_number),
    INDEX hearbeat_rate_index (heartbeat_rate),
    CONSTRAINT health_info_watch FOREIGN KEY (watch_serial_number) REFERENCES watch (serial_number) on update cascade on delete cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (11, 67, '4161362634');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (12, 69, '4161362634');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (13, 75, '4161362634');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (14, 77, '4161362634');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (15, 74, '9757255899');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (16, 77, '9757255899');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (17, 85, '7254178278');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (18, 80, '7254178278');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (19, 77, '7254178278');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (20, 100, '1447113540');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (21, 85, '1447113540');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (22, 69, '5943247042');
INSERT INTO health_info (id, heartbeat_rate, watch_serial_number)
VALUES (23, 75, '5943247042');



CREATE TABLE watch_battery
(
    id                  int           NOT NULL AUTO_INCREMENT,
    charge_level        decimal(3, 0) NOT NULL,
    watch_serial_number varchar(40)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT watch_battery_watch FOREIGN KEY (watch_serial_number) REFERENCES watch (serial_number) on update cascade on delete cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (11, 100, '4161362634');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (12, 98, '4161362634');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (13, 95, '4161362634');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (14, 100, '9757255899');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (15, 99, '9757255899');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (16, 100, '9665882612');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (17, 98, '9665882612');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (18, 93, '9665882612');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (19, 94, '7254178278');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (20, 89, '7254178278');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (21, 99, '3604317998');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (22, 97, '3604317998');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (23, 92, '3604317998');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (24, 79, '1447113540');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (25, 76, '1447113540');
INSERT INTO watch_battery (id, charge_level, watch_serial_number)
VALUES (26, 67, '1447113540');


CREATE TABLE watch_location
(
    id                  int           NOT NULL AUTO_INCREMENT,
    latitude            decimal(8, 6) NOT NULL,
    longitude           decimal(8, 6) NOT NULL,
    watch_serial_number varchar(40)   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_watch_location_watch1 FOREIGN KEY (watch_serial_number) REFERENCES watch (serial_number) on update cascade on delete cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;



INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (11, 49.026342, 24.328124, '4161362634');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (12, 48.568974, 23.941406, '4161362634');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (13, 52.346078, 12.524414, '9757255899');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (14, 54.346078, 10.524414, '9757255899');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (15, 53.012138, 12.304688, '9757255899');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (16, 51.995704, 15.996094, '3604317998');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (17, 51.724305, 16.347656, '3604317998');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (18, 52.076804, 21.093750, '8758758066');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (19, 52.022754, 21.462890, '8758758066');
INSERT INTO watch_location (id, latitude, longitude, watch_serial_number)
VALUES (20, 52.233855, 20.966309, '8758758066');
