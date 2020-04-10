CREATE TABLE if not exists category
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL
);
INSERT INTO category (id, title) VALUES (DEFAULT, 'book');
INSERT INTO category (id, title) VALUES (DEFAULT, 'food');
INSERT INTO category (id, title) VALUES (DEFAULT, 'pc');
INSERT INTO category (id, title) VALUES (DEFAULT, 'laptop');
INSERT INTO category (id, title) VALUES (DEFAULT, 'smartphone');
INSERT INTO category (id, title) VALUES (DEFAULT, 'wear');

CREATE TABLE roles (
    id SERIAL PRIMARY KEY ,
    role VARCHAR(30) NOT NULL
);
INSERT INTO roles (id, role) VALUES (DEFAULT, 'admin');
INSERT INTO roles (id, role) VALUES (DEFAULT, 'user');

CREATE TABLE delivery (
    id SERIAL primary key ,
    method varchar(50) not null
);
INSERT INTO delivery (id, method) values (DEFAULT, 'POST');
INSERT INTO delivery (id, method) values (DEFAULT, 'PICKUP');
INSERT INTO delivery (id, method) values (DEFAULT, 'COURIER');

CREATE TABLE orderStatus (
    id SERIAL primary key NOT NULL ,
    status VARCHAR(30) NOT NULL
);
INSERT INTO orderStatus (id, status) VALUES (DEFAULT, 'CREATED');
INSERT INTO orderStatus (id, status) VALUES (DEFAULT, 'APPROVED');
INSERT INTO orderStatus (id, status) VALUES (DEFAULT, 'AWAITING_PAYMENT');
INSERT INTO orderStatus (id, status) VALUES (DEFAULT, 'AWAITING_SHIPMENT');
INSERT INTO orderStatus (id, status) VALUES (DEFAULT, 'SHIPPED');
INSERT INTO orderStatus (id, status) VALUES (DEFAULT, 'DELIVERED');

CREATE TABLE paymentMethod (
    id SERIAL primary key NOT NULL ,
    p_method VARCHAR(50) NOT NULL
);
INSERT INTO paymentMethod (id, p_method) VALUES (DEFAULT, 'CASH');
INSERT INTO paymentMethod (id, p_method) VALUES (DEFAULT, 'CARD_PAYMENT');

CREATE TABLE paymentState (
    id SERIAL primary key NOT NULL ,
    p_state VARCHAR(50) NOT NULL
);
INSERT INTO paymentState (id, p_state) VALUES (DEFAULT, 'AWAITING_PAYMENT');
INSERT INTO paymentState (id, p_state) VALUES (DEFAULT, 'BEING_PROCESSED');
INSERT INTO paymentState (id, p_state) VALUES (DEFAULT, 'COMPLETED');
INSERT INTO paymentState (id, p_state) VALUES (DEFAULT, 'CANCELED');
INSERT INTO paymentState (id, p_state) VALUES (DEFAULT, 'FROZEN');
INSERT INTO paymentState (id, p_state) VALUES (DEFAULT, 'ERROR');

CREATE TABLE product (
    id SERIAL PRIMARY KEY NOT NULL ,
    title VARCHAR(50) NOT NULL ,
    category_id INTEGER REFERENCES category(id) NOT NULL,
    brand VARCHAR(30) NOT NULL ,
    color VARCHAR(30) NOT NULL ,
    weight DEC NOT NULL ,
    price NUMERIC(6, 2) NOT NULL ,
    description VARCHAR(100) ,
    count INTEGER not null
);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'War and piece', 1, 'Eksmo', 'Black', 2.44, 15.55, 'Cool book', 100);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Mein Kampf', 1, 'Adolf inc.', 'Red', 12.80, 65.30, 'Bad book', 5);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Snickers', 2, 'Coca-cola', 'Brown', 0.90, 0.5, 'Chokolate delicoius', 1000);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Banana', 2, 'Equador banana', 'Yellow', 0.90, 0.10, 'Fresh fruit', 10000);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'DELL Vostro 7700k', 3, 'DELL', 'Silver', 20.50, 450.00, 'best PC', 5);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'MacBook air', 4, 'Apple', 'Silver', 70.50, 1450.00, 'none', 2);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'ZenBook earth', 4, 'Asus', 'Black', 90.30, 850.00, 'none', 25);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Iphone XR', 5, 'Apple', 'Silver', 10.50, 1250.00, 'none', 14);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Mate 20', 5, 'Huawei', 'Blue', 10.00, 450.00, 'none', 6);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Note 5', 5, 'Xiaomi', 'Black', 8.00, 50.00, 'топ за свои деньги', 6000);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Kingman air', 6, 'Nike', 'Black', 870.50, 200.00, 'none', 21);
INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (
'Wd 623', 6, 'New Balance', 'Blue', 950.50, 150.00, 'none', 22);

CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    lastName VARCHAR(50) not null ,
    birthDate date NOT NULL ,
    role VARCHAR(50) not null,
    mail VARCHAR(50) NOT NULL ,
    password VARCHAR(50) not null
);
INSERT INTO users (name, lastName, birthDate, role, mail, password) VALUES (
'Aleksandr', 'Fedorov', '12/09/1918', 'ADMIN', 'kajuga@mail.ru', '111');
INSERT INTO users (name, lastName, birthDate, role, mail, password) VALUES (
'Polux', 'Abramovich', '13/05/1935', 'USER', 'polux@mail.ru', '222');
INSERT INTO users (name, lastName, birthDate, role, mail, password) VALUES (
'Olga', 'Fedorova', '14/06/1925', 'USER', 'olga@mail.ru', '333');


CREATE TABLE address (
  id SERIAL PRIMARY KEY not null ,
  user_id INTEGER not null REFERENCES users(id) ON DELETE CASCADE ,
  country VARCHAR(30) NOT NULL ,
  city VARCHAR(30) NOT NULL ,
  postcode INTEGER ,
  street VARCHAR(30) NOT NULL ,
  houseNumber VARCHAR(30) NOT NULL ,
  room VARCHAR(30) NOT NULL ,
  phone VARCHAR(30) NOT NULL
);
INSERT into address (user_id, country, city, postcode, street, houseNumber, room, phone)
VALUES (1, 'Germany', 'Weissenfels', 8442, 'Konig Schtrasse', '21k', '12', '+79195456762');
INSERT into address (user_id, country, city, postcode, street, houseNumber, room, phone)
VALUES (3, 'Russia', 'Voroneszh', 3141, 'Yamnzya pustosh', '13k', '13', '+7902020202');
INSERT into address (user_id, country, city, postcode, street, houseNumber, room, phone)
VALUES (2, 'Israel', 'Keona', 22342, 'Abbey Street', '48', '23', '+734453562224');

CREATE TABLE orders (
    id SERIAL PRIMARY KEY not null ,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE ,
    address_id INTEGER NOT NULL REFERENCES address(id) ON DELETE CASCADE ,
    paymentMethod varchar (50) NOT NULL ,
    deliveryMethod varchar (50) NOT NULL ,
    paymentState varchar (50) DEFAULT 'AWAITING_PAYMENT' NOT NULL ,
    orderStatus varchar (50) DEFAULT 'CREATED' NOT NULL ,
    orderCost NUMERIC(9, 2)
);
INSERT INTO orders (user_id, address_id, paymentMethod, deliveryMethod, paymentState, orderStatus, orderCost)
VALUES (1, 1, 'CASH', 'POST', 'AWAITING_PAYMENT', 'CREATED', 100);
INSERT INTO orders (user_id, address_id, paymentMethod, deliveryMethod, paymentState, orderStatus, orderCost)
VALUES (2, 2, 'CARD_PAYMENT', 'COURIER', 'BEING_PROCESSED', 'APPROVED', 200);
INSERT INTO orders (user_id, address_id, paymentMethod, deliveryMethod, paymentState, orderStatus, orderCost)
VALUES (3, 3, 'CASH', 'PICKUP', 'AWAITING_PAYMENT', 'CREATED', 20);

CREATE TABLE productInCart (
    id SERIAL PRIMARY KEY not null ,
    order_id INTEGER NOT NULL REFERENCES orders(id) ON DELETE CASCADE ,
    product_id INTEGER NOT NULL REFERENCES product(id),
    price numeric (9, 2) not null,
    count INTEGER NOT NULL
);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (1, 1, 15.55, 3);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (1, 2, 65.30, 2);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (1, 5, 450.00, 1);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (2, 6, 750.00, 1);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (2, 8, 950.00, 1);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (2, 10, 150.00, 2);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (3, 3, 0.5, 10);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (3, 4, 0.10, 12);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (3, 7, 850.00, 1);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (3, 9, 50.00, 2);
INSERT INTO productInCart (order_id, product_id, price, count) VALUES (3, 10, 200.00, 1);



SELECT p.id, p.title, p.category_id, c.title as category_title, p.brand, p.color, p.weight, p.price, p.description, p.count
FROM product as p LEFT JOIN category as c ON p.category_id = c.id WHERE p.id=1;

SELECT p.id, p.title, p.category_id, c.title, p.brand, p.color, p.weight, p.price, p.description, p.count
    FROM product AS p LEFT JOIN category AS c ON p.category_id = c.id WHERE p.title='Banana';

SELECT p.id, p.title, p.category_id, c.title AS category_title, p.brand, p.color, p.weight, p.price, p.description, p.count
FROM product as p LEFT JOIN category c on p.category_id = c.id;


SELECT * FROM address where id= 1;

SELECT a.id, a.user_id, u.name AS user_name, u.lastName AS user_lastname, u.birthDate as user_birthdate, u.role AS user_role,
u.mail AS user_email, u.password AS user_password, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone FROM address AS a
LEFT JOIN users AS u on a.user_id = u.id WHERE a.user_id = 1;

