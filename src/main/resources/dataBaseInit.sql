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
  status varchar(45),
  FOREIGN KEY (tutorId) REFERENCES users(id)
);

create table student_course(
  id int auto_increment,
  courseId int not null,
  studentId int not null,
  studentMark int,
  studentFeedback varchar(255),
  PRIMARY KEY (id),
  FOREIGN KEY (studentId) REFERENCES users(id),
  FOREIGN KEY (courseId) REFERENCES courses(id)
);

