package Pertemuan_2.Tugas_Pertemuan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankRegistrationForm extends JFrame {

    private JTextField txtName, txtPhone;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JSpinner spFrequency, spBirthDate;
    private JTextArea txtOutput;
    private JList<String> lstAccountTypes;

    public BankRegistrationForm() {
        setTitle("Form Pendaftaran Nasabah Bank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Label and TextField for Name
        JLabel lblName = new JLabel("Nama:");
        lblName.setBounds(15, 20, 100, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 20, 200, 30);
        add(txtName);

        // Label and TextField for Phone Number
        JLabel lblPhone = new JLabel("Nomor HP:");
        lblPhone.setBounds(15, 60, 100, 30);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(120, 60, 200, 30);
        add(txtPhone);

        // Label and JList for Account Types
        JLabel lblAccountType = new JLabel("Jenis Tabungan:");
        lblAccountType.setBounds(15, 100, 100, 30);
        add(lblAccountType);

        String[] accountTypes = {"Tabungan Reguler", "Tabungan Pendidikan", "Tabungan Berjangka", "Tabungan Haji","Tabungan Investasi"};
        lstAccountTypes = new JList<>(accountTypes);
        lstAccountTypes.setBounds(120, 100, 200, 90);
        add(lstAccountTypes);

        // Label and JSpinner for Frequency of Transactions
        JLabel lblFrequency = new JLabel("Frekuensi Transaksi:");
        lblFrequency.setBounds(15, 200, 150, 30);
        add(lblFrequency);

        spFrequency = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spFrequency.setBounds(170, 200, 50, 30);
        add(spFrequency);

        // Label and JSpinner for Birth Date
        JLabel lblBirthDate = new JLabel("Tanggal Lahir (tahun):");
        lblBirthDate.setBounds(15, 240, 150, 30);
        add(lblBirthDate);

        spBirthDate = new JSpinner(new SpinnerNumberModel(1990, 1900, 2024, 1));
        spBirthDate.setBounds(170, 240, 50, 30);
        add(spBirthDate);

        // Password and Confirm Password fields
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(15, 280, 100, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 280, 200, 30);
        add(txtPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setBounds(15, 320, 120, 30);
        add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(150, 320, 200, 30);
        add(txtConfirmPassword);

        // Save button
        JButton btnSave = new JButton("Simpan");
        btnSave.setBounds(15, 360, 100, 30);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String phone = txtPhone.getText();
                String accountType = lstAccountTypes.getSelectedValue();
                int frequency = (int) spFrequency.getValue();
                int birthYear = (int) spBirthDate.getValue();

                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());
                String passwordMatch = password.equals(confirmPassword) ? "Cocok" : "Tidak Cocok";

                txtOutput.setText("Nama: " + name + "\n");
                txtOutput.append("Nomor HP: " + phone + "\n");
                txtOutput.append("Jenis Tabungan: " + accountType + "\n");
                txtOutput.append("Frekuensi Transaksi: " + frequency + "\n");
                txtOutput.append("Tanggal Lahir: " + birthYear + "\n");
                txtOutput.append("Password: " + passwordMatch + "\n");
            }
        });
        add(btnSave);

        // Reset button
        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(120, 360, 100, 30);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset semua inputan dan output
                txtName.setText("");
                txtPhone.setText("");
                lstAccountTypes.clearSelection();
                spFrequency.setValue(1);
                spBirthDate.setValue(1990);
                txtPassword.setText("");
                txtConfirmPassword.setText("");
                txtOutput.setText("");
            }
        });
        add(btnReset);

        // Exit Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuItemExit);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Output area
        txtOutput = new JTextArea();
        txtOutput.setBounds(15, 400, 300, 150);
        add(txtOutput);

        setSize(350, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankRegistrationForm();
            }
        });
    }
}
