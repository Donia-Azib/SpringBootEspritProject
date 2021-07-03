-- MySQL dump 10.13  Distrib 5.7.33, for Linux (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	5.7.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `bookstore`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `bookstore` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bookstore`;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande` (
  `reference` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`reference`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande_livres`
--

DROP TABLE IF EXISTS `commande_livres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande_livres` (
  `commandes_reference` bigint(20) NOT NULL,
  `livres_reference` bigint(20) NOT NULL,
  KEY `FK1vogr002vx54e4eqip2colxdh` (`livres_reference`),
  KEY `FKg10el6rjlewviay5kw8u9umjq` (`commandes_reference`),
  CONSTRAINT `FK1vogr002vx54e4eqip2colxdh` FOREIGN KEY (`livres_reference`) REFERENCES `livre` (`reference`),
  CONSTRAINT `FKg10el6rjlewviay5kw8u9umjq` FOREIGN KEY (`commandes_reference`) REFERENCES `commande` (`reference`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande_livres`
--

LOCK TABLES `commande_livres` WRITE;
/*!40000 ALTER TABLE `commande_livres` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande_livres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demande`
--

DROP TABLE IF EXISTS `demande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demande` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demande`
--

LOCK TABLES `demande` WRITE;
/*!40000 ALTER TABLE `demande` DISABLE KEYS */;
/*!40000 ALTER TABLE `demande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `note` int(11) NOT NULL,
  `livre_reference` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvkrfebxojv9w2uwjn4yivls3` (`livre_reference`),
  KEY `FKpwwmhguqianghvi1wohmtsm8l` (`user_id`),
  CONSTRAINT `FKhvkrfebxojv9w2uwjn4yivls3` FOREIGN KEY (`livre_reference`) REFERENCES `livre` (`reference`),
  CONSTRAINT `FKpwwmhguqianghvi1wohmtsm8l` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livre`
--

DROP TABLE IF EXISTS `livre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livre` (
  `reference` bigint(20) NOT NULL AUTO_INCREMENT,
  `auteur` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `disponibilite` bit(1) NOT NULL,
  `prix` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `offre_reference` bigint(20) DEFAULT NULL,
  `wish_list_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`reference`),
  KEY `FKhavckc8ue5s5v7smxkiaw27hx` (`offre_reference`),
  KEY `FKj1n107jo6hjrqfts5hpf2eybw` (`wish_list_id`),
  CONSTRAINT `FKhavckc8ue5s5v7smxkiaw27hx` FOREIGN KEY (`offre_reference`) REFERENCES `offre` (`reference`),
  CONSTRAINT `FKj1n107jo6hjrqfts5hpf2eybw` FOREIGN KEY (`wish_list_id`) REFERENCES `wish_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livre`
--

LOCK TABLES `livre` WRITE;
/*!40000 ALTER TABLE `livre` DISABLE KEYS */;
/*!40000 ALTER TABLE `livre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livre_users`
--

DROP TABLE IF EXISTS `livre_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livre_users` (
  `livre_reference` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `FKtquqd858y7htgagpskatksuvy` (`users_id`),
  KEY `FKeigwaj3p0sklvl57985qyqu4w` (`livre_reference`),
  CONSTRAINT `FKeigwaj3p0sklvl57985qyqu4w` FOREIGN KEY (`livre_reference`) REFERENCES `livre` (`reference`),
  CONSTRAINT `FKtquqd858y7htgagpskatksuvy` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livre_users`
--

LOCK TABLES `livre_users` WRITE;
/*!40000 ALTER TABLE `livre_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `livre_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offre`
--

DROP TABLE IF EXISTS `offre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offre` (
  `reference` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_debut` varchar(255) DEFAULT NULL,
  `date_fin` varchar(255) DEFAULT NULL,
  `diponibilite` bit(1) NOT NULL,
  `pourcentage` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`reference`),
  KEY `FKk4ovbtm5h298yo9cb74fq6159` (`user_id`),
  CONSTRAINT `FKk4ovbtm5h298yo9cb74fq6159` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offre`
--

LOCK TABLES `offre` WRITE;
/*!40000 ALTER TABLE `offre` DISABLE KEYS */;
/*!40000 ALTER TABLE `offre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reclamation` (
  `reference` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reference`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reclamation`
--

LOCK TABLES `reclamation` WRITE;
/*!40000 ALTER TABLE `reclamation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reclamation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MANAGEMENT'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (2,1),(10,1),(4,2),(8,2),(10,2),(1,3),(10,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `wish_list_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKpk2gwh1ggweerd5bpx84lcjty` (`wish_list_id`),
  CONSTRAINT `FKpk2gwh1ggweerd5bpx84lcjty` FOREIGN KEY (`wish_list_id`) REFERENCES `wish_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@admin','admin','admin','$2a$10$Cq2uHe46ynRUpvAjnKBqxevCGoRO/An5u7RTDPBL/6ZTLrdysG8by','admin',NULL),(2,'test@test','test','test','$2a$10$ZlgFdz7WTXZ5bQWXnGTXKOnASvptza.p5FIrEOWpFZk27cHEhn./G','test',NULL),(4,'ahmed@gmail.com','ahmed','ahmed','$2a$10$yjhgZ4rDWAur0tOvk.sce.9SEaOfSauYzx3kUIqUWeuv9lacLBJB.','ahmed',NULL),(8,'yassine@gmail.com','yassine','yassine','$2a$10$zhG1RbtV7ggM8GiIx5TfK.MbMmftsWYRGfcLsITKiWLHPkjPQCPMe','yassine',NULL),(10,'amd@amd','amdmod','amdmod','$2a$10$ymUIMTTlXMU0fUkqdCFNNuQTEdH8tkE9wwkOwf2vYmQXoFp4E3H2O','amd',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_livres`
--

DROP TABLE IF EXISTS `users_livres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_livres` (
  `user_id` bigint(20) NOT NULL,
  `livres_reference` bigint(20) NOT NULL,
  KEY `FKbabg8ftgfedmp6cecxrtak74w` (`livres_reference`),
  KEY `FKo6xjvsbwgkewpfg9v1cwf1hvx` (`user_id`),
  CONSTRAINT `FKbabg8ftgfedmp6cecxrtak74w` FOREIGN KEY (`livres_reference`) REFERENCES `livre` (`reference`),
  CONSTRAINT `FKo6xjvsbwgkewpfg9v1cwf1hvx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_livres`
--

LOCK TABLES `users_livres` WRITE;
/*!40000 ALTER TABLE `users_livres` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_livres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish_list`
--

DROP TABLE IF EXISTS `wish_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wish_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_de_livre` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish_list`
--

LOCK TABLES `wish_list` WRITE;
/*!40000 ALTER TABLE `wish_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `wish_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-03 17:03:05
