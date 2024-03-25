public class Clothing extends Product{
    private String size;
    private String colour;

    public Clothing(String productID, String productName, int numberOfAvailableItems, double price,String size, String colour) {
        super(productID, productName, numberOfAvailableItems, price);
        this.colour = colour;
        this.size = size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return super.toString() + ", size = " + size + ", colour = " + colour ;
    }
}


