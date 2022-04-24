DROP TABLE IF EXISTS Salaries;
DROP TABLE IF EXISTS Projects;

CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;

CREATE TABLE Salaries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    salary_level VARCHAR(10),
    salary_hourly_value VARCHAR(10),
    PRIMARY KEY (salary_level)
);

CREATE TABLE Projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(50),
    project_id VARCHAR(10),
    PRIMARY KEY (project_id)
);


