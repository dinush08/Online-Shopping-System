import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class DisplayUserInterface extends JFrame {
    private ArrayList<Product> productList;
    private ArrayList<Product> clothingList;
    private ArrayList<Product> electronicsList;
    Object[][] data;
    private String dropDownMenu;
    private String comboItem;
    private JTextPane PaneDetails;
    private JTable table;
    private JPanel productDetails;
    private JScrollPane scrollpane;
    private DefaultTableModel tableModel;
    private JButton cartButton;
    private JButton addCartButton;

    private User user;
    public DisplayUserInterface(User user){
        productList = new ArrayList<Product>();
        clothingList = new ArrayList<Product>();
        electronicsList = new ArrayList<Product>();
        this.user = user;
        loadFile();
        setLayout(new BorderLayout());
        // create top panel

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(new JLabel("Select Product Category: "));
        JComboBox<String> comboBox = new JComboBox<>();

        comboBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 100));
        String[] items = {"Electronic", "Clothing"};
        comboBox.addItem("Select an item"); // Add a default item
        for (String item : items) {
            comboBox.addItem(item);
        }
        comboBox.setBounds(700, 50, 150, 30);
        topPanel.add(comboBox);
        comboBox.addActionListener(e -> {
            comboItem = (String) comboBox.getSelectedItem();
            comboEvent();
        });

        cartButton = new JButton("Shopping Cart");

        topPanel.add(cartButton);

        cartButton.addActionListener(e -> {
            ShopCartUserInterface newWindow = new ShopCartUserInterface();
            newWindow.setVisible(true);
        });

        add(topPanel, BorderLayout.NORTH);
//center panel
        Object[][] data = new Object[productList.size()][4];
        String columnName [] = { "Product ID" ,"Name" , "Category" , "Price($)"};

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product instanceof Clothing){
                dropDownMenu = "Clothing";
                clothingList.add(product);
            }else {
                dropDownMenu = "Electronic";
                electronicsList.add(product);
            }
            data[i] = new Object[]{product.getProductID(), product.getProductName(), dropDownMenu, product.getPrice()}; 
        }

        table = new JTable();
        tableModel = new DefaultTableModel(data, columnName);
        table.setModel(tableModel);

        scrollpane = new JScrollPane(table);
        table.setGridColor(Color.black);

        PaneDetails = new JTextPane();
        PaneDetails.setEditable(false);
        add(scrollpane, BorderLayout.CENTER);


// create bottom panel
        productDetails = new JPanel();
        productDetails.setLayout(new BoxLayout(productDetails, BoxLayout.Y_AXIS));
        productDetails.setVisible(true);
        add(productDetails, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e -> {
            int select_Row = table.getSelectedRow();
            if (select_Row != -1) {
                Display_Product_Details(select_Row);
            }
        });
        productDetails.add(PaneDetails);
        addCartButton = new JButton("Add To Cart");
        JPanel addCartPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addCartPannel.add(addCartButton);
        productDetails.add(addCartPannel);


        addCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Product selectedProduct = productList.get(selectedRow);
                    user.getShoppingCart().addProduct(selectedProduct);
                    JOptionPane.showMessageDialog(DisplayUserInterface.this, "Product added to the Shopping Cart!");
                    table.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                    Display_Product_Details(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(DisplayUserInterface.this, "Please select a product from the table.");
                }
            }
        });
    }
    public void Display_Product_Details(int select_Row){
        if (select_Row >= 0 && select_Row < productList.size()) {
            Product select_Product = productList.get(select_Row);

            // Build the details text
            StringBuilder detailsText = new StringBuilder();
            detailsText.append("<html><body style='margin: 50px;'>");

            // Header
            detailsText.append("<h1>Selected Product - Details</h1>");

            // Product details
            detailsText.append("<p><b>Product Id:</b> ").append(select_Product.getProductID()).append("</p>");
            detailsText.append("<p><b>Name:</b> ").append(select_Product.getProductName()).append("</p>");
            if (select_Product instanceof Electronics) {
                detailsText.append("<p><b>Brand:</b> ").append(((Electronics) select_Product).getBrand()).append("</p>");
                detailsText.append("<p><b>Warranty Period:</b> ").append(((Electronics) select_Product).getWarranty()).append(" months</p>");
            } else {
                detailsText.append("<p><b>Size:</b> ").append(((Clothing) select_Product).getSize()).append("</p>");
                detailsText.append("<p><b>Colour:</b> ").append(((Clothing) select_Product).getColour()).append("</p>");
            }
            detailsText.append("<p><b>Items Available:</b> ").append(select_Product.getNumberOfAvailableItems()).append("</p>");

            detailsText.append("</body></html>");

            // Set the formatted text to the JTextPane
            PaneDetails.setContentType("text/html");
            PaneDetails.setText(detailsText.toString());
        }
        addCartButton = new JButton("Shopping Cart");
    }
    public void loadFile()  {
        try{
            File f1 = new File("File.txt");
            FileInputStream fot = new FileInputStream(f1);
            ObjectInputStream obj = new ObjectInputStream(fot);

            while (true){
                try {
                    Product p1 = (Product) obj.readObject();
                    productList.add(p1);
                } catch (EOFException e) {
                    break;
                }
            }
            obj.close();
            fot.close();

        }catch (FileNotFoundException e){
            System.out.println("file not found");
        } catch (IOException e){
            System.out.println("there is a Error");
        }catch (ClassNotFoundException e){
            System.out.println("some classes are not found ");
        }catch (Exception e){
            System.out.println("error");
        }
    }
    public void comboEvent(){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        if(comboItem.equals("Clothing")) {
            Object[][] data = new Object[clothingList.size()][4];
            String columnName2[] = {"Product ID", "Name", "Category", "Price($)"};
            for (int i = 0; i < clothingList.size(); i++) {
                Product product = clothingList.get(i);
                data[i] = new Object[]{product.getProductID(), product.getProductName(), "Clothing", product.getPrice()};
                tableModel = new DefaultTableModel(data, columnName2);
                table.setModel(tableModel);
            }
        }
        else if (comboItem.equals("Electronic")){
            Object[][] data3 = new Object[electronicsList.size()][4];
            String columnName3 [] = { "Product ID" ,"Name" , "Category" , "Price($)"};
            for (int i = 0; i < electronicsList.size(); i++) {
                Product product = productList.get(i);
                data3[i] = new Object[]{product.getProductID(), product.getProductName(), "Electronic", product.getPrice()};
            }
            tableModel = new DefaultTableModel(data3, columnName3);
            table.setModel(tableModel);
        }
        else{
            Object[][] data3 = new Object[productList.size()][4];
            String columnName [] = { "Product ID" ,"Name" , "Category" , "Price($)"};
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i) instanceof Electronics){
                    Product product = productList.get(i);
                    data3[i] = new Object[]{product.getProductID(), product.getProductName(), "Electronic", product.getPrice()};
                }
                else {
                    Product product = productList.get(i);
                    data3[i] = new Object[]{product.getProductID(), product.getProductName(), "Clothing", product.getPrice()};
                }
            }
            tableModel = new DefaultTableModel(data3, columnName);
            table.setModel(tableModel);
        }
    }
}

