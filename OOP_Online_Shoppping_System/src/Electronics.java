public class Electronics extends Product{
    private String brand;
    private int warranty;

    public Electronics(String productID, String productName, int numberOfAvailableItems, double price, String brand,int warranty) {
        super(productID, productName, numberOfAvailableItems, price);
        this.brand = brand;
        this.warranty = warranty;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return super.toString() + ", brand = " + brand + ", warranty = " + warranty ;
    }
}
