CREATE DATABASE  IF NOT EXISTS `conference` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `conference`;
-- MySQL dump 10.13  Distrib 5.5.32, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: conference
-- ------------------------------------------------------
-- Server version	5.5.32-0ubuntu0.13.04.1

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
-- Table structure for table `Conference`
--

DROP TABLE IF EXISTS `Conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Conference` (
  `id` bigint(20) NOT NULL,
  `anfangsDatum` datetime DEFAULT NULL,
  `endDatum` datetime DEFAULT NULL,
  `kurzBeschreibung` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Conference`
--

LOCK TABLES `Conference` WRITE;
/*!40000 ALTER TABLE `Conference` DISABLE KEYS */;
INSERT INTO `Conference` VALUES (1,'2013-10-29 01:00:00','2013-10-29 01:00:00','Put Description here','Put name here');
/*!40000 ALTER TABLE `Conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
  `id` bigint(20) NOT NULL,
  `capazitaet` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Speaker`
--

DROP TABLE IF EXISTS `Speaker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Speaker` (
  `id` bigint(20) NOT NULL,
  `kurzBeschreibung` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Speaker`
--

LOCK TABLES `Speaker` WRITE;
/*!40000 ALTER TABLE `Speaker` DISABLE KEYS */;
/*!40000 ALTER TABLE `Speaker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Talk_Speaker`
--

DROP TABLE IF EXISTS `Talk_Speaker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Talk_Speaker` (
  `Talk_id` bigint(20) NOT NULL,
  `speakers_id` bigint(20) NOT NULL,
  KEY `FK7098BB6C4F0B3314` (`speakers_id`),
  KEY `FK7098BB6C98F2166B` (`Talk_id`),
  CONSTRAINT `FK7098BB6C98F2166B` FOREIGN KEY (`Talk_id`) REFERENCES `Talk` (`id`),
  CONSTRAINT `FK7098BB6C4F0B3314` FOREIGN KEY (`speakers_id`) REFERENCES `Speaker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Talk_Speaker`
--

LOCK TABLES `Talk_Speaker` WRITE;
/*!40000 ALTER TABLE `Talk_Speaker` DISABLE KEYS */;
/*!40000 ALTER TABLE `Talk_Speaker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Talk`
--

DROP TABLE IF EXISTS `Talk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Talk` (
  `id` bigint(20) NOT NULL,
  `dauer` int(11) NOT NULL,
  `kurzBeschreibung` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `conference_id` bigint(20) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27A8CC4733F40B` (`room_id`),
  KEY `FK27A8CCEDD557AB` (`conference_id`),
  CONSTRAINT `FK27A8CCEDD557AB` FOREIGN KEY (`conference_id`) REFERENCES `Conference` (`id`),
  CONSTRAINT `FK27A8CC4733F40B` FOREIGN KEY (`room_id`) REFERENCES `Room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Talk`
--

LOCK TABLES `Talk` WRITE;
/*!40000 ALTER TABLE `Talk` DISABLE KEYS */;
/*!40000 ALTER TABLE `Talk` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-29 18:37:21
