DROP DATABASE  IF EXISTS `enappwebshop`;

CREATE DATABASE `enappwebshop`;
 
use enappwebshop;

CREATE TABLE `enappwebshop`.`product` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'contain surname and last name',
  `description` VARCHAR(45),
  `mediapath` VARCHAR(180) COMMENT 'relative path to the media file',
  `unitprice` DECIMAL,
  PRIMARY KEY (`id`)
);
 
CREATE TABLE `enappwebshop`.`customer` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(15) NOT NULL  COMMENT 'have to be unique',
  `password` VARCHAR(32) COMMENT 'md5 hash',
  `name` VARCHAR(45),
  `address` VARCHAR(45),
  `email` VARCHAR(90),
  `navcustomerno` VARCHAR(30),
  PRIMARY KEY (`id`)
);
 
CREATE TABLE `enappwebshop`.`purchase` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` INTEGER UNSIGNED NOT NULL,
  `datetime` DATETIME COMMENT 'Date / Time of purchase',
  `status` VARCHAR(15) COMMENT 'state of purchase',
  `corrid` VARCHAR(50) COMMENT 'correlation id',
  PRIMARY KEY (`id`),
foreign key (`customerid`) 
	references `customer` (`id`)
);
 
CREATE TABLE `enappwebshop`.`purchaseitem` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `purchaseid` INTEGER UNSIGNED NOT NULL,
  `productno` VARCHAR(50) NULL,
  `quantity` DECIMAL COMMENT 'by a mp3 shop, generally one',
  `unitprice` DECIMAL,
  `lineamount` DECIMAL COMMENT 'total cost per line',
  `description` VARCHAR(90) COMMENT 'line description f.e. a comment',
  PRIMARY KEY (`id`),
foreign key (`purchaseid`)
	references `purchase` (`id`)
);

-- GROUPS
CREATE TABLE customergroups (
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	username VARCHAR(15) NOT NULL,
	groupname VARCHAR(15) NOT NULL,
	PRIMARY KEY (`id`)
);

