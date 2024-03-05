package vn.edu.stu.nodejs_api.control;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private String manv;
    private String tennv;
    private String diachinv;
    private int sđt;
    private String email;

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getDiachinv() {
        return diachinv;
    }

    public void setDiachinv(String diachinv) {
        this.diachinv = diachinv;
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

    public NhanVien(String manv, String tennv, String diachinv, int sđt, String email) {
        this.manv = manv;
        this.tennv = tennv;
        this.diachinv = diachinv;
        this.sđt = sđt;
        this.email = email;
    }

    public NhanVien() {
    }
}
