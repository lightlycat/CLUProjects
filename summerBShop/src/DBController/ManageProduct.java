package DBController;



import DBObject.Product;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/2
 * Time: PM10:12
 * To change this template use File | Settings | File Templates.
 */
public class ManageProduct {

    private Connection conn = null;
    private Statement statement = null;
    private ResultSet rs ;
    private PreparedStatement pst = null;

    public void insertProduct(Product pro){

        DBConn dbcon = new DBConn();
        conn = dbcon.conn();

        String insertdbSQL = "insert into products (SKU,product_Name,price, img, brand, categoryID, supplierID, cur_time )" +
                "values(?,?,?,?,?,?,?,?)";

        try
        {
            pst = conn.prepareStatement(insertdbSQL);

            pst.setString(1, pro.getSKU());
            pst.setString(2, pro.getProductName());
            pst.setString(3, pro.getPrice());
            pst.setString(4, pro.getImg());
            pst.setString(5, pro.getBrand());
            pst.setInt(6, pro.getCategoryID());
            pst.setInt(7, pro.getSupplierID());
            pst.setString(8, dbcon.getCurrentDate());
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
