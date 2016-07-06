package GUI;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class RewardOperation {
	public boolean status;
	String sql;
	Connection con;
	PreparedStatement pstm;
	ResultSet rs;
	static long cID;
	int ToV,NoV;
	static int rewardID = 0,itemID = 0;

	public RewardOperation(){
		status = false;
		con = ConnectionManager.getConnection();
		sql = null;
	}

	public boolean confirm(long cid) throws SQLException{
		cID = cid;
		System.out.println("in try to get number of visits for customer id="+cid);

		// first get the total no. of visits in current table;
		sql = "SELECT TotalNoVisits FROM customer WHERE cid = "+cid;
		System.out.println(sql);
		pstm = con.prepareStatement(sql);
		System.out.println(sql);
		rs = pstm.executeQuery();
		System.out.println("after result set 64");
		System.out.println("before visits ="+rs.getFetchSize());

		while(rs.next()){
			ToV = rs.getInt(1);
		}

		//select no. of visits from customer current reward table
		sql = "SELECT NumberOfVisits from CustomerCurrentReward where custId = "+cid;
		pstm = con.prepareStatement(sql);
		System.out.println(sql);
		rs = pstm.executeQuery();
		while(rs.next()){
			NoV=rs.getInt(1);
		}

		System.out.println("Total Number of Visits = " + ToV);
		System.out.println("Number Of Visits = "+NoV);

		//selecting required visits from reward table where requiredVisits = numberOfVisits(CustomerCurrent Table) and maxNoVisits >= totalNoVisits(customer table)
		//returning rewardId
		sql="SELECT rewardId FROM Rewards WHERE requiredVisits = "+NoV+" AND maxTotalVisits>="+ToV;
		pstm = con.prepareStatement(sql);
		System.out.println("line 76 query = "+sql);
		rs = pstm.executeQuery();
		System.out.println("after line 74 after resultset");
		while(rs.next()){
			rewardID=rs.getInt(1);
		}

		if(rewardID != 0){
			status = true;
		}
		else{
			status = false;
		}
		return status;
	}
	public void reward() throws SQLException
	{
		sql="SELECT itemId FROM Rewards WHERE rewardId = "+rewardID;
		pstm = con.prepareStatement(sql);
		System.out.println("line 76 query = "+sql);
		rs = pstm.executeQuery();
		System.out.println("line 78 after resultset");
		while(rs.next()){
			System.out.println("item id ="+rs.getInt(1));
			ItemHelper ih = new ItemHelper();
			ih.setItemId(rs.getInt(1));
		}
	}
	public void awared() throws SQLException{

		int NoV = 0;
		int pastID = 0;

		// first get the total no. of visits in current table;
		sql = "SELECT numberOfVisits FROM CustomerCurrentReward WHERE custId = "+CafeBill.cid;
		pstm = con.prepareStatement(sql);
		System.out.println(sql);
		rs = pstm.executeQuery();
		System.out.println("before visits ="+rs.getFetchSize());

		while(rs.next()){
			NoV = rs.getInt(1);
		}

		sql = "SELECT MAX(pastRewardNumber) from CustomerPastReward";
		pstm = con.prepareStatement(sql);
		System.out.println("line 98 query = "+sql);
		rs=pstm.executeQuery();
		while(rs.next()){
			pastID = rs.getInt(1);
		}
		pastID = pastID + 1;

		sql="INSERT INTO CustomerPastReward (pastRewardNumber,NumberOfVisits,custId,rewardId) VALUES ("+pastID+","+NoV+","+cID+","+rewardID+")";
		pstm = con.prepareStatement(sql);
		System.out.println("line 112 query = "+sql);
		pstm.executeUpdate();
		
		sql = "UPDATE CustomerCurrentReward SET numberOfVisits = 0 where custId ="+CafeBill.cid;
		pstm = con.prepareStatement(sql);
		System.out.println("line 117 query = "+sql);
		pstm.executeUpdate();
		System.out.println("before visits ="+rs.getFetchSize());
	}

}