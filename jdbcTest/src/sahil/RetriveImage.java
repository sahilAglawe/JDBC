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

public class RetriveImage {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = null;
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

        PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("SELECT photo FROM images WHERE id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ps.setInt(1, 1);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            InputStream is = rs.getBinaryStream("photo");

            FileOutputStream fos = null;
			try {
				fos = new FileOutputStream("C:/images/rohit.WEBP");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            byte[] buffer = new byte[1024];
            int bytesRead;

            try {
				while ((bytesRead = is.read(buffer)) != -1) {
				    try {
						fos.write(buffer, 0, bytesRead);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            fos.close();
            is.close();

            System.out.println("Image retrieved successfully!");
        }

        con.close();
	}

}
