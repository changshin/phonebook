Create database if not exists phonebook;
use phonebook;
DROP TABLE IF EXISTS `gender`;
-- Table structure for table `gender`
CREATE TABLE `gender` (
  `id` tinyint(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO `gender` VALUES (1,'Male'),(2,'Female'),(3,'Other');
-- Table structure for table `groups`
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
); 

INSERT INTO `groups` VALUES (1,'Family',NULL,NULL,NULL),(2,'Relative',NULL,NULL,NULL),(3,'Friends',NULL,NULL,NULL),(4,'Co-workers',0,NULL,NULL),(13,'Other',NULL,NULL,NULL);

DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `purpose` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `gender_id` tinyint(2) DEFAULT NULL,
  `first_name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `last_name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `note` text CHARACTER SET latin1,
  `street` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `city` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `state` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `zip` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `country` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `avatar_url` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `avatar_thumbnail` varchar(120) CHARACTER SET latin1 DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `last_logged_in` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `gender_id` (`gender_id`),
  KEY `email` (`email`)
);

INSERT INTO `user` VALUES (1,'adam@email.com',3,'Adam','Smith',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-06-04 09:17:34','2017-06-04 10:06:23',NULL),(2,'nancy@email.com',2,'Nancy','Smith',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-06-04 09:19:53','2017-06-04 09:19:53',NULL),(3,'James@email.com',1,'James','Bond',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-06-04 09:20:14','2017-06-04 09:20:14',NULL);

DROP TABLE IF EXISTS `user_groups`;

CREATE TABLE `user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `groups_id` tinyint(4) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `groups_id` (`groups_id`)
);

INSERT INTO `user_groups` VALUES (1,1,1,0,NULL,NULL),(2,1,2,0,NULL,NULL),(3,2,1,0,NULL,NULL),(4,2,2,0,NULL,NULL),(5,2,3,0,NULL,NULL),(6,2,4,0,NULL,NULL),(7,3,1,1,NULL,NULL),(8,3,2,0,NULL,NULL);