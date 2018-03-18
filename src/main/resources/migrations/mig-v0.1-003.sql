-- liquibase formatted sql

-- changeset mcarabolante:1
CREATE TABLE `product` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `store_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_store_fk`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`));

-- changeset mcarabolante:2
ALTER TABLE `product`
ADD COLUMN `price` DECIMAL(15, 2) NOT NULL AFTER `store_id`;

