/* Seeders */

INSERT INTO Salaries(salary_level, hourly) VALUES (1, 10);
INSERT INTO Salaries(salary_level, hourly) VALUES (2, 15);
INSERT INTO Salaries(salary_level, hourly) VALUES (3, 20);
INSERT INTO Salaries(salary_level, hourly) VALUES (4, 25);
INSERT INTO Salaries(salary_level, hourly) VALUES (5, 30);
INSERT INTO Salaries(salary_level, hourly) VALUES (6, 40);
INSERT INTO Salaries(salary_level, hourly) VALUES (7, 50);
INSERT INTO Salaries(salary_level, hourly) VALUES (8, 60);
INSERT INTO Salaries(salary_level, hourly) VALUES (9, 80);
INSERT INTO Salaries(salary_level, hourly) VALUES (10, 100);

INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    1, 'user1@depaul.edu', 'password', 'john', 'doe', '1988-04-01', 'address sample', true, '2022-04-01 23:55:55', 1
);
INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    2, 'user2@depaul.edu', 'password', 'steve', 'smith', '1990-05-01', 'address sample', true, '2022-04-01 23:55:55', 2
);
INSERT INTO Employees(e_id, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    3, 'user3@depaul.edu', 'password', 'sally', 'sherman', '1992-04-01', 'address sample', true, '2022-04-01 23:55:55', 3
);
INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    4, 'user4@depaul.edu', 'password', 'javon', 'freeman', '1994-04-01', 'address sample', true, '2022-04-01 23:55:55', 4
);
INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    5, 'user5@depaul.edu', 'password', 'max', 'strus', '2000-04-01', 'address sample', true, '2022-04-01 23:55:55', 5
);

INSERT INTO admins(account, password, e_id) VALUES ('admin01', 'password', 1);


INSERT INTO ApprovedTimeOff(time_off_id, date, is_paid, req_id, t_id) VALUES(
    1, '2022-01-01', true, 1, 200
);
INSERT INTO ApprovedTimeOff(time_off_id, date, is_paid, req_id, t_id) VALUES(
    2, '2022-02-02', true, 2, 201
);
INSERT INTO ApprovedTimeOff(time_off_id, date, is_paid, req_id, t_id) VALUES(
    3, '2022-03-03', true, 3, 202
);
INSERT INTO ApprovedTimeOff(time_off_id, date, is_paid, req_id, t_id) VALUES(
    4, '2022-04-04', true, 4, 203
);
INSERT INTO ApprovedTimeOff(time_off_id, date, is_paid, req_id, t_id) VALUES(
    5, '2022-05-05', true, 5, 203
);

INSERT INTO TimeOffRequests(req_id, from_date, to_date, is_paid, is_approved, e_id) VALUES(
    1, '2022-01-01', '2022-02-02', true, true, 1 
);
INSERT INTO TimeOffRequests(req_id, from_date, to_date, is_paid, is_approved, e_id) VALUES(
    2, '2022-03-03', '2022-04-04', true, true, 2 
);
INSERT INTO TimeOffRequests(req_id, from_date, to_date, is_paid, is_approved, e_id) VALUES(
    3, '2022-05-05', '2022-06-06', false, true, 3
);
INSERT INTO TimeOffRequests(req_id, from_date, to_date, is_paid, is_approved, e_id) VALUES(
    4, '2022-07-07', '2022-08-08', true, true, 4
);
INSERT INTO TimeOffRequests(req_id, from_date, to_date, is_paid, is_approved, e_id) VALUES(
    5, '2022-09-09', '2022-10-10', false, true, 5
);

INSERT INTO Projects(p_id, name, budget) VALUES (
    100, 'SpiderMan', 200000
); 

INSERT INTO Projects(p_id, name, budget) VALUES (
    101, 'Hulk', 145000
); 

INSERT INTO Projects(p_id, name, budget) VALUES (
    110, 'Avengers', 500000
); 

INSERT INTO Works(w_id, date, hours, p_id, e_id, t_id) VALUES (
    1, 2022-04-22, 20, 100, 2, 200 
);

INSERT INTO Works(w_id, date, hours, p_id, e_id, t_id) VALUES (
    2, 2022-04-22, 20, 101, 2, 200 
);

INSERT INTO Works(w_id, date, hours, p_id, e_id, t_id) VALUES (
    3, 2022-04-22, 15, 110, 3, 201 
);

INSERT INTO Works(w_id, date, hours, p_id, e_id, t_id) VALUES (
    3, 2022-04-22, 25, 101, 3, 201 
);

INSERT INTO Works(w_id, date, hours, p_id, e_id, t_id) VALUES (
    4, 2022-04-22, 40, 100, 4, 202 
);

INSERT INTO Works(w_id, date, hours, p_id, e_id, t_id) VALUES (
    5, 2022-04-22, 40, 110, 5, 203 
);

INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, e_id) VALUES (
    200, 2022-04-22, 2022-18-2022, true, true, 2
);

INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, e_id) VALUES (
    201, 2022-04-22, 2022-18-2022, true, true, 3
);

INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, e_id) VALUES (
    202, 2022-04-22, 2022-18-2022, true, true, 4
);
INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, e_id) VALUES (
    203, 2022-04-22, 2022-18-2022, true, true, 5
);