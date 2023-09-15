<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ListUtil.JDBCUtils"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
         </head>
    <body>
        <%
            //Checking whether customer in session or not
            if (session.getAttribute("Username") != null && session.getAttribute("Username") != "") {
        %>
    <body>
       
                   
                            <a href="checkout.jsp"> <%
                            		 Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
                            PreparedStatement ps = con.prepareStatement("select count(*) from tblcart where customer_id=" + session.getAttribute("id") );
                            ResultSet res = ps.executeQuery();
                            while(res.next()){
                                int count = res.getInt(1);
                            %>
                            <h3>
                                <div class="total">
                                   
                                    (
                                    <%=count%>
                                    items )
                              <%} %>
                                </div>
                            </h3>
                        </a>
                        <p>
                          
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="page-head">
            <div class="container">
                <h3>Check Out</h3>
            </div>
        </div>
        <div class="checkout">
            <div class="container">
                <h3>My Shopping Bag</h3>
                <%
                    int index = 0;
                    int paymentId = 101;
                    Connection conn=JDBCUtils.getConnection();
                    PreparedStatement ps1=conn.prepareStatement("select count(*) from tblcart where customer_id='" + session.getAttribute("id") + "'");
                   ResultSet rsCountCheck=ps1.executeQuery();
                    rsCountCheck.next();
                    int cartItem = rsCountCheck.getInt(1);
                    System.out.println("cartItem  " + cartItem);
                    if (cartItem > 0) {
                %>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>SR.No</th>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>MRP(Rs)</th>
                                <th>Selling Price(Rs)</th>
                                <th>Total Price(Rs)</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <%
                           
                        Connection con1=JDBCUtils.getConnection();
                        PreparedStatement ps2=con1.prepareStatement("select tblproduct.image,tblproduct.name,tblcart.quantity,tblcart.mrp_price,tblcart.discount_price,tblcart.total_price,tblcart.product_id from tblproduct,tblcart where tblproduct.id=tblcart.product_id and customer_id='"
                                    + session.getAttribute("id") + "' ");
                        ResultSet totalProduct=ps2.executeQuery();
                            while (totalProduct.next()) {
                                index++;
                        %>
                        <tr class="rem1">
                            <td class="invert"><%=index%></td>
                            <td class="invert"><img
                                    src="images/<%=totalProduct.getString(1)%>" alt=""
                                    class="pro-image-front" style="width: 150px; height: 100px;"><br><%=totalProduct.getString(2)%></td>
                            <td class="invert">
                                <div class="quantity">
                                    <div class="quantity-select">
                                        <form action="UpdateProductQuantity" method="post">
                                            <input type="hidden" value="<%=totalProduct.getInt(7)%>"
                                                   name="productId"> <input type="number"
                                                   name="quantity" value="<%=totalProduct.getInt(3)%>"
                                                   style="width: 50px; height: 35px;">&nbsp;<input
                                                   type="submit" class="btn btn-danger" value="Change" >
                                        </form>
                                    </div>
                                </div>
                            </td>
                            <td class="invert"><del><%=totalProduct.getString(4)%>&nbsp;Rs.
                                </del></td>
                            <td class="invert"><%=totalProduct.getString(5)%>&nbsp;Rs.</td>
                            <td class="invert"><%=totalProduct.getString(6)%>&nbsp;Rs.</td>
                            <td class="invert"><a
                                    href="removeProductFromCart.jsp?productId=<%=totalProduct.getInt(7)%>"
                                    onclick="return confirm('Are you sure you want to remove this item from cart?');"><i
                                        class="fa fa-trash"></i></a></td>
                        </tr>
                        <%
                            }
                        %>
                        <%
                        
                            double finalBill = 0.0;
                        Connection con2=JDBCUtils.getConnection();
                        PreparedStatement ps3=con2.prepareStatement("select sum(total_price), sum(mrp_price) from tblcart where customer_id='" + session.getAttribute("id") + "' ");
                        ResultSet totolAmount=ps3.executeQuery();   
                        if (totolAmount.next()) {
                                finalBill = totolAmount.getInt(1);
                            }
                        %>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2"><strong><center>
                                        Total Amount.:&nbsp;<%=finalBill%>
                                        Rs.</center></strong>
                            </td>
                        </tr>
                        <script>
                            $('.value-plus').on('click', function () {
                                var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) + 1;
                                divUpd.text(newVal);
                            });

                            $('.value-minus').on('click', function () {
                                var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) - 1;
                                if (newVal >= 1)
                                    divUpd.text(newVal);
                            });
                        </script>
                    </table>
                </div>
                <div>
                    <br> <br> <br>
                </div>
                <form action="GetProductOrders" method="post">
                    <h4>
                        <font color="blue"><strong>Billing Details</strong></font>
                    </h4>
                    <br>
                    <%
                    Connection con4=JDBCUtils.getConnection();
                    PreparedStatement ps4=con4.prepareStatement("select * from signup1 where id='" + session.getAttribute("id") + "' and Username='" + session.getAttribute("Username") + "'");
                       ResultSet userInfoResult=ps4.executeQuery();
                    if (userInfoResult.next()) {
                    %>
                    <div>
                        <div class="form-group">
                            <label>Your Name</label> <input type="text" name="Username"
                                                            value="<%=userInfoResult.getString(4)%>" placeholder=""
                                                            required="" style="width: 1135px; height: 40px;"
                                                            class="form-control" readonly>
                       
                        </div>
                        <div class="form-group">
                            <label>Email Id</label> <input type="text" name="Email"
                                                           value="<%=userInfoResult.getString(2)%>" placeholder=""
                                                           required="" style="width: 1135px; height: 40px;"
                                                           class="form-control" readonly>
                        
                        
                        </div>
                        
                        <div class="form-group">
                            <label>Select Payment Mode</label> <select name="payment"
                                                                       style="width: 1135px; height: 40px;" class="form-control">
                                <option>COD</option>
                                <option>Credit Card</option>
                                <option>Debit Card</option>
                                <option>Online Banking</option>
                                <option>UPI Id</option>
                            </select>
                        </div>
                        <div>
                            <%
                            Connection con5=JDBCUtils.getConnection();
                            PreparedStatement ps5=con5.prepareStatement("select max(payment_id) from tblorders");
                            ResultSet rsPaymentId=ps5.executeQuery();    
                            if (rsPaymentId.next()) {
                                    paymentId = rsPaymentId.getInt("max(payment_id)");
                                    paymentId++;
                                }
                            %>
                            <input type="text" name="payment_id" value="<%=paymentId%>" hidden>
                            <input type="submit" value="Buy Products" class="btn btn-success" onclick="return confirm('Are you sure Do you want to buy this order?');">
                        </div>
                    </div>
                </form>
                <%
                    }
                %>

                <%
                } else {
                %>
                <center>
                    <strong>Thanks for giving order.</strong>
                </center>
                <%
                    if (index == 0) {
                %>
                <center>
                    <strong>There is no item(s) in your Cart.</strong>
                </center>
                <%
                    }
                %>
                <%
                    }
                %>
               
                        <a href="products.jsp"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>Back To Shopping</a>
                    </div>
                </div>
            </div>
        </div>
       
        <%
            } else {
                response.sendRedirect("Index.html");
            }
        %>
    </body>
</html>