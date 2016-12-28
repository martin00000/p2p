CREATE TABLE `logininfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

  ALTER TABLE logininfo ADD (`usertype` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '用户类型：0（平台用户）1（后台操作员）',
    `admin` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否为管理员:1是，0不是');