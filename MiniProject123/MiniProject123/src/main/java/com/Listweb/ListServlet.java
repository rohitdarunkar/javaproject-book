package com.Listweb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Listmodel.List;
import com.ListDao.ListDao;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/ListServlet")
@MultipartConfig
public class ListServlet extends HttpServlet {

	//private final String UPLOAD_DIRECTORY = "D:\\myworkspace\\images";

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // List Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private ListDao ListDao;
	
	public void init() {
			
		ListDao = new ListDao();
			
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// List Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// List Auto-generated method stub
		doGet(req, res);
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		
		String button = req.getParameter("button");
		String id = req.getParameter("id");
		
		if("Add Records".equals(button)) {
			
			res.sendRedirect("ListForm.html");
			
		}
if("Save".equals(button)) {
			
        	saveList(req,res);
        
		}
	
        
		
		if("View".equals(button)) {
			
			viewList(req,res);
			
		}
	
			
		
        
        if("Delete".equals(button)) {
			
        	deleteList(req,res);
			
		} 
        if("Logout".equals(button)) {
			
        	HttpSession s = req.getSession(false);
    		s.invalidate();
    		
    		res.sendRedirect("Index.html");
			
		}
        
        

	}
	
	protected void saveList(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
				
		    res.setContentType("text/html");
			PrintWriter out= res.getWriter();
			
			String Name = req.getParameter("Name");
			String Price = req.getParameter("Price");
			String Category = req.getParameter("Category");
			String Mrp_price=req.getParameter("mprice");
			String Description=req.getParameter("description");
			String Status=req.getParameter("status");
			Part file=req.getPart("Image");
			
			
			HttpSession hs = req.getSession(false);
			String id = (String) hs.getAttribute("id");
			
			

		
			
			List list = new List();
			
		    list.setMrp_price(Mrp_price);
		    list.setDescription(Description);
		    list.setStatus(Status);
			list.setName(Name);
			list.setPrice(Price);
			list.setCategory(Category);
			list.setImageFileName(file.getSubmittedFileName());
			
			
			
			
			String uploadPath="/MiniProject123/src/main/webapp/images/"+file.getSubmittedFileName();  // upload path where we have to upload our actual image
			System.out.println("Upload Path : "+uploadPath);
			
			// Uploading our selected image into the images folder
			
			try
			{
			
			FileOutputStream fos=new FileOutputStream(uploadPath);
			InputStream is=file.getInputStream();
			
			byte[] data=new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
                 try {
                 if( id == null || id == "") {
 					
 					int result = ListDao.addList(list);
 					
 					if (result == 1) {
 						
 						out.println("<script> alert('A new List has been added in your List ') </script>");
 							
 					} 
 					
 				} else {
 					
 				    list.setId(Integer.parseInt(id));
 				    int result = ListDao.updateList(list);
 					
 					if (result == 1) {
 						
 						out.println("<script> alert('Your List has been updated successfully!') </script>");
 							
 					}
 					
 				}
 					
 				req.getRequestDispatcher("List.jsp").include(req, res);

             } catch (Exception e) {
                 e.printStackTrace();
             }

}
  	
	protected void viewList(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
				
				res.setContentType("text/html");
				PrintWriter out= res.getWriter();
				
				HttpSession session = req.getSession(false);
				String Name = (String) session.getAttribute("Name");
				int id = Integer.parseInt(req.getParameter("id"));
				

				List list = new List();
				list.setId(id);
				list.setName(Name);
				try {
							
					int count=0;
					ResultSet rs = ListDao.viewList(list);
					
				while(rs.next()) {
					
					count++;
					out.println("<script> alert('Book Product_name: " + rs.getString(5)+
							 "\t\t\t\t\t Book Price : " + rs.getString(6)+
							 "\t\t\t\t\t Book Product_category: " + rs.getString(8)+
							
							 "\t\t\t\t\t Book Product_Image: " + rs.getString(4)+
							
					       
					        "') </script>"
					        );
						
			}
				
				if(count==0) {
					
					out.println("<script> alert('This task is not present in your List !') </script>");
					
				}
				
				req.getRequestDispatcher("List.jsp").include(req, res);
				
				} catch (Exception e) {
						
					e.printStackTrace();
					
				}
				
			}
	
	protected void deleteList(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
				
				res.setContentType("text/html");
				PrintWriter out= res.getWriter();
				
				HttpSession session = req.getSession(false);
				String Name = (String) session.getAttribute("Name");
				int id = Integer.parseInt(req.getParameter("id"));
				
				List list = new List();
				list.setName(Name);
				list.setId(id);
				
				try {
							
					int count=0;
					int result = ListDao.deleteList(list);
					
					if (result == 1) {
						
						count++;
						out.println("<script> alert('List has been deleted successfully!') </script>");
								
					}
					
					if(count==0) {
						
						out.println("<script> alert('This task is not present in your List!') </script>");
						
					}
					
				req.getRequestDispatcher("List.jsp").include(req, res);
				
				} catch (Exception e) {
						
					e.printStackTrace();
					
				}
				
	}}

