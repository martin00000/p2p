CREATE TABLE `systemDictionaryItem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `tvalue` varchar(50) DEFAULT NULL,
  `sequence` tinyint(4) NOT NULL,
  `intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8