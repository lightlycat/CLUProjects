package DBObject;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/3
 * Time: AM12:08
 * To change this template use File | Settings | File Templates.
 */
public class Cart {
    private String status;
    private int productID;
    private int customerID;
    private int amount;


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
