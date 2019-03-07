CREATE DATABASE  IF NOT EXISTS `jdl_accounts` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `jdl_accounts`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jdl_accounts
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity_history`
--

DROP TABLE IF EXISTS `activity_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity_history` (
  `act_activityID` int(11) NOT NULL,
  `act_passportNo` int(11) DEFAULT NULL,
  `act_tinID` int(11) DEFAULT NULL,
  `act_aepID` int(11) DEFAULT NULL,
  `act_visaType` int(11) DEFAULT NULL,
  `act_visaStartDate` int(11) DEFAULT NULL,
  `act_visaEndDate` int(11) DEFAULT NULL,
  `act_permitType` int(11) DEFAULT NULL,
  `act_permitStartDate` int(11) DEFAULT NULL,
  `act_permitEndDate` int(11) DEFAULT NULL,
  `act_aepStartDate` int(11) DEFAULT NULL,
  `act_aepEndDate` int(11) DEFAULT NULL,
  `act_loginTimestamp` date DEFAULT NULL,
  `act_logoutTimestamp` date DEFAULT NULL,
  `act_reportTimestamp` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  PRIMARY KEY (`act_activityID`),
  UNIQUE KEY `act_activityID_UNIQUE` (`act_activityID`),
  KEY `user_id` (`user_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `activity_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `activity_history_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_history`
--

LOCK TABLES `activity_history` WRITE;
/*!40000 ALTER TABLE `activity_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clients` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_lastname` varchar(50) DEFAULT NULL,
  `client_firstname` varchar(50) DEFAULT NULL,
  `client_nationality` varchar(25) DEFAULT NULL,
  `client_birthdate` date DEFAULT NULL,
  `client_gender` varchar(15) DEFAULT NULL,
  `client_company` varchar(50) DEFAULT NULL,
  `client_position` varchar(25) DEFAULT NULL,
  `client_alias` varchar(25) DEFAULT NULL,
  `client_contact` int(11) DEFAULT NULL,
  `client_email` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Ching','Chong','Chinese','1986-07-12','Male','Chingchong','Manager','seven',988838374,'sch@yaooc.com'),(2,'Chang','Ching','Hongkongese','1945-01-08','Female','Chingchong','Janitress','eight',2147483647,'aa@bc.com'),(3,'Maare','Chael','Korean','1997-09-13','Male','Arbees','Breadman','three',2147483647,'KJKJ@hyhad.ccom'),(4,'James','Lebron','American','1985-12-30','Female','NBA','Playah','king',2147483647,'lebron@james.com'),(5,'Wang','Wong','Japanese','1893-11-28','Male','Julies','Breadman','one',2147483647,'wing@wang.com'),(6,'Wong','Tung','Chinese','1998-11-23','Male','KFC','Fryman','five',2147483647,'five@yahoo.com'),(7,'Curry','Chan','Singaporean','1993-05-11','Female','Jollibee','Dancer','four',2147483647,'four@yahoo.com'),(8,'Chan','Jeff','Chinese','1989-07-12','Male','PBA','Shooter','six',2147483647,'six@yahoo.com'),(9,'Bolado','Chris','Filipino','1979-05-22','Male','PBA','Center','nine',2147483647,'nine@yahoo.com'),(10,'Uzumaki','Naruto','Japanese','1991-12-12','Male','Ramen House','Ninja','ten',2147483647,'ten@yahoo.com');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `emp_lastname` varchar(50) DEFAULT NULL,
  `emp_firstname` varchar(50) DEFAULT NULL,
  `emp_position` varchar(25) DEFAULT NULL,
  `emp_gender` varchar(15) DEFAULT NULL,
  `emp_birthdate` date DEFAULT NULL,
  `emp_address` varchar(50) DEFAULT NULL,
  `emp_contact` int(11) DEFAULT NULL,
  `emp_email` varchar(70) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remarks`
--

DROP TABLE IF EXISTS `remarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `remarks` (
  `remarks_dateReceived` date DEFAULT NULL,
  `remarks_dateUpdated` date DEFAULT NULL,
  `remarks_reminders` varchar(50) DEFAULT NULL,
  `remarks_toDo` varchar(50) DEFAULT NULL,
  `remarks_transaction` varchar(50) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`),
  CONSTRAINT `remarks_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remarks`
--

LOCK TABLES `remarks` WRITE;
/*!40000 ALTER TABLE `remarks` DISABLE KEYS */;
/*!40000 ALTER TABLE `remarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_permits`
--

DROP TABLE IF EXISTS `status_permits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status_permits` (
  `statusP_dateReceived` date DEFAULT NULL,
  `statusP_instructions` varchar(100) DEFAULT NULL,
  `statusP_aepCancellation` varchar(70) DEFAULT NULL,
  `statusP_downgrading` varchar(70) DEFAULT NULL,
  `statusP_aepExitClearance` varchar(70) DEFAULT NULL,
  `statusP_updatedVisaExtend` varchar(70) DEFAULT NULL,
  `statusP_documentation` varchar(70) DEFAULT NULL,
  `statusP_addRequirements` int(11) DEFAULT NULL,
  `statusP_aepDateFiled` date DEFAULT NULL,
  `statusP_aepDateRelease` date DEFAULT NULL,
  `statusP_permitDateFiled` date DEFAULT NULL,
  `statusP_acrIcard` varchar(70) DEFAULT NULL,
  `statusP_permitDateReleased` date DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  KEY `client_id` (`client_id`),
  CONSTRAINT `status_permits_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_permits`
--

LOCK TABLES `status_permits` WRITE;
/*!40000 ALTER TABLE `status_permits` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_permits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_visa`
--

DROP TABLE IF EXISTS `status_visa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status_visa` (
  `statusV_documentation` varchar(70) DEFAULT NULL,
  `statusV_dateFiled` date DEFAULT NULL,
  `statusV_immigrant` varchar(70) DEFAULT NULL,
  `statusV_hearingDate` date DEFAULT NULL,
  `statusV_earlyHearing` date DEFAULT NULL,
  `statusV_agenda` varchar(70) DEFAULT NULL,
  `statusV_visaReleased` varchar(70) DEFAULT NULL,
  `statusV_waiverECC` varchar(70) DEFAULT NULL,
  `statusV_acrIcard` varchar(70) DEFAULT NULL,
  `statusV_docComplete` varchar(70) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  KEY `client_id` (`client_id`),
  CONSTRAINT `status_visa_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_visa`
--

LOCK TABLES `status_visa` WRITE;
/*!40000 ALTER TABLE `status_visa` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_visa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transactions` (
  `trans_passportNo` varchar(30) DEFAULT NULL,
  `trans_tinID` varchar(30) DEFAULT NULL,
  `trans_visaType` varchar(30) DEFAULT NULL,
  `trans_visaStartDate` date DEFAULT NULL,
  `trans_visaEndDate` date DEFAULT NULL,
  `trans_permitType` varchar(30) DEFAULT NULL,
  `trans_permitStartDate` date DEFAULT NULL,
  `trans_permitEndDate` date DEFAULT NULL,
  `trans_aepID` varchar(30) DEFAULT NULL,
  `trans_aepStartDate` date DEFAULT NULL,
  `trans_aepEndDate` date DEFAULT NULL,
  `trans_transId` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  PRIMARY KEY (`trans_transId`),
  UNIQUE KEY `trans_transId_UNIQUE` (`trans_transId`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES ('G87654359','765984999','9G',NULL,NULL,'',NULL,NULL,'NCR-2011-12-8694','2011-01-01','2011-06-01',1,1),('G92344544','995848306','',NULL,NULL,'Special working Permit','2019-12-12','2020-06-12','',NULL,NULL,2,2),('N87289812','545876648','Simple','2014-03-07','2016-03-08','',NULL,NULL,'',NULL,NULL,3,5),('N87289232','365984999','','2014-03-07','2016-03-08','',NULL,NULL,'',NULL,NULL,4,7),('N72898124','982349823','','2014-03-07','2016-03-08','',NULL,NULL,'',NULL,NULL,5,6),('G87633522','365984994','9G',NULL,NULL,'',NULL,NULL,'NCR-2011-12-8693','2015-11-01','2016-05-03',6,8),('G87623456','3498349389','9G',NULL,NULL,'',NULL,NULL,'NCR-2011-12-8694','2015-11-04','2016-05-05',7,9);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_username` varchar(20) DEFAULT NULL,
  `user_password` varchar(20) DEFAULT NULL,
  `user_ifAdmin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_username` (`user_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-07  9:25:37
