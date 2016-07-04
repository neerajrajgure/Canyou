package GUI;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckInForm extends JFrame implements ActionListener
{
	static Connection con = null;
	JFrame frame;
	JButton start,pause,resume,stop,validate;
	JTextField pass;
	JLabel l1;
	Statement st;
	ResultSet rs;
	PreparedStatement prstm;
	String s,passward,sql;
	static long empID;

	public CheckInForm(){
		setSize(500,500);
		setLocation(100,200);
		setLayout(null);
		l1=new JLabel("Employee Time Management");
		//c = new JComboBox();
		frame = new JFrame();
		validate = new JButton("Validate");
		start=new JButton("Start");
		pause=new JButton("Pause");
		resume=new JButton("Resume");
		stop=new JButton("Stop");
		pass = new JPasswordField(10);
	
		//add(c);
		add(l1);
		add(pass);
		add(validate);
		start=new JButton("start");
		pause=new JButton("pause");
		resume=new JButton("resume");
		stop=new JButton("stop");
		add(l1);
		add(pass);
		add(validate);
		add(start);
		add(pause);
		add(resume);
		add(stop);

		con = ConnectionManager.getConnection();

		l1.setBounds(70,30,200,30);
		pass.setBounds(50,70,200,30);
		validate.setBounds(50, 100, 200, 30);
		start.setBounds(30,130,100,30);
		pause.setBounds(200,130,100,30);
		resume.setBounds(30,180,100,30);
		stop.setBounds(200,180,100,30);

		validate.addActionListener(this);
		start.addActionListener(this);
		pause.addActionListener(this);
		resume.addActionListener(this);
		stop.addActionListener(this);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start){
			try{
				Date date = new Date();
				System.out.println("start");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");
				String formattedDate = sdf.format(date);
				System.out.println(formattedDate);
	  //String sql ="update emp set start_time ="+startDate+" where emp_id="+empId;
				String sql ="UPDATE timeSheet SET startTime = now() WHERE empId ="+empID;
				prstm= con.prepareStatement(sql);  
				System.out.println("line 88 sql = "+sql);
				prstm.executeUpdate();
				prstm.close(); 
				rs.close();
			}
			catch (Exception bx)
			{
				System.out.println(bx);
			}
		}
		if(e.getSource()==stop){
			try{
				System.out.println("stop");
	  //String sql ="update emp set stopTime = now() where emp_id="+empId;
				String sql ="UPDATE timeSheet SET stopTime = now() WHERE empid = "+ empID;
				prstm= con.prepareStatement(sql);        
				System.out.println("line 88 sql = "+sql);
				prstm.execute() ;
				prstm.close(); 
				rs.close();
			}
			catch (Exception bx)
			{
				System.out.println(bx);
			}
		}
		if(e.getSource()==pause){
			try{
				System.out.println("pause");
				String sql ="update timeSheet set pauseTime = now() where empid = "+ empID;
				prstm= con.prepareStatement(sql);
				System.out.println("line 88 sql = "+sql);
				prstm.execute() ;
				prstm.close(); 
				rs.close();
			}
			catch (Exception bx)
			{
				System.out.println(bx);
			}
		}
		if(e.getSource()==resume){
			try{
				System.out.println("resume");
				String sql ="update timeSheet set resumeTime = now() where empid = "+ empID;
				prstm= con.prepareStatement(sql);
				System.out.println("line 88 sql = "+sql);
				prstm.execute();
			}
			catch (Exception bx)
			{
				System.out.println(bx);
			}
		}
		if(e.getSource()==validate){
			try{
				passward = pass.getText();
				sql = "SELECT empId FROM emp WHERE password = "+passward;
				prstm = con.prepareStatement(sql);
				System.out.println(sql);
				rs =prstm.executeQuery();
				while(rs.next()){
					empID = rs.getInt(1);
				}
				System.out.println(empID);
				if(empID != 0){
					int tsId = 0;
					sql = "SELECT max(timeSheetId) FROM timeSheet";
					prstm = con.prepareStatement(sql);
					rs =prstm.executeQuery();
					while(rs.next()){
						tsId = rs.getInt(1);
					}
					JOptionPane.showMessageDialog(frame, "You are eligible to CheckIn");
					tsId = tsId + 1;
					sql = "INSERT INTO timeSheet(timeSheetId,empId,currDate) VALUES ("+tsId+","+empID+",now())";
					prstm = con.prepareStatement(sql);
					System.out.println(sql);
					prstm.executeUpdate();

					pass.setText("");
				}
				else{
					JOptionPane.showMessageDialog(frame, "You have Entered wrong password");
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	/*public static void main(String args[]){
		  CheckInForm f = new CheckInForm();
	}*/
}
