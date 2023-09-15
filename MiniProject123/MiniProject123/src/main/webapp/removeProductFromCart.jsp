<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ListUtil.JDBCUtils"%>
<%@ page import="java.sql.*"%>
<%
    int productId = Integer.parseInt(request.getParameter("productId"));
    //Delete query for cart of particular id
   
    Connection con=JDBCUtils.getConnection();
    		PreparedStatement ps=con.prepareStatement("delete from tblcart where product_id='" + productId + "' and customer_id='" + session.getAttribute("id") + "'");
    int removeCartProduct = ps.executeUpdate();
    if (removeCartProduct > 0) {
        response.sendRedirect("checkout.jsp");
    } else {
        response.sendRedirect("checkout.jsp");
    }
%>