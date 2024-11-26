Drop table if exists postgres.garmin_runs;
Drop sequence if exists postgres.garmin_runs_seq;
create sequence postgres.garmin_runs_seq start with 1 increment by 50;
CREATE TABLE IF NOT EXISTS postgres.garmin_runs
(
    id                   bigint      DEFAULT nextval('postgres.garmin_runs_seq') not null,
    activityID           numeric                                        not null,
    activity_date        text                                          not null,
    activity_type        text                                          not null,
    activity_name        text                                          not null,
    activity_description text,
    elapsed_time         text,
    distance             text                                          not null,
    max_heart_rate       text,
    calories             text                                          ,
    created_at           timestamp                                          NOT NULL,
    created_by           varchar(20)                                   NOT NULL,
    updated_at           timestamp        DEFAULT NULL,
    updated_by           varchar(20) DEFAULT NULL,
    primary key (id)
)
