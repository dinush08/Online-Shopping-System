public class User {
    private String username;
    private String password;
    private  ShoppingCart shoppingCart;
    private int numberofPurchases;

    public User(String username, String password ,int numberofPurchases) {
        this.username = username;
        this.password = password;
        this.shoppingCart = new ShoppingCart();
        this.numberofPurchases = numberofPurchases;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setnumberofPurchases(int numberofPurchases) {
        numberofPurchases = numberofPurchases;
    }

    public int getnumberofPurchases() {
        return numberofPurchases;
    }

    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }



}
