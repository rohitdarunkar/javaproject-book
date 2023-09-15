 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ListUtil.JDBCUtils"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ebook Management System</title>
       
    </head>
    <body>
    <body>
       
                    <div class="top_nav_right">
                        <div class="cart box_1">
                            <a href="checkout.jsp"> 
                            <%
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
                            PreparedStatement ps = con.prepareStatement("select count(*) from tblcart where customer_id=" + session.getAttribute("id") );
                            ResultSet res = ps.executeQuery();
                            while(res.next()){
                                int count = res.getInt(1);
                            %>
                            <h3>
                                <div class="total">
                                    <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>
                                    (
                                    <%=count   %>
                                    items )
                                <%} %>
                                </div>

                            </h3>
                        </a>
                        <p>
                            
                        </p>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="page-head">
            <div class="container">
                <h3>Products</h3>
            </div>
        </div>
        <br/>
        
                    <h3>
                        Our Products
                    </h3>
                    <%
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
                    
                     PreparedStatement ps1 = conn.prepareStatement("select * from tblproduct ");

                    ResultSet res1 = ps1.executeQuery();
                        while (res1.next()) {
                    %>
                    <form action="AddToCart" method="post">
                       
                                    <input type="hidden" name="productId" value="<%=res1.getInt(1)%>">
                                  
                                    <img src="images/<%=res1.getString(4)%>" alt="" class="pro-image-back"> <span class="product-new-top"></span> <br><br>
                                
                                    <h4>
                                        <%=res1.getString(5)%>
                                    </h4>
                                    <h5>
                                        Category: <%=res1.getString(8)%>
                                    </h5>
                                    <div class="info-product-price">
                                        <input type="hidden" name="price" value="<%=res1.getString(6)%>"> 
                                        <input type="hidden" name="mrp_price" value="<%=res1.getString(7)%>"> <span class="item_price"><%=res1.getString("price")%> Rs.</span>
                                        <del><%=res1.getString("Mrp_price")%> Rs.</del>
                                    </div>
                                    <input type="submit" value="Add to cart" class="btn btn-warning" onclick="return confirm('Are you sure Do you want to add this item in cart?');">
                                </div>
                            </div>
                        </div>
                    </form>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        
    </body>
</html>