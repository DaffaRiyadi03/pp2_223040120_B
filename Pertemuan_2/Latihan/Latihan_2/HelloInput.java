package Pertemuan_2.Latihan.Latihan_2;

import javax.swing.*;  // Impor pustaka Swing untuk JFrame, JLabel, JButton, JTextField
import java.awt.event.*;  // Impor pustaka AWT untuk ActionListener dan ActionEvent

// HelloInput extends JFrame, yang artinya HelloInput adalah child class dari JFrame
public class HelloInput extends JFrame {
    public HelloInput() {
        // Menentukan operasi ketika jendela ditutup
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

        // Membuat label untuk menampilkan hasil output
        JLabel labelOutput = new JLabel("");
        labelOutput.setBounds(130, 150, 200, 30);

        // Menambahkan ActionListener untuk tombol
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textField.getText();
                labelOutput.setText("Hello " + nama);
            }
        });

        // Menambahkan komponen ke JFrame
        this.add(button);
        this.add(textField);
        this.add(labelInput);
        this.add(labelOutput);

        // Menentukan ukuran jendela
        this.setSize(400, 500);

        // Menggunakan layout null untuk pengaturan manual posisi komponen
        this.setLayout(null);
    }

    public static void main(String[] args) {
        // Menjalankan aplikasi GUI dalam thread yang aman untuk Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloInput h = new HelloInput();  // Membuat objek HelloInput
                h.setVisible(true);  // Menampilkan jendela
            }
        });
    }
}
