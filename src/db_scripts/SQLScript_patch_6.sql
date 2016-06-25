#reward master table creation
#priority = 1-high	2-mid	3-low

//rename table names

CREATE TABLE Rewards(
rewardId INT PRIMARY KEY AUTO_INCREMENT,
itemId INT NOT NULL,
startDate DATE NOT NULL,
endDate DATE NOT NULL,
expirationTime_hrs INTEGER DEFAULT 0,
requiredVisits INT NOT NULL,
discountType VARCHAR(10) NOT NULL,
discountAmount INT NOT NULL,
maxTotalVisits INT NOT NULL,
flag VARCHAR(11) NOT NULL,
FOREIGN KEY (itemId) REFERENCES item(itemId)
);


#customer current reward table creation

CREATE TABLE CustomerCurrentReward (
CurrRewardNumber INT PRIMARY KEY AUTO_INCREMENT,#chenge variable as curr_rewardNumber
CustId Numeric(9) NOT NULL,
NumberOfVisits INT NOT NULL,
rewardId INT NOT NULL,
FOREIGN KEY (custId) REFERENCES customer(cid),
FOREIGN KEY (rewardId) REFERENCES rewards(rewardId)
);


#Customer past reward table creation

CREATE TABLE CustomerPastReward (
pastRewardNumber INT PRIMARY KEY AUTO_INCREMENT,#chenge variable as past_rewardNumber
numberOfVisits INT NOT NULL,
custId Numeric(9) NOT NULL,
rewardId INT NOT NULL,
FOREIGN KEY (custId) REFERENCES customer(cid),
FOREIGN KEY (rewardId) REFERENCES rewards(rewardId)
);


ALTER TABLE customer 
ADD COLUMN TotalNoVisits INT, 
ADD COLUMN lastVisit DATETIME, 
ADD COLUMN fiestVisit DATETIME;

################################################################################################
#on going rewards
select * from reward_master where end_date>now();

#upcomming rewards
select * from reward_master where start_date>now();

#selecting customer list eligible for reward = now need to get the date

SELECT ccr.CurrRewardNumber,r.rewardId,ccr.custId,c.FName,ccr.NumberOfVisits,r.requiredVisits,ccr.lastVisit,i.itemId,i.itemName
FROM CustomerCurrentReward ccr LEFT JOIN Rewards r 
ON ccr.rewardId=r.rewardId 
LEFT JOIN customer c on c.cid=ccr.custId
LEFT JOIN item i on r.itemId=i.itemId
where r.requiredVisits=ccr.NumberOfVisits AND c.cId=1 AND r.startDate<=now() AND endDate>=now();


##########################################################
#TESTING PURPOSE

#inserting in customer table
insert into customer values(3,'a','User','Address - 1','1234567890',1234567890,'TestHiveUser@gmail.com','1901-01-01','D');
insert into customer values(4,'b','User','Address - 1','1234567890',1234567890,'TestHiveUser@gmail.com','1901-01-01','D');
insert into customer values(5,'c','User','Address - 1','1234567890',1234567890,'TestHiveUser@gmail.com','1901-01-01','D');

#insert commands for reward master
#rewardId,itemId,start_date,end_date,expiration_time(Hrs),required_visits,discount_type,discount_amount,priority
INSERT INTO Rewards values(1,1001,'2016-06-01','2016-06-03',0,5,'per',100,3,'C');
INSERT INTO Rewards values(2,1002,'2016-06-03','2016-06-04',0,10,'per',80,9,'C');
INSERT INTO Rewards values(3,1003,'2016-06-15','2016-06-30',0,15,'per',50,17,'C');
INSERT INTO Rewards values(4,1004,'2016-06-14','2016-06-29',0,20,'rs',30,10000,'C');




#insert commands for customer_current_reward
#curr_rewardId,cust_id,visits,last visit
#insert into CustomerCurrentReward values (1,1,5,now(),2);
#INSERT INTO CustomerCurrentReward VALUES(1,2,4,now(),1);
#INSERT INTO CustomerCurrentReward VALUES(2,1,10,now(),3);
#INSERT INTO CustomerCurrentReward VALUES(3,3,20,now(),4);
#INSERT INTO CustomerCurrentReward VALUES(4,5,8,now(),5);
#INSERT INTO CustomerCurrentReward VALUES(5,4,15,now(),0);

INSERT INTO CustomerCurrentReward VALUES(1,1,5,now(),1);
INSERT INTO CustomerCurrentReward VALUES(2,1,10,now(),2);
INSERT INTO CustomerCurrentReward VALUES(3,1,15,now(),3);
INSERT INTO CustomerCurrentReward VALUES(7,1,4,now(),4);

INSERT INTO CustomerCurrentReward VALUES(4,2,20,now(),4);
INSERT INTO CustomerCurrentReward VALUES(5,2,25,now(),5);

INSERT INTO CustomerCurrentReward VALUES(6,3,5,now(),1);


