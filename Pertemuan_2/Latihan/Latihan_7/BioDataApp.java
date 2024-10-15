package Tugas.Pertemuan_2.Latihan.Latihan_7;

import java.awt.event.*;
import javax.swing.*;

public class BioDataApp extends JFrame {

    public BioDataApp() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLayout(null);

        // Name label and text field
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 20, 100, 30);
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(120, 20, 250, 30);

        // Phone Number label and text field
        JLabel labelHP = new JLabel("Nomor HP:");
        labelHP.setBounds(15, 70, 100, 30);
        JTextField textFieldHP = new JTextField();
        textFieldHP.setBounds(120, 70, 250, 30);

        // Gender label and radio buttons
        JLabel labelJK = new JLabel("Jenis Kelamin:");
        labelJK.setBounds(15, 120, 100, 30);
        JRadioButton radioButtonLaki = new JRadioButton("Laki-Laki", true);
        radioButtonLaki.setBounds(120, 120, 100, 30);
        JRadioButton radioButtonPerempuan = new JRadioButton("Perempuan");
        radioButtonPerempuan.setBounds(220, 120, 100, 30);

        ButtonGroup bgJK = new ButtonGroup();
        bgJK.add(radioButtonLaki);
        bgJK.add(radioButtonPerempuan);

        // Checkbox for Warga Negara Asing
        JCheckBox checkBoxWNA = new JCheckBox("Warga Negara Asing");
        checkBoxWNA.setBounds(15, 160, 200, 30);

        // Button to save data
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 200, 100, 40);

        // Output area for displaying the data
        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 250, 350, 100);
        txtOutput.setEditable(false);

        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String nomorHP = textFieldHP.getText();
                String jenisKelamin = radioButtonLaki.isSelected() ? "Laki-Laki" : "Perempuan";
                String wna = checkBoxWNA.isSelected() ? "Ya" : "Bukan";

                txtOutput.setText("Nama\t\t: " + nama + "\n");
                txtOutput.append("Nomor HP\t: " + nomorHP + "\n");
                txtOutput.append("Jenis Kelamin\t: " + jenisKelamin + "\n");
                txtOutput.append("WNA\t\t: " + wna + "\n");
                txtOutput.append("=================================================\n");
            }
        });

        // Add all components to the frame
        this.add(labelInput);
        this.add(textFieldNama);
        this.add(labelHP);
        this.add(textFieldHP);
        this.add(labelJK);
        this.add(radioButtonLaki);
        this.add(radioButtonPerempuan);
        this.add(checkBoxWNA);
        this.add(buttonSimpan);
        this.add(txtOutput);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BioDataApp();
            }
        });
    }
}