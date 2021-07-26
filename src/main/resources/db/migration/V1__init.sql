create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title) values ('Food');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table orders
(
    id          bigserial primary key,
    number_order integer,
    quantity integer,
    price       numeric(8, 2) not null,
    product_id bigint references products (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price, category_id)
values
('Молоко', 85, 1),
('Хлеб', 25, 1),
('Сыр', 450, 1),
('Печенье', 55, 1),
('Халва', 125, 1),
('Курица', 350, 1);