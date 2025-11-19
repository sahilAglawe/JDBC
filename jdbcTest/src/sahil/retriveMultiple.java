package sahil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class retriveMultiple {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Scanner sc = new Scanner(System.in);

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/students",
                "root",
                "@Sahil123"
            );

            String sql = "SELECT name, photo FROM images WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            while (true) {
                System.out.print("Enter Image ID to retrieve: ");
                int id = sc.nextInt();
                sc.nextLine(); // consume new line

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    InputStream is = rs.getBinaryStream("photo");

                    System.out.print("Enter output file path  ");
                    String outputPath = sc.nextLine();

                    FileOutputStream fos = new FileOutputStream(outputPath);

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }

                    fos.close();
                    is.close();

                    System.out.println("✅ Image retrieved and saved as: " + outputPath + "\n");
                } else {
                    System.out.println("❌ No image found for ID: " + id + "\n");
                }

                

                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

	}

}
