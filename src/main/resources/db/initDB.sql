DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;
//just for tests
CREATE SEQUENCE global_seq START WITH 1000;

CREATE TABLE users
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  email             VARCHAR UNIQUE                NOT NULL,
  password          VARCHAR                       NOT NULL,
  birth_date        TIMESTAMP                     NOT NULL,
  registration_date TIMESTAMP DEFAULT now()       NOT NULL,
);

CREATE UNIQUE INDEX users_unique_email_index
  ON users (email);

CREATE TABLE notepads (
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id INTEGER NOT NULL,
  title   VARCHAR NOT NULL,
  CONSTRAINT user_title UNIQUE (user_id, title),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

//test speed?
CREATE INDEX notepads_title_index
  ON notepads (title);