package DBObject;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/8/2
 * Time: PM10:51
 * To change this template use File | Settings | File Templates.
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String password;
    private String email;
    private String gender;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHas_family() {
        return has_family;
    }

    public void setHas_family(String has_family) {
        this.has_family = has_family;
    }

    private String birthday;
    private String has_family;

}
