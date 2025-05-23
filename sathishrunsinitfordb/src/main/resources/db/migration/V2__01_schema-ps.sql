Drop sequence if exists runs_schema.garmin_runs_seq CASCADE;
Drop table if exists runs_schema.garmin_runs CASCADE;
drop sequence if exists runs_schema.file_name_tracker_seq CASCADE;
Drop table if exists runs_schema.file_name_tracker CASCADE;
drop table if exists runs_schema.shedlock CASCADE;
create sequence runs_schema.garmin_runs_seq start with 1 increment by 50;
create sequence runs_schema.file_name_tracker_seq start with 1 increment by 50;
CREATE TABLE runs_schema.users
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    name       VARCHAR(100) NOT NULL,
    role       VARCHAR(20)  NOT NULL DEFAULT 'ROLE_USER',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS runs_schema.garmin_runs
(
    id                   bigint      DEFAULT nextval('runs_schema.garmin_runs_seq') not null,
    activityID           numeric                                        not null,
    activity_date        text                                          not null,
    activity_type        text                                          not null,
    activity_name        text                                             not null,
    activity_description text,
    elapsed_time         text,
    distance             text                                          not null,
    max_heart_rate       text,
    calories             text                                          ,
    created_at           timestamp                                          NOT NULL,
    created_by           BIGINT                                   NOT NULL,
    updated_at           timestamp        DEFAULT NULL,
    updated_by           varchar(40) DEFAULT NULL,
    primary key (id),
    CONSTRAINT fk_run_users FOREIGN KEY (created_by) REFERENCES runs_schema.users (id)
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
    id bigint DEFAULT nextval('runs_schema.file_name_tracker_seq') not null,
    file_name text not null,
    created_at timestamp NOT NULL,
    created_by           BIGINT                                   NOT NULL,
    updated_at           timestamp        DEFAULT NULL,
    updated_by           varchar(40) DEFAULT NULL,
    primary key (id),
    CONSTRAINT fk_file_name_tracker FOREIGN KEY (created_by) REFERENCES runs_schema.users (id)
);

