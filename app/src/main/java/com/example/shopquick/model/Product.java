package com.example.shopquick.model;

import java.math.BigDecimal;

public class Product {

    private int pId;
    private String pName;
    private BigDecimal pPrice;
    private String pDesc;
    private String pImgName;

    Product()
    {super();}

    public Product(int pId, String pName, BigDecimal pPrice, String pDesc, String pImgName)
    {
        setpId(pId);
        setpName(pName);
        setpPrice(pPrice);
        setpDesc(pDesc);
        setpImgName(pImgName);
    }

    public int getpId() {
        return pId;
    }

    public String getpName() {
        return pName;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public String getpDesc() {
        return pDesc;
    }

    public String getpImgName() {
        return pImgName;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public void setpImgName(String pImgName) {
        this.pImgName = pImgName;
    }


}
