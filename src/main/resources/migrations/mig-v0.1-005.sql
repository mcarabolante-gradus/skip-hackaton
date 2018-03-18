-- liquibase formatted sql

-- changeset mcarabolante:1
CREATE TABLE `session` (
  `token` varchar(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`token`),
  CONSTRAINT `session_customer_fk`
  FOREIGN KEY (`customer_id`)
  REFERENCES `customer` (`id`));


-- changeset mcarabolante:2
ALTER TABLE `session`
CHANGE COLUMN `token` `token` VARCHAR(128) NOT NULL ;
