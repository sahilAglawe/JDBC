package kapilit;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection con;

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students",
                    "root",
                    "@Sahil123"
            );

        } catch (Exception e) {
            throw new ServletException("Connection error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("sname");
            int age = Integer.parseInt(request.getParameter("age"));

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM students WHERE STUDENTS_NAME = ? OR age = ?" );

            ps.setString(1, name);
            ps.setInt(2, age);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.getWriter().println("Record deleted successfully.");
            } else {
                response.getWriter().println("No record found with given name or age.");
            }

            ps.close();

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    public void destroy() {
        try {
            if (con != null) con.close();
        } catch (Exception e) {
            System.out.println("Error closing connection");
        }
    }
}