#Update old items for price.

UPDATE item SET price = 32.00 WHERE itemId = 1001;#Espresso
UPDATE item SET price = 40.00 WHERE itemId = 1010;#cappu nat
UPDATE item SET price = 55.00 WHERE itemId = 1012;#cappu nat flav
UPDATE item SET price = 49.00 WHERE itemId = 1014;#latte nat
UPDATE item SET price = 64.00 WHERE itemId = 1016;#latte flav R
UPDATE item SET price = 35.00 WHERE itemId = 1002;#americano R
UPDATE item SET price = 48.00 WHERE itemId = 1004;#irish R
UPDATE item SET price = 55.00 WHERE itemId = 1006;#mocha R
UPDATE item SET price = 48.00 WHERE itemId = 1008;#flat white R
UPDATE item SET price = 39.00 WHERE itemId = 1020;#regular tea
UPDATE item SET price = 45.00 WHERE itemId = 1060;#masala tea
UPDATE item SET price = 45.00 WHERE itemId = 1021;#darjeeling tea
UPDATE item SET price = 47.00 WHERE itemId = 1022;#green tea
UPDATE item SET price = 45.00 WHERE itemId = 1023;#iced coffee
UPDATE item SET price = 55.00 WHERE itemId = 1024;#frappe
UPDATE item SET price = 62.00 WHERE itemId = 1025;#creamy frappe
UPDATE item SET price = 65.00 WHERE itemId = 1026;#mocha frappe
UPDATE item SET price = 45.00 WHERE itemId = 1027;#affogato
UPDATE item SET price = 65.00 WHERE itemId = 1028;#leamon iced tea
UPDATE item SET price = 65.00 WHERE itemId = 1029;#peach iced tea
UPDATE item SET price = 54.00 WHERE itemId = 1018;#hot chocolate R
UPDATE item SET price = 59.00 WHERE itemId = 1030;#cold chocolate
UPDATE item SET price = 69.00 WHERE itemId = 1031;#big o
UPDATE item SET price = 49.00 WHERE itemId = 1033;#strawberry
UPDATE item SET price = 49.00 WHERE itemId = 1032;#rose
UPDATE item SET price = 55.00 WHERE itemId = 1034;#butterscotch
UPDATE item SET price = 39.00 WHERE itemId = 1035;#cheese s/w
UPDATE item SET price = 49.00 WHERE itemId = 1036;#corn cheese s/w
UPDATE item SET price = 76.00 WHERE itemId = 1037;#club s/w
UPDATE item SET price = 59.00 WHERE itemId = 1039;#chocolate s/w
UPDATE item SET price = 65.00 WHERE itemId = 1038;#paneer s/w
UPDATE item SET price = 74.00 WHERE itemId = 1040;#chicken mayo
UPDATE item SET price = 69.00 WHERE itemId = 1041;#grilled chicken
UPDATE item SET price = 85.00 WHERE itemId = 1042;#tandoori chicken
UPDATE item SET price = 65.00 WHERE itemId = 1043;#bbq chicken
UPDATE item SET price = 49.00 WHERE itemId = 1046;#plain waffle large

UPDATE item SET price = 45.00 WHERE itemId = 1057;#large espresso
UPDATE item SET price = 55.00 WHERE itemId = 1011;#large cappu
UPDATE item SET price = 69.00 WHERE itemId = 1013;#large cappu flav
UPDATE item SET price = 64.00 WHERE itemId = 1015;#latte nat large
UPDATE item SET price = 79.00 WHERE itemId = 1017;#latte flav large
UPDATE item SET price = 50.00 WHERE itemId = 1003;#large americano
UPDATE item SET price = 64.00 WHERE itemId = 1005;#large irish
UPDATE item SET price = 70.00 WHERE itemId = 1007;#large mocha
UPDATE item SET price = 64.00 WHERE itemId = 1009;#large flat white
UPDATE item SET price = 69.00 WHERE itemId = 1019;#large hot chocolate

UPDATE item SET price = 65.00 WHERE itemId = 1067;#Green apple iced tea
UPDATE item SET price = 39.00 WHERE itemId = 1078;#corn mix
UPDATE item SET price = 15.00 WHERE itemId = 1079;#chips
UPDATE item SET price = 12.00 WHERE itemId = 1072;#bottled water
UPDATE item SET price = 49.00 WHERE itemId = 1068;#kiwi soda
UPDATE item SET price = 42.00 WHERE itemId = 1069;#straw soda
UPDATE item SET price = 65.00 WHERE itemId = 1070;#green apple
UPDATE item SET price = 55.00 WHERE itemId = 1071;#blue ocean
UPDATE item SET price = 45.00 WHERE itemId = 1064;#vanilla
UPDATE item SET price = 49.00 WHERE itemId = 1065;#mango
UPDATE item SET price = 49.00 WHERE itemId = 1075;#tomato cheese
UPDATE item SET price = 59.00 WHERE itemId = 1073;#coleslaw
UPDATE item SET price = 15.00 WHERE itemId = 1080;#chocolate cookies(1)
UPDATE item SET price = 25.00 WHERE itemId = 1081;#chocolate cookies(2)
UPDATE item SET price = 55.00 WHERE itemId = 1077;#veg pasta

#Insert new menu items.


INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1091, 'Veg Puff', 103, 20.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1092, 'Pasta (Chicken)(Marinara)', 103, 59.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1093, 'Egg S/W WB', 103, 42.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1094, 'Egg Burger S/W WB', 103, 49.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1095, 'Eggless-Vanilla Cupcake', 105, 25.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1096, 'Eggless-Chocolate Cupcake', 105, 25.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1097, 'Vanilla Cupcake', 105, 25.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1098, 'Chocolate Cupcake', 105, 25.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1101, 'Plain Waffle Reg.', 104, 25.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1102, 'Dry Fruit Waffle Reg.', 104, 35.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1103, 'Dry Fruit Waffle Large', 104, 69.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1104, 'Chocolate Waffle Reg.', 104, 33.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1105, 'Chocolate Waffle Large', 104, 65.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1106, 'Choco Chip Waffle Reg.', 104, 32.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1107, 'Choco Chip Waffle Large', 104, 62.00);