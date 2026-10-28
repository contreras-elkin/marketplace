--liquibase formatted sql

--changeset contreras-elkin:03-fk-payments-orders
ALTER TABLE transactions.payments
ADD CONSTRAINT fk_payments_orders
FOREIGN KEY (id_order)
REFERENCES transactions.orders(id);

CREATE INDEX idx_payments_id_order ON transactions.payments(id_order);
