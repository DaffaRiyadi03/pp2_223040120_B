package model;

import java.math.BigDecimal;

public class Karyawan {

    private int id;
    private String nama;
    private String jabatan;
    private boolean status;
    private int tahunMasuk;
    private BigDecimal gaji;

    public Karyawan(String nama, String jabatan, boolean status, int tahunMasuk, BigDecimal gaji) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.status = status;
        this.tahunMasuk = tahunMasuk;
        this.gaji = gaji;
    }

    public Karyawan() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID harus lebih besar dari 0");
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama;
        } else {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        if (jabatan != null && !jabatan.trim().isEmpty()) {
            this.jabatan = jabatan;
        } else {
            throw new IllegalArgumentException("Jabatan tidak boleh kosong");
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(int tahunMasuk) {
        if (tahunMasuk > 0) {
            this.tahunMasuk = tahunMasuk;
        } else {
            throw new IllegalArgumentException("Tahun Masuk harus lebih besar dari 0");
        }
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        if (gaji != null && gaji.compareTo(BigDecimal.ZERO) >= 0) {
            this.gaji = gaji;
        } else {
            throw new IllegalArgumentException("Gaji harus lebih besar dari 0");
        }
    }

    @Override
    public String toString() {
        return "Karyawan{"
                + "id=" + id
                + ", nama='" + nama + '\''
                + ", jabatan='" + jabatan + '\''
                + ", status=" + (status ? "Aktif" : "Tidak Aktif")
                + ", tahunMasuk=" + tahunMasuk
                + ", gaji=" + gaji
                + '}';
    }
}
