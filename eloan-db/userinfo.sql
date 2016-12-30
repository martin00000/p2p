CREATE TABLE `userinfo` (
  `id`                     BIGINT(20) NOT NULL,
  `version`                INT(11)    NOT NULL,
  `bitState`               BIGINT(20) NOT NULL,
  `realName`               VARCHAR(30) DEFAULT NULL,
  `idNumber`               VARCHAR(30) DEFAULT NULL,
  `phoneNumber`            VARCHAR(30) DEFAULT NULL,
  `incomeGrade_id`         BIGINT(20)  DEFAULT NULL,
  `marriage_id`            BIGINT(20)  DEFAULT NULL,
  `kidCount_id`            BIGINT(20)  DEFAULT NULL,
  `educationBackground_id` BIGINT(20)  DEFAULT NULL,
  `houseCondition_id`      BIGINT(20)  DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8

ALTER TABLE eloan.userinfo
  ADD (`email` VARCHAR(255) DEFAULT NULL
COMMENT '邮箱', `authScore` INT DEFAULT 0
COMMENT '用户当前认证分数', `realauthId` BIGINT DEFAULT NULL
COMMENT '用户有效的实名认证对象')