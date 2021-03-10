INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (20, 'Nick', 'Green', 'nick@mail.com', '$2a$10$9ctvhHUjI4sAUyUcKoV3p.6FipIvHnAmgdXdW5yoZ5N9MVW7yJ1tC', 'MANAGER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (21, 'Mike', 'Brown', 'mike@mail.com', '$2a$10$Ml/qm0Zh3E1DskOMJkweS.MFVpPSNTKREj/F9xQUOAchNzB49v3cO', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (22, 'Luck', 'Grey', 'luck@mail.com', '$2a$10$MfB4ICp90aVCA09D3QGRkeYZ8LOMAevaE1LlWqWpqEEmi4KDJYDJi', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (23, 'Lucy', 'Perry', 'lucy@mail.com', '$2a$10$Cai1mlvytX2aRUY6vBE8A.3qaXYaewOaL1eodQ53HTzvSIi.68lK6', 'USER');

INSERT INTO countries (id,name) VALUES (100,'India');
INSERT INTO countries (id,name) VALUES (101,'China');
INSERT INTO countries (id,name) VALUES (102,'Ukraine');

INSERT INTO hotels (id,name,stars,country_id) VALUES (100,'SpaResort', 3, 100);
INSERT INTO hotels (id,name,stars,country_id) VALUES (101,'GoldDragon', 2, 101);
INSERT INTO hotels (id,name,stars,country_id) VALUES (102,'Podillya', 5, 102);
INSERT INTO hotels (id,name,stars,country_id) VALUES (103,'Lviv', 4, 102);
INSERT INTO hotels (id,name,stars,country_id) VALUES (104,'Bucovel-resort', 5, 102);

INSERT INTO rooms (number, type, price, hotel_id) VALUES (101, 'SINGLE', 30, 100);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (102, 'SINGLE', 30, 100);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (103, 'DOUBLE', 50, 100);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (104, 'SUIT', 100, 100);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (105, 'PRESIDENT_SUIT', 200, 100);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (101, 'DOUBLE', 50, 102);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (102, 'KING', 60, 102);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (103, 'SINGLE', 40, 102);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (101, 'DOUBLE', 60, 103);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (102, 'QUAD', 65, 103);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (103, 'SUIT', 45, 103);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (101, 'DOUBLE', 60, 104);
INSERT INTO rooms (number, type, price, hotel_id) VALUES (102, 'PRESIDENT_SUIT', 250, 104);

INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (30, 21, 100, 104,'2020-03-05', '2020-03-10', 'DONE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (31, 22, 103, 102, '2020-04-25', '2020-04-26', 'CANCELED');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (32, 22, 103, 102, '2020-04-26', '2020-04-27', 'DONE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (33, 23, 104, 101, '2020-06-13', '2020-06-20', 'DONE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (34, 21, 102, 103, '2020-08-15', '2020-08-25', 'CANCELED');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (35, 22, 103, 101, '2021-03-13', '2021-03-20', 'ACTIVE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (36, 23, 100, 102, '2021-04-10', '2021-04-24', 'ACTIVE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (37, 22, 100, 102, '2021-05-01', '2021-05-03', 'ACTIVE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (38, 21, 102, 102, '2021-05-07', '2021-05-11', 'CANCELED');