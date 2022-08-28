-- Adminer 4.8.1 MySQL 8.0.30 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP DATABASE IF EXISTS `villains`;
CREATE DATABASE `villains` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `villains`;

DROP TABLE IF EXISTS `minions`;
CREATE TABLE `minions` (
                           `id` int unsigned NOT NULL AUTO_INCREMENT,
                           `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                           `age` int unsigned NOT NULL,
                           `town_id` int unsigned DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `town_id` (`town_id`),
                           CONSTRAINT `minions_ibfk_1` FOREIGN KEY (`town_id`) REFERENCES `towns` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `minions` (`id`, `name`, `age`, `town_id`) VALUES
                                                           (1,	'Starkiller',	21,	3),
                                                           (2,	'Vanilla Ice',	24,	2),
                                                           (3,	'Maraya',	19,	2),
                                                           (4,	'Pet Shop',	3,	2),
                                                           (5,	'Goblin',	7,	4),
                                                           (6,	'Orc',	7,	4),
                                                           (7,	'Undead Archer',	106,	4),
                                                           (8,	'Benezia',	942,	5),
                                                           (9,	'Dallis',	10000,	6),
                                                           (10,	'Bracus Rex',	3500,	6),
                                                           (11,	'Alexander',	29,	6),
                                                           (12,	'Anubis',	300,	2),
                                                           (13,	'Orc Mad Doctor',	32,	4);

DROP TABLE IF EXISTS `minions_villains`;
CREATE TABLE `minions_villains` (
                                    `minion_id` int unsigned NOT NULL,
                                    `villain_id` int unsigned NOT NULL,
                                    KEY `minion_id` (`minion_id`),
                                    KEY `villain_id` (`villain_id`),
                                    CONSTRAINT `minions_villains_ibfk_1` FOREIGN KEY (`minion_id`) REFERENCES `minions` (`id`),
                                    CONSTRAINT `minions_villains_ibfk_4` FOREIGN KEY (`villain_id`) REFERENCES `villains` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `minions_villains` (`minion_id`, `villain_id`) VALUES
                                                               (1,	1),
                                                               (2,	5),
                                                               (3,	5),
                                                               (4,	5),
                                                               (5,	2),
                                                               (6,	2),
                                                               (7,	2),
                                                               (8,	3),
                                                               (9,	4),
                                                               (10,	4),
                                                               (11,	4),
                                                               (12,	5),
                                                               (13,	2);

DROP TABLE IF EXISTS `towns`;
CREATE TABLE `towns` (
                         `id` int unsigned NOT NULL AUTO_INCREMENT,
                         `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                         `country` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `towns` (`id`, `name`, `country`) VALUES
                                                  (1,	'Moscow',	'Russia'),
                                                  (2,	'Cairo',	'Egypt'),
                                                  (3,	'Imperial City',	'Galactic Empire'),
                                                  (4,	'Tower',	'Country'),
                                                  (5,	'Citadel',	'Milky Way'),
                                                  (6,	'Arcs',	'Rivellon');

DROP TABLE IF EXISTS `villains`;
CREATE TABLE `villains` (
                            `id` int unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                            `evilness_factor` enum('good','bad','evil','super-evil') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `villains` (`id`, `name`, `evilness_factor`) VALUES
                                                             (1,	'Darth Vader',	'evil'),
                                                             (2,	'Overlord',	'super-evil'),
                                                             (3,	'Saren',	'bad'),
                                                             (4,	'Lucian',	'good'),
                                                             (5,	'DIO',	'super-evil');

-- 2022-08-28 10:43:04