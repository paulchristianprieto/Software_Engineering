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
  `act_passportNo` varchar(20) DEFAULT NULL,
  `act_tinID` varchar(20) DEFAULT NULL,
  `act_aepID` varchar(20) DEFAULT NULL,
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
  `act_reportTimestamp` date DEFAULT NULL,
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
  `client_nationality` varchar(50) DEFAULT NULL,
  `client_birthdate` date DEFAULT NULL,
  `client_gender` varchar(15) DEFAULT NULL,
  `client_company` varchar(50) DEFAULT NULL,
  `client_position` varchar(25) DEFAULT NULL,
  `client_alias` varchar(25) DEFAULT NULL,
  `client_contact` varchar(30) DEFAULT NULL,
  `client_email` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (16,'Jiabo','He','China','1987-06-16','Male','Theldi Construction Corp.','Project Manager','',' 0086-95583','jiabohe@netease.com'),(17,'Cunke','Jia','China','1982-06-05','Male','Theldi Construction Corp.','Consultant','',' 0086-95584','cunkejia@netease.com'),(18,' Cheng','Lu','China','1987-08-13','Male','Theldi Construction Corp','Construction Assistant','',' 0086-95585','LuCheng@netease.com'),(19,'Songyun','Zhang','China','1981-12-06','Male','Theldi Construction Corp','Project Assistant','',' 0086-95586','SongyunZhang@netease.com'),(20,'Hongkan','Wu','China','1980-02-27','Male','Theldi Construction Inc.','Construction Consultant','',' 0086-95586','HongkanPongkan@netease.com'),(21,'Yunchun','Gao','China','1980-02-28','Male','Theldi Corporation Inc.','Construction Manager','William',' 0086-95588','WilliamChina@netease.com'),(22,'Gyung','Jo ','China','1996-12-03','Male','Theldi Construction Corp.','Project Manager','',' 0086-95589','Gyungjo@gmail.com'),(25,'Shizuku','Rin','Japan','1980-01-31','Female','Capcom Co., Ltd','UI Developer','','000000000000','shizukurin04@gmail.com');
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
  `emp_address` varchar(150) DEFAULT NULL,
  `emp_contact` varchar(30) DEFAULT NULL,
  `emp_email` varchar(70) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('Moore','Michael Andrew','Document Controller','Male','1980-02-24','938 Aurora Blvd, Cubao, QC.','09090909090','taka@gmail.com',1);
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
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`),
  UNIQUE KEY `trans_id_UNIQUE` (`trans_transId`),
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
  `statusP_addRequirements` varchar(70) DEFAULT NULL,
  `statusP_aepDateFiled` date DEFAULT NULL,
  `statusP_aepDateRelease` date DEFAULT NULL,
  `statusP_permitDateFiled` date DEFAULT NULL,
  `statusP_acrIcard` varchar(70) DEFAULT NULL,
  `statusP_permitDateReleased` date DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`client_id`),
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
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`trans_transId`),
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
  `trans_transTimestamp` date NOT NULL,
  `trans_transAuthor` varchar(50) NOT NULL,
  PRIMARY KEY (`trans_transId`),
  UNIQUE KEY `trans_transId_UNIQUE` (`trans_transId`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES ('E68759489','486-442-390-000','9G','2019-04-01','2020-04-01','',NULL,NULL,'NCR-2016-11-16361','2019-05-01','2019-10-01',48,16,'2019-04-01','Mintaka'),('000000000000','000000000000','9G','2019-03-31','2020-03-31','',NULL,NULL,'NCR-0000-0000','2019-04-03','2019-09-03',51,25,'2019-04-04','');
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
  `user_ifAdmin` int(11) DEFAULT NULL,
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
INSERT INTO `users` VALUES (1,'Mintaka','mintakaaa',1),(2,'Paul','paulpaul',0);
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

-- Dump completed on 2019-04-06 16:06:20
