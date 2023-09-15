import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
/ * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	int id=0;
		String Username=request.getParameter("Username");	
	String Email = request.getParameter("txtEmail");
		String Password = request.getParameter("txtPassword");
		
      int count=0;
		HttpSession hs = request.getSession();
		hs.setAttribute("Username", Username);
		hs.setAttribute("id",id );
		out.println(Email);
		out.println(Password);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
     if(con!=null) {
	System.out.println("connection is successful");
	
}
     
		
PreparedStatement ps = con.prepareStatement("insert into signup1 values(?,?,?,?)");
PreparedStatement ps1=con.prepareStatement("select id from signup1");

ResultSet rs=ps1.executeQuery();
while(rs.next()) {
	count++;
}
 		 id=count+1;
ps.setString(2, Email);
ps.setString(3, Password);
ps.setInt(1, id);
ps.setString(4, Username);


		int i=ps.executeUpdate();
		if(i>0) {
			String message = "<script>alert('Customer registered successfully)</script>.";
            //Passing message via session.
            hs.setAttribute("success-message", message);
            //Sending response back to the user/customer
            response.sendRedirect("Login.html");
			//out.println("You have successfully logged in <a href='Login.html'> <input type='button' value='Login' name='Login'> ");
			//response.sendRedirect("Login.html");
		}else {
			String message = "Customer registration fail";
            //Passing message via session.
            hs.setAttribute("fail-message", message);
            //Sending response back to the user/customer
            response.sendRedirect("SignUp.html");
			//out.println("Try Signing up again<a href='SignUp.html'>SignUp");
		}
	
//		ResultSet rs=ps.executeQuery();
//		while(rs.next()) {
//			count++;
//		}
//		int id=count+1;
//		 		ps.setInt(3, id);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	}

