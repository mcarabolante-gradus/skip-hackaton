-- liquibase formatted sql

-- changeset mcarabolante:1
CREATE TABLE `order_item` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` INT(10) UNSIGNED NOT NULL,
  `product_id` INT(10) UNSIGNED NOT NULL,
  `price` DECIMAL(15,2) NOT NULL,
  `quantity` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_item_1_idx` (`order_id` ASC),
  INDEX `ord_item_prod_fk_idx` (`product_id` ASC),
  CONSTRAINT `ord_item_ord_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`),
  CONSTRAINT `ord_item_prod_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`));
