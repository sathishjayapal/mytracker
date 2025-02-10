Drop table if exists test_db.sathish_run_data;
CREATE TABLE IF NOT EXISTS test_db.sathish_run_data
(
    id          bigint      DEFAULT nextval('test_db.sathish_runs_seq') not null,
    total_miles decimal                                                 not null,
    primary key (id),
    created_at  timestamp default (now() at time zone 'utc') NOT NULL,
    created_by  varchar(20) NOT NULL,
    updated_at  timestamp default (now() at time zone 'utc') NOT NULL,
    updated_by  varchar(20) NOT NULL
);
