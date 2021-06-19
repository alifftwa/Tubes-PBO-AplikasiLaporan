package sample;

public class Jadwal {
    private String namapengawas;
    private int Ruangan;
    private String Tempat;
    private int Waktu;
    private int Jam;

    public Jadwal(String namapengawas,int Ruangan,String Tempat,int Waktu,int Jam){
        this.namapengawas = namapengawas;
        this.Ruangan = Ruangan;
        this.Tempat=Tempat;
        this.Waktu = Waktu;
        this.Jam = Jam;
    }


    public String getNamapengawas() {
        return namapengawas;
    }

    public void setNamapengawas(String namapengawas) {
        this.namapengawas = namapengawas;
    }

    public int getRuangan() {
        return Ruangan;
    }

    public void setRuangan(int ruangan) {
        Ruangan = ruangan;
    }

    public String getTempat() {
        return Tempat;
    }

    public void setTempat(String tempat) {
        Tempat = tempat;
    }
}
