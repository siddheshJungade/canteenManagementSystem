create database CMS71545;
use CMS71545;

create table CustomerM(
cust_id varchar(5) not null,
cust_name varchar(25) not null,
user_name varchar(15) not null,
pass varchar(15),
phone_no numeric(10),
wallet_balance float(10),
address varchar(30),
constraint c_pk primary key(cust_id)
);

create table Vender(
vendor_id varchar(5) not null,
ven_name varchar(15) not null,
ven_usern varchar(15) not null,
ven_pass varchar(15) not null,
ven_phone int,
ven_add varchar(30),
constraint v_pk primary key(vendor_id)
);

create table FoodMenu(
food_id varchar(5) not null,
food_item varchar(15) not null,
amount float(10) not null,
constraint fm_pk primary key(food_id)
);

create table FoodOrder(
order_id varchar(5) not null,
quantity int not null,
dates date ,
esti_time timestamp,
total_amount float(10) not null,
Order_status varchar(5),
reason varchar(15),
constraint o_pk primary key(order_id)
);



