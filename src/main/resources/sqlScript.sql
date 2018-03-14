create table users(
  id int primary key AUTO_INCREMENT,
  email varchar(45) not null,
  pass varchar(45) not null,
  name varchar(45),
  lastName varchar(45),
  role varchar(45) not null);

create table courses(
  id int primary key AUTO_INCREMENT,
  name varchar(45) not null,
  tutorId int,
  annotation varchar(255),
  status varchar(45));

create table student_course(
  id int primary key auto_increment,
  courseId int not null,
  studentId int not null,
  studentMark int,
  studentFeedback varchar(255));

insert into users(email, pass, name, lastName, role)
values('s1@mail.ru','asd123','Ivan','Ivanov','student'),
  ('s2@mail.ru','asd123','Zefir','Marmeladov','student'),
  ('t1@mail.ru','asd123','Tutor','Tutorialov','tutor');

insert into courses(name, annotation, status)
values('History of Uganda', 'Cource about history of Uganda', 'planned'),
  ('Java', 'How to write code like a real patsan', 'planned');

insert into student_course(courseId, studentId)
values((select id from courses where name='Java'),(select id from users where name='Ivan')),
  ((select id from courses where name='History of Uganda'),(select id from users where name='Zefir'));

