# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# HÙte: localhost (MySQL 5.5.38)
# Base de donnÈes: jassur
# Temps de gÈnÈration: 2016-05-29 14:55:25 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Affichage de la table addresses
# ------------------------------------------------------------

DROP TABLE IF EXISTS `addresses`;

CREATE TABLE `addresses` (
  `id_address` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(255) NOT NULL DEFAULT '',
  `city` varchar(255) NOT NULL DEFAULT '',
  `zip` int(11) NOT NULL,
  `country` varchar(255) NOT NULL DEFAULT '',
  `region` varchar(255) NOT NULL DEFAULT '',
  `client_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_address`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;

INSERT INTO `addresses` (`id_address`, `street`, `city`, `zip`, `country`, `region`, `client_id`)
VALUES
	(20,'20 RUE DES COLNOTTES','YERRES',91330,'FRANCE','ILE DE FRANCE',28),
	(21,'17 AVENUE DU PARC','PARIS',75012,'FRANCE','PARIS',29),
	(22,'67 RUE DES ROSIERS','MELUN',77010,'FRANCE','ILE DE FRANCE',30),
	(23,'12 RUE DU PAPE','BRUNOY',91800,'FRANCE','ILE DE FRANCE',31),
	(24,'20 RUE DES ARBRES','MASSY',91021,'FRANCE','IDF',32);

/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table categories
# ------------------------------------------------------------

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id_category` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `label_category` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;

INSERT INTO `categories` (`id_category`, `label_category`)
VALUES
	(1,'Immobilier'),
	(2,'Automobile'),
	(3,'Etudiant');

/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table clients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `id_client` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `business` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;

INSERT INTO `clients` (`id_client`, `last_name`, `first_name`, `phone`, `email`, `business`, `created_at`, `updated_at`)
VALUES
	(4,'Allouche','Sarah','0621354856','sarahallouche@hotmail.fr',0,NULL,NULL),
	(5,'jean','jean','0606060605','jean@gmail.com',0,NULL,NULL),
	(28,'DE VILLARTAY','ULYSSE','0685850979','u.devilartay@gmail.com',0,'2016-05-26',NULL),
	(29,'DUPONT','ROBERT','0134988734','r.dupont@free.fr',0,'2016-05-28',NULL),
	(30,'MARTIN','JEAN','006789876','jean.martin@hotmail.com',0,'2016-05-28','2016-05-28'),
	(31,'PIERRES','REMI','9876543212','r.pierre@gmail.com',0,'2016-05-28','2016-05-28'),
	(32,'DUMONT','ALEXANDRE','0687542154','a.dumont@free.fr',0,'2016-05-28',NULL);

/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table companies
# ------------------------------------------------------------

DROP TABLE IF EXISTS `companies`;

CREATE TABLE `companies` (
  `id_company` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `address_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id_company`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Affichage de la table loans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `loans`;

CREATE TABLE `loans` (
  `id_loan` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `total_duration` int(11) NOT NULL,
  `total_amount` double NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `insurance` double DEFAULT NULL,
  PRIMARY KEY (`id_loan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;

INSERT INTO `loans` (`id_loan`, `client_id`, `category_id`, `amount`, `total_duration`, `total_amount`, `created_at`, `updated_at`, `insurance`)
VALUES
	(33,28,3,5000,24,5100,'2016-05-29',NULL,NULL),
	(34,28,1,10000,24,10120,'2013-05-29',NULL,NULL),
	(35,28,1,100000,56,101200,'2014-05-29',NULL,NULL),
	(36,28,2,20000,36,20600,'2016-05-29',NULL,NULL);

/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table newrates
# ------------------------------------------------------------

DROP TABLE IF EXISTS `newrates`;

CREATE TABLE `newrates` (
  `id_newRate` int(11) NOT NULL AUTO_INCREMENT,
  `id_profile` int(11) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `job` text,
  `healthy` tinyint(1) DEFAULT NULL,
  `ratecompany` double NOT NULL,
  `newRate` double NOT NULL,
  `risk` text NOT NULL,
  PRIMARY KEY (`id_newRate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `newrates` WRITE;
/*!40000 ALTER TABLE `newrates` DISABLE KEYS */;

INSERT INTO `newrates` (`id_newRate`, `id_profile`, `age`, `duration`, `job`, `healthy`, `ratecompany`, `newRate`, `risk`)
VALUES
	(5,0,24,24,'CDI',1,3.5,2.9,'Le risque que le client soit d¬ùfaillant est faible !');

/*!40000 ALTER TABLE `newrates` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table rates
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rates`;

CREATE TABLE `rates` (
  `id_rate` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `loan_id` int(11) NOT NULL,
  `interest_rate` double NOT NULL,
  `duration` int(11) NOT NULL,
  `monthly_payment` double NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id_rate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;

INSERT INTO `rates` (`id_rate`, `loan_id`, `interest_rate`, `duration`, `monthly_payment`, `created_at`, `updated_at`)
VALUES
	(24,33,2,24,212.5,NULL,NULL),
	(25,34,1.2,24,421.6666666666667,NULL,NULL),
	(26,35,1.2,56,1807.142857142857,NULL,NULL),
	(27,36,2,36,572.2222222222222,NULL,NULL);

/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id_role` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Affichage de la table states
# ------------------------------------------------------------

DROP TABLE IF EXISTS `states`;

CREATE TABLE `states` (
  `id_state` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `loan_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `label_state` varchar(255) NOT NULL DEFAULT '',
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id_state`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;

INSERT INTO `states` (`id_state`, `loan_id`, `user_id`, `label_state`, `created_at`, `updated_at`)
VALUES
	(29,33,1,'Simulation',NULL,NULL),
	(30,34,1,'Simulation',NULL,NULL),
	(31,35,1,'Simulation',NULL,NULL),
	(32,36,1,'Simulation',NULL,NULL);

/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id_user` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id_user`, `role_id`, `password`, `last_name`, `first_name`, `phone`, `email`)
VALUES
	(1,0,'toto','jarnouen de villartay','ulysse','0689562148','ulysse@jassur.fr');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
