package gui;

import file.SkincarePurchaseHistory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Skincare
{
    private JFrame frame;
    private JPanel buttonPanel;
    private JTextArea messageArea;

    private String[] cartNames = new String[100];
    private int[] cartPrices = new int[100];
    private int[] cartQuantities = new int[100];
    private int cartCount = 0;

    public Skincare()
    {
        frame = new JFrame("Skincare Store");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 240, 245));

        frame.add(createProductPanel("D:/final project(mod)/Final Project/Final Project/images/Acnecontrolcleanser.jpg", 40, 30, "Cleanser", 200));
        frame.add(createProductPanel("D:/final project(mod)/Final Project/Final Project/images/moisturizer.jpg", 190, 30, "Moisturizer", 300));
        frame.add(createProductPanel("D:/final project(mod)/Final Project/Final Project/images/biodarmasunscreen.jpg", 340, 30, "Sunscreen", 250));
        frame.add(createProductPanel("D:/final project(mod)/Final Project/Final Project/images/serum.jpeg", 490, 30, "Serum", 450));
        frame.add(createProductPanel("D:/final project(mod)/Final Project/Final Project/images/MoisturizingLotion.jpg", 640, 30, "Toner", 180));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 250, 900, 100);
        buttonPanel.setBackground(new Color(220, 245, 255));

        JButton buyButton = new JButton("Buy");
        buyButton.setBounds(30, 30, 100, 35);
        buyButton.addActionListener(new BuyListener());
        buttonPanel.add(buyButton);

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(140, 30, 120, 35);
        viewCartButton.addActionListener(new ViewCartListener());
        buttonPanel.add(viewCartButton);

        JButton receiptButton = new JButton("Show Receipt");
        receiptButton.setBounds(270, 30, 140, 35);
        receiptButton.addActionListener(new ShowReceiptListener());
        buttonPanel.add(receiptButton);

        JButton historyButton = new JButton("Purchase History");
        historyButton.setBounds(420, 30, 160, 35);
        historyButton.addActionListener(new ShowHistoryListener());
        buttonPanel.add(historyButton);

        JButton clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.setBounds(590, 30, 140, 35);
        clearHistoryButton.addActionListener(new ClearHistoryListener());
        buttonPanel.add(clearHistoryButton);

        // Back button - clears the purchase history file
        JButton backButton = new JButton("Back");
        backButton.setBounds(740, 30, 90, 35);
        backButton.addActionListener(new BackListener());
        backButton.setBackground(new Color(255,192,203));
        buttonPanel.add(backButton);

        frame.add(buttonPanel);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBounds(50, 370, 800, 250);
        frame.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class BuyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buyItems();
        }
    }

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

    private class ShowReceiptListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            showReceipt();
        }
    }

    private class ShowHistoryListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            showHistory();
        }
    }

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
        panel.setBackground(Color.PINK);

        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(10, 10, 120, 90);
        panel.add(imageLabel);

        JLabel nameLabel = new JLabel(productName);
        nameLabel.setBounds(10, 110, 120, 20);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nameLabel);

        JLabel priceLabel = new JLabel("Price: $" + price);
        priceLabel.setBounds(10, 135, 120, 20);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(priceLabel);

        JLabel quantityLabel = new JLabel("Qty:");
        quantityLabel.setBounds(20, 160, 30, 20);
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(quantityLabel);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantitySpinner.setBounds(60, 160, 50, 25);
        panel.add(quantitySpinner);

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

    private void buyItems()
    {
        if (cartCount == 0)
        {
            messageArea.setText("Cart is empty. Add items before buying.");
            return;
        }

        try
        {
            SkincarePurchaseHistory.savePurchase(cartNames, cartQuantities, cartCount);
            messageArea.setText("Purchase completed successfully!");
            cartCount = 0;
        }
        catch (Exception e)
        {
            messageArea.setText("Error saving purchase.");
            e.printStackTrace();
        }
    }

    private void showHistory()
    {
        try
        {
            String result = SkincarePurchaseHistory.readHistory();
            messageArea.setText(result);
        }
        catch (Exception e)
        {
            messageArea.setText("Error reading history.");
            e.printStackTrace();
        }
    }

    private void clearHistory()
    {
        try
        {
            SkincarePurchaseHistory.clearHistory();
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
