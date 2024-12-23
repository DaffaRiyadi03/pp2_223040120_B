package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import model.Karyawan;

public class KaryawanView extends JFrame {

    private JTable table;
    private JTextField namaField, tahunMasukField, gajiField;
    private JComboBox<String> jabatanComboBox;
    private JRadioButton aktifRadioButton, tidakAktifRadioButton;
    private ButtonGroup statusButtonGroup;
    private JButton tambahButton, updateButton, hapusButton, refreshButton;
    private JPanel panel;

    public KaryawanView() {
        setTitle("Kelola Data Karyawan");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nama", "Jabatan", "Status", "Tahun Masuk", "Gaji"}
        ));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Pilih hanya 1 baris

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));

        formPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        formPanel.add(namaField);

        formPanel.add(new JLabel("Jabatan:"));
        jabatanComboBox = new JComboBox<>(new String[]{"Manager", "Staff", "Intern"});
        formPanel.add(jabatanComboBox);

        formPanel.add(new JLabel("Status:"));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        aktifRadioButton = new JRadioButton("Aktif");
        tidakAktifRadioButton = new JRadioButton("Tidak Aktif");
        statusButtonGroup = new ButtonGroup();
        statusButtonGroup.add(aktifRadioButton);
        statusButtonGroup.add(tidakAktifRadioButton);
        statusPanel.add(aktifRadioButton);
        statusPanel.add(tidakAktifRadioButton);
        formPanel.add(statusPanel);

        formPanel.add(new JLabel("Tahun Masuk:"));
        tahunMasukField = new JTextField();
        formPanel.add(tahunMasukField);

        formPanel.add(new JLabel("Gaji:"));
        gajiField = new JTextField();
        formPanel.add(gajiField);

        tambahButton = new JButton("Tambah Karyawan");
        updateButton = new JButton("Update Karyawan");
        hapusButton = new JButton("Hapus Karyawan");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(tambahButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(hapusButton);
        buttonPanel.add(refreshButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // Mendapatkan data dari form
    public JTextField getNamaField() {
        return namaField;
    }

    public JComboBox<String> getJabatanComboBox() {
        return jabatanComboBox;
    }

    public JRadioButton getAktifRadioButton() {
        return aktifRadioButton;
    }

    public JRadioButton getTidakAktifRadioButton() {
        return tidakAktifRadioButton;
    }

    public JTextField getTahunMasukField() {
        return tahunMasukField;
    }

    public JTextField getGajiField() {
        return gajiField;
    }

    public JTable getTable() {
        return table;
    }

    public void addTambahKaryawanListener(ActionListener listener) {
        tambahButton.addActionListener(listener);
    }

    public void addUpdateKaryawanListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addHapusKaryawanListener(ActionListener listener) {
        hapusButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    public void updateKaryawanTable(List<Karyawan> karyawans) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Menghapus data lama
        for (Karyawan karyawan : karyawans) {
            model.addRow(new Object[]{
                karyawan.getId(), karyawan.getNama(), karyawan.getJabatan(),
                karyawan.isStatus() ? "Aktif" : "Tidak Aktif", karyawan.getTahunMasuk(), karyawan.getGaji()
            });
        }
    }

    public void clearInputFields() {
        namaField.setText("");
        jabatanComboBox.setSelectedIndex(0);
        statusButtonGroup.clearSelection();
        tahunMasukField.setText("");
        gajiField.setText("");
    }
}
