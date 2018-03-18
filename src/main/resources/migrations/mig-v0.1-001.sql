-- liquibase formatted sql

-- changeset mcarabolante:1
CREATE TABLE `cuisine` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cuisine_name_uk` (`name` ASC));


-- changeset mcarabolante:2
ALTER TABLE `cuisine`
CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ;
