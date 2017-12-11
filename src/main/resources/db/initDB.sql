DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS notepads;
DROP TABLE IF EXISTS notes;

DROP SEQUENCE IF EXISTS global_seq;
/*just for tests
CREATE SEQUENCE global_seq START WITH 1000;*/

CREATE TABLE users
(
  id                INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  email             VARCHAR UNIQUE                     NOT NULL,
  password          VARCHAR                            NOT NULL,
  birth_date        TIMESTAMP,
  registration_date TIMESTAMP DEFAULT now(),
);

/*CREATE UNIQUE INDEX users_unique_email_index
  ON users (email);*/

CREATE TABLE notepads (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  user_id INTEGER                            NOT NULL,
  title   VARCHAR                            NOT NULL,
  CONSTRAINT user_title UNIQUE (user_id, title),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE notes (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  notepad_id  INTEGER                            NOT NULL,
  title       VARCHAR                            NOT NULL,
  description VARCHAR,
  CONSTRAINT notepad_id_title UNIQUE (notepad_id, title),
  FOREIGN KEY (notepad_id) REFERENCES notepads (id) ON DELETE CASCADE
);