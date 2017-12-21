DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS notepads;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS tags;

DROP SEQUENCE IF EXISTS global_seq;

/*CREATE SEQUENCE global_seq START WITH 1;*/

CREATE TABLE users
(
  id                BIGINT                  ,
  email             VARCHAR                 ,
  username          VARCHAR                 ,
  password          VARCHAR                 ,
  registration_date TIMESTAMP DEFAULT now() ,
);

/*CREATE UNIQUE INDEX users_unique_email_index
  ON users (email);*/

CREATE TABLE notepads (
  id      BIGINT                            ,
  user_id BIGINT                            ,
  title   VARCHAR                           ,
);

CREATE TABLE notes (
  id          BIGINT                        ,
  title       VARCHAR                       ,
  description VARCHAR                       ,
);

CREATE TABLE tags (
  id      BIGINT                            ,
  title   VARCHAR                           ,
  note_id BIGINT                            ,
);