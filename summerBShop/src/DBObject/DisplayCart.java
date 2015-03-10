package DBObject;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/4
 * Time: PM3:46
 * To change this template use File | Settings | File Templates.
 */
public class DisplayCart extends Cart {
    private String img;
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getimg() {
        return img;
    }

    public void setimg(String img) {
        this.img = img;
    }

}
