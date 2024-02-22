drop database if exists PJMD5;
create database PJMD5;
use PJMD5;

create table category(
id int auto_increment primary key,
category_name varchar(255),
status bit default 1 
);

create table banner(
banner_id int auto_increment primary key,
banner_name varchar(255) not null,
status bit default 1

);

create table product(
id int auto_increment primary key,
product_name varchar(255),
img_url text,
description text,
price double,
stock int,
category_id int,
banner_id int,
status bit default 1,
foreign key (category_id) references category(id),
foreign key (banner_id) references banner(banner_id)
);

create table role(
id int primary key auto_increment,
role_name varchar(255)
);

create table user(
id int auto_increment primary key,
username varchar(255) unique,
email varchar(255) unique,
password varchar(255),
role_id int default 2,
status bit default 1,
foreign key (role_id) references role(id)
);

create table cart(
id int auto_increment primary key,
user_id int,
product_id int,
quantity int default 1,
foreign key (user_id) references user(id),
foreign key (product_id) references product(id)
);

create table orders(
id int auto_increment primary key,
user_id int,
phone varchar(11),
adress varchar(255),
other text,
create_date datetime default now(),
status varchar(100),
foreign key  (user_id) references user(id)
);

create table order_detail(
id int auto_increment primary key,
order_id int,
product_id int,
price double,
quantity int,
foreign key (product_id) references product(id)
);
 use pjmd5;
alter table order_detail add constraint pk_o_od foreign key (order_id) references orders(id);

insert into role(role_name) value("ADMIN"), ("USER");
insert into user(username, password,role_id) value("admin123", "admin123", 1);
