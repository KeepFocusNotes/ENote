DELETE FROM users;
DELETE FROM notepads;

//ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO users (email, password, birth_date, registration_date) VALUES
('​UserFirstMail@userdata.com','UserFirstPasswordUserData​',{ts'​1980-01-01​ 00:00:00.00'},
{ts'​2017-09-04​ 00:00:00.00'});

INSERT INTO notepads (title, user_id) VALUES
('​notepadTitle1​','​34​');
