create table Product (
    product_id varchar(36) not null,
    name varchar(255) not null,
    description text,
    price decimal(10, 2) not null,
    primary key (product_id)
);

INSERT INTO Product (product_id, name, description, price) VALUES ('123', 'Consolo GG', 'Penis bem grande e grosso', 79.99)