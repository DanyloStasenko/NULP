-- ------------------- ADVANCED SELECTS -------------------

-- select all available info about orders
SELECT order_id, o.description as 'order description', 
	item as 'item id', i.description as 'item description',
	t.name as 'transporter', amount, cf.name as 'from', 
	ct.name as 'to', dt.name as 'delivery type'

	FROM orders as o
	INNER JOIN cities as cf
	ON o.city_from = cf.city_id
	INNER JOIN cities as ct
	ON o.city_to = ct.city_id

	INNER JOIN transporters as t
	ON o.transporter = t.transporter_id

	INNER JOIN items as i
	ON o.item = i.item_id

	INNER JOIN deliveryTypes as dt
	ON o.delivery = dt.deliveryType_id
	;


-- select all possible delivery types for all transporters
SELECT t.name, dt.name FROM transporters as t 
	INNER JOIN transporters_deliveryTypes 
	ON t.transporter_id = transporters_deliveryTypes.transporter
	INNER JOIN deliveryTypes as dt
	ON type = dt.deliveryType_id;


-- all availables deliveries for order
SELECT order_id, o.description as 'order description', 
item as 'item id', i.description as 'item description', i.price as 'item price', amount as 'item amount',
t.name as 'transporter', cf.name as 'from', ct.name as 'to', dt.name as 'user preferred delivery type', 
delt.name as 'possible delivery', tdt.price as 'possible price'

FROM orders as o
INNER JOIN cities as cf
ON o.city_from = cf.city_id
INNER JOIN cities as ct
ON o.city_to = ct.city_id

INNER JOIN transporters as t
ON o.transporter = t.transporter_id

INNER JOIN items as i
ON o.item = i.item_id

INNER JOIN deliveryTypes as dt
ON o.delivery = dt.deliveryType_id

INNER JOIN transporters_deliveryTypes as tdt 
ON t.transporter_id = tdt.transporter
INNER JOIN deliveryTypes as delt
ON type = delt.deliveryType_id;
;
