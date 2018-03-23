CREATE SCHEMA test;

CREATE TABLE test.users (
  userId   INT AUTO_INCREMENT,
  email    VARCHAR(45)  NOT NULL UNIQUE,
  pass     VARCHAR(244) NOT NULL,
  name     VARCHAR(45),
  lastName VARCHAR(45),
  role     VARCHAR(10)  NOT NULL,
  PRIMARY KEY (userId)
);

CREATE TABLE test.courses (
  courseId   INT AUTO_INCREMENT,
  name       VARCHAR(45) NOT NULL,
  tutorId    INT         NOT NULL,
  annotation VARCHAR(255),
  status     VARCHAR(45),
  PRIMARY KEY (courseId),
  FOREIGN KEY (tutorId) REFERENCES test.users (userId)
);

CREATE TABLE test.student_course (
  id              INT AUTO_INCREMENT,
  courseId        INT NOT NULL,
  studentId       INT NOT NULL,
  studentMark     INT,
  studentFeedback VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY (studentId) REFERENCES test.users (userId),
  FOREIGN KEY (courseId) REFERENCES test.courses (courseId)
);