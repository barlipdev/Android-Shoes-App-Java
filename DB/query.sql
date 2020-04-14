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

insert into shoes(id_brand, model_name, factor, image) VALUES (1,'M2K TEKNO',0,'0');

