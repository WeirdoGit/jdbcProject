package jdbcProject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class EmployeesCallable {
	
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3307/jdbcdb";
	public static String username = "root"; // Your user name
	public static String password = ""; // Your password
	public static Connection con = null;
	
	public static void createConnection() {
		try {
			// STEP 1 - Load the driver
			Class.forName(driver);
			
			// STEP 2 - Establish the connection
		    con = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callableExample() {
		try {
			createConnection();
			
			String sql = "{call Simple()}";
			CallableStatement cs = con.prepareCall(sql);
			Boolean hasResult = cs.execute();
			
			if(hasResult) {
				ResultSet rs = cs.getResultSet();
				
				while(rs.next()) {
					System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
					
				}
			}
			cs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void callableWithINParameter() {
		try {
			createConnection();
			
			String sql = "{call SimpleWithINParameter(?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, 18);
			Boolean hasResult = cs.execute();
			
			if(hasResult) {
				ResultSet rs = cs.getResultSet();
				
				while(rs.next()) {
					System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
					
				}
			}
			cs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callableWithOUTParameter() {
		try {
			createConnection();
			
			String sql = "{call SimpleWithOUTParameter(?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, 18);
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			Boolean hasResult = cs.execute();
			
			if(hasResult) {
				int countReturned = cs.getInt(2);
				System.out.println("Number of Employees Get= " + countReturned);
				ResultSet rs = cs.getResultSet();
				
				while(rs.next()) {
					System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
					
				}
			}
			cs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//callableExample();
		//callableWithINParameter();
		  callableWithOUTParameter();

	}

}
