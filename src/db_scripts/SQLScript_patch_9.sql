/*Adding new column in coupon table*/
ALTER TABLE coupon ADD COLUMN categoryId int;

 
/*Making itemId as foreign key from item table*/
ALTER TABLE coupon ADD CONSTRAINT couponId PRIMARY KEY;

ALTER TABLE coupon ADD CONSTRAINT categoryId FOREIGN KEY (categoryId) REFERENCES categories(categoryId);



/*Adding Test coupon*/
INSERT INTO Coupon (couponId, couponName, startDate, startTime, endDate, endTime, itemId, percentage            ) VALUES (111, 'HIVE10)', '2016-01-01', '', '2016-02-29','', 1002, 10 );

INSERT INTO Coupon (couponId, couponName, startDate, startTime, endDate, endTime, itemId, percentage) VALUES (222, 'test', '2017-01-01', '', '2019-02-29','', 1001, 10);