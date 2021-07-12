--DROP TABLE products IF EXISTS;
--CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price integer, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('Молоко', 56);