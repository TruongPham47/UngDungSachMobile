package vn.edu.stu.nodejs_api.control;

import java.io.Serializable;

public class DonHang implements Serializable {


    private String madh, makh, masp;
    private String ngaygiaohang, ngaydathang;
    private float tongtien;
    private int soluong;

    public String getMadh() {
        return madh;
    }

    public void setMadh(String madh) {
        this.madh = madh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getNgaygiaohang() {
        return ngaygiaohang;
    }

    public void setNgaygiaohang(String ngaygiaohang) {
        this.ngaygiaohang = ngaygiaohang;
    }

    public String getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(String ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


    public DonHang(String madh, String ngaygiaohang, String ngaydathang, int tongtien, int soluong, String makh, String masp) {
        this.madh = madh;
        this.ngaygiaohang = ngaygiaohang;
        this.ngaydathang = ngaydathang;
        this.tongtien = tongtien;
        this.soluong = soluong;
        this.makh = makh;
        this.masp = masp;
    }
    public DonHang() {
    }

}
