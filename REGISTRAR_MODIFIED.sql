DROP DATABASE IF EXISTS registrar;

CREATE DATABASE registrar;

USE registrar;


DROP TABLE IF EXISTS `student`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `student` (
  `studentID` int(10) NOT NULL AUTO_INCREMENT,
  
`firstName` varchar(20) NOT NULL,
  
`lastName` varchar(20) NOT NULL,
  
`gpa` float NOT NULL,
  
`status` varchar(15) NOT NULL,
  
`mentor` varchar(20) NOT NULL,
  
`level` varchar(10) NOT NULL DEFAULT '',
  
`thesisTitle` varchar(60) NOT NULL DEFAULT '',
  
`thesisAdvisor` varchar(20) NOT NULL DEFAULT '',
  
`company` varchar(20) NOT NULL DEFAULT '',
  
PRIMARY KEY (`studentID`)
);




