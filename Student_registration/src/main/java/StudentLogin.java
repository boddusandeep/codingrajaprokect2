import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentLogin extends HttpServlet
{

	public void  service (HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		
		String name=request.getParameter("name");
		long id=Integer.parseInt(request.getParameter("id"));
		
		PrintWriter out=response.getWriter();
		
		//out.println(name);
		 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

 				 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_registration","root","S@ndy1234");

				 Statement stmt=con.createStatement();
				
			 PreparedStatement pstmt=con.prepareStatement("select*from details where name=? and id=?");
			 
		          pstmt.setString(1,name);
		          pstmt.setLong(2,id );
		         ResultSet r= pstmt.executeQuery();
		         if(r.next())
		         {
		        	//  out.println("success");
		        	 response.sendRedirect("course.html");
		        	 
		         }else {
		        	 out.println("emo emo aindhi");
		         }
				 con.close();
				 
			
			
			
		}catch(Exception e) {
			out.println("exception occures");
		}
		
	}
}
