CREATE DATABASE  IF NOT EXISTS `wifiwolf` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wifiwolf`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: wifiwolf
-- ------------------------------------------------------
-- Server version	5.5.36

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
-- Table structure for table `t_auth_page`
--

DROP TABLE IF EXISTS `t_auth_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) DEFAULT NULL,
  `customize_css` text,
  `customize_html` text,
  `template_page` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_page`
--

LOCK TABLES `t_auth_page` WRITE;
/*!40000 ALTER TABLE `t_auth_page` DISABLE KEYS */;
INSERT INTO `t_auth_page` VALUES (1,1,'css','html','template');
/*!40000 ALTER TABLE `t_auth_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_auth_type`
--

DROP TABLE IF EXISTS `t_auth_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auth_type` varchar(45) DEFAULT NULL COMMENT 'refer to dictionary',
  `status` int(11) DEFAULT NULL COMMENT '0 means currently is not used.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_auth_type`
--

LOCK TABLES `t_auth_type` WRITE;
/*!40000 ALTER TABLE `t_auth_type` DISABLE KEYS */;
INSERT INTO `t_auth_type` VALUES (1,'USER_UNAUTHENTICATION',1),(2,'USER_AUTHENTIVATED',1),(3,'PHONEUSER_UNAUTHENTICATION',1),(4,'PHONEUSER_AUTHENTIVATED',1);
/*!40000 ALTER TABLE `t_auth_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_connection`
--

DROP TABLE IF EXISTS `t_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_connection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) DEFAULT NULL,
  `ip` varchar(16) DEFAULT NULL,
  `mac` char(17) DEFAULT NULL,
  `token_id` bigint(20) DEFAULT NULL,
  `outgoing` bigint(20) DEFAULT '0',
  `incoming` bigint(20) DEFAULT '0',
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `origin_url` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_connection`
--

LOCK TABLES `t_connection` WRITE;
/*!40000 ALTER TABLE `t_connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dict`
--

DROP TABLE IF EXISTS `t_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(60) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dict`
--

LOCK TABLES `t_dict` WRITE;
/*!40000 ALTER TABLE `t_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log`
--

DROP TABLE IF EXISTS `t_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `exception` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log`
--

LOCK TABLES `t_log` WRITE;
/*!40000 ALTER TABLE `t_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_node`
--

DROP TABLE IF EXISTS `t_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gateway_id` varchar(45) NOT NULL,
  `node_name` varchar(255) DEFAULT NULL,
  `owner_id` bigint(20) NOT NULL DEFAULT '1',
  `last_heartbeat_sys_uptime` int(11) DEFAULT NULL,
  `last_heartbeat_wifidog_uptime` int(11) DEFAULT NULL,
  `last_heartbeat_sys_memfree` int(11) DEFAULT NULL,
  `last_heartbeat_sys_load` float DEFAULT NULL,
  `last_heartbeat_ip` varchar(16) DEFAULT NULL,
  `last_heartbeat_timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='table for router information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_node`
--

LOCK TABLES `t_node` WRITE;
/*!40000 ALTER TABLE `t_node` DISABLE KEYS */;
INSERT INTO `t_node` VALUES (1,'wifiwolf','asus',1,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_phone_user`
--

DROP TABLE IF EXISTS `t_phone_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_phone_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone_num` varchar(20) DEFAULT NULL,
  `verify_code` varchar(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0: not verified\n1: already verified',
  `create_time` datetime DEFAULT NULL,
  `verify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_phone_user`
--

LOCK TABLES `t_phone_user` WRITE;
/*!40000 ALTER TABLE `t_phone_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_phone_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_portal_page`
--

DROP TABLE IF EXISTS `t_portal_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_portal_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) DEFAULT NULL,
  `template_page` varchar(45) DEFAULT NULL,
  `customize_css` text,
  `customize_html` text,
  `use_origin_url` int(11) DEFAULT '0' COMMENT '''0'' stands for not using origin URL\n''1'' stands for using origin URL',
  `customize_url` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_portal_page`
--

LOCK TABLES `t_portal_page` WRITE;
/*!40000 ALTER TABLE `t_portal_page` DISABLE KEYS */;
INSERT INTO `t_portal_page` VALUES (1,1,NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `t_portal_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_token`
--

DROP TABLE IF EXISTS `t_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(40) DEFAULT NULL,
  `auth_type_id` bigint(20) DEFAULT NULL,
  `registered_user_id` bigint(20) DEFAULT NULL,
  `phone_user_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_token`
--

LOCK TABLES `t_token` WRITE;
/*!40000 ALTER TABLE `t_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `sex` int(11) DEFAULT '0' COMMENT '0 means female, 1 means male',
  `age` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `wifi_status` int(11) DEFAULT '1' COMMENT '0 means wifi is not avalibale currently, 1 means user can use the network',
  `account_status` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL COMMENT 'Since we only has two user types, so use 0 to indicate current user is a normal user, on the other side, 1 means admin user.',
  `create_time` datetime DEFAULT NULL,
  `is_phone_verified` int(11) DEFAULT NULL,
  `is_email_verified` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='table for user information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','admin',1,25,'13134524356','admin@wifiwolf.com',1,1,1,'2014-11-20 16:24:38',1,1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-21 14:54:08
