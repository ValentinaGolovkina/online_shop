create table products (id bigserial primary key, title varchar(255), price int);
insert into products (title, price)
values
('Молоко', 85),
('Хлеб', 25),
('Сыр', 450),
('Печенье', 55),
('Халва', 125),
('Курица', 350);