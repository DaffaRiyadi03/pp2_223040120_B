package Pertemuan_2.Latihan.Latihan_1;

import java.awt.event.*;
import javax.swing.*;

public class ButtonEventSwing extends JFrame {
    // Constructor untuk inisialisasi JFrame
    public ButtonEventSwing() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Membuat JLabel
        JLabel label = new JLabel("Hello World");
        label.setBounds(130, 40, 400, 10);
        
        // Membuat JButton
        JButton button = new JButton("Klik");
        button.setBounds(130, 100, 100, 40);
        
        // Menambahkan ActionListener ke JButton
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Hello Pemrograman II");
            }
        });
        
        // Menambahkan komponen ke JFrame
        this.add(button);
        this.add(label);
        
        // Menyetel ukuran JFrame
        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        // Menjalankan JFrame menggunakan SwingUtilities
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ButtonEventSwing b = new ButtonEventSwing();
                b.setVisible(true);
            }
        });
    }
}
