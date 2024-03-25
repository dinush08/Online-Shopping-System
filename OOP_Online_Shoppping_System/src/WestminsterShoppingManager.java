import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager{

    //create arraylist
    private ArrayList<Product> productArrayList;
    private int option;

    public WestminsterShoppingManager(){
        productArrayList = new ArrayList<>();
        loadFile();
        Scanner input = new Scanner(System.in);
        UserInterface u;
        do {
            System.out.println("_______________________________________________");
            System.out.println("Enter 1 to add a new product");
            System.out.println("Enter 2 to delete a product");
            System.out.println("Enter 3 to print the list of the products");
            System.out.println("Enter 4 to save the file");
            System.out.println("Enter 5 to open the GUI");
            System.out.println("Enter 6 to exist");

            while(true){
                try{
                    option = input.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input! please enter valid input !");
                    input.nextLine();
                }
            }

            switch (option){
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    printProduct();
                    break;
                case 4:
                    try {
                        saveFile();
                    } catch (IOException e) {
                        System.out.println("Error has occurred !");;
                    }
                    break;
                case 5:
                    User user = new User("user name" , "new password" , 10);
                    u = new UserInterface(user);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please enter a valid option !");
            }
        }while(option!=6);
    }
//add product method
    public void addProduct(){
        Scanner input = new Scanner(System.in);
        int type;
        int numberOfAvailableProducts;
        double price;
        int warranty;

        if (productArrayList.size()<50){
            while(true){
                try{
                    while(true){
                        System.out.println(
                                "Press 1 to add Electronic products.\n" +
                                "Press 2 to add Clothing products.");
                        type = input.nextInt();
                        if(type == 1 || type == 2){
                            break;
                        }
                        else{
                            System.out.println("Invalid input! Please enter valid number ");
                        }
                    }
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input! Please enter valid number ");
                    input.nextLine();
                }
            }
            if (type == 1){
                input.nextLine();
                System.out.print("Enter product id -: ");
                String productID = input.nextLine();
                System.out.print("Enter product name -: ");
                String productName = input.nextLine();

                while(true){
                    try{
                        System.out.print("Enter number of available item -: ");
                        numberOfAvailableProducts = input.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input! Please enter valid number of available item");
                        input.nextLine();
                    }
                }

                while(true){
                    try{
                        System.out.print("Enter price -: ");
                        price = input.nextDouble();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid price");
                        input.nextLine();
                    }
                }

                while(true){
                    try{
                        System.out.print("Enter warranty period  -: ");
                        warranty  = input.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid warranty period");
                        input.nextLine();
                    }
                }
                input.nextLine();
                System.out.print("Enter brand -: ");
                String brand = input.nextLine();

                Product e1 = new Electronics(productID,productName,numberOfAvailableProducts,price,brand,warranty);
                productArrayList.add(e1);
                System.out.println("adding complete");
                System.out.println("_______________________________________________");

            } else if (type == 2) {
                input.nextLine();
                System.out.print("Enter product id -: ");
                String productID = input.nextLine();

                System.out.print("Enter product name -: ");
                String productName = input.nextLine();

                while(true){
                    try{
                        System.out.print("Enter number of available item -: ");
                        numberOfAvailableProducts = input.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid number of available item");
                        input.nextLine();
                    }
                }

                while(true){
                    try{
                        System.out.print("Enter price -: ");
                        price = input.nextDouble();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input! Please enter a valid price");
                        input.nextLine();
                    }
                }
                input.nextLine();
                System.out.print("Enter colour -: ");
                String colour = input.nextLine();

                System.out.print("Enter size -: ");
                String size  = input.nextLine();


                Product c1 = new Clothing(productID,productName,numberOfAvailableProducts,price,size,colour);
                productArrayList.add(c1);
                System.out.println("adding complete");

            }
        }
        else{
            System.out.println("Can not add above 50 products ");
        }
    }
// remove product method
    public void removeProduct() {
        //remove product from list
        if (productArrayList.isEmpty()) {
            System.out.println("Product list is empty.");
        } else {
            boolean IDfound = false;
            while (!IDfound){
                System.out.print("Enter product id -: ");
                Scanner input = new Scanner(System.in);
                String productID = input.nextLine();
                for (int i = 0; i < productArrayList.size(); i++) {
                    if (productArrayList.get(i).getProductID().equals(productID)) {
                        System.out.println(productArrayList.get(i).toString());
                        productArrayList.remove(i);
                        System.out.println("Product removed");
                        System.out.println("Total number of products in the list =  " + productArrayList.size());
                        IDfound = true;
                        break;
                    }
                }
                if (!IDfound){
                    System.out.println("ID number is not found, please enter correct ID number");
                }
            }
        }

    }

//print product method
    public void printProduct(){
        Collections.sort(productArrayList, new Comparator<Product>(){
            public int compare(Product p1, Product p2){
                return p1.getProductID().compareTo(p2.getProductID());
            }
        });
        for (Object obj : productArrayList) {
            if (obj instanceof Clothing) {
                Clothing clothing = (Clothing) obj;
                System.out.println("Clothing: " + clothing.toString());
            } else if (obj instanceof Electronics) {
                Electronics electronics = (Electronics) obj;
                System.out.println("Electronics: " + electronics.toString());
            } else {
                System.out.println("Unknown object: " + obj.toString());
            }
        }
    }
//saveFile method
    public void saveFile() throws IOException {
        try{
            File f1 = new File("File.txt");
            if (!f1.exists()){
                f1.createNewFile();
            }
            FileOutputStream fout1 = new FileOutputStream(f1);
            ObjectOutputStream obj1 = new ObjectOutputStream(fout1);
            Iterator it = productArrayList.iterator();
            while (it.hasNext()){
                Product p1 = (Product) it.next();
                obj1.writeObject(p1);

            }
            System.out.println("File saved");
            obj1.close();
            fout1.close();

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println("Error has been occoured");
        }catch (Exception e){
            System.out.println("Error has been occoured");
        }


    }
//loadFile method
    public void loadFile()  {
        try{
            File f1 = new File("File.txt");
            FileInputStream fout1 = new FileInputStream(f1);
            ObjectInputStream obj1 = new ObjectInputStream(fout1);

            while (true){
                try {
                    Product p1 = (Product) obj1.readObject();
                    productArrayList.add(p1);
                } catch (EOFException e) {
                    break;
                }
            }
            obj1.close();
            fout1.close();

        }catch (FileNotFoundException e){
            System.out.println("file not found");
        } catch (IOException e){
            System.out.println("Error has been occoured");
        }catch (ClassNotFoundException e){
            System.out.println("class is not found ");
        }

    }
}
