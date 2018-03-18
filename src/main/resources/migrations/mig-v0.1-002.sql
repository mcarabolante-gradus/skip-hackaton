-- liquibase formatted sql

-- changeset mcarabolante:1
CREATE TABLE `store` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `address` VARCHAR(300) NOT NULL,
  `cuisine_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `store_cuisine_fk`
    FOREIGN KEY (`cuisine_id`)
    REFERENCES `cuisine` (`id`));

