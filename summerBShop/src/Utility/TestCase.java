package Utility;

import DBController.*;
import DBObject.Cart;
import DBObject.Customer;
import DBObject.DisplayCart;
import DBObject.Product;
import com.javafx.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/2
 * Time: PM10:35
 * To change this template use File | Settings | File Templates.
 */
public class TestCase {

    public static void main(String[] args) {
        //TestCase t = new TestCase();
        //t.insertCart();
        String classpath = System.getProperty("java.class.path");
        System.out.println("classpath= " + classpath);
        //t.selectCart();
        //t.checkLogin();

        //t.insertCustomer();
        //t.insertProduct();

    }
    private void insertCart() {
        ManageCart mc = new ManageCart();
        mc.insertCart("1","11");



    }
    private void insertProduct(){
        ManageProduct mp = new ManageProduct();
        Product p = new Product();
        p.setPrice("15");
        p.setProductName("pocketnotebook pink");
        p.setImg("1subject_pocketnotebook_pink1_c.jpg");
        p.setBrand("poppin");
        p.setCategoryID(11);
        p.setSupplierID(1);
        p.setSKU("A0018");
        mp.insertProduct(p);

    }
    private void insertCustomer() {
        ManageCustomer mc = new ManageCustomer();
        Customer c = new Customer();
        c.setCustomerName("Fiona");
        c.setPassword("1234");
        c.setBirthday("1987-12-12");
        c.setEmail("f5@gmail.com");
        c.setGender("1");
        c.setHas_family("1");

        mc.insertCustomer(c);
    }
    private void checkLogin(){
        boolean re = false;
        RetriveCustomer rc  = new RetriveCustomer();
        re = rc.getCustomerWithNamePsd("Fiona", "1234");
        System.out.println("Is member? " + re);
        Customer cc = rc.getCustomerInfo("1");
        System.out.println("customer name: " + cc.getCustomerName());
    }
    private void selectCart() {
        ArrayList<DisplayCart> result = new ArrayList<DisplayCart>();
        RetriveCart rc = new RetriveCart();
        result = rc.getCartDisolayWithCusomterid("1");
        for(Cart c: result){
            System.out.println("This cart has productID:" + c.getProductID());
        }


    }

}
