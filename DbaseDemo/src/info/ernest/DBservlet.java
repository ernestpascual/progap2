package info.ernest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.sqlite.*;

/**
 * Servlet implementation class DBservlet
 */
@WebServlet("/DBservlet")
public class DBservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			// type of database
			Class.forName("org.sqlite.JDBC");
			
			//connect to db
			Connection conn = DriverManager.getConnection("jdbc:sqlite:d:\\sqlite2\\petshop.db");
			
			//extract data
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from petInfo;");
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			
			// begin table
			out.println("<body><table border='1'>");
			out.println("<tr><th> PET CODE </th><th>PET NAME</th>");
			out.println("<th> PET TYPE </th> <th> PRICE </th> </tr></thead>");
			
			//loop while there is data to create rows
			while (rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getString("petID") + "</td>");
				out.println("<td>" + rs.getString("petName") + "</td>");
				out.println("<td>" + rs.getString("petType") + "</td>");
				out.println("<td>" + rs.getString("petPrice") + "</td>");
				out.println("</tr>");
			}
			
			//end table
			out.println("</table></body></html>");
			
			//close connection
			rs.close();
			conn.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
			
		}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
