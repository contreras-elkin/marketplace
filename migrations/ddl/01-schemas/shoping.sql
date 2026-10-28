--liquibase formatted sql

--changeset contreras-elkin:01-schemas
CREATE SCHEMA IF NOT EXISTS transactions;
CREATE SCHEMA IF NOT EXISTS catalog;
CREATE SCHEMA IF NOT EXISTS auth;
