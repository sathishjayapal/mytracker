CREATE TABLE shedlock(
     name VARCHAR(64) NOT NULL,
     lock_until TIMESTAMP NOT NULL,
     locked_at TIMESTAMP NOT NULL,
     locked_by VARCHAR(255) NOT NULL,
     PRIMARY KEY (name)
);
Drop table if exists garmin_runs;
Drop sequence if exists garmin_runs_seq;
create sequence garmin_runs_seq start with 1 increment by 50;
CREATE TABLE IF NOT EXISTS garmin_runs
(
    id                   bigint      DEFAULT nextval('garmin_runs_seq') not null,
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
    created_by           varchar(40)                                   NOT NULL,
    updated_at           timestamp        DEFAULT NULL,
    updated_by           varchar(40) DEFAULT NULL,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS file_name_tracker
(
    id bigint DEFAULT nextval('garmin_runs_seq') not null,
    filename text not null,
    created_at timestamp NOT NULL,
    created_by varchar(40) NOT NULL,
    primary key (id)
)
