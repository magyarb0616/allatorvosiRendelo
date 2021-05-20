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
  `last_vaccinated` date,
  `is_active` boolean,
  `species` int,
  `owner` int
);

CREATE TABLE `species` (
  `species_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `is_active` boolean
);

ALTER TABLE `animal` ADD FOREIGN KEY (`species`) REFERENCES `species` (`species_id`);

ALTER TABLE `animal` ADD FOREIGN KEY (`owner`) REFERENCES `owner` (`owner_id`);
