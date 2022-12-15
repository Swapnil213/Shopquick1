package com.example.shopquick.model;

public class Users {
    String uid;
    String name;
    String Email;
    String Imguri;

    public Users(String uid, String name, String Email, String Imguri)
    {
        this.uid= uid;
        this.Email=Email;
        this.name=name;
        this.Imguri=Imguri;
    }

    public String getEmail() {
        return Email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getImguri() {
        return Imguri;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImguri(String imguri) {
        Imguri = imguri;
    }
}
