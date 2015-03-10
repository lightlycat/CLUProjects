package DBController;

import DBObject.Cart;
import DBObject.DisplayCart;
import DBObject.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/3
 * Time: AM12:11
 * To change this template use File | Settings | File Templates.
 */
public class RetriveCart {
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet rs ;

    public ArrayList<DisplayCart> getCartDisolayWithCusomterid(String cid) {
        ArrayList<DisplayCart> result = new ArrayList<DisplayCart>();
        String sql = "select * from carts where customerid = " + cid + " and status = 1";
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            RetriveProduct rp = new RetriveProduct();
            while(rs.next())
            {
                DisplayCart c = new DisplayCart();

                Product p = rp.getProductInfoWithID(Integer.valueOf(rs.getObject(3).toString()));
                c.setCustomerID(Integer.valueOf(rs.getObject(2).toString()));
                c.setProductName(p.getProductName());
                c.setimg(p.getImg());
                c.setAmount(Integer.valueOf(rs.getObject(4).toString()));
                result.add(c);
            }
            rs.close();
            statement.close();
            dbcon.close();

        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        finally
        {
            dbcon.close();
        }
        return result;
    }
}

