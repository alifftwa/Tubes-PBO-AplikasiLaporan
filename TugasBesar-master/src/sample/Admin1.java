package sample;

public class Admin1 {
    private String txtfield1;
    private int txtfield2;
    private String txtfield3;
    private int txtfield4;

    public Admin1 (String txtfield1, int txtfield2, String txtfield3, int txtfield4){
        this.setTxtfield1(txtfield1);
        this.setTxtfield2(txtfield2);
        this.setTxtfield3(txtfield3);
        this.setTxtfield4(txtfield4);
    }


    public String getTxtfield1 ( ) {
        return txtfield1;
    }

    public void setTxtfield1 (String txtfield1) {
        this.txtfield1 = txtfield1;
    }

    public int getTxtfield2 ( ) {
        return txtfield2;
    }

    public void setTxtfield2 (int txtfield2) {
        this.txtfield2 = txtfield2;
    }

    public String getTxtfield3 ( ) {
        return txtfield3;
    }

    public void setTxtfield3 (String txtfield3) {
        this.txtfield3 = txtfield3;
    }

    public int getTxtfield4 ( ) {
        return txtfield4;
    }

    public void setTxtfield4 (int txtfield4) {
        this.txtfield4 = txtfield4;
    }
}
