package Dispatch;


import DBController.RetriveCart;
import DBController.RetriveCustomer;
import DBObject.DisplayCart;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CartInformation extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CartInformation() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.print("......doPost");
        String customerName = request.getParameter("customerName");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        System.out.println("......run getInfo");

        RetriveCustomer rc = new RetriveCustomer();
        String id = rc.getCustomerIDwithName(customerName);

        ArrayList<DisplayCart> carts = getInfo(id);
        JsonElement cartsObj = gson.toJsonTree(carts);

        if(carts.get(0) == null){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("carts", cartsObj);
        System.out.println("myObj: " + myObj);
        out.println(myObj.toString());

        out.close();

    }

    //Get Country Information
    private ArrayList<DisplayCart> getInfo(String customerID) {

        ArrayList<DisplayCart> result;
        RetriveCart rc = new RetriveCart();
        result = rc.getCartDisolayWithCusomterid(customerID);
        //System.out.print(".....get result" + result.size());
        return result;

    }

}
