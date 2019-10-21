-- ---------------- inserting cities ----------------
 
INSERT INTO cities VALUES(1, 'Lviv');
INSERT INTO cities VALUES(2, 'Kyiv');
INSERT INTO cities VALUES(3, 'Ternopil');


-- SELECT * FROM cities;
-- DELETE from transporters;


-- ---------------- inserting transporters ----------------

INSERT INTO transporters VALUES(1, 'Nova Poshta');
INSERT INTO transporters VALUES(2, 'UkrPoshta');
INSERT INTO transporters VALUES(3, 'Meest');

--SELECT * FROM transporters;
-- DELETE from transporters;


-- ---------------- inserting categories ----------------

INSERT INTO categories VALUES(1, 'Clothes');
INSERT INTO categories VALUES(2, 'Food');
INSERT INTO categories VALUES(3, 'Electronics');

-- SELECT * FROM categories;
-- DELETE from categories;


-- ---------------- inserting deliveryTypes ----------------

INSERT INTO deliveryTypes VALUES(1, 'Shipping');
INSERT INTO deliveryTypes VALUES(2, 'Train');
INSERT INTO deliveryTypes VALUES(3, 'Truck');
INSERT INTO deliveryTypes VALUES(4, 'Plane');

-- SELECT * FROM deliveryTypes;
-- DELETE from deliveryTypes;

-- ---------------- inserting items ----------------

INSERT INTO items VALUES(1, 'Bon Aqua', 'Cold water', 5, 2);
INSERT INTO items VALUES(2, 'Coca-cola', 'Soda', 10, 2);
INSERT INTO items VALUES(3, 'Sprite', 'Soda', 10, 2);
INSERT INTO items VALUES(4, 'Fanta', 'Soda', 10, 2);

INSERT INTO items VALUES(5, 'T-shirt', 'Brand new tshirt', 50, 1);
INSERT INTO items VALUES(6, 'Jeans', 'Brand new jeans', 650, 1);
INSERT INTO items VALUES(7, 'Shoes', 'Brand new shoes', 1650, 1);

INSERT INTO items VALUES(8, 'Raspberry Pi', 'mini computer', 1950, 3);
INSERT INTO items VALUES(9, 'Notebook', 'i5 8 128', 8950, 3);
INSERT INTO items VALUES(10, 'PC', 'i3 16 256', 6650, 3);

-- SELECT * FROM items;
-- DELETE from items;


-- ---------------- inserting transporters_deliveryTypes ----------------

INSERT INTO transporters_deliveryTypes VALUES(1, 1, 10);
INSERT INTO transporters_deliveryTypes VALUES(1, 2, 15);
INSERT INTO transporters_deliveryTypes VALUES(1, 3, 18);
INSERT INTO transporters_deliveryTypes VALUES(1, 4, 50);

INSERT INTO transporters_deliveryTypes VALUES(2, 3, 20);
INSERT INTO transporters_deliveryTypes VALUES(3, 4, 100);
INSERT INTO transporters_deliveryTypes VALUES(3, 1, 15);


-- ---------------- inserting orders ----------------

 INSERT INTO orders VALUES(1, 'T->L', 3, 1, 1, 1, 100, 4);
 INSERT INTO orders VALUES(2, 'Another order', 1, 2, 2, 1, 35, 3);
 INSERT INTO orders VALUES(3, 'Third order', 2, 1, 3, 3, 35, 1);


-- DELETE FROM orders where order_id = 2;

SELECT * FROM orders;