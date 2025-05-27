package entity;

public abstract class Medicineshop 
{
    private int quantity;  // Changed to private
    private double price;  // Changed to private

    // Default constructor
    public Medicineshop() 
    {
        System.out.println("Medicineshop.");
    }

    // Parameterized constructor
    public Medicineshop(int quantity, double price) 
    {
        System.out.println("Medicineshop initialized.");
        setQuantity(quantity);
        setPrice(price);
    }

    //  getter and setter for quantity
    public void setQuantity(int quantity) 
    {
        if (quantity > 0) 
        {
            this.quantity = quantity;
        } 
        else 
        {
            System.out.println("Quantity cannot be negative");
        }
    }

    public int getQuantity() 
    {
        return quantity;
    }

    // getter and setter for price
    public void setPrice(double price)
     {
        if (price > 0)
        {
            this.price = price;
        } 
        else 
        {
            System.out.println("Price cannot be less than zero");
        }
    }

    public double getPrice() 
    {
        return price;
    }

    // Abstract methods to be implemented in child classes
    public abstract void show();

    public abstract double totalPrice();

    public abstract String getProduct();
}
