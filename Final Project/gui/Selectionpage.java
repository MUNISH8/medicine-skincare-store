package gui;
import javax.swing.*;
import entity.MedicineItem;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public  class Selectionpage implements ActionListener
{

      private JLabel welcomeLabel,selectLabel;
      private JFrame frame;
      //private JTextField tf1, tf2, tf3;
     // private JTextArea historyArea;
      private JButton medicineBtn,skincareBtn;
      private ImageIcon image;

      public Selectionpage()
      {
        frame = new JFrame("Selectionpage");
        frame.setSize(650, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(24,59,78)); //This Sets the Backround Color of the Frame


        ImageIcon icon = new ImageIcon("D:/Java Final Project/WhatsApp Image 2025-05-16 at 14.35.42_c33a4c0d.jpg"); //  image er path
        Image img = icon.getImage().getScaledInstance(350, 100, Image.SCALE_SMOOTH); // Scale the image(image er width 250,height 100)
        icon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(100,480, 200, 100); // (image er ) position and size
        frame.add(imageLabel);//100,350

        ImageIcon icon2 = new ImageIcon("D:/Java Final Project/WhatsApp Image 2025-05-16 at 14.35.42_d0207985.jpg"); //  image er path
        Image img2 = icon2.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH); // Scale the image(image er width 250,height 100)
        icon2 = new ImageIcon(img2);
        JLabel imageLabel2 = new JLabel(icon2);
        imageLabel2.setBounds(300,480, 300, 100); // (image er ) position and size
        frame.add(imageLabel2);

        //Top Image
        ImageIcon IconTop=new ImageIcon("D:/final project(mod)/Final Project/Final Project/images/Logo.png");
        Image topImage=IconTop.getImage().getScaledInstance(250,150,Image.SCALE_SMOOTH);
        IconTop=new ImageIcon(topImage);
        JLabel imageLabe3 = new JLabel(IconTop);
        imageLabe3.setBounds(190, 10, 250, 100); // (image er ) position and size
        frame.add(imageLabe3);

        welcomeLabel = new JLabel("Welcome to SM Medicine Corner");
        welcomeLabel.setBounds(140,120,400, 40);
        welcomeLabel.setOpaque(false); //Jlabel er color jeno show kore tai (We donot need it)
        welcomeLabel.setFont(new Font("Monospaced", Font.BOLD|Font.ITALIC, 20)); //Font type and size
        welcomeLabel.setForeground(Color.BLACK);//Font color of the label
        frame.add(welcomeLabel);

        selectLabel = new JLabel("Please select an option to buy:");
        selectLabel.setBounds(180,350,300, 30);  // position of the label
        selectLabel.setFont(new Font("Monospaced",Font.BOLD|Font.ITALIC, 16));//Font type and size
        selectLabel.setForeground(Color.BLACK);//Font color of the label
        frame.add(selectLabel);


         medicineBtn= new JButton("Medicine");
         medicineBtn.setBounds(140,425,110,40);
         medicineBtn.setFont(new Font("SansSerif", Font.BOLD, 14)); //sets Font Size and Font Type and Makes the Fontss BOLD
         medicineBtn.setBackground(new Color(148,180,193));
         medicineBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Changes the cursor to a hand icon
         medicineBtn.setFocusPainted(false); //Removes default focus border
         frame.add(medicineBtn);

         skincareBtn= new JButton("Skincare");
         skincareBtn.setBounds(380,425,110,40);
         skincareBtn.setFont(new Font("SansSerif", Font.BOLD, 14)); //sets Font Size and Font Type and Makes the Fontss BOLD
         skincareBtn.setBackground(new Color(148,180,193));
         skincareBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Changes the cursor to a hand icon
         skincareBtn.setFocusPainted(false); //Removes default focus border
         frame.add(skincareBtn);


        medicineBtn.addActionListener(this); //prottek ta button e actionlistener add kore dicchi jeno proti dorjay knock holei sound hoy
        skincareBtn.addActionListener(this);





         frame.setVisible(true);
         frame.setLocationRelativeTo(null);//frame ta moddhe khulbe



      }


public void actionPerformed(ActionEvent e)
{
    if (e.getSource() == medicineBtn)
    {
        new Medicine();      // Launch Medicine window
        frame.dispose();     // Optional: Close selection window
    }
    else if (e.getSource() == skincareBtn)
    {
       new Skincare();
       frame.dispose();
    }
}

  }
