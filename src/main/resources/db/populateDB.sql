DELETE FROM users;
DELETE FROM notepads;

//ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO users (id, email, username) VALUES
  (1,'UserFirstMail@userdata.com', 'UserFirstName'),
  (2,'Admin@epam.com', 'toAdd00806907'),
  (3,'KirAgeevichOstrozhskiy@yandex.com', 'Ageevich35874315'),
  (4,'LeonidProkofevichMatkevich@gmail.com', 'Prokofevich10061731'),
  (5,'AvvakumProtasovichGarshin@yandex.com', 'Protasovich42546135'),
  (6,'BazhenHarlampievichShheglovitov@gmail.com', 'Harlampievich28034124'),
  (7,'MavrPotapovichMezenczov@gmail.com', 'Potapovich07044570'),
  (8,'MarianValentinovichSulistrovskiy@gmail.com', 'Valentinovich26758463'),
  (9,'VitalianArdalionovichVoyczehovich@yandex.com', 'Ardalionovich86960443'),
  (10,'VukolAmvrosievichRyleev@yandex.com', 'Amvrosievich62357805');

INSERT INTO notepads (id, title, user_id) VALUES
  (1,'NotepadFirst', 1),
  (2,'notepadTitle2', 2),
  (3,'notepadTitle3', 3),
  (4,'notepadTitle4', 4),
  (5,'notepadTitle5', 5),
  (6,'notepadTitle6', 6),
  (7,'notepadTitle7', 7),
  (8,'notepadTitle8', 8),
  (9,'notepadTitle9', 9),
  (10,'notepadTitle10', 10);

INSERT INTO notes (id, title, description) VALUES
  (1,'NoteFirst', 'NoteFirstDescription'),
  (2,'noteTitle2', 'noteDescription2'),
  (3,'noteTitle3', 'noteDescription3'),
  (4,'noteTitle4', 'noteDescription4'),
  (5,'noteTitle5', 'noteDescription5'),
  (6,'noteTitle6', 'noteDescription6'),
  (7,'noteTitle7', 'noteDescription7'),
  (8,'noteTitle8', 'noteDescription8'),
  (9,'noteTitle9', 'noteDescription9'),
  (10,'noteTitle10', 'noteDescription10');

INSERT INTO tags (id, title) VALUES
(1,'TagFirst',),
(2,'tagTitle2'),
(3,'tagTitle3'),
(4,'tagTitle4'),
(5,'tagTitle5'),
(6,'tagTitle6'),
(7,'tagTitle7'),
(8,'tagTitle8'),
(9,'tagTitle9'),
(10,'tagTitle10');
