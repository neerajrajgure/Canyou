package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

// public class RewardDialogue extends JDialog {

public class RewardDialogue extends javax.swing.JDialog {

	Frame frame;
	String msg,name,sql;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    int pastID,NoV,custID,rewardID,itemID;
    long cID;
	public RewardDialogue(long cid) throws SQLException {
		System.out.println("in reward dialogue constructor");
		setModal(true);
        //createAndShowGUI();
		frame = new Frame();
		//add(frame);
		cID = cid;
	    pastID=1;
	    rewardID=0;
	    custID=0;
	    NoV=0;
	    Connection con = ConnectionManager.getConnection();
	    
	    SQLExecution(cid);
		setTitle("Reward Syst");
        setSize(413,300);
        setLocation(500, 200);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}

	public void SQLExecution(long cid) throws SQLException 
	{
		int visits = 0;
		System.out.println("in sql excepion method");
		try
		{
			System.out.println("in try to get number of visits for customer id="+cid);
			
			// first get the total no. of visits in current table;
			sql = "SELECT TotalNoVisits FROM customer WHERE cid = "+cid+";";
			preparedStatement = con.prepareStatement(sql);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("after result set 64");
			System.out.println("before visits ="+resultSet.getFetchSize());
			
			while(resultSet.next()){
				visits = resultSet.getInt(1);
			}
			
			//select no. of visits from customer current reward table
			sql = "SELECT NumberOfVisits from CustomerCurrentReward where custId = "+cid;
			preparedStatement = con.prepareStatement(sql);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				NoV=resultSet.getInt(1);
			}
			
			System.out.println("Number of Visits = " + visits);
			
			//selecting required visits from reward table where requiredVisits = numberOfVisits(CustomerCurrent Table) and maxNoVisits >= totalNoVisits(customer table)
			//returning rewardId
			sql="SELECT rewardId FROM Rewards WHERE requiredVisits = (SELECT NumberOfVisits from CustomerCurrentReward where custId="+cid+") AND maxTotalVisits>(select TotalNoVisits from customer where cid="+cid+")";
			preparedStatement = con.prepareStatement(sql);
			System.out.println("line 76 query = "+sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("after line 74 after resultset");
			while(resultSet.next()){
				rewardID=resultSet.getInt(1);
			}
			//checking if rewardID is NULL
			if(rewardID == 0){
				//No Reward
				noreward();
			}
			else{
				//Reward
				reward();
			}			
		}
		catch(Exception e){}
	}
	void reward() throws SQLException{
		//Insert into past table
		sql = "SELECT MAX(pastRewardNumber) from CustomerPastReward";
		preparedStatement = con.prepareStatement(sql);
		System.out.println("line 98 query = "+sql);
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			pastID = resultSet.getInt(1);
		}
		pastID = pastID + 1;

		sql="INSET INTO CustomerPastReward (pastRewardNumber,NomberOfVisits,custId,rewardId) VALUES ("+pastID+",(SELECT numberOfVisits from CustomerCurrentReward where custId="+cID+"),"+cID+","+rewardID+")";
		preparedStatement = con.prepareStatement(sql);
		System.out.println("line 118 query = "+sql);
		preparedStatement.executeUpdate();

		//update customer table TotalNoVisits = TotalNoVisits + 1
		sql = "UPDATE customer SET TotalNoVisis = TotalNoVisis + 1, lastVisit = now() where cid = "+cID;
		preparedStatement = con.prepareStatement(sql);
		System.out.println("line 124 query = "+sql);
		preparedStatement.executeUpdate();

		//update customer current table NumberOfVisits = 0 for custID
		sql = "UPDATE CustomerCurrentReward SET NumberOfVisits = 0 where cid = "+cID;
		preparedStatement = con.prepareStatement(sql);
		System.out.println("line 124 query = "+sql);
		preparedStatement.executeUpdate();
	}
	void noreward() throws SQLException{
		System.out.println("Customer "+cID+" is not applicble for reward.");

		//TotalNoVisits = TotalNoVisits + 1 in customer table AND last visit = now()
		sql="UPDATE customer SET TotalNoVisits = TotalNoVisits + 1, lastVisit = now() where cid = "+cID;
		preparedStatement = con.prepareStatement(sql);
		System.out.println("line 76 query = "+sql);
		preparedStatement.executeUpdate();

		//NumberOfVisits = Number of Visits + 1
		sql="UPDATE CustomerCurrentReward SET numberOfVisits = numberOfVisits + 1 where custId = "+cID;
		preparedStatement = con.prepareStatement(sql);
		System.out.println("line 98 query = "+sql);
		preparedStatement.executeUpdate();

		//confirming
		sql="SELECT rewardId FROM Rewards WHERE requiredVisits = (SELECT NumberOfVisits from CustomerCurrentReward where custId="+cID+") AND maxTotalVisits>(select TotalNoVisits from customer where cid="+cID+")";
		preparedStatement = con.prepareStatement(sql);
		System.out.println("line 76 query = "+sql);
		resultSet = preparedStatement.executeQuery();
		System.out.println("after line 74 after resultset");
		while(resultSet.next()){
			rewardID=resultSet.getInt(1);
		}
		if(rewardID == 0){
			JOptionPane.showMessageDialog(frame, "Customer is not eligibale for reward.");
		}
		else{
			reward();
		}

	}
	
}
