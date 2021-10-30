package com.example.voda_handy;

import android.content.Intent;

import java.io.Serializable;

public class Menu implements Serializable {
    private String menuname;
    private String explanation;
    private String imageurl;
    private Integer price;
    private Integer number;

    public Menu() {
    }

    public Menu(String menuname, Integer price, Integer number) {
        this.menuname = menuname;
        this.price = price;
        this.number = number;
    }

    public Menu(String menuname, String explanation, String imageurl, Integer price, Integer number) {
        this.menuname = menuname;
        this.explanation = explanation;
        this.imageurl = imageurl;
        this.price = price;
        this.number = number;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
