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

INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, is_rejected, phone, created_time, salary_level, pto_bank) VALUES (
    0, 'user0@depaul.edu', 'password', 'john', 'doe', '1988-04-01', 'address sample', true, true, 2245322202, '2022-04-01 23:55:55', 1, 20
);

INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, is_rejected, phone, created_time, salary_level, pto_bank) VALUES (
    1, 'user1@depaul.edu', 'password', 'john', 'doe', '1988-04-01', 'address sample', true, true, 2245322202, '2022-04-01 23:55:55', 1, 20
);
INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, is_rejected, phone, created_time, salary_level, pto_bank) VALUES (
    6, 'user4@depaul.edu', 'password', 'john', 'knox', '1988-04-01', 'address sample', false, false, 2245322202, '2022-04-01 23:55:55', 1, 20
);
INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, is_rejected, phone,  created_time, salary_level, pto_bank) VALUES (
    4, 'user2@depaul.edu', 'password', 'steve', 'smith', '1990-05-01', 'address sample', false, false, 8473303000, '2022-04-01 23:55:55', 2, 20
);
INSERT INTO Employees(e_id,email, password, first_name, last_name, dob, address, is_approved, is_rejected, phone, created_time, salary_level, pto_bank) VALUES (
    3, 'user3@depaul.edu', 'password', 'sally', 'sherman', '1992-04-01', 'address sample', false, false, 2245378888, '2022-04-01 23:55:55', 2, 20
);


INSERT INTO admins(account, email, password, e_id) VALUES ('admin01', 'socialboommedia@gmail.com', 'password', 1);



INSERT INTO time_off_requests(req_id, from_date, to_date, is_paid, is_approved, is_rejected, e_id) VALUES(
    8, '2022-01-21', '2022-01-23', true, false, false, 3 
);
INSERT INTO time_off_requests(req_id, from_date, to_date, is_paid, is_approved, is_rejected, e_id) VALUES(
    9, '2022-01-21', '2022-01-23', true, false, false, 4 
);

INSERT INTO time_off_requests(req_id, from_date, to_date, is_paid, is_approved, is_rejected, e_id) VALUES(
    10, '2022-02-21', '2022-02-27', true, false, false, 4 
);

INSERT INTO time_off_requests(req_id, from_date, to_date, is_paid, is_approved, is_rejected, e_id) VALUES(
11, '2022-03-21', '2022-03-28', true, false, false, 3 
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


INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, is_rejected, e_id) VALUES (
    200, '2022-01-01', '2022-01-20', true, false, false,  0
);

INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, is_rejected, e_id) VALUES (
    205, '2022-01-01', '2022-01-20', true, false, false,  1
);

INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, is_rejected, e_id) VALUES (
    206, '2022-01-01', '2022-01-20', true, false, false,  3
);

INSERT INTO TimeSheets(t_id, start_date, end_date, is_submitted, is_approved, is_rejected, e_id) VALUES (
    207, '2022-01-01', '2022-01-20', true, false, false,  4
);

