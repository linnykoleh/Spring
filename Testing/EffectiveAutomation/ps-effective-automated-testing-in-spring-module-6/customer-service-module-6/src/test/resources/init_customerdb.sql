CREATE TABLE customers (
id SERIAL, 
first_name varchar(20),
last_name varchar(20),
middle_name varchar(20),
suffix varchar(5),
date_of_last_stay date);

INSERT INTO customers (first_name, last_name, middle_name, suffix, date_of_last_stay) VALUES ( 'John', 'Doe', 'Middle', '', '2017-10-30');	
INSERT INTO customers (first_name, last_name, middle_name, suffix, date_of_last_stay) VALUES ( 'Jane', 'Doesf', 'Middleth', '', '2017-10-15');