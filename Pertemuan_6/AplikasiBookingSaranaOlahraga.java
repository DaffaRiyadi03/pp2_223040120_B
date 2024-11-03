package Pertemuan_6;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AplikasiBookingSaranaOlahraga {
    private JFrame frame;
    private JTextField namaPenyewaField;
    private JCheckBox memberCheckBox, nonMemberCheckBox;
    private JList<String> saranaList;
    private JSlider durasiSlider;
    private JSpinner jumlahOrangSpinner;
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    public AplikasiBookingSaranaOlahraga() {
        frame = new JFrame("Aplikasi Booking Sarana Olahraga");
        frame.setLayout(new BorderLayout(10, 10));

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        
        exitMenuItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Panel Form Input dengan GridLayout
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulir Booking"));

        // Input Nama Penyewa
        formPanel.add(new JLabel("Nama Penyewa:"));
        namaPenyewaField = new JTextField();
        formPanel.add(namaPenyewaField);

        // Jenis Member dengan 2 CheckBox (Member dan Non-Member)
        formPanel.add(new JLabel("Jenis Penyewa:"));
        JPanel jenisPenyewaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        memberCheckBox = new JCheckBox("Member");
        nonMemberCheckBox = new JCheckBox("Non-Member");

        // Logika hanya satu pilihan yang dapat dipilih
        memberCheckBox.addActionListener(e -> {
            if (memberCheckBox.isSelected()) {
                nonMemberCheckBox.setSelected(false);
            }
        });
        nonMemberCheckBox.addActionListener(e -> {
            if (nonMemberCheckBox.isSelected()) {
                memberCheckBox.setSelected(false);
            }
        });

        jenisPenyewaPanel.add(memberCheckBox);
        jenisPenyewaPanel.add(nonMemberCheckBox);
        formPanel.add(jenisPenyewaPanel);

        // List Sarana yang Dipesan
        formPanel.add(new JLabel("Sarana:"));
        String[] saranaOptions = {"Lapangan Futsal", "Lapangan Basket", "Lapangan Tenis", "Kolam Renang"};
        saranaList = new JList<>(saranaOptions);
        saranaList.setVisibleRowCount(2);
        formPanel.add(new JScrollPane(saranaList));

        // Slider Durasi Pemakaian
        formPanel.add(new JLabel("Durasi (Jam):"));
        durasiSlider = new JSlider(1, 5, 1);
        durasiSlider.setMajorTickSpacing(1);
        durasiSlider.setPaintTicks(true);
        durasiSlider.setPaintLabels(true);
        formPanel.add(durasiSlider);

        // Spinner Jumlah Orang
        formPanel.add(new JLabel("Jumlah Orang:"));
        jumlahOrangSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        formPanel.add(jumlahOrangSpinner);

        // Tabel untuk Menampilkan Data Booking
        String[] columnNames = {"Nama Penyewa", "Jenis Penyewa", "Sarana", "Durasi", "Jumlah Orang"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookingTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookingTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Data Booking"));

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton tambahButton = new JButton("Tambah Booking");
        tambahButton.addActionListener(e -> tambahBookingKeTabel());
        JButton rubahButton = new JButton("Rubah Booking");
        rubahButton.addActionListener(e -> rubahBooking());
        JButton hapusButton = new JButton("Hapus Booking");
        hapusButton.addActionListener(e -> hapusBooking());

        buttonPanel.add(tambahButton);
        buttonPanel.add(rubahButton);
        buttonPanel.add(hapusButton);

        // Menambahkan Panel dan Komponen ke Frame
        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Pengaturan Frame
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void tambahBookingKeTabel() {
        String namaPenyewa = namaPenyewaField.getText();
        String jenisPenyewa = memberCheckBox.isSelected() ? "Member" : (nonMemberCheckBox.isSelected() ? "Non-Member" : "");
        String sarana = saranaList.getSelectedValue();
        String durasi = durasiSlider.getValue() + " jam";
        String jumlahOrang = jumlahOrangSpinner.getValue() + " orang";

        Object[] row = {namaPenyewa, jenisPenyewa, sarana, durasi, jumlahOrang};
        tableModel.addRow(row);

        clearForm();
    }

    private void clearForm() {
        namaPenyewaField.setText("");
        memberCheckBox.setSelected(false);
        nonMemberCheckBox.setSelected(false);
        saranaList.clearSelection();
        durasiSlider.setValue(1);
        jumlahOrangSpinner.setValue(1);
    }

    private void rubahBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow >= 0) {
            namaPenyewaField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            String jenisPenyewa = tableModel.getValueAt(selectedRow, 1).toString();
            memberCheckBox.setSelected(jenisPenyewa.equals("Member"));
            nonMemberCheckBox.setSelected(jenisPenyewa.equals("Non-Member"));
            saranaList.setSelectedValue(tableModel.getValueAt(selectedRow, 2).toString(), true);
            durasiSlider.setValue(Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString().split(" ")[0]));
            jumlahOrangSpinner.setValue(Integer.parseInt(tableModel.getValueAt(selectedRow, 4).toString().split(" ")[0]));

            tableModel.removeRow(selectedRow); // Hapus baris untuk diperbarui setelah perubahan
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data booking yang ingin dirubah", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void hapusBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data booking yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AplikasiBookingSaranaOlahraga::new);
    }
}
