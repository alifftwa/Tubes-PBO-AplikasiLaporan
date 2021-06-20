package sample;

public class Jadwal {
    private String namapengawas;
    private int Ruangan;
    private String Tempat;
    private int Waktu;

    public Jadwal(String namapengawas,int Ruangan,String Tempat,int Waktu,int Jam){
        this.setNamapengawas(namapengawas);
        this.setRuangan(Ruangan);
        this.setTempat(Tempat);
        this.setWaktu(Waktu);
    }


    public String getNamapengawas ( ) {
        return namapengawas;
    }

    public void setNamapengawas (String namapengawas) {
        this.namapengawas = namapengawas;
    }

    public int getRuangan ( ) {
        return Ruangan;
    }

    public void setRuangan (int ruangan) {
        Ruangan = ruangan;
    }

    public String getTempat ( ) {
        return Tempat;
    }

    public void setTempat (String tempat) {
        Tempat = tempat;
    }

    public int getWaktu ( ) {
        return Waktu;
    }

    public void setWaktu (int waktu) {
        Waktu = waktu;
    }
}
