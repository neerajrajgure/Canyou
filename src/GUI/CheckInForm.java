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
	final static String db_name= "HMS";
	final static String username = "billing";
	final static String password = "hmsbilling";
	final static String hmsDbUrl ="jdbc:mysql://localhost/"+db_name+"?"+ "user=" + username + "&" + "password=" + password;
	static Connection con = null;
	
	JButton start,pause,resume,stop;
	JLabel l1;
	Statement st;
	JComboBox c;
	ResultSet rs;
	PreparedStatement prstm;
	String s;
	CheckInForm(){
		setSize(500,500);
		setLocation(100,200);
		setLayout(null);
		l1=new JLabel("Employee Time Management");
		c = new JComboBox();
		start=new JButton("start");
		pause=new JButton("pause");
		resume=new JButton("resume");
		stop=new JButton("stop");

		add(c);
		add(l1);
		add(start);
		add(pause);
		add(resume);
		add(stop);

		try{
			Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection(CafeBill.hmsDbUrl);
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
			rs = st.executeQuery("select * from emp");
			while(rs.next()){
				c.addItem(rs.getString(2));
			}
			rs.close();
		}
		catch(Exception s){
			System.out.println(s);
		}
        
		l1.setBounds(70,30,200,30);
		c.setBounds(50,70,200,30);
		start.setBounds(30,130,100,30);
		pause.setBounds(200,130,100,30);
		resume.setBounds(30,180,100,30);
		stop.setBounds(200,180,100,30);
		        
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
				String empName = c.getSelectedItem().toString();
				rs = st.executeQuery("select empid from emp where firstName ='"+empName+"'");
				rs.first();
				int empId = rs.getInt(1);
				System.out.println(empId);
				Date date = new Date();
				System.out.println("start");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");
				String formattedDate = sdf.format(date);
				System.out.println(formattedDate);
	  //String sql ="update emp set start_time ="+startDate+" where emp_id="+empId;
				String sql ="insert into timeSheet(empid,startTime,currDate) values("+empId+",'"+formattedDate+"',"+"now())";
				prstm= con.prepareStatement(sql);        
				prstm.execute();
				//Addition of time.
				//sql ="update timeSheet set totalTime = SUM(TIMEDIFF(now(), startTime))  where empid="+empId;
				//prstm= con.prepareStatement(sql);        
				//prstm.execute() ;
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
				String empName = c.getSelectedItem().toString();
				rs = st.executeQuery("select empid from emp where firstName ='"+empName+"'");
				rs.first();
				int empId = rs.getInt(1);
				//System.out.println(empId);
				System.out.println("stop");
	  //String sql ="update emp set stopTime = now() where emp_id="+empId;
				String sql ="update timeSheet set stopTime = now() where empid = "+ empId;
				prstm= con.prepareStatement(sql);        
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
				String empName = c.getSelectedItem().toString();
				rs = st.executeQuery("select empid from emp where firstName ='"+empName+"'");
				rs.first();
				int empId = rs.getInt(1);
				System.out.println("pause");
	  //String sql ="update emp set pauseTime = now() where emp_id="+empId;
				String sql ="update timeSheet set pauseTime = now() where empid = "+ empId;
				prstm= con.prepareStatement(sql);        
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
				String empName = c.getSelectedItem().toString();
				rs = st.executeQuery("select empid from emp where firstName ='"+empName+"'");
				rs.first();
				int empId = rs.getInt(1);
				System.out.println("resume");
	  //String sql ="update emp set resumeTime = now() where emp_id="+empId;
				String sql ="update timeSheet set resumeTime = now() where empid = "+ empId;
				prstm= con.prepareStatement(sql);        
				prstm.execute() ;
				prstm.close(); 
				rs.close();
			}
			catch (Exception bx)
			{
				System.out.println(bx);
			}
		}
	}
	/*public static void main(String args[]){
		  CheckInForm f = new CheckInForm();
	}*/
}
