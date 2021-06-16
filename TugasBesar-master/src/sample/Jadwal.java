package sample;

public class Jadwal {
    private String namapengawas;
    private int ruangan;
    private String tempat;
    private int waktu;

    public Jadwal(String namapengawas,int ruangan,String tempat,int waktu){
        this.namapengawas = namapengawas;
        this.ruangan = ruangan;
        this.tempat = tempat;
        this.waktu = waktu;
    }

    public String getNamapengawas() {
        return namapengawas;
    }

    public void setNamapengawas(String namapengawas) {
        this.namapengawas = namapengawas;
    }

    public int getRuangan() {
        return ruangan;
    }

    public void setRuangan(int ruangan) {
        this.ruangan = ruangan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }
}
