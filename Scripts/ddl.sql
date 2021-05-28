drop database if exists spring5;
create database spring5;

create table spring5.MEMBER (
	ID int auto_increment primary key,
	EMAIL varchar(255),
	PASSWORD varchar(100),
	NAME varchar(100),
	REGDATE datetime,
	unique key (EMAIL)
);