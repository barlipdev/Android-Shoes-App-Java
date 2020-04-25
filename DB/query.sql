create database sneakers;

create table brands
(
    id_brand int auto_increment,
    name varchar(15) not null,
	image varchar(100) not null,
    constraint brands_pk
        primary key (id_brand)
);


insert into brands(id_brand, name) values (1,'Nike');
insert into brands(id_brand, name) values (2,'Adidas');
insert into brands(id_brand, name) values (3,'New Balance');
insert into brands(id_brand, name) values (4,'Puma');
insert into brands(id_brand, name) values (5,'Converse');

create table shoes
(
    id_shoes int auto_increment,
    id_brand int not null,
    model_name varchar(25) not null,
    factor decimal(1,1) default 0 null,
    image varchar(100) null,
    constraint shoes_pk
        primary key (id_shoes),
    constraint sneakers_brands_id_brand_fk
        foreign key (id_brand) references brands (id_brand)
);


create table user
(
	id_user int auto_increment,
	email VARCHAR(50) not null,
	password varchar(25) not null,
	name VARCHAR(50) null,
	photo varchar(200) null,
	constraint user_pk
		primary key (id_user)
);

create unique index user_email_uindex
	on user (email);

insert into user values (1,'root@root.com','root','root','no photo');
insert into user values (2,'user@user.com','user','user','photo');
insert into user values (3,'oskar.skowronski1@gmail.com','password','skowronsky','none');

create table base_shoes
(
	id_base int auto_increment,
	id_user int not null,
	id_shoes int not null,
	size double not null,
	hidden_size double not null,
	constraint base_shoes_pk
		primary key (id_base),
	constraint base_shoes_id_shoes_fk
		foreign key (id_shoes) references shoes (id_shoes),
	constraint base_shoes_id_user_fk
		foreign key (id_user) references user (id_user)
);

insert into base_shoes values (1,1,1,9,0);
insert into base_shoes values (2,1,4,7,0);
insert into base_shoes values (3,2,8,9,9);
insert into base_shoes values (4,2,7,9,9);
insert into base_shoes values (5,2,14,9,9);
insert into base_shoes values (6,1,14,9,0);


create table favorite_shoes
(
	id_favorite int auto_increment,
	id_user int not null,
	id_shoes int not null,
	size double not null,
	constraint favorite_shoes_pk
		primary key (id_favorite),
	constraint favorite_shoes_id_shoes_fk
		foreign key (id_shoes) references shoes (id_shoes),
	constraint favorite_shoes_id_user_fk
		foreign key (id_user) references user (id_user)
);

insert into favorite_shoes values (1,1,6,9);
insert into favorite_shoes values (2,1,8,9);
insert into favorite_shoes values (3,2,16,7);
insert into favorite_shoes values (4,2,18,9);
insert into favorite_shoes values (5,1,16,7);
insert into favorite_shoes values (6,1,18,9);
insert into favorite_shoes values (7,1,12,9);
insert into favorite_shoes values (8,1,13,9);


		-------Nike
insert into shoes VALUES (1,1,'M2K TEKNO',0,'0');
insert into shoes values(2,1,'Flyknit Racer',0.5,'--image--');
insert into shoes values(3,1,'Air Force 1 High',0.0,'--image--');
insert into shoes values(4,1,'Air Max 720',0.0,'--image--');
insert into shoes values(5,1,'Air Max 97',0.0,'--image--');
insert into shoes values(6,1,'Air Max 98',0.0,'--image--');

		------Adidas
insert into shoes values(7,2,'Ultra Boost',0.0,'--image--');
insert into shoes values(8,2,'NMD R1',0.0,'--image--');
insert into shoes values(9,2,'EQT Gazelle',0.0,'--image--');
insert into shoes values(10,2,'Stan Smith',-0.5,'--image--');
insert into shoes values(11,2,'Yeezy Boost 350',0.0,'--image--');
		
		------NB
insert into shoes values(12,3,'400',-0.5,'--image--');
insert into shoes values(13,3,'577',-0.5,'--image--');
insert into shoes values(14,3,'580',0.0,'--image--');
insert into shoes values(15,3,'650',0.0,'--image--');
insert into shoes values(16,3,'997',-0.5,'--image--');
		
		------Puma
insert into shoes values(17,4,'Blaze of Glory',0.5,'--image--');
insert into shoes values(18,4,'Speeder',0.0,'--image--');
insert into shoes values(19,4,'Clyde',0.5,'--image--');
insert into shoes values(20,4,'Fast Rider',0.0,'--image--');
insert into shoes values(21,4,'TX-3',0.0,'--image--');
		
		------Converse
insert into shoes values(22,5,'Chuck Taylor All Star High',-1.0,'--image--');
insert into shoes values(23,5,'One Star',-1,'--image--');
insert into shoes values(24,5,'Pro Blaze',-0.5,'--image--');
insert into shoes values(25,5,'Star Player',-0.5,'--image--');
insert into shoes values(26,5,'KA II',-5.0,'--image--');




