use hms;

/* Introduce Regular (small) Sandwich (Corn, Cheese, Tomato and Chocolate) */
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1112, 'Cheese S/W WB Reg', 103, 23.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1113, 'Corn Ch S/W WB Reg', 103, 30.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1114, 'Tomato Ch S/W WB Reg', 103, 28.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (1115, 'Chocolate S/W WB Reg', 103, 30.00);

/* Move Cakes to Waffles category to make space. Update Category Id for Cake. */
UPDATE item SET categoryId = 104 WHERE categoryId = 105;


/* Update Icon from Waffle to Waffle / Cake. Rename Waffle category */
UPDATE categories SET imageIcon = './src/images/Waffle_Cake.png' WHERE categoryId = 104;
UPDATE item SET categoryName = 'Waffle_Cake' WHERE categoryId = 104;

/* DELETE empty Category (ID = 105 i.e Cake) */
DELETE FROM categories WHERE categoryId = 105;

/* Adding new column in the item table named secCategoryId */
ALTER TABLE item ADD COLUMN secCategoryId INT DEFAULT NULL;

/* Inserting new row in category table (for Sides and Breakfast) */
INSERT INTO categories VALUES(108, 'Sides_Breakfast','./src/images/Sides_Breakfast.png' );

/* Move Corn Mix to Sides and Breakfast category */
UPDATE item SET CategoryId = 108 WHERE itemId = 1078;

/* Move Veg Puff to Sides and Breakfast category */
UPDATE item SET CategoryId = 108 WHERE itemId = 1091;

/* Move French Fries to Sides and Breakfast category */
UPDATE item SET CategoryId = 108 WHERE itemId = 1111;

/* Breakfast and Sides item */
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (3001, 'Bun Butter', 108, 24.00);
INSERT INTO item (itemId, itemName, categoryId, price) VALUES (3002, 'Maggie Noodles - Veg', 108, 20.00);

/* Update Plain Waffle - Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1101;

/* Update Dry Fruit Waffle - Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1102;

/* Update Chocolate Waffle - Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1104;

/* Update Choco Chip Waffle - Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1106;

/* Update Cheese S/W WB Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1112;

/* Update Corn Ch S/W WB Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1113;

/* Update Tomato Ch S/W WB Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1114;

/* Update Chocolate S/W WB Reg to be in Breakfast and Sides category */
UPDATE item SET secCategoryId = 108 WHERE itemId = 1115;

