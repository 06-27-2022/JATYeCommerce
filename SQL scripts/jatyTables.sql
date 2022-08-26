create table jatyAccount(
"id" serial primary key,
"username" varchar(16) not null unique,
"password" varchar(32) not null,
"role" varchar(16) not null,
city text,
state varchar(16)
);

create table jatyWallet(
id serial primary key,
accountID int references jatyAccount(id) not null,
balance decimal not null
);

create table jatyProduct(
id serial primary key,
accountID int references jatyaccount(id) not null,
picture varchar(16) unique,
description text,
stock integer not null,
price decimal not NULL,
name varchar(32) NOT NULL
);

create table jatyTag(
id serial primary key,
tag varchar(64) unique not null,
ban boolean not null
);

CREATE TABLE public.jatyproducttotag (
	productid int4 NOT NULL,
	tagid int4 NOT NULL,
	CONSTRAINT jatyproduct_jatytag_pkey PRIMARY KEY (productid, tagid),
	CONSTRAINT jatyproducttotag_productid_fkey FOREIGN KEY (productid) REFERENCES public.jatyproduct(id),
	CONSTRAINT jatyproducttotag_tagid_fkey FOREIGN KEY (tagid) REFERENCES public.jatytag(id)
);

create table jatyReview(
id serial primary key,
accountID int references jatyAccount(id) not null,
productID int references jatyProduct(id) not null,
rating int not null,
review text
);