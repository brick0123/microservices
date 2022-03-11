-- mysql 8.0

create database if not exists customer;
create database if not exists fraud;
create database if not exists notification;

GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';
