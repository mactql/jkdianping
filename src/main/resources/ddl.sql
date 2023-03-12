CREATE TABLE `dianpingdb`.`user`  (
      `id` int NOT NULL AUTO_INCREMENT,
      `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
      `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
      `telphone` varchar(40) NOT NULL DEFAULT '',
      `password` varchar(200) NOT NULL DEFAULT '',
      `nick_name` varchar(40) NOT NULL DEFAULT '',
      `gender` int NULL DEFAULT 0,
      PRIMARY KEY (`id`),
      UNIQUE INDEX `telphone_unique-Index`(`telphone`) USING BTREE
);

CREATE TABLE `dianpingdb`.`Untitled`  (
                                          `id` int NOT NULL AUTO_INCREMENT,
                                          `name` varchar(255) NOT NULL DEFAULT '',
                                          `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
                                          `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
                                          `remark_score` decimal(2, 1) NOT NULL DEFAULT 0,
                                          `disabled_flag` int NULL DEFAULT 0,
                                          PRIMARY KEY (`id`)
);

CREATE TABLE `dianpingdb`.`category`  (
                                          `id` int NOT NULL AUTO_INCREMENT,
                                          `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
                                          `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
                                          `name` varchar(255) NOT NULL DEFAULT '',
                                          `icon_url` varchar(255) NULL DEFAULT '',
                                          `sort` int NULL DEFAULT 0,
                                          PRIMARY KEY (`id`),
                                          UNIQUE INDEX `name_unique_index`(`name`) USING BTREE
);
