package vn.edu.stu.nodejs_api.control;

import java.io.Serializable;

public class PhieuNhap implements Serializable {
    private String sopn, manv, masp, ngaypn;
    private int soluongpn;

    public String getSopn() {
        return sopn;
    }

    public void setSopn(String sopn) {
        this.sopn = sopn;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getNgaypn() {
        return ngaypn;
    }

    public void setNgaypn(String ngaypn) {
        this.ngaypn = ngaypn;
    }

    public int getSoluongpn() {
        return soluongpn;
    }

    public void setSoluongpn(int soluongpn) {
        this.soluongpn = soluongpn;
    }


    public PhieuNhap(String sopn, String manv, String masp, String ngaypn, int soluongpn) {
        this.sopn = sopn;
        this.manv = manv;
        this.masp = masp;
        this.ngaypn = ngaypn;
        this.soluongpn = soluongpn;
    }



    public PhieuNhap() {
    }
}
