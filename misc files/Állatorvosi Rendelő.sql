CREATE TABLE `owner` (
  `owner_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `birthdate` date,
  `phone` varchar(255),
  `city` varchar(255),
  `address` varchar(255),
  `sex` boolean,
  `is_active` boolean
);

CREATE TABLE `animal` (
  `animal_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `birthdate` date,
  `sex` boolean,
  `is_active` boolean,
  `species` int,
  `owner` int
);

CREATE TABLE `species` (
  `species_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `is_active` boolean
);

CREATE TABLE `doctor` (
  `doctor_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `birthdate` date,
  `phone` varchar(255),
  `city` varchar(255),
  `address` varchar(255),
  `sex` boolean,
  `is_active` boolean
);

CREATE TABLE `medicalHistory` (
  `history_id` int PRIMARY KEY AUTO_INCREMENT,
  `animal_id` int,
  `registry` varchar(255),
  `is_active` boolean
);

ALTER TABLE `species` ADD FOREIGN KEY (`species_id`) REFERENCES `animal` (`species`);

ALTER TABLE `owner` ADD FOREIGN KEY (`owner_id`) REFERENCES `animal` (`owner`);

ALTER TABLE `animal` ADD FOREIGN KEY (`animal_id`) REFERENCES `medicalHistory` (`animal_id`);
