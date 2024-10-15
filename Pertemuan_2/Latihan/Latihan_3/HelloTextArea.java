package Pertemuan_2.Latihan.Latihan_3;

import java.awt.event.*;
import javax.swing.*;

public class HelloTextArea extends JFrame {  // HelloTextArea sekarang extends JFrame
    public HelloTextArea() {
        // Mengatur operasi ketika jendela ditutup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Membuat label untuk input
        JLabel labelInput = new JLabel("Input Nama:");
        labelInput.setBounds(130, 40, 100, 10);

        // Membuat text field untuk memasukkan nama
        JTextField textField = new JTextField();
        textField.setBounds(130, 60, 100, 30);

        // Membuat tombol
        JButton button = new JButton("Klik");
        button.setBounds(130, 100, 100, 40);

        // Membuat text area untuk menampilkan output
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(130, 150, 200, 100);

        // Menambahkan ActionListener untuk tombol
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textField.getText();
                txtOutput.append("Hello " + nama + "\n");
                textField.setText("");
            }
        });

        // Menambahkan komponen ke JFrame
        this.add(button);
        this.add(textField);
        this.add(labelInput);
        this.add(txtOutput);

        // Menentukan ukuran jendela
        this.setSize(400, 500);

        // Menggunakan layout null untuk pengaturan manual posisi komponen
        this.setLayout(null);
    }

    public static void main(String[] args) {
        // Menjalankan aplikasi GUI dalam thread yang aman untuk Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloTextArea h = new HelloTextArea();  // Membuat objek HelloTextArea
                h.setVisible(true);  // Menampilkan jendela
            }
        });
    }
}
