package view;

import dao.BookingDAO;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Booking;

public class MainView extends JFrame {
    private final JTextField namaPenyewaField;
    private final JCheckBox memberCheckBox, nonMemberCheckBox;
    private final JList<String> saranaList;
    private final JSlider durasiSlider;
    private final JSpinner jumlahOrangSpinner;
    private final JTable bookingTable;
    private final DefaultTableModel tableModel;
    private final BookingDAO bookingDAO;

    public MainView() {
        setTitle("Aplikasi Booking Sarana Olahraga");
        setLayout(new BorderLayout(10, 10));
        bookingDAO = new BookingDAO();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        exitMenuItem.addActionListener(_ -> System.exit(0));

        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulir Booking"));

        formPanel.add(new JLabel("Nama Penyewa:"));
        namaPenyewaField = new JTextField();
        formPanel.add(namaPenyewaField);

        formPanel.add(new JLabel("Jenis Penyewa:"));
        JPanel jenisPenyewaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        memberCheckBox = new JCheckBox("Member");
        nonMemberCheckBox = new JCheckBox("Non-Member");

        memberCheckBox.addActionListener(_ -> {
            if (memberCheckBox.isSelected()) {
                nonMemberCheckBox.setSelected(false);
            }
        });
        nonMemberCheckBox.addActionListener(_ -> {
            if (nonMemberCheckBox.isSelected()) {
                memberCheckBox.setSelected(false);
            }
        });

        jenisPenyewaPanel.add(memberCheckBox);
        jenisPenyewaPanel.add(nonMemberCheckBox);
        formPanel.add(jenisPenyewaPanel);

        formPanel.add(new JLabel("Sarana:"));
        String[] saranaOptions = {"Lapangan Futsal", "Lapangan Basket", "Lapangan Tenis", "Kolam Renang"};
        saranaList = new JList<>(saranaOptions);
        saranaList.setVisibleRowCount(2);
        formPanel.add(new JScrollPane(saranaList));

        formPanel.add(new JLabel("Durasi (Jam):"));
        durasiSlider = new JSlider(1, 5, 1);
        durasiSlider.setMajorTickSpacing(1);
        durasiSlider.setPaintTicks(true);
        durasiSlider.setPaintLabels(true);
        formPanel.add(durasiSlider);

        formPanel.add(new JLabel("Jumlah Orang:"));
        jumlahOrangSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        formPanel.add(jumlahOrangSpinner);

        String[] columnNames = {"Nama Penyewa", "Jenis Penyewa", "Sarana", "Durasi", "Jumlah Orang"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookingTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookingTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Data Booking"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton tambahButton = new JButton("Tambah Booking");
        tambahButton.addActionListener(_ -> tambahBookingKeTabel());
        JButton rubahButton = new JButton("Rubah Booking");
        rubahButton.addActionListener(_ -> rubahBooking());
        JButton hapusButton = new JButton("Hapus Booking");
        hapusButton.addActionListener(_ -> hapusBooking());

        buttonPanel.add(tambahButton);
        buttonPanel.add(rubahButton);
        buttonPanel.add(hapusButton);

        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void tambahBookingKeTabel() {
        String namaPenyewa = namaPenyewaField.getText();
        String jenisPenyewa = memberCheckBox.isSelected() ? "Member" : (nonMemberCheckBox.isSelected() ? "Non-Member" : "");
        String sarana = saranaList.getSelectedValue();
        String durasi = durasiSlider.getValue() + " jam";
        int jumlahOrang = (int) jumlahOrangSpinner.getValue();

        Object[] row = {namaPenyewa, jenisPenyewa, sarana, durasi, jumlahOrang + " orang"};
        tableModel.addRow(row);

        Booking booking = new Booking(namaPenyewa, jenisPenyewa, sarana, durasi, jumlahOrang);
        bookingDAO.saveBooking(booking);

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
            jumlahOrangSpinner.setValue(Integer.valueOf(tableModel.getValueAt(selectedRow, 4).toString().split(" ")[0]));

            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data booking yang ingin dirubah", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void hapusBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data booking yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
