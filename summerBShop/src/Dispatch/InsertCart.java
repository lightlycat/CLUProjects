package Dispatch;


import DBController.ManageCart;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertCart extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public InsertCart() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.print("......doPost");
        String customerID = request.getParameter("customerID");
        String productID = request.getParameter("productID");
        System.out.println("parameters= " + customerID + ":" + productID);

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
        System.out.println("......run InsertCart");


        ManageCart mc = new ManageCart();
        if(!mc.insertCart(customerID,productID)){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }

        System.out.println("myObj: " + myObj);
        out.println(myObj.toString());
        out.close();


    }


}
