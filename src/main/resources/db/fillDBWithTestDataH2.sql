INSERT INTO test.users (email, pass, name, lastName, role)
VALUES ('s1@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Ivan', 'Ivanov', 'student'),
  ('s2@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Zefir', 'Marmeladov', 'student'),
  ('s3@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Venya', 'Erofeev', 'student'),
  ('s4@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Iosif', 'Brodskii', 'student'),
  ('s5@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Osip', 'Mandelshtam', 'student'),
  ('s6@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Graf', 'Dracula', 'student'),
  ('t1@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Osip', 'Mandelshtam', 'student'),
  ('t2@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Osip', 'Mandelshtam', 'student'),
  ('t3@mail.ru', '$2a$05$ExEIw8WuiAAP1ZZFb3FYx.nK6q7FOzX4I/XskYNNihOEv6qk.xy7S', 'Tutor', 'Tutorialov', 'tutor');

INSERT INTO test.courses (name, tutorId, annotation, status)
VALUES ('History of Uganda', 7, 'Cource about history of Uganda', 'planned'),
  ('Java', 7, 'How to write code like a real patsan', 'planned'),
  ('Quantum mechanical', 8, 'Course about everything', 'planned'),
  ('DevOps', 8, 'How to write code like a bearded system administrator', 'planned'),
  ('Python', 9, 'How to write code like a little girl', 'planned'),
  ('SQL', 9, 'How to write code and feel pain', 'planned');

INSERT INTO test.student_course (courseId, studentId)
VALUES ((SELECT test.courses.courseId
         FROM test.courses
         WHERE name = 'Java'), (SELECT userId
                                FROM test.users
                                WHERE name = 'Ivan')),
  ((SELECT test.courses.courseId
    FROM test.courses
    WHERE name = 'Java'), (SELECT userId
                           FROM test.users
                           WHERE name = 'Zefir')),
  ((SELECT test.courses.courseId
    FROM test.courses
    WHERE name = 'Java'), (SELECT userId
                           FROM test.users
                           WHERE name = 'Venya')),
  ((SELECT test.courses.courseId
    FROM test.courses
    WHERE name = 'Java'), (SELECT userId
                           FROM test.users
                           WHERE name = 'Zefir')),
  ((SELECT test.courses.courseId
    FROM test.courses
    WHERE name = 'Java'), (SELECT userId
                           FROM test.users
                           WHERE name = 'Zefir')),
  ((SELECT test.courses.courseId
    FROM test.courses
    WHERE name = 'Java'), (SELECT userId
                           FROM test.users
                           WHERE name = 'Zefir')),
  ((SELECT test.courses.courseId
    FROM test.courses
    WHERE name = 'History of Uganda'), (SELECT userId
                                        FROM test.users
                                        WHERE name = 'Zefir'));