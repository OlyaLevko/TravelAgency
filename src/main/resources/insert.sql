INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (20, 'Nick', 'Green', 'nick@mail.com', '1111', 'MANAGER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (21, 'Mike', 'Brown', 'mike@mail.com', '2222', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (22, 'Luck', 'Grey', 'luck@mail.com', '3333', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (23, 'Lucy', 'Perry', 'lucy@mail.com', '4444', 'USER');

INSERT INTO countries (id,name,picture_url) VALUES (100,'India','https://www.planetware.com/wpimages/2020/01/india-in-pictures-beautiful-places-to-photograph-taj-mahal.jpg');
INSERT INTO countries (id,name,picture_url) VALUES (101,'China','https://www.planetware.com/wpimages/2020/01/china-in-pictures-beautiful-places-to-photograph-the-great-wall.jpg');
INSERT INTO countries (id,name,picture_url) VALUES (102,'Ukraine','https://www.worldtravelguide.net/wp-content/uploads/2017/03/shu-Ukraine-Kiev-MonumentIndependence_1088907020-1440x823-EDITORIAL.jpg');

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
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (37, 21, 102, 102, '2021-04-07', '2021-04-11', 'CANCELED');