package sample;

public class Jadwal {
    private String namapengawas;
    private int Ruangan;
    private String Tempat;
    private int Waktu;
    private int jam;

    public Jadwal(String namapengawas,int Ruangan,String Tempat,int Waktu,int jam){
        this.setNamapengawas(namapengawas);
        this.setRuangan(Ruangan);
        this.setTempat(Tempat);
        this.setWaktu(Waktu);
        this.setJam(jam);

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


    public int getJam() {
        return jam;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }


    public String getNamapengawas() {
        return namapengawas;
    }

    public void setNamapengawas(String namapengawas) {
        this.namapengawas = namapengawas;
    }
}
