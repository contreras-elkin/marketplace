--liquibase formatted sql

--changeset contreras-elkin:02-products
CREATE TABLE catalog.products(
    id UUID PRIMARY KEY,
    name varchar(100) NOT NULL,
    unit varchar(20) NOT NULL,
    CHECK(unit IN('KILOGRAMO', 'LIBRA', 'ARROBA', 'BULTO', 'CANASTA', 'CAJA', 'DOCENA', 'MANOJO', 'LITRO', 'UNIDAD')),
    price_unit BIGINT NOT NULL CHECK(price_unit > 0),
    stock INTEGER NOT NULL CHECK(stock >= 0),
    municipality varchar(100) NOT NULL,
    id_producer UUID,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);
