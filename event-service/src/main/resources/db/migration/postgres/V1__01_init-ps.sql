Drop sequence if exists runeventsprojectschema.event_id_seq cascade;
Drop sequence if exists runeventsprojectschema.domain_id_seq cascade;
Drop table if exists runeventsprojectschema.domain_events cascade;
drop table if exists runeventsprojectschema.domains cascade;

create sequence  runeventsprojectschema.event_id_seq start with 1 increment by 50;
create sequence  runeventsprojectschema.domain_id_seq start with 1 increment by 50;

create table  runeventsprojectschema.domains
(
    id                        bigint default nextval('runeventsprojectschema.domain_id_seq') not null,
    domain_name               text not null unique,
    status                    text not null,
    comments                  text,
    created_at                timestamp,
    updated_at                timestamp,
    primary key (id)
);



create table runeventsprojectschema.domain_events
(
    id          bigint      default nextval('runeventsprojectschema.event_id_seq'::regclass) not null
        primary key,
    domain_name text                                                                         not null
        references runeventsprojectschema.domains (domain_name),
    event_id    text                                                                         not null,
    event_type  text                                                                         not null,
    payload     text                                                                         not null,
    created_at  timestamp                                                                    not null,
    updated_at  timestamp                                                                    not null,
    created_by  varchar(20)                                                                  not null,
    updated_by  varchar(20) default NULL::character varying                                  not null,
    unique (domain_name, event_type, event_id)
);
alter table runeventsprojectschema.domains add column created_by varchar(20) not null;
alter table runeventsprojectschema.domains add column updated_by varchar(20) not null default NULL;

CREATE OR REPLACE FUNCTION runeventsprojectschema.notify_insert() RETURNS TRIGGER AS $$
BEGIN
    PERFORM pg_notify('new_insert', row_to_json(NEW)::text);
    RETURN NEW;
END;

$$ LANGUAGE plpgsql;
CREATE TRIGGER domain_events_table_trigger AFTER INSERT ON runeventsprojectschema.domain_events FOR EACH ROW EXECUTE FUNCTION runeventsprojectschema.notify_insert();
