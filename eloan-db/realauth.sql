CREATE TABLE `realauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `realname` varchar(50) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `birthDate` varchar(50) DEFAULT NULL,
  `idNumber` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `image1` varchar(255) NOT NULL,
  `image2` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `applyTime` datetime NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `applier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8