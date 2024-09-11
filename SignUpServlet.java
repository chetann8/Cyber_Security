package cyber_security;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cy_sec";
 private static final String JDBC_USER = "root";
 private static final String JDBC_PASSWORD = "shang08";
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws 
ServletException, IOException {
 response.setContentType("text/html");
 try {
 Class.forName("com.mysql.cj.jdbc.Driver");
 } catch (ClassNotFoundException e) {
 e.printStackTrace();
 response.getWriter().println("Error loading MySQL JDBC Driver: " + e.getMessage());
 return;
 }
 String name = request.getParameter("name");
 String dob = request.getParameter("dob");
 String email = request.getParameter("email");
 String year = request.getParameter("year");
 String stream = request.getParameter("stream");
 String semester = request.getParameter("semester");
 String gender = request.getParameter("gender");
 String password = request.getParameter("password");
 try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, 
JDBC_PASSWORD)) {
 String sql = "INSERT INTO details (name, dob, email, year, stream, semester, gender, 
password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
 PreparedStatement statement = connection.prepareStatement(sql);
 statement.setString(1, name);
 statement.setString(2, dob);
 statement.setString(3, email);
 statement.setString(4, year);
 statement.setString(5, stream);
 statement.setString(6, semester);
 statement.setString(7, gender);
 statement.setString(8, password);
 int rowsInserted = statement.executeUpdate();
 if (rowsInserted > 0) {
 response.sendRedirect("signupsuccess.html");
 } else {
 response.getWriter().println("Registration failed.");
 }
 } catch (SQLException e) {
 e.printStackTrace();
 response.getWriter().println("Error: " + e.getMessage());
 }
 }
}
