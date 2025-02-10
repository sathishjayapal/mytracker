Drop table if exists garminrunsschema.garmin_runs;
Drop sequence if exists garminrunsschema.garmin_runs_seq;
create sequence garminrunsschema.garmin_runs_seq start with 1 increment by 50;
CREATE TABLE IF NOT EXISTS garminrunsschema.garmin_runs
(
    id                   bigint      DEFAULT nextval('garminrunsschema.garmin_runs_seq') not null,
    activityID           bigint                                        not null,
    activity_date        text                                          not null,
    activity_type        text                                          not null,
    activity_name        text                                          not null,
    activity_description text,
    elapsed_time         text,
    distance             text                                          not null,
    max_heart_rate       text,
    calories             text                                          ,
    created_at           date                                          NOT NULL,
    created_by           varchar(20)                                   NOT NULL,
    updated_at           date        DEFAULT NULL,
    updated_by           varchar(20) DEFAULT NULL,
    primary key (id)
)
