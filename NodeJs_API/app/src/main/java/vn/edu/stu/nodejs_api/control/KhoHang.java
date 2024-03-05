package vn.edu.stu.nodejs_api.control;

import java.io.Serializable;

public class KhoHang implements Serializable {
    private String sokho;
    private int tong, nhap, xuat;

    public String getSokho() {
        return sokho;
    }

    public void setSokho(String sokho) {
        this.sokho = sokho;
    }

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }

    public int getNhap() {
        return nhap;
    }

    public void setNhap(int nhap) {
        this.nhap = nhap;
    }

    public int getXuat() {
        return xuat;
    }

    public void setXuat(int xuat) {
        this.xuat = xuat;
    }

    public KhoHang(String sokho, int tong, int nhap, int xuat) {
        this.sokho = sokho;
        this.tong = tong;
        this.nhap = nhap;
        this.xuat = xuat;
    }

    public KhoHang() {
    }
}
