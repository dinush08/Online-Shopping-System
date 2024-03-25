import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    private ArrayList<Product> cart;

    public ShoppingCart(){
        this.cart = new ArrayList<>();
    }
    public void addProduct(Product product){
        cart.add(product);
        System.out.println("adding is complete ");
    }
    public void removeProduct() {
        String found = "no";
        while (true) {
            System.out.println("if you want to remove product, enter the product id ");
            Scanner input = new Scanner(System.in);
            String pId = input.nextLine();
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getProductID() == pId) {
                    found = "yes";
                    cart.get(i).toString();
                    cart.remove(i);
                    System.out.println("total number of product in the list =  " + cart.size());
                } else {
                    System.out.println("Enter valid product id ");
                }
            }
            if (found == "yes") {
                break;
            }
        }
    }
    public double totalCost(){
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total += cart.get(i).getPrice();
        }
        return total;
    }

}
