use hms;

/* Add Ice-Cream category */
INSERT INTO categories (categoryId, categoryName, imageIcon) VALUES(109, 'Ice-Cream', './src/images/IceCream.png');

/* Add Bombay Ice-Cream menu items */
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4001, 'Malai (100 gms)', 109, 30);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4002, 'Pista (100 gms)', 109, 30);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4003, 'Mango (100 gms)', 109, 30);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4004, 'Chocolate (100 gms)', 109, 40);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4005, 'Gulkand (100 gms)', 109, 40);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4006, 'Mixed (100 gms)', 109, 40);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4006, 'Kasata - Dry Fruit (100 gms)', 109, 50);

/* Add Kulfi menu items */
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4021, 'Malai Kulfi', 109, 20);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4022, 'Pista Kulfi', 109, 20);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4023, 'Mango Kulfi', 109, 20);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4024, 'Chocolate Kulfi', 109, 20);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4025, 'Gulkand Kulfi', 109, 20);

/* Add Other Ice-Cream menu items */
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4041, 'Fruit Blitz Reg', 109, 35);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4042, 'Fruit Blitz Large', 109, 50);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4051, 'Faluda Reg', 109, 35);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES(4052, 'Faluda Large', 109, 50);

