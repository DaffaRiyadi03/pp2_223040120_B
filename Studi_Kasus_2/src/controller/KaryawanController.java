package controller;

import org.apache.ibatis.session.SqlSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Karyawan;
import model.KaryawanMapper;
import model.MyBatisUtil;
import view.KaryawanView;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class KaryawanController {

    private KaryawanView karyawanView;
    private KaryawanMapper karyawanMapper;

    public KaryawanController(KaryawanView karyawanView) {
        this.karyawanView = karyawanView;

        // Tambahkan Listener ke View
        karyawanView.addTambahKaryawanListener(new TambahKaryawanListener());
        karyawanView.addUpdateKaryawanListener(new UpdateKaryawanListener());
        karyawanView.addHapusKaryawanListener(new HapusKaryawanListener());
        karyawanView.addRefreshButtonListener(new RefreshButtonListener());
        karyawanView.addTableSelectionListener(new TableSelectionListener());

        refreshKaryawanTable(); // Awal load, refresh data tabel
    }

    // Method untuk refresh data tabel
    private void refreshKaryawanTable() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            karyawanMapper = session.getMapper(KaryawanMapper.class);
            List<Karyawan> karyawans = karyawanMapper.getAllKaryawans();
            karyawanView.updateKaryawanTable(karyawans);
            karyawanView.clearInputFields(); // Kosongkan form input
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(karyawanView, "Error: " + ex.getMessage());
        }
    }

    // Listener untuk menambahkan karyawan
    class TambahKaryawanListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Ambil input
                String nama = karyawanView.getNamaField().getText();
                String jabatan = karyawanView.getJabatanComboBox().getSelectedItem().toString();
                boolean status = karyawanView.getAktifRadioButton().isSelected();
                int tahunMasuk = Integer.parseInt(karyawanView.getTahunMasukField().getText());
                BigDecimal gaji = new BigDecimal(karyawanView.getGajiField().getText());

                // Validasi input kosong
                if (nama.isEmpty() || jabatan.isEmpty()) {
                    JOptionPane.showMessageDialog(karyawanView, "Harap isi semua bidang.");
                    return;
                }

                // Simpan karyawan ke database
                Karyawan karyawan = new Karyawan(nama, jabatan, status, tahunMasuk, gaji);
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    karyawanMapper = session.getMapper(KaryawanMapper.class);
                    karyawanMapper.addKaryawan(karyawan);
                    session.commit();
                    refreshKaryawanTable();
                    JOptionPane.showMessageDialog(karyawanView, "Karyawan berhasil ditambahkan!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(karyawanView, "Error: " + ex.getMessage());
            }
        }
    }

    // Listener untuk memperbarui karyawan
    class UpdateKaryawanListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = karyawanView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    int id = (int) karyawanView.getTable().getValueAt(selectedRow, 0);

                    String nama = karyawanView.getNamaField().getText();
                    String jabatan = karyawanView.getJabatanComboBox().getSelectedItem().toString();
                    boolean status = karyawanView.getAktifRadioButton().isSelected();
                    int tahunMasuk = Integer.parseInt(karyawanView.getTahunMasukField().getText());
                    BigDecimal gaji = new BigDecimal(karyawanView.getGajiField().getText());

                    Karyawan karyawan = new Karyawan(nama, jabatan, status, tahunMasuk, gaji);
                    karyawan.setId(id);

                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        karyawanMapper = session.getMapper(KaryawanMapper.class);
                        karyawanMapper.updateKaryawan(karyawan);
                        session.commit();
                        refreshKaryawanTable();
                        JOptionPane.showMessageDialog(karyawanView, "Karyawan berhasil diperbarui!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(karyawanView, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(karyawanView, "Pilih karyawan untuk diperbarui.");
            }
        }
    }

    // Listener untuk menghapus karyawan
    class HapusKaryawanListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = karyawanView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) karyawanView.getTable().getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(karyawanView, "Hapus karyawan ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        karyawanMapper = session.getMapper(KaryawanMapper.class);
                        karyawanMapper.deleteKaryawan(id);
                        session.commit();
                        refreshKaryawanTable();
                        JOptionPane.showMessageDialog(karyawanView, "Karyawan berhasil dihapus!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(karyawanView, "Error: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(karyawanView, "Pilih karyawan untuk dihapus.");
            }
        }
    }

    // Listener untuk tombol refresh
    class RefreshButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshKaryawanTable();
        }
    }

    // Listener untuk memilih data di tabel
    public class TableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = karyawanView.getTable().getSelectedRow();
                if (selectedRow >= 0) {
                    karyawanView.getNamaField().setText(karyawanView.getTable().getValueAt(selectedRow, 1).toString());
                    karyawanView.getJabatanComboBox().setSelectedItem(karyawanView.getTable().getValueAt(selectedRow, 2).toString());
                    karyawanView.getAktifRadioButton().setSelected(karyawanView.getTable().getValueAt(selectedRow, 3).toString().equals("Aktif"));
                    karyawanView.getTidakAktifRadioButton().setSelected(karyawanView.getTable().getValueAt(selectedRow, 3).toString().equals("Tidak Aktif"));
                    karyawanView.getTahunMasukField().setText(karyawanView.getTable().getValueAt(selectedRow, 4).toString());
                    karyawanView.getGajiField().setText(karyawanView.getTable().getValueAt(selectedRow, 5).toString());
                }
            }
        }
    }
}
