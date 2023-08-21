package com.fpoly.nguyenvietanh_ph32739;

public class LapTop {
    private String maLapTop;
    private String tenLapTop;
    private int giaLapTop;
    private int khuyenMai;

    public LapTop(String maLapTop, String tenLapTop, int giaLapTop, int khuyenMai) {
        this.maLapTop = maLapTop;
        this.tenLapTop = tenLapTop;
        this.giaLapTop = giaLapTop;
        this.khuyenMai = khuyenMai;
    }

    public String getMaLapTop() {
        return maLapTop;
    }

    public void setMaLapTop(String maLapTop) {
        this.maLapTop = maLapTop;
    }

    public String getTenLapTop() {
        return tenLapTop;
    }

    public void setTenLapTop(String tenLapTop) {
        this.tenLapTop = tenLapTop;
    }

    public int getGiaLapTop() {
        return giaLapTop;
    }

    public void setGiaLapTop(int giaLapTop) {
        this.giaLapTop = giaLapTop;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }
}
