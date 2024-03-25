import java.io.IOException;

public interface ShoppingManager {
    public void addProduct();

    public void removeProduct();

    public void printProduct();

    public void saveFile() throws IOException;
}
