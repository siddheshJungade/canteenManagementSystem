create database ddl1;
use ddl1;
create table trg_dept(
id int(7),
name varchar(25));
create table tar_emp(
id int(7),
last_name varchar(25),
first_name varchar(25),
dept_id int(7));
alter table tar_emp modify column last_name varchar(100);
create table trg_employees(
employee_id int(7),
first_name varchar(25),
last_name varchar(25),
salary int(7),
dept_id int(5)
);
drop table tar_emp;
alter table trg_employees rename to tar_emp;
alter table tar_emp drop column first_name;
desc tar_emp;

