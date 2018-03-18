-- liquibase formatted sql

-- changeset mcarabolante:1
CREATE TABLE `orders` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` INT(10) UNSIGNED NOT NULL,
  `delivery_address` VARCHAR(300) NOT NULL,
  `contact` VARCHAR(150) NOT NULL,
  `store_id` INT(10) UNSIGNED NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `order_cust_fk_idx` (`customer_id` ASC),
  INDEX `order_store_fk_idx` (`store_id` ASC),
  CONSTRAINT `order_cust_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`),
  CONSTRAINT `order_store_fk`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`));
