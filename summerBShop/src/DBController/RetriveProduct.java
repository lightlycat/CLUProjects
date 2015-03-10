package DBController;

import DBObject.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/2
 * Time: PM9:58
 * To change this template use File | Settings | File Templates.
 */
public class RetriveProduct {

    private Connection conn = null;
    private Statement statement = null;
    private ResultSet rs ;

    public ArrayList<Product> getProductWithCat(String catid) {
        ArrayList<Product> result = new ArrayList<Product>();
        String sql = "select * from products where categoryID = " + catid;
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while(rs.next())
            {
                //products (productID,SKU,product_Name,price, img, brand, categoryID, supplierID, cur_time )
                Product p = new Product();
                p.setProductid(Integer.parseInt(rs.getObject(1).toString()));
                p.setSKU(rs.getObject(2).toString());
                p.setProductName(rs.getObject(3).toString());
                p.setPrice(rs.getObject(4).toString());
                p.setImg(rs.getObject(5).toString());
                result.add(p);
                //printout
//                System.out.print(rs.getObject(1) + " - ");
//                System.out.print(rs.getObject(2) + " - ");
//                System.out.println(rs.getObject(3));
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

    public void getProduct() {
        ArrayList<Product> result = new ArrayList<Product>();
        String sql = "select * from products" ;
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while(rs.next())
            {
                System.out.print(rs.getObject(1) + " - ");
                System.out.println(rs.getObject(2));
                System.out.println(rs.getObject(3));
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
    }
    public Product getProductInfoWithID(int id){
        Product p = new Product();
        //products (productID,SKU,product_Name,price, img, brand, categoryID, supplierID, cur_time )
        String sql = "select * from products where productID = " + id ;
        DBConn dbcon = new DBConn();
        conn = dbcon.conn();
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            while(rs.next())
            {
                p.setProductName(rs.getObject(3).toString());
                p.setPrice(rs.getObject(4).toString());
                p.setImg(rs.getObject(5).toString());

            }
            rs.close();
            statement.close();

        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        finally
        {
            dbcon.close();
        }
        return p;
    }


}
