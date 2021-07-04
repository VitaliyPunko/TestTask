DROP TABLE IF EXISTS worked_hours;
DROP TABLE IF EXISTS workers;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments
(
    id              Integer Primary key AUTO_INCREMENT,
    department_name Varchar(50) NOT NULL UNIQUE
);

CREATE TABLE workers
(
    id            Integer Primary key AUTO_INCREMENT,
    First_name    Varchar(50) NOT NULL,
    Last_name     Varchar(50) NOT NULL,
    Email         Varchar(50) NOT NULL UNIQUE,
    Department_id Integer,
    CONSTRAINT workers_FK FOREIGN KEY (Department_id) REFERENCES departments (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);
-- make Worker_id UNIQUE?
CREATE TABLE worked_hours
(
    id           Integer Primary key AUTO_INCREMENT,
    Worker_id    Integer,
    Worker_hours Integer,
    CONSTRAINT worked_hours_FK FOREIGN KEY (Worker_id) REFERENCES workers (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

INSERT INTO departments (department_name)
VALUES ('Sales');
INSERT INTO departments (department_name)
VALUES ('HR');
INSERT INTO departments (department_name)
VALUES ('IT');


INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Stephen', 'King', 'stephenking@test.com', 1);
INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Den', 'Brown', 'denbrown@test.com', 1);
INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Lev', 'Tolstoy', 'levtolstoy@test.com', 1);

INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Jane', 'Smith', 'janesmith@test.com', 2);
INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('John', 'Dow', 'johndow@test.com', 2);
INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Silvana', 'Windrunner', 'silvanawindrunner@test.com', 2);

INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Bill', 'Gates', 'billgates@test.com', 3);
INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Mark', 'Zuckerberg', 'markzuckerberg@test.com', 3);
INSERT INTO workers(First_name, Last_name, Email, Department_id)
VALUES ('Jeff', 'Bezos', 'jeffbezos@test.com', 3);

INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (1, 160);
INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (2, 165);
INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (3, 155);

INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (4, 140);
INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (5, 155);
INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (6, 170);

INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (7, 200);
INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (8, 190);
INSERT INTO worked_hours(Worker_id, Worker_hours)
VALUES (9, 210);
