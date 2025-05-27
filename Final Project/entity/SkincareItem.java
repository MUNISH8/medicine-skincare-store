package entity;

public class SkincareItem extends Medicineshop 
{
    private String productName;

    // Default constructor
    public SkincareItem() 
    {
        System.out.println("Skincare.");
    }

    // Parameterized constructor
    public SkincareItem(String productName, int quantity, double price) 
    {
        super(quantity, price); // Call to parent constructor
        System.out.println("Skincare initialized.");
        setProductName(productName);
    }

    // Setter and getter for productName
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }


    public void show() 
    {
        System.out.println("Skincare Product Name: " + productName);
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Price: " + getPrice() + " USD");
    }


    public double totalPrice() 
    {
        return getPrice() * getQuantity();
    }


    public String getProduct() 
    {
        return "Skincare Product Name: " + productName + "\n" +
                "Quantity: " + getQuantity() + "\n" +
                "Price: " + getPrice() + " USD\n";
    }
}
