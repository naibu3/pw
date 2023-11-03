DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities` (
  `name` varchar(30) NOT NULL COMMENT 'This has to be unique',
  `educationallevel` varchar(8) NOT NULL COMMENT 'Allows only ''Children'', ''Youth'' or ''Teenager''',
  `timetable` varchar(10) NOT NULL COMMENT 'Allows only ''Morning'' or ''Afternoon''',
  `maxparticipants` int(11) NOT NULL,
  `monitorsrequired` int(11) NOT NULL,
  PRIMARY KEY (`name`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`educationallevel` in ('Children','Youth','Teenager')),
  CONSTRAINT `CONSTRAINT_2` CHECK (`timetable` in ('Morning','Afternoon'))
);

DROP TABLE IF EXISTS `activities_in_camps`;
CREATE TABLE `activities_in_camps` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' This table is here to do a Many to Many relationships between Activities and Camps ',
  `campid` int(11) NOT NULL,
  `activityname` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_campid_camps` (`campid`),
  KEY `fk_activityname_activities` (`activityname`),
  CONSTRAINT `fk_activityname_activities` FOREIGN KEY (`activityname`) REFERENCES `activities` (`name`),
  CONSTRAINT `fk_campid_camps` FOREIGN KEY (`campid`) REFERENCES `camps` (`id`)
);

DROP TABLE IF EXISTS `camps`;
CREATE TABLE `camps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `educationallevel` varchar(8) NOT NULL COMMENT 'Allows only ''Children'', ''Youth'' or ''Teenager''',
  `maxparticipants` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`educationallevel` in ('Children','Youth','Teenager'))
);

DROP TABLE IF EXISTS `monitors`;
CREATE TABLE `monitors` (
  `dni` varchar(9) NOT NULL,
  `name` varchar(20) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `specialeducator` varchar(3) NOT NULL COMMENT 'Allows ''Yes'' or ''No''',
  PRIMARY KEY (`dni`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`specialeducator` in ('Yes','No'))
);

DROP TABLE IF EXISTS `monitors_in_activity`;
CREATE TABLE `monitors_in_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'This table is here to do a Many to Many relationships between Monitors and Camps',
  `activityname` varchar(30) NOT NULL,
  `monitorid` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_monitorid_monitors` (`monitorid`),
  KEY `fk_activities_activityname` (`activityname`),
  CONSTRAINT `fk_activities_activityname` FOREIGN KEY (`activityname`) REFERENCES `activities` (`name`),
  CONSTRAINT `fk_monitorid_monitors` FOREIGN KEY (`monitorid`) REFERENCES `monitors` (`dni`)
);


DROP TABLE IF EXISTS `participants`;
CREATE TABLE `participants` (
  `dni` varchar(9) NOT NULL,
  `name` varchar(20) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `birthdate` date NOT NULL,
  `specialneeds` varchar(3) NOT NULL COMMENT 'Allows only ''Yes'' or ''No''',
  PRIMARY KEY (`dni`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`specialneeds` in ('Yes','No'))
);


DROP TABLE IF EXISTS `registrations`;
CREATE TABLE `registrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `participantid` varchar(9) NOT NULL,
  `campid` int(11) NOT NULL,
  `dateregistered` date NOT NULL,
  `price` float NOT NULL,
  `type` varchar(7) NOT NULL COMMENT 'It can be either ''Partial'' or ''Full''',
  `timetable` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_camp_camps` (`campid`),
  KEY `fk_participantid_participants` (`participantid`),
  CONSTRAINT `fk_camp_camps` FOREIGN KEY (`campid`) REFERENCES `camps` (`id`),
  CONSTRAINT `fk_participantid_participants` FOREIGN KEY (`participantid`) REFERENCES `participants` (`dni`),
  CONSTRAINT `CONSTRAINT_3` CHECK (`timetable` in ('Morning','Afternoon','All day'))
);
