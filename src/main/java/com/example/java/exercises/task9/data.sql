-- Customers
INSERT INTO Customers (name, email, phone) VALUES ('Nguyen Van A', 'a@example.com', '0901234567');
INSERT INTO Customers (name, email, phone) VALUES ('Tran Thi B', 'b@example.com', '0902345678');
INSERT INTO Customers (name, email, phone) VALUES ('Le Van C', 'c@example.com', '0903456789');
INSERT INTO Customers (name, email, phone) VALUES ('Pham Thi D', 'd@example.com', '0904567890');
INSERT INTO Customers (name, email, phone) VALUES ('Hoang Van E', 'e@example.com', '0905678901');
INSERT INTO Customers (name, email, phone) VALUES ('Nguyen Thi F', 'f@example.com', '0906789012');
INSERT INTO Customers (name, email, phone) VALUES ('Tran Van G', 'g@example.com', '0907890123');
INSERT INTO Customers (name, email, phone) VALUES ('Le Thi H', 'h@example.com', '0908901234');
INSERT INTO Customers (name, email, phone) VALUES ('Pham Van I', 'i@example.com', '0909012345');
INSERT INTO Customers (name, email, phone) VALUES ('Hoang Thi J', 'j@example.com', '0910123456');

-- PaymentAccount
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (1, 'Ví A', 1000000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (2, 'Ví B', 500000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (3, 'Ví C', 200000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (4, 'Ví D', 800000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (5, 'Ví E', 300000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (6, 'Ví F', 1500000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (7, 'Ví G', 400000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (8, 'Ví H', 700000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (9, 'Ví I', 600000);
INSERT INTO PaymentAccount (customerId, name, balance) VALUES (10, 'Ví J', 900000);

-- Products
INSERT INTO Products (name, price) VALUES ('Áo thun', 150000);
INSERT INTO Products (name, price) VALUES ('Quần jeans', 350000);
INSERT INTO Products (name, price) VALUES ('Giày thể thao', 800000);
INSERT INTO Products (name, price) VALUES ('Mũ lưỡi trai', 100000);
INSERT INTO Products (name, price) VALUES ('Balo laptop', 600000);
INSERT INTO Products (name, price) VALUES ('Áo khoác', 450000);
INSERT INTO Products (name, price) VALUES ('Túi xách', 500000);
INSERT INTO Products (name, price) VALUES ('Đồng hồ', 1200000);
INSERT INTO Products (name, price) VALUES ('Kính mát', 250000);
INSERT INTO Products (name, price) VALUES ('Váy nữ', 300000);
INSERT INTO Products (name, price) VALUES ('Áo sơ mi', 200000);
INSERT INTO Products (name, price) VALUES ('Quần short', 180000);
INSERT INTO Products (name, price) VALUES ('Giày cao gót', 750000);
INSERT INTO Products (name, price) VALUES ('Áo hoodie', 400000);
INSERT INTO Products (name, price) VALUES ('Tất vớ', 50000);
INSERT INTO Products (name, price) VALUES ('Áo thể thao', 220000);
INSERT INTO Products (name, price) VALUES ('Quần thể thao', 270000);
INSERT INTO Products (name, price) VALUES ('Áo dài', 650000);
INSERT INTO Products (name, price) VALUES ('Khăn choàng', 90000);
INSERT INTO Products (name, price) VALUES ('Giày lười', 680000);

-- Inventory
INSERT INTO Inventory (productId, quantity) VALUES (1, 100);
INSERT INTO Inventory (productId, quantity) VALUES (2, 50);
INSERT INTO Inventory (productId, quantity) VALUES (3, 30);
INSERT INTO Inventory (productId, quantity) VALUES (4, 80);
INSERT INTO Inventory (productId, quantity) VALUES (5, 40);
INSERT INTO Inventory (productId, quantity) VALUES (6, 60);
INSERT INTO Inventory (productId, quantity) VALUES (7, 70);
INSERT INTO Inventory (productId, quantity) VALUES (8, 20);
INSERT INTO Inventory (productId, quantity) VALUES (9, 90);
INSERT INTO Inventory (productId, quantity) VALUES (10, 35);
INSERT INTO Inventory (productId, quantity) VALUES (11, 45);
INSERT INTO Inventory (productId, quantity) VALUES (12, 55);
INSERT INTO Inventory (productId, quantity) VALUES (13, 25);
INSERT INTO Inventory (productId, quantity) VALUES (14, 65);
INSERT INTO Inventory (productId, quantity) VALUES (15, 85);
INSERT INTO Inventory (productId, quantity) VALUES (16, 75);
INSERT INTO Inventory (productId, quantity) VALUES (17, 95);
INSERT INTO Inventory (productId, quantity) VALUES (18, 15);
INSERT INTO Inventory (productId, quantity) VALUES (19, 100);
INSERT INTO Inventory (productId, quantity) VALUES (20, 50);

-- Orders
INSERT INTO Orders (customerId, status, totalPrice) VALUES (1, 1, 500000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (2, 0, 350000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (3, 1, 900000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (4, 0, 1200000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (5, 1, 750000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (6, 1, 300000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (7, 0, 450000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (8, 1, 600000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (9, 0, 800000);
INSERT INTO Orders (customerId, status, totalPrice) VALUES (10, 1, 1000000);

-- OrdersItem
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (1, 1, 2, 300000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (1, 4, 2, 200000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (2, 2, 1, 350000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (3, 3, 1, 800000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (3, 5, 1, 100000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (4, 8, 1, 1200000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (5, 13, 1, 750000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (6, 10, 1, 300000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (7, 6, 1, 450000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (8, 5, 1, 600000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (9, 3, 1, 800000);
INSERT INTO OrdersItem (orderId, productId, quantity, price) VALUES (10, 7, 2, 1000000);

COMMIT;

SELECT * FROM ORDERS;
SELECT * FROM ORDERSITEM where PRODUCTID = 1;
SELECT * FROM INVENTORY;
select * from PAYMENTACCOUNT;
select * from INVENTORY;
select * from PRODUCTS;

update PAYMENTACCOUNT
set balance = 10000000
where id = 2;
commit;

select * from PRODUCTS;