SELECT * FROM users WHERE id=1;

SELECT * FROM orders LEFT JOIN (SELECT * FROM users) as usr ON orders.user_id = usr.id WHERE orders.id = 2;


SELECT o.id, o.user_id, o.address_id, o.paymentMethod, o.deliveryMethod, o.paymentState, o.orderStatus, o.orderCost,
        u.name, u.lastName, u.birthDate, u.role, u.mail, u.password,
        a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone, pm.id, pm.p_method,  d.id, d.method, ps.id, ps.p_state
FROM orders AS o LEFT JOIN users AS u ON o.user_id = u.id LEFT JOIN address AS a ON o.address_id = a.id LEFT JOIN paymentMethod AS pm ON o.paymentMethod = pm.p_method
    LEFT JOIN delivery AS d ON o.deliveryMethod = d.method LEFT JOIN paymentState AS ps ON o.paymentState=ps.p_state WHERE o.id=1;

SELECT p.product_id, p.price, p.count ,  (p.price * p.count) AS summary FROM productInCart AS p WHERE p.order_id = 1;
SELECT p.product_id, p.count FROM productInCart AS p WHERE p.order_id = 1;

SELECT p_cart.order_id, p_cart.product_id, p_cart.count AS count_incart,
       p.title, p.category_id, c.title, p.brand, p.color, p.weight, p.price AS price_of_this_date, p.description
       FROM productInCart AS p_cart LEFT JOIN product p ON p_cart.product_id = p.id LEFT JOIN category AS c ON p.category_id = c.id where p_cart.order_id=(?);



SELECT o.id, o.user_id, o.address_id, o.paymentMethod, o.deliveryMethod, o.paymentState, o.orderStatus, o.orderCost FROM orders AS o;
SELECT p_cart.id, p_cart.order_id, p_cart.product_id, p_cart.price AS old_price, p_cart.count, (p_cart.price * p_cart.count) AS summary_Oldprice, p.price AS current_price, (p_cart.count * p.price) AS current_price FROM productInCart AS p_cart LEFT JOIN product AS p ON p_cart.product_id = p.id;


SELECT o.user_id, o.address_id, o.paymentMethod, o.deliveryMethod, o.orderCost FROM orders AS o WHERE o.id = 1;

SELECT p_cart.product_id, p_cart.count, pr.price AS current_price_per_one, (p_cart.count * pr.price) AS current_summar_price_for_position
FROM productInCart AS p_cart LEFT JOIN product AS pr ON p_cart.product_id = pr.id WHERE p_cart.order_id = 1;

SELECT sum(current_summar_price_for_position) FROM
(SELECT p_cart.count, (p_cart.count * pr.price) AS current_summar_price_for_position
FROM productInCart AS p_cart LEFT JOIN product AS pr ON p_cart.product_id = pr.id WHERE p_cart.order_id = 1) AS q;

-- findById in Orders
-- Собрал тут все кроме продкутов в корзине
SELECT o.id AS order_id, o.paymentMethod, o.deliveryMethod, o.paymentState, o.orderStatus, o.orderCost,
       u.id AS user_id, u.name, u.lastName, u.birthDate, u.role, u.mail, u.password,
       a.id AS address_id, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone
           from orders AS o LEFT JOIN users AS u ON o.user_id = u.id LEFT JOIN address AS a on u.id = a.user_id WHERE o.id = (4);
-- Собираю корзину
SELECT prc.id, prc.order_id AS order_id, prc.count AS count_in_cart, prc.price AS prod_in_cart_price, (prc.count * prc.price) AS summar_cost,
       pr.title AS product_title, cat.id AS cat_id, cat.title AS category_title,
       pr.brand, pr.color, pr.weight, pr.description from productInCart AS prc
           LEFT JOIN product AS pr on prc.product_id = pr.id LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (4);
-- считаю суммарную стоимость заказа не момент когда был сделан
SELECT sum(summar_cost) FROM(SELECT  (prc.count * prc.price) AS summar_cost
       from productInCart AS prc LEFT JOIN product AS pr on prc.product_id = pr.id
LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (4)) as q;
-- ============================================================================================
-- for FindByUser orders
SELECT id FROM orders WHERE orders.user_id = (?);
-- ==============================================================================================
-- for update OrderStatus
UPDATE orders SET orderStatus = (?) where orders.id = id;





drop table productInCart;
drop table orders;
drop table address;
drop table users;
drop table product;
drop table paymentState;
drop table paymentMethod;
drop table orderStatus;
drop table delivery;
drop table roles;
drop table category;








SELECT prc.id, prc.order_id AS order_id, prc.count AS count_in_cart, prc.price AS prod_in_cart_price, (prc.count * prc.price) AS summar_cost, pr.title AS product_title, cat.id AS cat_id, cat.title AS category_title, pr.brand, pr.color, pr.weight, pr.description from productInCart AS prc LEFT JOIN product AS pr on prc.product_id = pr.id LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (4);