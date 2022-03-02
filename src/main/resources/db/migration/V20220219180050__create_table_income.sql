CREATE TABLE IF NOT EXISTS `income`
(
    `id`         bigint(20)     NOT NULL AUTO_INCREMENT,
    `uid`        bigint(20)     NOT NULL,
    `order_id`   bigint(20)     NOT NULL,
    `benefit`    decimal(10, 4) NOT NULL DEFAULT 0,
    `created_at` datetime       NOT NULL,
    `updated_at` datetime       NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_uid` (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
