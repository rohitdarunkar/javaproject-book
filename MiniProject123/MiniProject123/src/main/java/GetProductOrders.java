
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ListUtil.JDBCUtils;

import java.sql.*;

@WebServlet(name = "GetProductOrders", urlPatterns = {"/GetProductOrders"})
public class GetProductOrders extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Creating Session
        HttpSession hs = request.getSession();
        int order_no = 1000;
        int orderProducts = 0;
        //Getting all the parameters from the user
        int paymentId = Integer.parseInt(request.getParameter("payment_id"));
        String customerName = request.getParameter("Username");
        
        String email = request.getParameter("Email");
      
        String product_name = null;
        int quantity = 0;
        String product_price = null;
        String product_selling_price = null;
        String product_total_price = null;
        String order_status = null;
        String payment_mode = request.getParameter("payment");
        HttpSession session = request.getSession();
        //Storing payment attrbute in session
        session.setAttribute("paymentId", paymentId);

        try {
            //Getting maximium column of tblorders table

            Connection con=JDBCUtils.getConnection();
            PreparedStatement ps=con.prepareStatement("select max(order_no) from tblorders");
            ResultSet rsMaxOrderNo=ps.executeQuery();
            if (rsMaxOrderNo.next()) {
                order_no = rsMaxOrderNo.getInt(1);
                order_no = 1000 + order_no;
            }
            //Getting all the orders from the database

            Connection con1=JDBCUtils.getConnection();
            PreparedStatement ps1=con1.prepareStatement("select tblproduct.image,tblproduct.name,tblcart.quantity,tblcart.mrp_price,tblcart.discount_price,tblcart.total_price,tblcart.product_id from tblproduct,tblcart where tblproduct.id=tblcart.product_id and customer_id='"
                    + session.getAttribute("id") + "' ");
            ResultSet totalProduct=ps1.executeQuery();
            while (totalProduct.next()) {
                order_no++;
                String image = totalProduct.getString(1);
                product_name = totalProduct.getString(2);
                quantity = totalProduct.getInt(3);
                product_price = totalProduct.getString(4);
                product_selling_price = totalProduct.getString(5);
                product_total_price = totalProduct.getString(6);
                order_status = "Pending";
                //Inserting product details inside the table
                Connection con4=JDBCUtils.getConnection();
                PreparedStatement ps4=con4.prepareStatement(
                		"insert into tblorders(order_no,customer_name,email_id,image,product_name,quantity,product_price,product_selling_price,product_total_price,order_status,payment_mode,payment_id) values('"
                                + order_no + "','" + customerName + "','" 
                                + email + "','"  + image + "','"
                                + product_name + "','" + quantity + "','" + product_price + "','"
                                + product_selling_price + "','" + product_total_price + "','" + order_status + "','"
                                + payment_mode + "','" + paymentId + "')");
                orderProducts=ps4.executeUpdate();
            }
            Connection con2=JDBCUtils.getConnection();
            PreparedStatement ps2=con2.prepareStatement("delete from tblcart where customer_id='" + session.getAttribute("id") + "'");
            orderProducts=ps2.executeUpdate();
            if (orderProducts > 0) {
                //Sending response back to the user/customer
                String message = "Thank you for your order.";
                hs.setAttribute("success", message);
                response.sendRedirect("checkout.jsp");
            } else {
                response.sendRedirect("checkout.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
