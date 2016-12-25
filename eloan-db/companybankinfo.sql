CREATE TABLE `companybankinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bankname` varchar(255) DEFAULT NULL,
  `accountname` varchar(255) DEFAULT NULL,
  `banknumber` varchar(255) DEFAULT NULL,
  `bankforkname` varchar(255) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8