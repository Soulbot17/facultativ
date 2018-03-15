insert into users(email, pass, name, lastName, role)
values('s1@mail.ru','asd123','Ivan','Ivanov','student'),
  ('s2@mail.ru','asd123','Zefir','Marmeladov','student'),
  ('t1@mail.ru','asd123','Tutor','Tutorialov','tutor');

insert into courses(name, tutorId, annotation, status)
values('History of Uganda', 3, 'Cource about history of Uganda', 'planned'),
  ('Java', 3, 'How to write code like a real patsan', 'planned');

insert into student_course(courseId, studentId)
values((select courses.courseId from courses where name='Java'),(select userId from users where name='Ivan')),
  ((select courses.courseId from courses where name='History of Uganda'),(select userId from users where name='Zefir'));