import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		String Email = request.getParameter("txtEmail");
		String Password = request.getParameter("txtPassword");
		
		
		
		HttpSession hs = request.getSession();
		
		

        
		
			
		

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
          .prepareStatement("select * from signup1 where Email = ? and Password =  ? ")) {
            preparedStatement.setString(1, Email);
            preparedStatement.setString(2, Password);

           
            ResultSet rs = preparedStatement.executeQuery();
            if(Email!=null && Password!=null) {
            if( rs.next()) {
            	hs.setAttribute("id",rs.getInt("id") );
            	hs.setAttribute("Email",rs.getString("Email"));
            	response.sendRedirect("products.jsp");
            
            	out.println("<script>alert('Logged in Successfully')</script>");
            
            }else {
            	String message ="you have entered wrong credentials";
            	hs.setAttribute("credential",message);
            	
            	response.sendRedirect("Login.html");
            }
            }
        } catch(Exception e) {
			System.out.println("connection error"+ e.getMessage());
			
		}
      
	}

}
