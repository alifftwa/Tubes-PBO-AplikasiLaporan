package sample;

public class Admin1 {
    private String id_jadwal_ujian;
    private String nama_pengawas;
    private String Ruangan;
    private String Gkb;
    private String Waktu;
    private String Nama_Peserta;

    public Admin1 (String id_jadwal_ujian, String nama_pengawas, String Ruangan, String Gkb, String Waktu, String Nama_Peserta) {
        this.id_jadwal_ujian = id_jadwal_ujian;
        this.nama_pengawas = nama_pengawas;
        this.Ruangan = Ruangan;
        this.Gkb = Gkb;
        this.Waktu = Waktu;
        this.Nama_Peserta = Nama_Peserta;
    }

    public String getId_jadwal_ujian ( ) {
        return id_jadwal_ujian;
    }

    public void setId_jadwal_ujian (String id_jadwal_ujian) {
        this.id_jadwal_ujian = id_jadwal_ujian;
    }

    public String getNama_pengawas ( ) {
        return nama_pengawas;
    }

    public void setNama_pengawas (String namapengawas) {
        this.nama_pengawas = nama_pengawas;
    }

    public String getRuangan ( ) {
        return Ruangan;
    }

    public void setRuangan (String ruangan) {
        Ruangan = ruangan;
    }

    public String getGkb ( ) {
        return Gkb;
    }

    public void setGkb (String gkb) {
        Gkb = Gkb;
    }

    public String getWaktu ( ) {
        return Waktu;
    }

    public void setWaktu (String waktu) {
        Waktu = waktu;
    }

    public String getNama_Peserta ( ) {
        return Nama_Peserta;
    }

    public void setNama_Peserta (String nama_Peserta) {
        Nama_Peserta = nama_Peserta;
    }
}
