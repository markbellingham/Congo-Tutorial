CREATE TABLE Music_Recordings (
	recording_id int(11) PRIMARY KEY NOT NULL,
	artist_name varchar(75),
	title varchar(75),
	category varchar(75),
	image_name varchar(75),
	num_tracks int(11),
	price float,
	stock_count int(11)
);

CREATE TABLE Music_Categories (
	id int(11)  PRIMARY KEY NOT NULL,
	name varchar(75)
);

CREATE TABLE Music_Tracks (
	id int(11) PRIMARY KEY NOT NULL,
	recording_id int(11),
	title varchar(75),
	duration int(11)
);

DROP TABLE order_details;
DROP TABLE orders;
DROP TABLE customers;

CREATE TABLE customers (
    custid INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fname VARCHAR(25) NOT NULL,
    lname VARCHAR(25) NOT NULL,
    address1 VARCHAR(40) NOT NULL,
    address2 VARCHAR(40),
    city VARCHAR(25) NOT NULL,
    postcode VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    password VARCHAR(60) NOT NULL,
    admin BOOLEAN
);

CREATE TABLE orders (
    orderid INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    custid INT(10) NOT NULL ,
    order_date DATETIME,
    order_total FLOAT,
    FOREIGN KEY (custid) REFERENCES customers (custid)
);

CREATE TABLE order_details (
    orderid INT(10) ,
    recording_id INT(11) ,
    price FLOAT,
    order_quantity INT(3),
    total FLOAT,
    PRIMARY KEY (orderid, recording_id),
    FOREIGN KEY (orderid) REFERENCES orders (orderid),
    FOREIGN KEY (recording_id) REFERENCES Music_Recordings (recording_id)
);
