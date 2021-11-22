package com.example.ongun.myapplication.Aktivite;

public class Aktivite {

    private String aktiviteAdi;
    private String aktiviteAyrinti;
    private int thumbnail;

    public Aktivite() {
    }

    public Aktivite(String aktiviteAdi, String aktiviteAyrinti, int thumbnail) {
        this.aktiviteAdi = aktiviteAdi;
        this.aktiviteAyrinti = aktiviteAyrinti;
        this.thumbnail = thumbnail;
    }

    public void setAktiviteAdi(String aktiviteAdi) {
        this.aktiviteAdi = aktiviteAdi;
    }

    public void setAktiviteAyrinti(String aktiviteAyrinti) {
        this.aktiviteAyrinti = aktiviteAyrinti;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAktiviteAdi() {
        return aktiviteAdi;
    }

    public String getAktiviteAyrinti() {
        return aktiviteAyrinti;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
