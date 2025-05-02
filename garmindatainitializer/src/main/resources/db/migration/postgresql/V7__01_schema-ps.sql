Drop sequence if exists runs_schema.garmin_runs_seq CASCADE;
Drop table if exists runs_schema.garmin_runs CASCADE;
Drop sequence if exists runs_schema.garmin_runs_seq CASCADE;
drop schema if exists runs_schema CASCADE;
CREATE SCHEMA runs_schema;
create sequence runs_schema.garmin_runs_seq start with 1 increment by 50;
CREATE TABLE IF NOT EXISTS runs_schema.garmin_runs
(
    id                   bigint      DEFAULT nextval('runs_schema.garmin_runs_seq') not null,
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
);
CREATE TABLE  IF NOT EXISTS runs_schema.shedlock(
  name text NOT NULL,
  lock_until timestamp NOT NULL,
  locked_at timestamp NOT NULL,
  locked_by text NOT NULL,
  PRIMARY KEY (name)
);
CREATE TABLE IF NOT EXISTS runs_schema.file_name_tracker
(
    id bigint DEFAULT nextval('runs_schema.garmin_runs_seq') not null,
    filename text not null,
    created_at timestamp NOT NULL,
    created_by varchar(40) NOT NULL,
    primary key (id)
)

