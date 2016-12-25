CREATE TABLE `userinfo` (
  `id` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `bitState` bigint(20) NOT NULL,
  `realName` varchar(30) DEFAULT NULL,
  `idNumber` varchar(30) DEFAULT NULL,
  `phoneNumber` varchar(30) DEFAULT NULL,
  `incomeGrade_id` bigint(20) DEFAULT NULL,
  `marriage_id` bigint(20) DEFAULT NULL,
  `kidCount_id` bigint(20) DEFAULT NULL,
  `educationBackground_id` bigint(20) DEFAULT NULL,
  `houseCondition_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8