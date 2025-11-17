package kapilit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students",
                    "root",
                    "@Sahil123"
            );
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO students  VALUES (?, ?, ?)");

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("sname");
            int age = Integer.parseInt(request.getParameter("age"));
            
            
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
           

         
            int result = ps.executeUpdate();
          
            response.getWriter().println("Student added successfully! Rows affected: " + result);
         
            ps.close();
            con.close();

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}