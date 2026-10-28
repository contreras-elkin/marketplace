--liquibase formatted sql

--changeset contreras-elkin:02-users
CREATE TABLE auth.users(
    id UUID PRIMARY KEY,
    name varchar(250) NOT NULL,
    email varchar(250) NOT NULL UNIQUE,
    phone_number varchar(20) NOT NULL,
    role varchar(20) NOT NULL,
    CHECK(role IN('PRODUCER', 'BUYER')),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);
