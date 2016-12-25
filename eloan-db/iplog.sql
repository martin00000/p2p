CREATE TABLE `iplog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) NOT NULL,
  `loginstate` tinyint(4) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `logininfoid` bigint(20) DEFAULT NULL,
  `logintype` tinyint(4) NOT NULL,
  `logintime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8