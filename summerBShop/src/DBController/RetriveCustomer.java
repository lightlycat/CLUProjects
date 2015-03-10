package DBController;

import DBObject.Customer;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/2
 * Time: PM11:41
 * To change this template use File | Settings | File Templates.
 */
public class RetriveCustomer {

    private Connection conn = null;
    private Statement statement = null;
    private ResultSet rs ;

    public boolean getCustomerWithNamePsd(String name,String psd) {
        boolean result = false;
        String sql = "select * from customers where customerName = '" + name +"' and password= '" + psd + "'";
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            result = rs.next();
            System.out.println("result =" + result);
            rs.close();
            statement.close();
            statement = null;

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
    public String getCustomerIDwithName(String name) {
        String c = null;
        String sql = "select customerID from customers where customerName = '" + name +"'" ;
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();
        System.out.println("getCustomerIDwithName sql:" + sql);
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()){
                c= rs.getObject(1).toString();
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
        return c;
    }
    public Customer getCustomerInfo(String cid) {
        Customer c = new Customer();
        String sql = "select * from customers where customerID = " + cid ;
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()){
                c.setCustomerName(rs.getObject(2).toString());
                c.setPassword(rs.getObject(3).toString());
                c.setEmail(rs.getObject(4).toString());
                c.setGender(rs.getObject(5).toString());
                c.setBirthday(rs.getObject(6).toString());
                c.setHas_family(rs.getObject(7).toString());

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
        return c;
    }

}
