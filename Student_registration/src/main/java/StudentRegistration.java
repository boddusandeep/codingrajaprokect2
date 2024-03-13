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

public class StudentRegistration extends HttpServlet
{

	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String name=request.getParameter("name");
		long id=Integer.parseInt(request.getParameter("id"));
		String password=request.getParameter("psw");
		String course=request.getParameter("course");
		 
		PrintWriter out=response.getWriter();
		
		 

		//out.println(name+" "+ id);
		try {
			Class.forName("com.mysql.jdbc.Driver");

		 	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_registration","root","S@ndy1234");

			 Statement stmt=con.createStatement();
			 
			 
			 PreparedStatement pstmt=con.prepareStatement("select*from details where name=? and id=?");
			 
	          pstmt.setString(1,name);
	          pstmt.setLong(2, id);
	         ResultSet r= pstmt.executeQuery();
	         if(r.next())
	         {
	        	 out.println("alredy existis");
	        	 
	        	 
	         }else {
	        	 
	          
			
			 int n=stmt.executeUpdate("insert into details(name,id,password,course)values('"+name+"','"+id+"','"+password+"','"+course+"')");
	              if(n==1) {
	            	  //out.println("updated");  
	            	  
	            	  response.sendRedirect("login.html");
	            	  
	            	  
	              }
			 con.close();
	         }
			
			
			
			
		}catch(Exception e) {
			out.println("exception occures");
		}
		
		
		
		
	}
	
}
