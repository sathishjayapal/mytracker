CREATE TABLE IF NOT EXISTS `strava_user` (`customer_id` int AUTO_INCREMENT  PRIMARY KEY, `name` varchar(100) NOT NULL,`email` varchar(100) NOT NULL,`phone_number` varchar(20) NOT NULL, `created_at` date NOT NULL, `created_by` varchar(20) NOT NULL, `updated_at` date DEFAULT NULL, `updated_by` varchar(20) DEFAULT NULL);
CREATE TABLE IF NOT EXISTS `strava_runs` (`customer_id` bigint NOT NULL, `run_number` bigint AUTO_INCREMENT  PRIMARY KEY,
`run_name` varchar(100) NOT NULL, `run_date` date NOT NULL, `miles` int NOT NULL, `start_location` long NOT NULL,
    `created_at` date NOT NULL, `created_by` varchar(20) NOT NULL, `updated_at` date DEFAULT NULL, `updated_by` varchar(20) DEFAULT NULL);
