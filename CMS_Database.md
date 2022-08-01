# Execute the following query

## Create Database 
## replace 123456 with your employeeid
drop database CMSDB123456;
create database CMSDB123456;

use CMSDB123456;

 

CREATE TABLE Vendor(
Vendor_id integer PRIMARY KEY,
Vendor_Name varchar(255),
Vendor_Phone varchar(255),
Vendor_Specs varchar(255)
);
INSERT INTO Vendor VALUES(001,'Pizza Hut','9002628508','Veg/NonVeg'),
(002,'Starbucks','9434877319','Veg'),
(003,'Wow Momos','9800586359','Veg/NonVeg');

CREATE TABLE Customer(
Customer_id varchar(255) Primary Key,
Customer_name varchar(255),
Customer_phone varchar(255),
Customer_Email varchar(255),
Customer_walletbal integer,
Customer_Login_id integer,
Customer_Password varchar(255)
);
INSERT INTO Customer VALUES
('C0001','Sheldon','9434346142','Sheldon@gmail.com', 10000,100,'abcd'),
('C0002','Amy','9800641110','Amy@gmail.com', 5500,101,'xyz'),
('C0003','Leonard','9800456987','Leonard@gmail.com', 8700,102,'pqrs'),
('C0004','Penny','9434346589','Penny@gmail.com',9000,103,'mnop'),
('C0005','Harvard','9800262887','Harvard@gmail.com',4500,104,'ijkl');

 
CREATE TABLE Menu(
Food_id integer PRIMARY KEY,
Food_Name varchar(255),
Food_Price integer,
Vendor_id integer);
 
INSERT INTO Menu VALUES
(201,'Veggie Farmhouse Pizza',320,001),
(202,'Veggie Delite Pizza',300,001),
(203,'Chicken Sausage Pizza',350,001),
(204,'Peppy Paneer Pizza',259,001),
(205,'Expresso',80,002),(206,'Latte',85,002),(207,'Cold Coffee',100,002),
(208,'Chicken Fried Momo',250,003),(209,'Chicken Steam Momo',220,003),
(210,'Veg Steam Momo',180,003);

CREATE TABLE OrderDetails(
Order_No integer PRIMARY KEY auto_increment,
Vendor_id integer,
Customer_id varchar(255),
Food_id integer,
Quantity integer,
 DateandTime datetime default now(),
Order_value integer,
Order_status varchar(255));

 
ALTER TABLE OrderDetails ADD FOREIGN KEY(Vendor_id) references Vendor(Vendor_id);
ALTER TABLE OrderDetails ADD FOREIGN KEY(Customer_id) references Customer(Customer_id);
ALTER TABLE OrderDetails ADD FOREIGN KEY(Food_id) references Menu(Food_id);

INSERT INTO OrderDetails VALUES(1,001,'C0001',201,2, '2022-04-15 01:00:00',640,'Accepted'),
(2,001,'C0002',202,1, '2022-04-14 02:15:00',300,'Accepted');
INSERT INTO OrderDetails VALUES(3,002,'C0003',206,1, '2022-04-21 03:00:00',85,'Accepted'),
(4,002,'C0004',206,1, '2022-04-21 03:00:00',85,'Accepted');
INSERT INTO OrderDetails VALUES(5,003,'C0001',209,1, '2022-04-21 05:00:12',220,'Accepted');
INSERT INTO OrderDetails VALUES(6,003,'C0005',209,1,'2022-04-21 06:00:12',220,'Accepted');


select * from orderDetails;





 