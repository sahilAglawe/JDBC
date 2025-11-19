package sahil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMultipleImages {

	public static void main(String[] args) throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   
        java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/students",
			        "root",
			        "@Sahil123"
			);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String sql = "INSERT INTO images (name, photo) VALUES (?, ?)";
        PreparedStatement ps = null;
        
        System.out.print("Enter Image Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Image Path (full path): ");
        String path = sc.nextLine();

        File file = new File(path);
		try {
			ps = ((java.sql.Connection) con).prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ps.setString(1, "myImage");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Read image file
        FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps.setString(1, name);
        ps.setBinaryStream(2, fis, (int) file.length());
        try {
			ps.setBinaryStream(2, fis, fis.available());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        int rows = ps.executeUpdate();

        System.out.println("Image inserted: " + rows);

        con.close();

	}
}
