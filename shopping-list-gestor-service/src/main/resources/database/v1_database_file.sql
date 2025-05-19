-- db_shoppinglist_gestor.shoppinglist definition

CREATE TABLE `shoppinglist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `close_date` datetime(6) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `total_price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;