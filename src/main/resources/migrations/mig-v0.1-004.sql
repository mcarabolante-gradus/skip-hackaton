-- liquibase formatted sql

-- changeset mcarabolante:1
CREATE TABLE `customer` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` CHAR(60) NOT NULL,
  PRIMARY KEY (`id`));

-- changeset mcarabolante:2
ALTER TABLE `customer`
ADD UNIQUE INDEX `cust_email_uk` (`email` ASC);
