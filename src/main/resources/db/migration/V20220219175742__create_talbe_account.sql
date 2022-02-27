CREATE TABLE `account`
(
    `id`         bigint(20)     NOT NULL AUTO_INCREMENT,
    `uid`        bigint(20)     NOT NULL,
    `properties` decimal(10, 4) NOT NULL DEFAULT 0,
    `status`     varchar(10)    NOT NULL,
    `created_at` datetime       NOT NULL,
    `updated_at` datetime       NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_uid` (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
