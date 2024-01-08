package com.example.questionbank;

public class sem_modal {

    public String imageurl;
 private  String semno;
public String oc;
    public sem_modal() {
    }

    public sem_modal(String imageurl , String semno) {
        this.imageurl = imageurl;
        this.semno=semno;
        this.oc="";
    }

    public sem_modal(String oc) {
        this.oc = oc;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSemno() {
        return semno;
    }

    public void setSemno(String semno) {
        this.semno = semno;
    }

    public String getOc() {
        return oc;
    }

    public void setOc(String oc) {
        this.oc = oc;
    }
}
