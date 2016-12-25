CREATE TABLE `accountflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountActionType` tinyint(4) NOT NULL,
  `amount` decimal(18,4) NOT NULL,
  `note` varchar(255) NOT NULL,
  `balance` decimal(18,4) NOT NULL,
  `freezed` decimal(18,4) NOT NULL,
  `actionTime` datetime NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8