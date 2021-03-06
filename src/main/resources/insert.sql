INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (20, 'Nick', 'Green', 'nick@mail.com', '1111', 'MANAGER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (21, 'Mike', 'Brown', 'mike@mail.com', '2222', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (22, 'Luck', 'Grey', 'luck@mail.com', '3333', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (23, 'Lucy', 'Perry', 'lucy@mail.com', '4444', 'USER');

INSERT INTO countries (id,name) VALUES (100,'India');
INSERT INTO countries (id,name) VALUES (101,'China');
INSERT INTO countries (id,name) VALUES (102,'Ukraine');

INSERT INTO hotels (id,name,stars,country_id) VALUES (100,'SpaResort', 3, 100);
INSERT INTO hotels (id,name,stars,country_id) VALUES (101,'GoldDragon', 2, 101);
INSERT INTO hotels (id,name,stars,country_id) VALUES (102,'Podillya', 5, 102);
INSERT INTO hotels (id,name,stars,country_id) VALUES (103,'Lviv', 4, 102);
INSERT INTO hotels (id,name,stars,country_id) VALUES (104,'Bucovel-resort', 5, 102);

INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (40, 101, 'SINGE', 30, 100);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (41, 102, 'SINGE', 30, 100);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (42, 103, 'DOUBLE', 50, 100);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (43, 104, 'SUIT', 100, 100);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (44, 105, 'PRESIDENT_SUIT', 200, 100);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (45, 101, 'DOUBLE', 50, 102);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (46, 102, 'KING', 60, 102);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (47, 103, 'SINGE', 40, 102);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (48, 101, 'DOUBLE', 60, 103);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (49, 102, 'QUEEN', 65, 103);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (50, 103, 'SUIT', 45, 103);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (51, 101, 'DOUBLE', 60, 104);
INSERT INTO rooms (id, number, type, price, hotel_id) VALUES (52, 102, 'PRESIDENT_SUIT', 250, 104);

INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (30, 21, 43,'2020-03-05', '2020-03-10', 'DONE');
INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (31, 22, 50, '2020-04-25', '2020-04-26', 'CANCELED');
INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (32, 22, 50, '2020-04-26', '2020-04-27', 'DONE');
INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (33, 23, 52, '2020-06-13', '2020-06-20', 'DONE');
INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (34, 21, 47, '2020-08-15', '2020-08-25', 'CANCELED');
INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (35, 22, 48, '2021-03-13', '2021-03-20', 'ACTIVE');
INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (36, 23, 41, '2021-04-10', '2021-04-24', 'ACTIVE');
INSERT INTO orders (id, user_id, room_id, from_date, to_date, status) VALUES (37, 21, 46, '2021-04-07', '2021-04-11', 'CANCELED');