package com.example.shopquick.model;

public class AddProdModel {

    String pid,name,price,description,category,Image,date,time;
    public AddProdModel(){ }

    public AddProdModel(String pid,String name,String price,String description
            ,String category,String Image,String date,String time)
    {
        this.pid=pid;
        this.name=name;
        this.price=price;
        this.description=description;
        this.category=category;
        this.Image=Image;
        this.date=date;
        this.time=time;
    }

    public String getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return Image;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
