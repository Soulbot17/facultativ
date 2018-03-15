create table users(
  userId int AUTO_INCREMENT,
  email varchar(45) not null,
  pass varchar(45) not null,
  name varchar(45),
  lastName varchar(45),
  role varchar(45) not null,
  PRIMARY KEY (userId)
);

create table courses(
  courseId int AUTO_INCREMENT,
  name varchar(45) not null,
  tutorId int not null,
  annotation varchar(255),
  status varchar(45),
  PRIMARY KEY (courseId),
  FOREIGN KEY (tutorId) REFERENCES users(userId)
);

create table student_course(
  id int auto_increment,
  courseId int not null,
  studentId int not null,
  studentMark int,
  studentFeedback varchar(255),
  PRIMARY KEY (id),
  FOREIGN KEY (studentId) REFERENCES users(userId),
  FOREIGN KEY (courseId) REFERENCES courses(courseId)
);

