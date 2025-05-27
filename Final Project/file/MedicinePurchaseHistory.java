package file;
import entity.MedicineItem;
import java.io.*;
import java.util.*;

public class MedicinePurchaseHistory 
{

    // Load medicine inventory from file
    public static void loadFromFile(MedicineItem[] medicines) 
    {
        try {
            Scanner sc = new Scanner(new File("./File/MedicineHistory.txt"));
            while (sc.hasNextLine()) 
            {
                String[] data = sc.nextLine().split(";");
                if (data.length == 4) 
                {
                    int index = Integer.parseInt(data[0]);
                    String productName = data[1];
                    int quantity = Integer.parseInt(data[2]);
                    double totalPrice = Double.parseDouble(data[3]);
                    double unitPrice = totalPrice / quantity;

                    if (index >= 0 && index < medicines.length) 
                    {
                        medicines[index] = new MedicineItem(productName, quantity, unitPrice);
                    } 
                    else 
                    {
                        System.out.println("Index out of bounds: " + index);
                    }
                }
            }
            sc.close();
        }
         catch (Exception e) 
         {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
         }
    }

    // Save medicine inventory to file
    public static void saveChangesInFile(MedicineItem[] medicines) 
    {
        try {
            FileWriter writer = new FileWriter(new File("./File/MedicineHistory.txt"));
            for (int i = 0; i < medicines.length; i++) 
            {
                if (medicines[i] != null) 
                {
                    String name = medicines[i].getMedicineName();
                    int quantity = medicines[i].getQuantity();
                    double totalPrice = medicines[i].totalPrice();

                    writer.write(i + ";" + name + ";" + quantity + ";" + totalPrice + "\n");
                }
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Save purchase history (names and quantities)
    public static void savePurchase(String[] names, int[] quantities, int count) {
        try (FileWriter writer = new FileWriter("./File/MedicineHistory.txt", true)) {
            for (int i = 0; i < count; i++) 
            {
                writer.write(names[i] + ";" + quantities[i] + "\n");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving purchase history: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Read purchase history and return as String
    public static String readHistory() 
    {
        StringBuilder history = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("./File/MedicineHistory.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                history.append(line).append("\n");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading purchase history: " + e.getMessage());
            e.printStackTrace();
        }
        return history.toString();
    }

    // Clear purchase history
    public static void clearHistory() 
    {
        try (FileWriter writer = new FileWriter("./File/MedicineHistory.txt")) 
        {
            // Writing nothing clears the file
        } 
        catch (IOException e) 
        {
            System.out.println("Error clearing purchase history: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
