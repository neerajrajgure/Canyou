package GUI;

import java.sql.Timestamp;

public class DateTimeHelper {

	public static java.sql.Time getCurrentTime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Time(today.getTime());
	}
	public Timestamp getCurrentTimeStamp() {
		// TODO Auto-generated method stub
		return null;
	}
	public static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
}