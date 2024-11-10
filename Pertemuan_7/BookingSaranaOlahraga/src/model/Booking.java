package model;

public class Booking {
    private String namaPenyewa;
    private String jenisPenyewa;
    private String sarana;
    private String durasi;
    private int jumlahOrang;

    // Constructor
    public Booking(String namaPenyewa, String jenisPenyewa, String sarana, String durasi, int jumlahOrang) {
        this.namaPenyewa = namaPenyewa;
        this.jenisPenyewa = jenisPenyewa;
        this.sarana = sarana;
        this.durasi = durasi;
        this.jumlahOrang = jumlahOrang;
    }

    // Getter and Setter
    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getJenisPenyewa() {
        return jenisPenyewa;
    }

    public void setJenisPenyewa(String jenisPenyewa) {
        this.jenisPenyewa = jenisPenyewa;
    }

    public String getSarana() {
        return sarana;
    }

    public void setSarana(String sarana) {
        this.sarana = sarana;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public int getJumlahOrang() {
        return jumlahOrang;
    }

    public void setJumlahOrang(int jumlahOrang) {
        this.jumlahOrang = jumlahOrang;
    }
}
