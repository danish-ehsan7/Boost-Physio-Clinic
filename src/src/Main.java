import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a JFrame for the main window
        JFrame mainFrame = new JFrame("Welcome to the Booking System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLayout(new BorderLayout());

        // Create a custom JPanel that will handle the background image
        JPanel panel = new JPanel() {
            private Image backgroundImage;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    if (backgroundImage == null) {
                        // Load the background image from file
                        backgroundImage = ImageIO.read(new File("src/public/bg.png"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());

        // Create a title label
        JLabel titleLabel = new JLabel("Booking System for Boost Physio Clinic", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);  // Change text color to white
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create a start button to open the BookingSystem window
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 14));
        startButton.setForeground(Color.WHITE);  
        startButton.setBackground(new Color(0, 123, 255));  // Blue background color
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(150, 40));  
        
        // Set rounded border with radius 10px
        startButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 3));
        
        // Custom painting for rounded corners
        startButton.setContentAreaFilled(false);
        startButton.setOpaque(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the BookingSystem window
                BookingSystem bookingSystem = new BookingSystem();
                bookingSystem.displayGUI();
                mainFrame.dispose(); 
            }
        });

        // Create a new panel for the button and set its position
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50)); 
        buttonPanel.setOpaque(false); 
        buttonPanel.add(startButton);

        // Add the button panel to the bottom center of the frame
        panel.add(buttonPanel, BorderLayout.SOUTH); // Set button at bottom center

        // Set the main frame properties
        mainFrame.add(panel);
        mainFrame.setLocationRelativeTo(null);  
        mainFrame.setVisible(true);
    }
}
