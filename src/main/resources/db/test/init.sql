DROP TABLE IF EXISTS `account`;

CREATE TABLE `account`
(
    `id`         bigint(20)     NOT NULL AUTO_INCREMENT,
    `uid`        bigint(20)     NOT NULL,
    `properties` decimal(10, 4) NOT NULL DEFAULT 0,
    `status`     varchar(10)    NOT NULL,
    `created_at` datetime       NOT NULL,
    `updated_at` datetime       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `income`;

CREATE TABLE `income`
(
    `id`         bigint(20)     NOT NULL AUTO_INCREMENT,
    `uid`        bigint(20)     NOT NULL,
    `order_id`   bigint(20)     NOT NULL,
    `benefit`    decimal(10, 4) NOT NULL DEFAULT 0,
    `created_at` datetime       NOT NULL,
    `updated_at` datetime       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `withdraw`;

CREATE TABLE `withdraw`
(
    `id`         bigint(20)     NOT NULL AUTO_INCREMENT,
    `uid`        bigint(20)     NOT NULL,
    `amount`     decimal(10, 4) NOT NULL DEFAULT 0,
    `created_at` datetime       NOT NULL,
    `updated_at` datetime       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;