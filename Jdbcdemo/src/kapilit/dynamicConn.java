package kapilit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class dynamicConn {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/students",
                "root",
                "@Sahil123"
            );
		PreparedStatement ps = con.prepareStatement("insert into students values (?,?,?)");
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter the id");
		int id = sc.nextInt();
		
		System.out.println("Enter the name");
		String name = sc.next();
		
		System.out.println("Enter the Salary");
		int salary = sc.nextInt();
		
		/*System.out.println("Enter the gender");
		String gender = sc.next();
		
		System.out.println("Enter the course");
		String course = sc.next();
		
		System.out.println("Enter the marks");
		int marks = sc.nextInt(); */
		
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, salary);
	/*	ps.setString(4, gender);
		ps.setString(5, course);
		ps.setInt(6, marks); */
		
		int i =ps.executeUpdate();
		
		System.out.println(i+" rows got inserted");
		
		con.close();
		
	}

}