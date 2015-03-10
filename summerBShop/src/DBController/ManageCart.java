package DBController;

import DBObject.Customer;


import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/2
 * Time: PM11:01
 * To change this template use File | Settings | File Templates.
 */
public class ManageCart {

    private Connection conn = null;
    private PreparedStatement pst = null;

    public boolean insertCart(String cid, String pid){

        Boolean result = false;
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();

        String insertdbSQL = "insert into carts (customerID, productID, status, amount,cur_time )" +
                "values(?,?,?,?,?)";

        try
        {
            pst = conn.prepareStatement(insertdbSQL);
            pst.setInt(1,Integer.parseInt(cid));
            pst.setInt(2,Integer.parseInt(pid));
            pst.setString(3, "1");
            pst.setInt(4, 1);
            pst.setString(5, dbcon.getCurrentDate());
            pst.executeUpdate();
            result = true;
            pst.close();
        }

        catch(SQLException e)
        {
            System.out.println("InsertDB Exception :" + e.toString());
        }
        finally
        {

            dbcon.close();
        }
        System.out.println("resut::" + result);
        return result;
    }
}
