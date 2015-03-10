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
public class ManageCustomer {

    private Connection conn = null;
    private PreparedStatement pst = null;

    public void insertCustomer(Customer customer){

        DBConn dbcon = new DBConn();
        conn = dbcon.conn();

        String insertdbSQL = "insert into customers (customerName, password, email, gender,birthday, has_family, cur_time )" +
                "values(?,?,?,?,?,?,?)";

        try
        {
            pst = conn.prepareStatement(insertdbSQL);

            pst.setString(1, customer.getCustomerName());
            pst.setString(2, customer.getPassword());
            pst.setString(3, customer.getEmail());
            pst.setString(4, customer.getGender());
            pst.setString(5, customer.getBirthday());
            pst.setString(6, customer.getHas_family());
            pst.setString(7, dbcon.getCurrentDate());
            pst.executeUpdate();
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
    }
}
