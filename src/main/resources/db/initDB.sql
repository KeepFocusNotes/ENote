DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS notepads;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS tags;

DROP SEQUENCE IF EXISTS global_seq;

/*CREATE SEQUENCE global_seq START WITH 1;*/

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
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE notes (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  notepad_id  INTEGER                            NOT NULL,
  title       VARCHAR                            NOT NULL,
  description VARCHAR,
  CONSTRAINT notepad_id_title UNIQUE (notepad_id, title),
  FOREIGN KEY (notepad_id) REFERENCES notepads (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tags (
  id    INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  title VARCHAR                            NOT NULL,
);

CREATE TABLE notes_tags (
  note_id INTEGER NOT NULL,
  tag_id  INTEGER NOT NULL,
  CONSTRAINT note_id_tag_id UNIQUE (note_id, tag_id),
  FOREIGN KEY (note_id) REFERENCES notes (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE ON UPDATE CASCADE,
);