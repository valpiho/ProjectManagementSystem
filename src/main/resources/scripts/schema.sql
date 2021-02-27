CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `project` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `created_at` datetime(6) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `end_date` datetime(6) DEFAULT NULL,
                           `project_name` varchar(255) DEFAULT NULL,
                           `start_date` datetime(6) DEFAULT NULL,
                           `status` varchar(11) DEFAULT NULL,
                           `updated_at` datetime(6) DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `project_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`),
                           KEY `FK4a06bn71vioqip4r57hpqg3hm` (`project_id`),
                           CONSTRAINT `FK4a06bn71vioqip4r57hpqg3hm` FOREIGN KEY (`project_id`) REFERENCES `user` (`id`),
                           CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `task` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `created_at` datetime(6) DEFAULT NULL,
                        `priority` varchar(6) DEFAULT NULL,
                        `status` varchar(11) DEFAULT NULL,
                        `task_description` varchar(255) DEFAULT NULL,
                        `title` varchar(255) DEFAULT NULL,
                        `updated_at` datetime(6) DEFAULT NULL,
                        `project_id` bigint DEFAULT NULL,
                        `user_id` bigint DEFAULT NULL,
                        `task_id` bigint DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FKk8qrwowg31kx7hp93sru1pdqa` (`project_id`),
                        KEY `FK2hsytmxysatfvt0p1992cw449` (`user_id`),
                        KEY `FKae1mroqa689kinqybv3bcauv` (`task_id`),
                        CONSTRAINT `FK2hsytmxysatfvt0p1992cw449` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                        CONSTRAINT `FKae1mroqa689kinqybv3bcauv` FOREIGN KEY (`task_id`) REFERENCES `user` (`id`),
                        CONSTRAINT `FKk8qrwowg31kx7hp93sru1pdqa` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user` (
                        `id` bigint NOT NULL,
                        `authorities` tinyblob,
                        `created_at` datetime(6) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `first_name` varchar(255) DEFAULT NULL,
                        `is_enabled` bit(1) NOT NULL,
                        `last_name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `role` varchar(255) DEFAULT NULL,
                        `updated_at` datetime(6) DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `users_projects` (
                                  `user_id` bigint NOT NULL,
                                  `project_id` bigint NOT NULL,
                                  KEY `FK5gka63hbj8siyiahiqs0kupe2` (`project_id`),
                                  KEY `FK5tvof3bxcwalwr5y11jo3fpn0` (`user_id`),
                                  CONSTRAINT `FK5gka63hbj8siyiahiqs0kupe2` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
                                  CONSTRAINT `FK5tvof3bxcwalwr5y11jo3fpn0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

