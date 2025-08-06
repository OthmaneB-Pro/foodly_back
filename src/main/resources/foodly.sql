CREATE DATABASE FOODLY;

CREATE TABLE USER (
    ID integer primary key not null AUTO_INCREMENT,
    USERNAME varchar(15)
)

CREATE TABLE MENU (
    ID integer primary key not null AUTO_INCREMENT,
    IMAGE_SOURCE varchar(200),
    TITLE varchar(30),
    PRICE integer,
    QUANTITY integer,
    IS_AVAILABLE true,
    IS_ADVERTISED false,
)