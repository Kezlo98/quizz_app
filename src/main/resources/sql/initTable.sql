CREATE DATABASE IF NOT EXISTS quizz_app;

USE quizz_app;
CREATE TABLE IF NOT EXISTS user
(
    user_id   int(10)     NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    email varchar(50) NOT NULL,
    description varchar(255),
    first_name varchar(50),
    last_name varchar(50),
    university_name varchar(50),
    password  VARCHAR(50) NOT NULL,
    role      VARCHAR(25) NOT NULL,
    PRIMARY KEY (user_id)
);