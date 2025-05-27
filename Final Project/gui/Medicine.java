package gui;
import file.MedicinePurchaseHistory;
import entity.MedicineItem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Medicine
{
    private JFrame frame;  // Main window frame
    private JPanel buttonPanel;  // Panel Which holds the buttons like Buy, View Cart.
    private JTextArea messageArea;  // Area where i am  displaying the messages, cart contents, receipt, history

    // Arrays to store cart details: product names, prices, and quantities
    private String[] cartNames = new String[100];
    private int[] cartPrices = new int[100];
    private int[] cartQuantities = new int[100];
    private int cartCount = 0;  // number of items in cart

    // Constructor for GUI
    public Medicine()
    {
        frame = new JFrame("Medicine Store");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 255, 204));  // background

        // Adding product panels with image, name, price, quantity selector, and add button
        frame.add(createProductPanel("D:/Java Final Project/paracetamol.jpeg", 40, 30, "Paracetamol", 50));
        frame.add(createProductPanel("D:/Java Final Project/Omeprazole.jpeg", 190, 30, "Omeprazole", 120));
        frame.add(createProductPanel("D:/Java Final Project/Napa.jpeg", 340, 30, "Napa", 70));
        frame.add(createProductPanel("D:/Java Final Project/Seclo.jpeg", 490, 30, "Seclo", 90));
        frame.add(createProductPanel("D:/Java Final Project/Histacin.jpeg", 640, 30, "Histacin", 40));

        // Panel at the bottom to hold action buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 250, 900, 100);
        buttonPanel.setBackground(new Color(200, 220, 255));  // Light blue background

        // Buy button - to  purchase and save buy history
        JButton buyButton = new JButton("Buy");
        buyButton.setBounds(30, 30, 100, 35);
        buyButton.addActionListener(new BuyListener());
        buttonPanel.add(buyButton);

        // Cart button - to display  cart items
        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(140, 30, 120, 35);
        viewCartButton.addActionListener(new ViewCartListener());
        buttonPanel.add(viewCartButton);

        // Show Receipt button - shows total cost and itemized bill
        JButton receiptButton = new JButton("Show Receipt");
        receiptButton.setBounds(270, 30, 140, 35);
        receiptButton.addActionListener(new ShowReceiptListener());
        buttonPanel.add(receiptButton);

        // Purchase History button - displays all previous purchases from history file
        JButton historyButton = new JButton("Purchase History");
        historyButton.setBounds(420, 30, 160, 35);
        historyButton.addActionListener(new ShowHistoryListener());
        buttonPanel.add(historyButton);

        // Clear History button - clears the purchase history file
        JButton clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.setBounds(590, 30, 140, 35);
        clearHistoryButton.addActionListener(new ClearHistoryListener());
        buttonPanel.add(clearHistoryButton);

        // Back button - clears the purchase history file
        JButton backButton = new JButton("Back");
        backButton.setBounds(740, 30, 90, 35);
        backButton.addActionListener(new BackListener());
        backButton.setBackground(new Color(255,239,213));
        buttonPanel.add(backButton);

        frame.add(buttonPanel);

        // Text area to display messages, cart details, receipts, and history
        messageArea = new JTextArea();
        messageArea.setEditable(false);  // User cannot edit messages
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 13));  // Monospaced font for better readability
        messageArea.setLineWrap(true);  // Wrap lines
        messageArea.setWrapStyleWord(true);  // Wrap by words, not characters

        // Scroll pane for the text area to allow scrolling
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBounds(50, 370, 800, 250);
        frame.add(scrollPane);

        frame.setLocationRelativeTo(null);  // Center the window on screen
        frame.setVisible(true);  // Show the GUI
    }

    // Action listener for Buy button where i am calling buyItems method
    private class BuyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buyItems();
        }
    }

    // Action listener for View Cart button  cart contents or empty message
    private class ViewCartListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
              if (cartCount == 0)
            {
                messageArea.setText("Cart is empty.");
            }
              else
            {
                StringBuilder cartContents = new StringBuilder();
                cartContents.append("--- Cart Contents ---\n");
                for (int i = 0; i < cartCount; i++)
                {
                    cartContents.append(cartNames[i]).append(" x").append(cartQuantities[i]).append("\n");
                }
                messageArea.setText(cartContents.toString());
            }
        }
    }

    // Action listener for Show Receipt button  display total cost and itemized bill
    private class ShowReceiptListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            showReceipt();
        }
    }

    // Action listener for Purchase History button - displays purchase history from file
    private class ShowHistoryListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            showHistory();
        }
    }

    // Action listener for Clear History button - clears purchase history file
    private class ClearHistoryListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            clearHistory();
        }
    }

      private class BackListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            back();
        }
    }

   
    private JPanel createProductPanel(String imagePath, int x, int y, String productName, int price)
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, 140, 190);
        panel.setBackground(Color.LIGHT_GRAY);

        // Load and scale product image
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(10, 10, 120, 90);
        panel.add(imageLabel);

        // Product name label - centered and bold
        JLabel nameLabel = new JLabel(productName);
        nameLabel.setBounds(10, 110, 120, 20);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nameLabel);

        // Price label - centered
        JLabel priceLabel = new JLabel("Price: $" + price);
        priceLabel.setBounds(10, 135, 120, 20);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(priceLabel);

        // Label for quantity selector
        JLabel quantityLabel = new JLabel("Qty:");
        quantityLabel.setBounds(20, 160, 30, 20);
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(quantityLabel);

        // Spinner to select quantity 1 to 100
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));//(initial value,Minimum value,Maximum value,Step count)
        quantitySpinner.setBounds(60, 160, 50, 25);
        panel.add(quantitySpinner);

        // Small Add button to add the selected quantity of this product to cart
        JButton addButton = new JButton("Add");
        addButton.setBounds(115, 160, 20, 25);
        addButton.setFont(new Font("Arial", Font.PLAIN, 8));
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int quantity = (Integer) quantitySpinner.getValue();
                if (cartCount < 100)
                {
                    
                    cartNames[cartCount] = productName;
                    cartPrices[cartCount] = price;
                    cartQuantities[cartCount] = quantity;
                    cartCount++;
                    messageArea.setText("Added to cart: " + productName + " x" + quantity);
                }
                 else
                {
                    messageArea.setText("Cart is full!");
                }
            }
        });
        panel.add(addButton);

        return panel;
    }

    /*
        receipt showing each product, quantity, subtotal and total cost
     */
    private void showReceipt()
    {
        if (cartCount == 0)
        {
            messageArea.setText("Cart is empty.");
            return;
        }
        StringBuilder receipt = new StringBuilder();
        int total = 0;
        receipt.append("--- Receipt ---\n");
        for (int i = 0; i < cartCount; i++)
        {
            int subtotal = cartPrices[i] * cartQuantities[i];
            receipt.append(cartNames[i]).append(" x").append(cartQuantities[i]).append(" = $").append(subtotal).append("\n");
            total += subtotal;
        }
        receipt.append("Total: $").append(total);
        messageArea.setText(receipt.toString());
    }

    /*
      Finalize the purchase saveing the current cart items to a purchase history file
      and clear the cart
     */
     private void buyItems()
     {
         if (cartCount == 0)
         {
             messageArea.setText("Cart is empty. Add items before buying.");
             return;
         }

         try
         {
             MedicinePurchaseHistory.savePurchase(cartNames, cartQuantities, cartCount);
             messageArea.setText("Purchase completed successfully!");
             cartCount = 0; // Clearing the cart
         } 

          catch (Exception e)
          {
             messageArea.setText("Error saving purchase.");
             e.printStackTrace();
          }
     }


    /*
      Reads and display the purchase history from the file
     */
     private void showHistory()
     {
         try
         {
             String result = MedicinePurchaseHistory.readHistory();
             messageArea.setText(result);
         }
         catch (Exception e)
         {
             messageArea.setText("Error reading history.");
             e.printStackTrace();
         }
     }


    /**
     * Clears the purchase history by overwriting the history file with nothing
     */
     private void clearHistory() {
       try
       {
           MedicinePurchaseHistory.clearHistory();
           messageArea.setText("Purchase history cleared successfully!");
       }
       catch (Exception e)
       {
           messageArea.setText("Failed to clear history.");
           e.printStackTrace();
       }
   }
    private void back() 
    {
       frame.dispose();
       new Selectionpage();
   }

}
