-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: cinematic
-- ------------------------------------------------------
-- Server version	10.7.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Sequence structure for `hibernate_sequence`
--

CREATE SEQUENCE `hibernate_sequence` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1000 nocycle ENGINE=InnoDB;
SELECT SETVAL(`hibernate_sequence`, 1001, 0);

--
-- Table structure for table `films`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `films` (
  `film_id` bigint(20) NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `on_show` bit(1) DEFAULT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`film_id`),
  UNIQUE KEY `UK_p58gkmydwloxo5swyv4adqtbt` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES
(8,'Malaikat yang paling ditakuti','\0','Malaikat pencabut nyali'),
(9,'Film yang kontroversial(Katanya)','','Tetangga Genit'),
(10,'Film yang dirilis enggak tau kapan','\0','Kapal Oleng Kapten'),
(11,'Salah satu film yang tidak tau bahwa dia adalah film','','Katanya Film'),
(12,'Skripsi dipersulit. Bimbingan pun jadi sulit. Lulus pun jadi sulit','','Chat Ku Tenggelam oleh banyaknya Pesan dari Teman Dosenku'),
(13,'Salah Satu pernyataan paling nyelekit','\0','Katanya Mau Lulus? Skripsi Kok Dianggurin'),
(14,'Ini film horor ya?','','Pocong Absurd');
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedules` (
  `schedulesid` bigint(20) NOT NULL,
  `end_time` time DEFAULT NULL,
  `price` float DEFAULT NULL,
  `show_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `film_id` bigint(20) NOT NULL,
  PRIMARY KEY (`schedulesid`),
  KEY `FKd4yk1lqx5o4urgi84k055u89u` (`film_id`),
  CONSTRAINT `FKd4yk1lqx5o4urgi84k055u89u` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES
(150,'20:00:00',35000,'2022-08-04','17:30:00',8),
(151,'21:00:00',40000,'2023-03-15','18:45:00',9),
(152,'13:00:00',45000,'2022-06-14','10:00:00',10),
(153,'13:50:00',25000,'2021-12-25','11:30:00',11),
(154,'13:00:00',45000,'2022-06-19','11:45:00',12),
(155,'15:30:00',55000,'2022-02-12','12:30:00',13),
(156,'14:00:00',45000,'2022-06-04','12:15:00',14);
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seats` (
  `seatsid` bigint(20) NOT NULL,
  `seats_number` bigint(20) NOT NULL,
  `seats` int(11) DEFAULT NULL,
  PRIMARY KEY (`seatsid`,`seats_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `join_date` datetime(6) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'Surabaya','sukron@email.com','2022-04-09 15:00:59.369000','aaah Ngantuk','sukronganteng'),
(2,'Malang','rizky@email.com','2022-04-09 15:00:59.401000','apasihhh','rizky'),
(3,'Situbondo','malaikat@email.com','2022-04-09 15:00:59.405000','ngantuk','malaikat'),
(4,'Magelang','kusiono@email.com','2022-04-09 15:00:59.409000','aaah','kusionojumari'),
(5,'Purwokerto','andre@email.com','2022-04-09 15:00:59.413000','gak Ngantuk','andrejipun'),
(6,'Jakarta','izam@email.com','2022-04-09 15:00:59.417000','janganGitu','izamkipli'),
(7,'Bandung','mamat@email.com','2022-04-09 15:00:59.421000','sayaHalu','mamatskonat');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cinematic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-09 15:18:00
