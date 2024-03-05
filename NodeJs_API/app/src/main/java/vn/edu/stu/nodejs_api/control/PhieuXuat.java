package vn.edu.stu.nodejs_api.control;

import java.io.Serializable;

public class PhieuXuat implements Serializable {
    public String getSopx() {
        return sopx;
    }

    public void setSopx(String sopx) {
        this.sopx = sopx;
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

    public String getNgaypx() {
        return ngaypx;
    }

    public void setNgaypx(String ngaypx) {
        this.ngaypx = ngaypx;
    }

    public int getSoluongpx() {
        return soluongpx;
    }

    public void setSoluongpx(int soluongpx) {
        this.soluongpx = soluongpx;
    }

    private String sopx, manv, masp, ngaypx;
    private int soluongpx;
    public PhieuXuat(String sopx, String manv, String masp, String ngaypx, int soluongpx) {
        this.sopx = sopx;
        this.manv = manv;
        this.masp = masp;
        this.ngaypx = ngaypx;
        this.soluongpx = soluongpx;
    }


    public PhieuXuat() {
    }


}
