-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Máj 22. 19:30
-- Kiszolgáló verziója: 10.4.14-MariaDB
-- PHP verzió: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `rendelo`
--

DELIMITER $$
--
-- Eljárások
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `animalAdd` (IN `name_IN` VARCHAR(100), IN `birthdate_IN` DATE, IN `sex_IN` BOOLEAN, IN `species_IN` INT, IN `owner_IN` INT)  NO SQL
INSERT INTO `animal`(`name`, `birthdate`, `sex`, `species`, `owner`) 
VALUES (name_IN, birthdate_IN, sex_IN, species_IN, owner_IN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `animalEdit` (IN `id_IN` INT, IN `name_IN` VARCHAR(100), IN `birthdate_IN` DATE, IN `sex_IN` BOOLEAN, IN `last_vaccinated_IN` DATE, IN `species_IN` INT, IN `owner_IN` INT)  NO SQL
UPDATE `animal` SET `name`=name_IN,`birthdate`=birthdate_IN,`sex`=sex_IN,`last_vaccinated`=last_vaccinated_IN, `species`=species_IN,`owner`=owner_IN WHERE animal.animal_id = id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `animalList` ()  NO SQL
SELECT * FROM animal 
where animal.is_active=true$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `animalSetActive` (IN `id_IN` INT, IN `isActive_IN` BOOLEAN)  NO SQL
UPDATE `animal` SET `is_active`=isActive_IN WHERE animal.animal_id = id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `animalVaccinate` (IN `id_IN` INT)  NO SQL
UPDATE `animal` SET `last_vaccinated`=CURRENT_TIMESTAMP WHERE animal.animal_id = id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ownerAdd` (IN `name_IN` VARCHAR(100), IN `birthdate_IN` DATE, IN `phone_IN` VARCHAR(100), IN `city_IN` VARCHAR(100), IN `address_IN` VARCHAR(100), IN `sex_IN` BOOLEAN)  NO SQL
INSERT INTO `owner`(`name`, `birthdate`, `phone`, `city`, `address`, `sex`) 
VALUES (name_IN, birthdate_IN, phone_IN, city_IN, address_IN, sex_IN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ownerAnimalList` (IN `id_IN` INT)  NO SQL
SELECT `animal_id`,animal.`name`,animal.`birthdate`,animal.`sex`,`last_vaccinated`,animal.`is_active`,`species`,`owner` FROM `animal`
INNER JOIN owner on owner.owner_id=animal.owner
WHERE animal.owner=id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ownerList` ()  NO SQL
SELECT * FROM `owner` WHERE owner.is_active = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ownerSetActive` (IN `id_IN` INT, IN `isActive_IN` BOOLEAN)  NO SQL
UPDATE `owner` SET `is_active`=isActive_IN
WHERE owner.owner_id = id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ownerUpdate` (IN `id_IN` INT, IN `name_IN` VARCHAR(100), IN `birthdate_IN` DATE, IN `phone_IN` VARCHAR(100), IN `city_IN` VARCHAR(100), IN `address_IN` VARCHAR(100), IN `sex_IN` BOOLEAN)  NO SQL
UPDATE `owner` SET `name`=name_IN,`birthdate`=birthdate_IN,`phone`=phone_IN,`city`=city_IN,`address`=address_IN,`sex`=sex_IN
WHERE owner.owner_id = id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `speciesAdd` (IN `name_IN` VARCHAR(100))  NO SQL
INSERT INTO `species`(`name`)
VALUES (name_IN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `speciesCount` ()  NO SQL
SELECT species.name,COUNT(*) as num FROM `species` 
INNER JOIN `animal` on animal.species = species.species_id
WHERE 1
GROUP BY species_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `speciesEdit` (IN `id_IN` INT, IN `name_IN` VARCHAR(100))  NO SQL
UPDATE `species` SET `name`=name_IN where species.species_id = id_IN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `speciesList` ()  NO SQL
SELECT * FROM `species` WHERE species.is_active = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `speciesSetActive` (IN `id_IN` INT, IN `isActive_IN` BOOLEAN)  NO SQL
UPDATE `species` SET `is_active`=isActive_IN
WHERE species.species_id = id_IN$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `animal`
--

CREATE TABLE `animal` (
  `animal_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `last_vaccinated` date DEFAULT '0001-01-01',
  `is_active` tinyint(1) DEFAULT 1,
  `species` int(11) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `animal`
--

INSERT INTO `animal` (`animal_id`, `name`, `birthdate`, `sex`, `last_vaccinated`, `is_active`, `species`, `owner`) VALUES
(1, 'Yoda', '2011-05-16', 1, '2021-05-22', 0, 1, 2),
(2, 'Kókusz', '2015-06-14', 0, '2021-05-22', 1, 1, 2),
(3, 'Tita', '2015-06-14', 0, '0001-01-01', 1, 2, 4),
(4, 'Perec', '2019-07-28', 1, '2020-04-12', 1, 4, 4);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `owner`
--

CREATE TABLE `owner` (
  `owner_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `owner`
--

INSERT INTO `owner` (`owner_id`, `name`, `birthdate`, `phone`, `city`, `address`, `sex`, `is_active`) VALUES
(2, 'Gyula Bácsi', '1959-06-24', ' 36302098896', 'Felsőtomaj', 'Arany János utca 16.', 1, 1),
(4, 'Kis Juliska', '1999-02-02', ' 36201987406', 'Pécs', 'valami utca 100', 0, 1),
(5, 'Vég Béla', '1999-04-19', '06300060834', 'Kiskuntarcsa alsó', 'Petőfi Sándor utca 1.', 1, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `species`
--

CREATE TABLE `species` (
  `species_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `species`
--

INSERT INTO `species` (`species_id`, `name`, `is_active`) VALUES
(1, 'Kutya', 1),
(2, 'Macska', 0),
(3, 'Nyul', 1),
(4, 'diszno', 1),
(5, 'diszno2', 1),
(6, 'diszno2', 1),
(7, 'diszn3', 1),
(8, 'csakjómár', 1),
(9, 'Tehén', 1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`animal_id`),
  ADD KEY `species` (`species`),
  ADD KEY `owner` (`owner`);

--
-- A tábla indexei `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`owner_id`);

--
-- A tábla indexei `species`
--
ALTER TABLE `species`
  ADD PRIMARY KEY (`species_id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `animal`
--
ALTER TABLE `animal`
  MODIFY `animal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `owner`
--
ALTER TABLE `owner`
  MODIFY `owner_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `species`
--
ALTER TABLE `species`
  MODIFY `species_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `animal_ibfk_1` FOREIGN KEY (`species`) REFERENCES `species` (`species_id`),
  ADD CONSTRAINT `animal_ibfk_2` FOREIGN KEY (`owner`) REFERENCES `owner` (`owner_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
