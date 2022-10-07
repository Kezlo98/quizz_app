-- liquibase formatted sql
-- changeset admin:create db and add temp account

CREATE TABLE IF NOT EXISTS user
(
    user_id   int(10)     NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    email varchar(50) NOT NULL,
    first_name varchar(50),
    last_name varchar(50),
    user_description varchar(255),
    university_name varchar(50),
    user_password  VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS role
(
    id int(10) NOT NULL AUTO_INCREMENT,
    user_id int(10),
    role VARCHAR(50),
    PRIMARY KEY (id)
);

ALTER TABLE role ADD FOREIGN KEY (user_id) REFERENCES user(user_id);

INSERT INTO user VALUES (1,'admin','admin@gmail.com','admin','admin','admin role account','admin','admin');
INSERT INTO role VALUES (null,1,'ADMIN');

INSERT INTO user VALUES (2,'test','test@gmail.com','test','test','test user role account','test','1234');
INSERT INTO role VALUES (null,2,'USER');