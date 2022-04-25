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

INSERT INTO Employees(email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    'user1@depaul.edu', 'password', 'john', 'doe', '1988-04-01', 'address sample', true, '2022-04-01 23:55:55', 1
);
INSERT INTO Employees(email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    'user2@depaul.edu', 'password', 'john', 'doe', '1990-05-01', 'address sample', true, '2022-04-01 23:55:55', 2
);
INSERT INTO Employees(email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    'user3@depaul.edu', 'password', 'john', 'doe', '1992-04-01', 'address sample', true, '2022-04-01 23:55:55', 3
);
INSERT INTO Employees(email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    'user4@depaul.edu', 'password', 'john', 'doe', '1994-04-01', 'address sample', true, '2022-04-01 23:55:55', 4
);
INSERT INTO Employees(email, password, first_name, last_name, dob, address, is_approved, create_time, salary_level) VALUES (
    'user5@depaul.edu', 'password', 'john', 'doe', '2000-04-01', 'address sample', true, '2022-04-01 23:55:55', 5
);

INSERT INTO admins(account, password, e_id) VALUES ('admin01', 'password', 1);

INSERT INTO Projects()

