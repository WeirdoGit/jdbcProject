package jdbcProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeesDynamicParameters {
	
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
	
	
	public static void readAllEmployees() {
		try {

		    createConnection();
		    Statement stmt = con.createStatement();
		    String sql = "SELECT * FROM employees";
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
	
	public static void insertEmployees(String firstName, String lastName, Double salary, int age) {
		try {
			createConnection();
			String sql = "INSERT INTO employees (first_name, last_name, salary, age) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setDouble(3, salary);
			ps.setInt(4, age);
			
			ps.executeUpdate();
			System.out.println("Successfully inserted Employees");
			
			con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEmployees(int id, double salary) {
		try {
			createConnection();
			
			String sql = "UPDATE employees SET salary=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, salary);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			con.close();
			
			System.out.println("Updated Successfully!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteEmployees(int id) {
		try {
			createConnection();
			
			String sql = "DELETE FROM employees WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			con.close();
			
			System.out.println("Deleted Successfully!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            // readAllEmployees();
		     //  insertEmployees("Hagos", "Kenenisa", 8000, 28);
		      //   updateEmployees(1, 9000);
		       //     deleteEmployees(1);
	}

}
