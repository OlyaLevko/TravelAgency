INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (20, 'Nick', 'Green', 'nick@mail.com', '$2a$10$9ctvhHUjI4sAUyUcKoV3p.6FipIvHnAmgdXdW5yoZ5N9MVW7yJ1tC', 'MANAGER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (21, 'Mike', 'Brown', 'mike@mail.com', '$2a$10$Ml/qm0Zh3E1DskOMJkweS.MFVpPSNTKREj/F9xQUOAchNzB49v3cO', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (22, 'Luck', 'Grey', 'luck@mail.com', '$2a$10$MfB4ICp90aVCA09D3QGRkeYZ8LOMAevaE1LlWqWpqEEmi4KDJYDJi', 'USER');
INSERT INTO users (id, firstname, lastname, email, password, role_name) VALUES (23, 'Lucy', 'Perry', 'lucy@mail.com', '$2a$10$Cai1mlvytX2aRUY6vBE8A.3qaXYaewOaL1eodQ53HTzvSIi.68lK6', 'USER');

INSERT INTO countries (id,name,picture_url) VALUES (100,'India','https://www.planetware.com/wpimages/2020/01/india-in-pictures-beautiful-places-to-photograph-taj-mahal.jpg');
INSERT INTO countries (id,name,picture_url) VALUES (101,'China','https://www.planetware.com/wpimages/2020/01/china-in-pictures-beautiful-places-to-photograph-the-great-wall.jpg');
INSERT INTO countries (id,name,picture_url) VALUES (102,'Ukraine','https://www.worldtravelguide.net/wp-content/uploads/2017/03/shu-Ukraine-Kiev-MonumentIndependence_1088907020-1440x823-EDITORIAL.jpg');

INSERT INTO hotels (id,name,stars,country_id,picture_url) VALUES (100,'SpaResort', 3, 100,'https://i1.wp.com/theluxurytravelexpert.com/wp-content/uploads/2019/07/header4.jpg');
INSERT INTO hotels (id,name,stars,country_id,picture_url) VALUES (101,'GoldDragon', 2, 101,'https://www.planetware.com/wpimages/2019/05/china-beijing-best-hotels-the-peninsula-beijing.jpg');
INSERT INTO hotels (id,name,stars,country_id,picture_url) VALUES (102,'Podillya', 5, 102,'https://www.hotelscombined.com/himg/c1/ba/91/hotelsdotcom-1323648960-fd1a59f6_w-224493.jpg');
INSERT INTO hotels (id,name,stars,country_id,picture_url) VALUES (103,'Lviv', 4, 102,'https://ua.laspi.com/files/sanats/1411/images/main.jpg');
INSERT INTO hotels (id,name,stars,country_id,picture_url) VALUES (104,'Bucovel-resort', 5, 102,'https://cf.bstatic.com/images/hotel/max1024x768/256/256410556.jpg');

INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (101, 'SINGLE', 30, 100,'https://www.gannett-cdn.com/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (102, 'SINGLE', 30, 100,'https://www.londontoolkit.com/where-to-stay/images/family-rooms/xhero-luxury-family-marriott-kensington.jpg.pagespeed.ic.MFvdhxkG34.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (103, 'DOUBLE', 50, 100,'https://s31606.pcdn.co/wp-content/uploads/2016/08/hotel-rooms.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (104, 'SUIT', 100, 100,'https://www.designhotels.com/media/kyfhhkkr/hotel-pacai-artists-studio-r-r2-jpg.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (105, 'PRESIDENT_SUIT', 200, 100,'https://media.cntraveler.com/photos/53e2f3a6dddaa35c30f66549/master/pass/four-seasons-hotel-lion-palace-st-petersburg-200004-1.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (101, 'DOUBLE', 50, 102,'https://2486634c787a971a3554-d983ce57e4c84901daded0f67d5a004f.ssl.cf1.rackcdn.com/hendricks-hotel/media/hendricks-header-room-02-inset-5cb5f446c8575.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (102, 'KING', 60, 102,'https://blog.grandluxuryhotels.com/wp-content/uploads/2017/10/The-Peninsula-Hong-Kong-The-Peninsula-Suite-Most-beautiful-hotel-rooms-1920x1080.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (103, 'SINGLE', 40, 102,'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/10/6a/df/ae/superior-double-room.jpg?w=900&h=-1&s=1');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (101, 'DOUBLE', 60, 103,'https://www.urbanpixxels.com/wp-content/uploads/Where-to-stay-in-Bangkok-Boutique-Hotels-Volve-Hotel-Sukhumvit-Thonglor-Room(pp_w768_h512).jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (102, 'QUAD', 65, 103,'https://www.telegraph.co.uk/content/dam/Travel/hotels/europe/united-kingdom/Hotels%20-%20England/brighton/drakes-hotel-brighton2.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (103, 'SUIT', 45, 103,'https://www.trumphotels.com/uploads/16555/13/cloudinary/trump-hotels-cloudinary/image/upload/x_369,y_246,w_6623,h_4421,c_crop/c_fill,w_700,ar_7:8/v1520872704/egr6v8xapifvwmco9pl7.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (101, 'DOUBLE', 60, 104,'https://www.h-hotels.com/_Resources/Persistent/0f1e2a465f31a73e1682dc6ac9e8c405c67b439e/zimmer-komfort-doppelzimmer-01-hyperion-hotel-basel-2400x1349.jpg');
INSERT INTO rooms (number, type, price, hotel_id,picture_url) VALUES (102, 'PRESIDENT_SUIT', 250, 104,'https://www.trumphotels.com/uploads/17440/0/cloudinary/trump-hotels-cloudinary/image/upload/x_0,y_819,w_7360,h_3752,c_crop/c_fill,w_1440,ar_8:3/v1523385191/lbc4mco5kmxrtks1v9tc.jpg');

INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (30, 21, 100, 104,'2020-03-05', '2020-03-10', 'DONE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (31, 22, 103, 102, '2020-04-25', '2020-04-26', 'CANCELED');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (32, 22, 103, 102, '2020-04-26', '2020-04-27', 'DONE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (33, 23, 104, 101, '2020-06-13', '2020-06-20', 'DONE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (34, 21, 102, 103, '2020-08-15', '2020-08-25', 'CANCELED');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (35, 22, 103, 101, '2021-03-13', '2021-03-20', 'ACTIVE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (36, 23, 100, 102, '2021-04-10', '2021-04-24', 'ACTIVE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (37, 22, 100, 102, '2021-05-01', '2021-05-03', 'ACTIVE');
INSERT INTO orders (id, user_id, hotel_id, number, from_date, to_date, status) VALUES (38, 21, 102, 102, '2021-05-07', '2021-05-11', 'CANCELED');