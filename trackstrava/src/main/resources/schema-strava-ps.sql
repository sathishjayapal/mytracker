CREATE TABLE IF NOT EXISTS runs_schema.strava_runs (customer_id bigint NOT NULL, run_number bigint PRIMARY KEY,
run_name varchar(100) NOT NULL, run_date date NOT NULL, miles int NOT NULL, start_location bigint NOT NULL,
    created_at date NOT NULL, created_by varchar(20) NOT NULL, updated_at date DEFAULT NULL,
                                        updated_by varchar(20) DEFAULT NULL);
