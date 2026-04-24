create schema retail_analysis;
use retail_analysis;

CREATE TABLE Categories(
category_id INT primary key,
category_name VARCHAR(50) NOT NULL
);
CREATE TABLE Products(
product_id INT PRIMARY KEY auto_increment,
product_name VARCHAR(100) NOT NULL,
category_id INT,
price Decimal(10,2) check (price > 0),
stock INT check(stock >= 0),
expiry_date DATE,
Foreign key (category_id) references Categories(category_id));
	
CREATE TABLE SalesTransactions(
trans_id int primary key,
trans_date DATETIME,
userid int not null,
product_id int Not null,
total_amount decimal(10,2),
quantity_sold int,
foreign key (product_id) references Products(product_id)
);

INSERT INTO Categories (category_id, category_name) VALUES 
(1, 'Dairy'), 
(2, 'Furnitures'), 
(3, 'Snacks'), 
(4, 'Bakery'),
(5, 'Electronics');

INSERT INTO Products (product_id, product_name, category_id, price, stock, expiry_date) VALUES 
(1, 'Milk', 1, 3.50, 75, '2026-04-18'), 
(2, 'Curd', 1, 2.00, 120, '2026-04-21'),   
(3, 'Chair', 2, 45.00, 10, '2029-12-31'),  
(4, 'Table', 2, 150.00, 5, '2029-12-31'),   
(5, 'Chips', 3, 2.00, 100, '2027-01-01'),  
(6, 'Cake', 4, 25.00, 20, '2026-04-30'),   
(7, 'Phone', 5, 800.00, 2, '2028-01-01');  


INSERT INTO SalesTransactions (trans_id, trans_date, userid, product_id, total_amount, quantity_sold) VALUES 
(201, '2026-03-05 10:00:00', 501, 1, 35.00, 10),   
(202, '2026-03-10 14:20:00', 502, 5, 20.00, 10),   
(203, '2026-03-15 09:45:00', 503, 6, 250.00, 10), 
(204, '2026-03-22 11:30:00', 501, 6, 125.00, 5),   
(205, '2026-03-28 16:00:00', 504, 5, 10.00, 5),    
(206, '2026-04-02 08:15:00', 505, 1, 7.00, 2),    
(207, '2026-04-05 12:00:00', 502, 2, 20.00, 10),   
(208, '2026-04-10 15:50:00', 506, 7, 800.00, 1),   
(209, '2026-04-12 10:20:00', 501, 7, 800.00, 1),   
(210, '2026-04-14 17:00:00', 503, 2, 4.00, 2);    

select * from categories;
select * from products;
select * from salestransactions;


-- Top 3 selling product --
select p.product_name , sum(s.quantity_sold)
from salestransactions s
join products p on s.product_id = p.product_id
group by p.product_name
order by sum(s.quantity_sold) Desc
limit 3;


-- Expiring Soon Query:--
select * 
from products
where datediff(expiry_date, curdate()) < 7 and stock > 50;


-- Dead Stock Analysis (for last 60 days) Query:--

select * from products p
Left join salestransactions s on p.product_id = s.product_id
and s.trans_date >= date_sub(current_date(), interval 60 day)
where s.trans_id is null;


-- Highest Revenue Category in last month Query:--

select category_name, sum(total_amount) as revenue
from (
select product_id, category_name from categories c 
join products p on c.category_id = p.category_id
)as t join salestransactions s on s.product_id = t.product_id
where s.trans_date >= date_sub(current_date(), interval 30 day)
group by category_name
order by sum(total_amount) desc limit 1;

-- unsold products --
select p.product_name , p.stock
from products p 
left join  salestransactions s on p.product_id = s.product_id
where s.product_id is null;

-- category wise revenue --
select c.category_name, sum(total_amount)
from salestransactions s 
join products p on s.product_id = p.product_id
join categories c on p.category_id = c.category_id
group by category_name
order by sum(total_amount)
desc;


create index index_product_id on salestransactions(product_id);
create index index_category_id on products(category_id);


create view retail_insights as
select p.product_id,
  c.category_name,
  p.product_name,
  p.price,
  p.stock,
  p.expiry_date,
  s.trans_date,
  s.userid,
  s.quantity_sold,
  s.total_amount
from categories c
join products p 
on c.category_id = p.category_id
join salestransactions s 
on s.product_id = p.product_id;
