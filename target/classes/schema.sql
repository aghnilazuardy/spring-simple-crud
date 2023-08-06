CREATE TABLE employee
(
    id bigint NOT NULL,
    name varchar(50) NOT NULL,
    salary bigint NOT NULL,
    grade int DEFAULT 3,
    PRIMARY KEY (id)
);