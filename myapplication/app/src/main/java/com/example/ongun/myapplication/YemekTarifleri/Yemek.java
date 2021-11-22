package com.example.ongun.myapplication.YemekTarifleri;

public class Yemek {

    private String tarifler;
    private String adımlar;
    private String malzemeler;
    private String kalori;
    private String photourl;
    private String foodid;

    public Yemek(String tarifler, String adımlar, String malzemeler, String kalori, String photourl,String foodid) {
        this.tarifler = tarifler;
        this.adımlar = adımlar;
        this.malzemeler = malzemeler;
        this.kalori = kalori;
        this.photourl = photourl;
        this.foodid = foodid;
    }

    public Yemek() {
    }

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

    public String getTarifler() {
        return tarifler;
    }

    public void setTarifler(String tarifler) {
        this.tarifler = tarifler;
    }

    public String getAdımlar() {
        return adımlar;
    }

    public void setAdımlar(String adımlar) {
        this.adımlar = adımlar;
    }

    public String getMalzemeler() {
        return malzemeler;
    }

    public void setMalzemeler(String malzemeler) {
        this.malzemeler = malzemeler;
    }

    public String getKalori() {
        return kalori;
    }

    public void setKalori(String kalori) {
        this.kalori = kalori;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}