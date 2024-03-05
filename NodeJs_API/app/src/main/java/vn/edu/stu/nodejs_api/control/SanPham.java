package vn.edu.stu.nodejs_api.control;

import java.io.Serializable;

public class SanPham implements Serializable {
   private String masp;
    private String tensp;
    private float giasp;
    private int soluong;
    private String loaisach;
    private String tentacgia;
    private String sokho;

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public float getGiasp() {
        return giasp;
    }

    public void setGiasp(float giasp) {
        this.giasp = giasp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public String getSokho() {
        return sokho;
    }

    public void setSokho(String sokho) {
        this.sokho = sokho;
    }

    public SanPham(String masp, String tensp, float giasp, int soluong, String loaisach, String tentacgia, String sokho) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.loaisach = loaisach;
        this.tentacgia = tentacgia;
        this.sokho = sokho;
    }

    public SanPham() {
    }
}
