#reward master table creation

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
currRewardNumber INT PRIMARY KEY AUTO_INCREMENT,#chenge variable as curr_rewardNumber
custId Numeric(9) NOT NULL,
numberOfVisits INT NOT NULL,
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