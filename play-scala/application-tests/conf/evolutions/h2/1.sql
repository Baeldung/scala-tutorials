-- !Ups

CREATE TABLE arrival (
   id BIGINT PRIMARY KEY,
   origin VARCHAR(50) NOT NULL,
   destination VARCHAR(50) NOT NULL,
   plane VARCHAR(50) NOT NULL
);

-- !Downs

DROP TABLE arrival;
