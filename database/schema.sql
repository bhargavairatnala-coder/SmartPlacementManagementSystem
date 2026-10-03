CREATE DATABASE smart_placement;

USE smart_placement;

CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    branch VARCHAR(50),
    year INT
);

CREATE TABLE companies (
    company_id INT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    job_role VARCHAR(100),
    location VARCHAR(100),
    package_lpa DECIMAL(5,2)
);

CREATE TABLE placements (
    placement_id INT PRIMARY KEY,
    student_id INT,
    company_id INT,
    status VARCHAR(30),

    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (company_id) REFERENCES companies(company_id)
);