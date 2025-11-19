package sahil;

import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class retriveFile {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students",
                    "root",
                    "@Sahil123"
            );

            String sql = "SELECT filename, filedata FROM files WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            while (true) {

                System.out.print("Enter File ID to retrieve: ");
                int id = sc.nextInt();
                sc.nextLine(); // clear buffer

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String filename = rs.getString("filename");
                    InputStream is = rs.getBinaryStream("filedata");

                    System.out.print("Enter output folder path ");
                    String folderPath = sc.nextLine();

                    // Ensure folder exists
                    File folder = new File(folderPath);
                    folder.mkdirs(); // creates folder if not exists

                    // Complete path = folder + filename
                    File outputFile = new File(folderPath + File.separator + filename);
                    FileOutputStream fos = new FileOutputStream(outputFile);

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }

                    fos.close();
                    is.close();

                    System.out.println("File retrieved and saved");
             //       System.out.println(outputFile.getAbsolutePath() + "\n");

                } else {
                    System.out.println(" No file found with ID: " + id + "\n");
                }

               
            }
          

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
