package sample;

public class Edosen {
    private int id_pengawas;
    private String nama_pengawas;
    private int gkb;
    private int ruangan;


    public Edosen (int id_pengawas, String nama_pengawas, int gkb, int ruangan) {
        this.id_pengawas = id_pengawas;
        this.nama_pengawas = nama_pengawas;
        this.gkb = gkb;
        this.ruangan = ruangan;
    }

    public int getId_pengawas ( ) {
        return id_pengawas;
    }

    public void setId_pengawas (int id_pengawas) { this.id_pengawas = id_pengawas; }

    public String getNama_pengawas ( ) {
        return nama_pengawas;
    }

    public void setNama_pengawas (String nama_pengawas) {
        this.nama_pengawas = nama_pengawas;
    }

    public int getGkb ( ) {
        return gkb;
    }

    public void setGkb (int gkb) {
        this.gkb = gkb;
    }

    public int getRuangan ( ) {
        return ruangan;
    }

    public void setRuangan (int ruangan) { this.ruangan = ruangan; }
}
