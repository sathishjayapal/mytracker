Drop sequence if exists garmin_runs_seq;
create sequence garmin_runs_seq start with 1 increment by 50;

CREATE TABLE IF NOT EXISTS garmin_runs (id bigint DEFAULT nextval('garmin_runs_seq') not null,
                                        activityID text not null,
                                        activity_date text not null,
                                        activity_type text not null,
                                        activity_name text not null,
                                        activity_description text not null,
                                        elapsed_time text,
                                        distance text not null,
                                        max_heart_rate text,
                                        calories text not null,
                                        primary key (id));
