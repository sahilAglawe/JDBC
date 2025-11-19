package sahil;

import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class insertFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students",
                    "root",
                    "@Sahil123"
            );

            String sql = "INSERT INTO files (filename, filedata) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            while (true) {

                System.out.print("Enter file path: ");
                String path = sc.nextLine();

                File file = new File(path);

                // Check if file exists
                if (!file.exists()) {
                    System.out.println(" File not found. Try again.");
                    continue;
                }

                String filename = file.getName(); // extract file name
                FileInputStream fis = new FileInputStream(file);

                ps.setString(1, filename);
                ps.setBinaryStream(2, fis, (int) file.length());

                int rows = ps.executeUpdate();

                System.out.println("✅ File inserted successfully: " + filename + "\n");

                fis.close();

                

                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
