INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE)
VALUES (1, 's1@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Ivan', 'Taranov',
        'student');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE) VALUES
  (2, 's2@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Zefir', 'Marmeladov',
   'student');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE) VALUES
  (3, 't1@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Vasiliy', 'Terkin',
   'tutor');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE) VALUES
  (4, 't2@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Anton', 'Fedorov',
   'tutor');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE) VALUES
  (5, 's3@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Denis', 'Kotelnikov',
   'student');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE)
VALUES (6, 's4@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Egor', 'Vyrva',
        'student');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE) VALUES
  (7, 's5@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Evgenii', 'Krysenko',
   'student');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE)
VALUES (8, 's6@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Igor', 'Balabaev',
        'student');
INSERT INTO TEST.USERS (USERID, EMAIL, PASS, NAME, LASTNAME, ROLE) VALUES
  (9, 't3@mail.ru', '$2a$05$yJyr/jn9Fppmpy.9xd.gcOMqbzW/R4IMrKJhsqN/fvRjt4siXFnPG', 'Gregory', 'Rasputin',
   'tutor');

INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (1, 'History of Uganda', 3, 'Cource about history of Uganda', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (2, 'Java', 3, 'Learn to program using the Java programming language', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (3, 'Git', 4, 'Basic Git Workflow. Git Branching, commiting, merging', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (4, 'Jedi', 9, 'Patience you must have my young padavan', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (5, 'Paleontology ', 3, 'Paleontology for python developers', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (6, 'Composers of 15th century', 3, 'Influence of Franz Schubert to nowadays music', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (7, 'Mimicry', 4, 'Pretend being Java developer', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (8, 'Soft skills', 9, 'How to be nice, friendly and collaborative', 'planned');
INSERT INTO TEST.COURSES (COURSEID, NAME, TUTORID, ANNOTATION, STATUS)
VALUES (9, 'Agile', 9, 'The Manifesto for Agile Software Development basic principles', 'planned');