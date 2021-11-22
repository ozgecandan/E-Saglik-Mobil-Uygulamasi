package com.example.ongun.myapplication.KanBagis;

import com.google.firebase.database.ServerValue;

public class Post {
    private String postKey;
    private String sehir;
    private String hastane;
    private String kanGrubu;
    private Object timeStamp;
    private String userid;
    private String telefon;

    public Post(String sehir,String hastane, String kanGrubu, String telefon, String userid) {
        this.sehir = sehir;
        this.hastane = hastane;
        this.kanGrubu = kanGrubu;
        this.timeStamp = ServerValue.TIMESTAMP;
        this.telefon = telefon;
        this.userid = userid;
    }
    public Post(){}

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getSehir() {
        return sehir;
    }


    public String getHastane() {
        return hastane;
    }

    public String getKanGrubu() {
        return kanGrubu;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }
    public String getUserid() {
        return userid;
    }


    public void setSehir(String sehir) {
        this.sehir = sehir;
    }



    public void setHastane(String hastane) {
        this.hastane = hastane;
    }

    public void setKanGrubu(String kanGrubu) {
        this.kanGrubu = kanGrubu;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

}
