/*Update Category Id for waffel*/
UPDATE item SET categoryId = 104 WHERE categoryId = 105;

/*Update Waffel's Icon*/
UPDATE categories SET imageIcon = './src/images/Waffel_Cake.png' WHERE categoryId = 104;

/*DELETE unwanted row from Category table where ID = 105 i.e of Cakes*/
DELETE FROM categories WHERE categoryId = 105;

/*Inserting new column in category table*/
INSERT INTO categories VALUES(108, 'Breakfast','./src/images/Breakfast.png' );

/*Adding new column in the item table named secCategoryId*/
ALTER TABLE item ADD COLUMN secCategoryId INT DEFAULT NULL;