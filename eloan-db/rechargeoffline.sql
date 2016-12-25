CREATE TABLE `rechargeoffline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `applyTime` datetime NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `applier_id` bigint(20) NOT NULL,
  `tradeCode` varchar(255) NOT NULL,
  `tradeTime` datetime NOT NULL,
  `amount` decimal(18,4) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `bankinfo_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8