package com.example.questionbank;

public class sem_courses {
    String bimage;
    String bname;

    public sem_courses() {
    }

    public sem_courses(String bimage , String bname) {
        this.bimage = bimage;
        this.bname=bname;
    }


    public String getBimage() {
        return bimage;
    }

    public void setBimage(String bimage) {
        this.bimage = bimage;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
