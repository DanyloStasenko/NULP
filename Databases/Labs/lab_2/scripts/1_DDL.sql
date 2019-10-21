-- ---------------------- D D L ----------------------

CREATE DATABASE Transport;
GO

USE Transport;

-- at first, creating tables with PRIMARY_KEY only
CREATE TABLE cities (
    city_id bigint NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (city_id)
);

CREATE TABLE transporters (
    transporter_id bigint NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (transporter_id)
);

CREATE TABLE categories (
    category_id bigint NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE deliveryTypes (
    deliveryType_id bigint NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
);

-- creating tables with both PRIMARY_KEY and FOREIGN_KEY
CREATE TABLE items (
    item_id bigint NOT NULL ,
    name varchar(255) NOT NULL,
	description varchar(255) NOT NULL,
	price int NOT NULL,
	category bigint,

	PRIMARY KEY (item_id),
	FOREIGN KEY (category) REFERENCES categories(category_id)
);

CREATE TABLE orders (
    order_id bigint NOT NULL,
	description varchar(255) NOT NULL,
	city_from bigint,
	city_to bigint,
	transporter bigint,
	item bigint,
	amount int NOT NULL,
	delivery bigint NOT NULL,
	
	PRIMARY KEY (order_id),
	FOREIGN KEY (city_from) REFERENCES cities(city_id),
	FOREIGN KEY (city_to) REFERENCES cities(city_id),
	FOREIGN KEY (transporter) REFERENCES transporters(transporter_id),
	FOREIGN KEY (item) REFERENCES items(item_id),
	FOREIGN KEY (delivery) REFERENCES deliveryTypes(deliveryType_id),
);

CREATE TABLE transporters_deliveryTypes (
	transporter bigint NOT NULL,
	type bigint NOT NULL,
	price int NOT NULL,
	
	PRIMARY KEY (transporter, type),
	FOREIGN KEY (transporter) REFERENCES transporters(transporter_id),
	FOREIGN KEY (type) REFERENCES deliveryTypes(deliveryType_id)
);
