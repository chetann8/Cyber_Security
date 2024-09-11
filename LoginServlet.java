package cyber_security;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cy_sec";
 private static final String JDBC_USER = "root";
 private static final String JDBC_PASSWORD = "shang08";
 static {
 try {
 Class.forName("com.mysql.cj.jdbc.Driver");
 } catch (ClassNotFoundException e) {
 e.printStackTrace();
 }
 }
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 String email = request.getParameter("email");
 String password = request.getParameter("password");
 try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, 
JDBC_PASSWORD)) {
 String sql = "SELECT * FROM details WHERE email = ? AND password = ?";
 PreparedStatement statement = connection.prepareStatement(sql);
 statement.setString(1, email);
 statement.setString(2, password);
 ResultSet result = statement.executeQuery();
 if (result.next()) {
 // Valid credentials, redirect to home.html
 response.sendRedirect("home.html");
 } else {
 // Authentication failed, redirect back to login page with error message
 String message = "Invalid credentials";
 request.setAttribute("message", message);
 request.getRequestDispatcher("login.html").forward(request, response);
 }
 } catch (SQLException e) {
 e.printStackTrace();
 response.sendRedirect("login.html?error=1");
 }
 }
}
