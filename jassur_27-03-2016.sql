# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Hôte: localhost (MySQL 5.5.38)
# Base de données: jassur
# Temps de génération: 2016-03-27 16:25:17 +0000
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
	(5,'2 rue voltaire','PARIS',75012,'FRANCE','PARIS',11),
	(6,'2 rue voltaire','PARIS',75012,'FRANCE','PARIS',12),
	(7,'2 rue voltaire','PARIS',75012,'FRANCE','PARIS',13),
	(8,'rue de Paris','Crteil',94000,'France','Ile de France',17),
	(9,'4 rue de la rpublique','Paris',75012,'FRANCE','Paris',18),
	(10,'','',0,'','',19),
	(11,'18 rue des signes','Paris',75015,'FRANCE','Ile de France',20),
	(12,'erouzhr','ouheroij',9090,'dfkbjnefbjn','oijeboi',21);

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
	(1,'Immobilier');

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
	(11,'TITI','Toto','0154982635','toto@gmail.com',0,'2016-03-01',NULL),
	(12,'TITI','Toto','0154982635','toto@gmail.com',0,'2016-03-01',NULL),
	(13,'TITI','Toto','0154982635','toto@gmail.com',0,'2016-03-01',NULL),
	(14,'de villartay','ulysse','9876543454','u.devillartay@gmail.com',0,'2016-03-14',NULL),
	(15,'de villartay','ulysse','98875','u.devillartay@gmail.com',0,'2016-03-14',NULL),
	(16,'de Villartay','Ulysse','0678569878','u.devillartay@gmail.com',0,'2016-03-14',NULL),
	(17,'de Villartay','Ulysse','9809988776','u.devillartay@gmail.com',0,'2016-03-14',NULL),
	(20,'Dupont','Albert','0687659867','albert.dupont@gmail.com',0,'2016-03-15',NULL);

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
  PRIMARY KEY (`id_loan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;

INSERT INTO `loans` (`id_loan`, `client_id`, `category_id`, `amount`, `total_duration`, `total_amount`, `created_at`, `updated_at`)
VALUES
	(1,1,1,10000,24,10100.12,'0000-00-00','0000-00-00'),
	(2,0,1,5000,24,5095.5,NULL,NULL),
	(3,0,1,5000,24,5095.5,NULL,NULL),
	(4,0,1,5000,24,5095.5,NULL,NULL),
	(5,1,1,5000,24,5095.5,NULL,NULL),
	(6,1,1,5000,24,5095.5,NULL,NULL);

/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
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
	(1,1,3.5,24,332.23,'0000-00-00','0000-00-00'),
	(2,1,3.1,24,320.2,'0000-00-00','0000-00-00'),
	(3,3,1.9,24,212.5,NULL,NULL),
	(4,4,1.9,24,212.5,NULL,NULL),
	(5,5,1.9,24,212.5,NULL,NULL),
	(6,6,1.9,24,212.5,NULL,NULL);

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
	(1,1,1,'simulation','0000-00-00','0000-00-00'),
	(2,1,1,'en cours','0000-00-00','0000-00-00'),
	(3,3,1,'Simulation',NULL,NULL),
	(4,4,1,'Simulation',NULL,NULL),
	(5,5,1,'Simulation',NULL,NULL),
	(6,6,1,'Simulation',NULL,NULL);

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
