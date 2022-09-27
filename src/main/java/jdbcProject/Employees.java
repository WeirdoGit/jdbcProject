package jdbcProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employees {
	
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
	
	
	public static void readAllemployees21() {
		try {

		    createConnection();
		    Statement stmt = con.createStatement();
		    String sql = "SELECT * FROM employees21";
		    ResultSet rs = stmt.executeQuery(sql);

		    while(rs.next()) {
		    	int id = rs.getInt("id");
		    	String firstName = rs.getString("first_name");
		    	String lastName = rs.getString("last_name");
		    	double salary = rs.getDouble("salary");
		    	int age = rs.getInt("age");
		    	System.out.println(id + " " + firstName + " "+lastName+ " " + salary+ " "+ age);
		    }
		    con.close();
			} catch(Exception e) {
				e.printStackTrace();
				
			}

	}
	
	public static void insertemployees21() {
		try {
			createConnection();
			String sql = "INSERT INTO employees21 (first_name, last_name, salary, age) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "Abebe");
			ps.setString(2, "Chala");
			ps.setDouble(3, 5000);
			ps.setInt(4, 24);
			
			ps.executeUpdate();
			System.out.println("Successfully inserted employees21");
			
			con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateemployees21() {
		try {
			createConnection();
			
			String sql = "UPDATE employees21 SET salary=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, 8000);
			ps.setInt(2, 3);
			
			ps.executeUpdate();
			con.close();
			
			System.out.println("Updated Successfully!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteemployees21() {
		try {
			createConnection();
			
			String sql = "DELETE FROM employees21 WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 2);
			
			ps.executeUpdate();
			con.close();
			
			System.out.println("Deleted Successfully!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            // readAllemployees21();
		     //  insertemployees21();
		      //   updateemployees21();
		        //    deleteemployees21();
	}

}
