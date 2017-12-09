DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;
//just for tests
CREATE SEQUENCE global_seq START WITH 1000;

CREATE TABLE users
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  email             VARCHAR                 NOT NULL,
  password          VARCHAR                 NOT NULL,
  birth_date        TIMESTAMP               NOT NULL,
  registration_date TIMESTAMP DEFAULT now() NOT NULL,
);

CREATE UNIQUE INDEX users_unique_email_index
  ON users (email);

CREATE TABLE notepads (
  //TODO
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
//TODO CREATE UNIQUE INDEX