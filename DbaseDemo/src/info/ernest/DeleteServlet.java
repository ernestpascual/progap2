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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//tell Java what type of database we are using
			Class.forName("org.sqlite.JDBC");
			//connect to database
			Connection conn = DriverManager.getConnection("jdbc:sqlite:d:\\sqlite2\\petshop.db");
			
			//get data from form/request object
			String pname =request.getParameter("petname");

			//form sql command string
			String sqlCommand= "delete from petinfo where petname like '" + pname + "';";
					
			//extract data
			Statement stat = conn.createStatement();
			boolean result = stat.execute(sqlCommand);
			
			
			//output result
			response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		    
		    if(!result) {
		    	out.println("<h2>"+ pname +" record deleted Successfully!</h2><br>");
		    	out.println("* <a href='add.html'> Add Again? </a><br>");
		    	out.println("* <a href='showdata'> View Records? </a><br>");
		    	out.println("* <a href='index.html'> Home </a><br>");
		    } else {
		    	out.println("<h2>Record not deleted</h2><br>");
		    	out.println("* <a href='add.html'> Delete Again? </a><br>");
		    	out.println("* <a href='index.html'> Home </a><br>");
		    }

            out.println("<h2>by: Ernest Pascual </h2></body></html>");
            
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
