package Tugas.Pertemuan_2.Latihan.Latihan_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiodataApp extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextArea outputArea;
    private JCheckBox agreeCheckBox;

    public BiodataApp() {
        // Set up the frame
        setTitle("Biodata Teman");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create components
        JLabel nameLabel = new JLabel("Nama:");
        nameField = new JTextField(15);

        JLabel phoneLabel = new JLabel("No. Telepon:");
        phoneField = new JTextField(15);

        JButton addButton = new JButton("Tambah");
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        agreeCheckBox = new JCheckBox("Setuju dengan syarat dan ketentuan");

        // Add action listener to the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                
                // Check if both fields are filled
                if (!name.isEmpty() && !phone.isEmpty()) {
                    String separator = "------------------------------\n"; // or you can use "====" or "____"
                    outputArea.append("Nama: " + name + "\n");
                    outputArea.append("No. Telepon: " + phone + "\n");
                    outputArea.append(separator);
                    
                    // Clear input fields
                    nameField.setText("");
                    phoneField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan isi nama dan nomor telepon!");
                }
            }
        });

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(phoneLabel);
        add(phoneField);
        add(addButton);
        add(scrollPane);
        add(agreeCheckBox);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BiodataApp app = new BiodataApp();
            app.setVisible(true);
        });
    }
}
    