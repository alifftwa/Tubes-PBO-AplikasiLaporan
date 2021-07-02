package sample;

public class Emahasiswa {
    private int id_peserta;
    private String nama_peserta;
    private String waktu;
    private String tempat;


    public Emahasiswa (int id_peserta, String nama_peserta, String waktu, String tempat) {
        this.id_peserta = id_peserta;
        this.nama_peserta = nama_peserta;
        this.waktu = waktu;
        this.tempat = tempat;
    }

    public int getId_peserta ( ) {
        return id_peserta;
    }

    public void setId_peserta (int id_peserta) { this.id_peserta = id_peserta; }

    public String getNama_peserta ( ) {
        return nama_peserta;
    }

    public void setNama_peserta (String nama_peserta) {
        this.nama_peserta = nama_peserta;
    }

    public String getWaktu ( ) {
        return waktu;
    }

    public void setWaktu (String waktu) {
        this.waktu = waktu;
    }

    public String getTempat ( ) {
        return tempat;
    }

    public void setTempat (String tempat) {
        this.tempat = tempat;
    }
}
