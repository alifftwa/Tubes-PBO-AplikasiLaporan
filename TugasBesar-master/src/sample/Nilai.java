package sample;

public class Nilai {
    private int tingkatkeberhasilan;
    private int penulisanlaporan;
    private int keaktifanmahasiswa;

    public Nilai(int tingkatkeberhasilan,int penulisanlaporan,int keaktifanmahasiswa){
        this.tingkatkeberhasilan = tingkatkeberhasilan;
        this.penulisanlaporan = penulisanlaporan;
        this.keaktifanmahasiswa = keaktifanmahasiswa;
    }


    public int getTingkatkeberhasilan() {
        return tingkatkeberhasilan;
    }

    public void setTingkatkeberhasilan(int tingkatkeberhasilan) {
        this.tingkatkeberhasilan = tingkatkeberhasilan;
    }

    public int getPenulisanlaporan() {
        return penulisanlaporan;
    }

    public void setPenulisanlaporan(int penulisanlaporan) {
        this.penulisanlaporan = penulisanlaporan;
    }

    public int getKeaktifanmahasiswa() {
        return keaktifanmahasiswa;
    }

    public void setKeaktifanmahasiswa(int keaktifanmahasiswa) {
        this.keaktifanmahasiswa = keaktifanmahasiswa;
    }
}
