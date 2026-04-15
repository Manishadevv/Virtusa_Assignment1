**Retail Insights Database**

The Retail Insights Database is a SQL-based project designed to manage and analyze retail store operations. It covers inventory tracking, category management, and sales transactions, along with analytical queries to extract meaningful business insights.

**Database Schema**

**Products Table**

Stores details about products.

| Column Name  | Data Type                | Description           |
| ------------ | ------------------------ | --------------------- |
| product_id   | INT (PK, Auto Increment) | Unique product ID     |
| product_name | VARCHAR(50)              | Name of the product   |
| category_id  | INT                      | References Categories |
| price        | NUMERIC                  | Product price         |
| stock        | INT                      | Available stock       |
| expiry_date  | DATE                     | Expiry date           |


** Categories Table**

Stores product categories.

| Column Name   | Data Type   | Description        |
| ------------- | ----------- | ------------------ |
| category_id   | INT (PK)    | Unique category ID |
| category_name | VARCHAR(50) | Category name      |


**Sale Tansactions Table**

Tracks sales activity.

| Column Name   | Data Type | Description       |
| ------------- | --------- | ----------------- |
| trans_id      | INT (PK)  | Transaction ID    |
| trans_date    | DATETIME  | Transaction date  |
| user_id       | INT       | Customer ID       |
| product_id    | INT       | Purchased product |
| total_amount  | NUMERIC   | Total value       |
| quantity_sold | INT       | Quantity sold     |


Sample Data

Includes sample entries for:

Categories: Dairy, Furniture, Snacks, Bakery, Electronics
Products: Milk, Curd, Chair, etc.
Transactions: Sales data for March–April 2026

**Analytical Queries**

1.Expiring Soon Products

      SELECT * 
      FROM products
      WHERE DATEDIFF(expiry_date, CURDATE()) < 7 
      AND stock > 50;
      
2.Dead Stock Analysis

      SELECT * 
      FROM products p
      LEFT JOIN salestransactions s 
      ON p.product_id = s.product_id
      AND s.trans_date < DATE_SUB(CURRENT_DATE(), INTERVAL 60 DAY)
      WHERE s.trans_id IS NULL;
      
3.Highest Revenue Category (Last Month)

      SELECT category_name, SUM(total_amount) AS revenue
      FROM categories c
      JOIN products p ON c.category_id = p.category_id
      JOIN salestransactions s ON s.product_id = p.product_id
      WHERE s.trans_date >= DATE_SUB(CURRENT_DATE(), INTERVAL 30 DAY)
      GROUP BY category_name
      ORDER BY revenue DESC
      LIMIT 1;


<img width="384" height="266" alt="Screenshot 2026-04-15 161625" src="https://github.com/user-attachments/assets/c40fa501-57e4-41db-9fa2-3e8377b2ead9" />

<img width="363" height="259" alt="Screenshot 2026-04-15 161630" src="https://github.com/user-attachments/assets/ba4308b8-a9d5-4302-b06c-914ff7b082d4" />

<img width="373" height="255" alt="Screenshot 2026-04-15 161635" src="https://github.com/user-attachments/assets/74104f01-55a3-4a95-bde0-92eb7a962bb0" />

