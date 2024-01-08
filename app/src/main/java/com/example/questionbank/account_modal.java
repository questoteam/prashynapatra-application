package com.example.questionbank;

public class account_modal {
    String name;
    String email;
    String password;
    String branch;

    String id;
String oc;
    public account_modal(String id, String email   , String password , String name  ,String branch) {
        this.name = name;
        this.password=password;
        this.email=email;
        this.branch =branch;
        this.id=id;
        this.oc="";

    }
//    public account_modal(String id, String oc){
//        this.id=id;
//        this.oc=oc;
//    }

    public account_modal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOc() {
        return oc;
    }

    public void setOc(String oc) {
        this.oc = oc;
    }
}
