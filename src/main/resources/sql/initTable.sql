CREATE DATABASE IF NOT EXISTS quizz_app;

USE quizz_app;
CREATE TABLE IF NOT EXISTS user
(
    user_id   int(10)     NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    password  VARCHAR(50) NOT NULL,
    role      VARCHAR(25) NOT NULL,
    PRIMARY KEY (user_id)
);