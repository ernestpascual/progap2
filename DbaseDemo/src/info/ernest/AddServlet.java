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
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.sqlite.JDBC");
			//connect to database
			Connection conn = DriverManager.getConnection("jdbc:sqlite:d:\\sqlite2\\petshop.db");
			//get data from form/request object
			String pname =request.getParameter("petname");
			String ptype =request.getParameter("pettype");
			double pprice = Double.parseDouble(request.getParameter("petprice"));
			
			//form sql command string
			String sqlCommand= "insert into petinfo(petName, petType, petPrice) values ('" + pname + "','" +
								ptype + "'," + pprice + ");";
			//extract data
			Statement stat = conn.createStatement();
			boolean result = stat.execute(sqlCommand);
			
			
			//output result
			response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		    
		    if(!result) {
		    	out.println("<h2>Record Inserted Successfully!</h2><br>");
		    	out.println("* <a href='add.html'> Add Again? </a><br>");
		    	out.println("* <a href='showdata'> View Records? </a><br>");
		    	out.println("* <a href='index.html'> Home </a><br>");
		    } else {
		    	out.println("<h2>Record not Inserted</h2><br>");
		    	out.println("* <a href='add.html'> Add Again? </a><br>");
		    	out.println("* <a href='index.html'> Home </a><br>");
		    }

            out.println("<h2>by: Ernest Pascual</h2></body></html>");
            
            //close connection
            conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
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
