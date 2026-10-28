--liquibase formatted sql

--changeset contreras-elkin:02-payments
CREATE TABLE transactions.payments(
    id UUID PRIMARY KEY,
    id_order UUID NOT NULL,
    reference varchar(255) NOT NULL UNIQUE,
    amount BIGINT NOT NULL CHECK(amount > 0),
    currency varchar(3) NOT NULL,
    status varchar(50) NOT NULL DEFAULT('PENDING'),
    CHECK(status IN('PENDING', 'APPROVED', 'DECLINED', 'VOIDED', 'ERROR')),
    gateway_transaction_id varchar(255) UNIQUE,
    payment_method_type varchar(50),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);
