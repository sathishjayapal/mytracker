CREATE TABLE IF NOT EXISTS `garmin_runs` (`id` bigint AUTO_INCREMENT  PRIMARY KEY,
                                          `run_name` varchar(100) NOT NULL, `run_date` date NOT NULL,`miles` INTEGER NOT NULL,
    `created_at` date NOT NULL, `created_by` varchar(20) NOT NULL, `updated_at` date DEFAULT NULL, `updated_by` varchar(20) DEFAULT NULL);
