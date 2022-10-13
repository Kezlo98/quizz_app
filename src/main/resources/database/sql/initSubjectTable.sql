-- liquibase formatted sql
-- changeset admin:init subject table

CREATE TABLE IF NOT EXISTS subject
(
    subject_id   int(10)     NOT NULL,
    subject_name VARCHAR(50) NOT NULL,
    category     varchar(50) NOT NULL,
    duration     int(10),
    price        int(10),
    sale_price   int(10),
    featured     bool,
    description  varchar(255),
    status       varchar(10),
    PRIMARY KEY (subject_id)
);

INSERT INTO subject VALUES ('1','3 month package','Combo package',3,50000,40000,true,'Most Buy','Active');