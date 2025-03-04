
Drop sequence if exists garminrunsschema.garmin_runs_seq CASCADE;
Drop table if exists garminrunsschema.garmin_runs CASCADE;
drop sequence if exists garminrunsschema.file_name_tracker_seq CASCADE;
Drop table if exists garminrunsschema.file_name_tracker CASCADE;
drop table if exists garminrunsschema.shedlock CASCADE;
drop schema if exists garminrunsschema CASCADE;


CREATE SCHEMA garminrunsschema;
create sequence garminrunsschema.garmin_runs_seq start with 1 increment by 50;
create sequence garminrunsschema.file_name_tracker_seq start with 1 increment by 50;
CREATE TABLE IF NOT EXISTS garminrunsschema.garmin_runs
(
    id                   bigint      DEFAULT nextval('garminrunsschema.garmin_runs_seq') not null,
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
CREATE TABLE  IF NOT EXISTS garminrunsschema.shedlock(
  name text NOT NULL,
  lock_until timestamp NOT NULL,
  locked_at timestamp NOT NULL,
  locked_by text NOT NULL,
  PRIMARY KEY (name)
);
CREATE TABLE IF NOT EXISTS garminrunsschema.file_name_tracker
(
    id bigint DEFAULT nextval('garminrunsschema.file_name_tracker_seq') not null,
    filename text not null,
    created_at timestamp NOT NULL,
    created_by varchar(40) NOT NULL,
    updated_at           timestamp        DEFAULT NULL,
    updated_by           varchar(20) DEFAULT NULL,
    primary key (id)
)

