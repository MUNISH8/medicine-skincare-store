package entity;

public class MedicineItem extends Medicineshop 
{
    private String medicineName;

    // Default constructor
    public MedicineItem() 
    {
        System.out.println("Medicine.");
    }

    // Parameterized constructor
    public MedicineItem(String medicineName, int quantity, double price) 
    {
        super(quantity, price); // Call to parent constructor
        System.out.println("Medicine initialized.");
        setMedicineName(medicineName);
    }

    // Setter and getter for medicineName
    public void setMedicineName(String medicineName) 
    {
        this.medicineName = medicineName;
    }

    public String getMedicineName() 
    {
        return medicineName;
    }


    public void show() 
    {
        System.out.println("Medicine Name: " + medicineName);
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Price: " + getPrice() + " USD");
    }


    public double totalPrice() 
    {
        return getPrice() * getQuantity();
    }

    
    public String getProduct() 
    {
        return "Medicine Name: " + medicineName + "\n" +
                "Quantity: " + getQuantity() + "\n" +
                "Price: " + getPrice() + " USD\n";
    }
}
