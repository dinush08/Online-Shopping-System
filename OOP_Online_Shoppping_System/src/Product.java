import java.io.Serializable;

public abstract class  Product implements Serializable {

private static final long seriaVersionID = 1L;
private String  productID;
private String  productName;
private int  numberOfAvailableItems;
private double price;

public Product(String productID, String productName, int numberOfAvailableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.numberOfAvailableItems = numberOfAvailableItems;
        this.price = price;
        }

public String getProductID() {
        return productID;
        }

public String getProductName() {
        return productName;
        }

public int getNumberOfAvailableItems() {
        return numberOfAvailableItems;
        }

public double getPrice() {
        return price;
        }

public void setProductID(String productID) {
        this.productID = productID;
        }

public void setProductName(String productName) {
        this.productName = productName;
        }

public void setNumberOfAvailableItems(int numberOfAvailableItems) {
        if(numberOfAvailableItems >= 0){
        this.numberOfAvailableItems = numberOfAvailableItems;
        }
        else{
        System.out.println("number Of Available Items can not be negative ");
        }
        }

public void setPrice(double price) {
        if(price >= 0){
        this.price = price;
        }
        else{
        System.out.println("price can not be negative ");
        }
        }

public String toString() {
        return "productID = " + productID + ", productName = " + productName + ", numberOfAvailableItems = " + numberOfAvailableItems + ", price = " + price ;
        }
}
