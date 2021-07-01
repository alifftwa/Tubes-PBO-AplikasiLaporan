package sample;

public class Eadmin {
    private int id_pkn;
    private int jumlah_anggota;
    private String waktu;
    private String tempat;


    public Eadmin (int id_pkn, int jumlah_anggota, String waktu, String tempat) {
        this.id_pkn = id_pkn;
        this.jumlah_anggota = jumlah_anggota;
        this.waktu = waktu;
        this.tempat = tempat;
    }

    public int getId_pkn ( ) {
        return id_pkn;
    }

    public void setId_pkn (int id_pkn) { this.id_pkn = id_pkn; }

    public int getJumlah_anggota ( ) {
        return jumlah_anggota;
    }

    public void setjumlah_anggota (int jumlah_peserta) {
        this.jumlah_anggota = jumlah_peserta;
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
