package vn.edu.stu.nodejs_api.control;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private String makh;
    private String tenkh;
    private String diachikh;
    private int sđt;
    private String email;

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDiachikh() {
        return diachikh;
    }

    public void setDiachikh(String diachikh) {
        this.diachikh = diachikh;
    }

    public int getSđt() {
        return sđt;
    }

    public void setSđt(int sđt) {
        this.sđt = sđt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public KhachHang(String makh, String tenkh, String diachikh, int sđt, String email) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.diachikh = diachikh;
        this.sđt = sđt;
        this.email = email;
    }
    public KhachHang() {

    }
}
