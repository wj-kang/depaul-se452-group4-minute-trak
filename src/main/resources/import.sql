-- INSERT INTO Salaries(salary_level, hourly) VALUES (1, 10);
-- INSERT INTO Salaries(salary_level, hourly) VALUES (2, 15);
-- INSERT INTO Salaries(salary_level, hourly) VALUES (3, 20);

INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, created_time, salary_level, phone) VALUES (
    1, 'user1@depaul.edu', 'password', 'john', 'doe', '1988-04-01', 'address sample', true, '2022-04-01 23:55:55', 1, '555-1111'
);

INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, created_time, salary_level, phone) VALUES (
    2, 'user2@depaul.edu', 'password', 'steve', 'smith', '1990-05-01', 'address sample', true, '2022-04-01 23:55:55', 2, '555-2222'
);
INSERT INTO Employees(e_id, email, password, first_name, last_name, dob, address, is_approved, created_time, salary_level, phone) VALUES (
    3, 'user3@depaul.edu', 'password', 'sally', 'sherman', '1992-04-01', 'address sample', true, '2022-04-01 23:55:55', 3, '555-3333'
);

INSERT INTO time_off_requests(req_id, start_date, end_date, is_paid, is_approved, is_rejected, e_id, reason) VALUES(
    1, '2022-01-01', '2022-02-02', true, true, true, 1, 'sick'
);
INSERT INTO time_off_requests(req_id, start_date, end_date, is_paid, is_approved, is_rejected, e_id, reason) VALUES(
    2, '2022-03-03', '2022-04-04', true, true, false, 2,  'covid19'
);
INSERT INTO time_off_requests(req_id, start_date, end_date, is_paid, is_approved, is_rejected, e_id, reason) VALUES(
    3, '2022-05-05', '2022-06-06', false, true, false, 3, 'maternity'
);


--select * from salaries;
--select * from employees;
--select * from time_off_requests;