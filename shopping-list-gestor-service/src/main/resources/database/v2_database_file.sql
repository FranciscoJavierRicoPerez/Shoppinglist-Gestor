-- db_shoppinglist_gestor.shoppinglist_item definition

CREATE TABLE `shoppinglist_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assignation_list_date` datetime(6) DEFAULT NULL,
  `calculated_price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `shoppinglist_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe6ei2b7vh22opfgq96dmv046` (`shoppinglist_id`),
  CONSTRAINT `FKe6ei2b7vh22opfgq96dmv046` FOREIGN KEY (`shoppinglist_id`) REFERENCES `shoppinglist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;