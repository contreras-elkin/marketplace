--liquibase formatted sql

--changeset contreras-elkin:02-orders
CREATE TABLE transactions.orders(
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    buyer_id UUID NOT NULL,
    unit_price BIGINT NOT NULL CHECK(unit_price > 0),
    quantity INTEGER NOT NULL CHECK(quantity > 0),
    amount BIGINT NOT NULL CHECK(amount > 0),
    currency varchar(3) NOT NULL DEFAULT('COP'),
    status varchar(50) NOT NULL DEFAULT('PENDING'),
    CHECK(status IN('PENDING', 'PAID', 'CANCELLED')),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);
