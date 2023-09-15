<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>  List </title>
</head>
<style>
.button1 {
  background-color: #4CAF50;
  border: 1;
  color: powderblue;
  padding: 3px 8px;
  text-align: center;
  display: inline-block;
  font-size: 15px;
  margin: 2px 1px;
  cursor: pointer;
}
.button2 {
  color: blue;
  padding: 2px 6px;
  text-align: center;
  display: inline-block;
  font-size: 13px;
  margin: 2px 1px;
  cursor: pointer;
}
.button3 {
  background-color: black;
  color: white;
  padding: 3px 8px;
  text-align: center;
  display: inline-block;
  font-size: 15px;
  margin: 2px 1px;
  cursor: pointer;
}
</style>

<body>	
<form method = POST action="ListServlet" enctype="multipart/form-data">

<% 
   int count = 0;
   int id = 0;
   
   Class.forName("com.mysql.cj.jdbc.Driver");
   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
   
   PreparedStatement ps = con.prepareStatement("select * from tblproduct ");

   ResultSet res = ps.executeQuery();
%>

 <fieldset>
  <legend><b> List:</b></legend><br>
  <input type="submit" class="button" name="button" value="Add Records"> <br> <br>
 
  <center>
  <table width=600 border=2>
  <tr height=50>
    <th> <center> Id </center> </th>
    <th> <center> status </center> </th>
    <th> <center> Product name </center> </th>
    <th> <center>Price </center> </th>
    <th> <center> Category </center> </th>
        <th> <center> Image </center> </th>
    <th>MRP_Price</th>
 			
 			 <th>Description</th>
 			 
  </tr>


  
<% while(res.next()) {
	count++;
%>	
   
  <tr height=50>
    <td> <%= res.getInt(1) %> </td>
    <td> <%= res.getString(2) %> </td>
    <td> <%= res.getString(5) %> </td>
    <td> <%= res.getString(6) %> </td>
      <td> <%= res.getString(8) %> </td>
 
    <td>  <img src="/MiniProject123/src/main/webapp/images/<%=res.getString(4)%>" ></td>   
  <td> <%= res.getString(7) %> </td>
<td> <%= res.getString(3) %> </td>
      
  </tr> 
  
<% } 
   
   if(count==0) {
%>

  <tr height=50>
     <td colspan=6>  </td>
  </tr>
  
<% } %>  
   
</table> <br>
</center>
Enter List id: <input type=text name= "id"> <br><br>
<input type=submit  class="button2" name= "button" value= "View"><br><br>

<input type=submit class="button2" name= "button" value= "Delete"> <br><br>
</fieldset><br>
<center> <input type=submit class="button3" name= "button" value= "Logout"><br><br> </center>
</form>

</body>
</html>