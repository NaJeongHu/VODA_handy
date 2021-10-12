package com.example.voda_handy;

public class StoreInfo {

    private String imageurl;
    private Integer star;
    private String storename;
    private Integer waiting;
    private String idToken;

    public StoreInfo() { }

//    public StoreInfo(String imageurl, Integer star, String storename, Integer waiting) {
//        this.imageurl = imageurl;
//        this.star = star;
//        this.storename = storename;
//        this.waiting = waiting;
//    }

    public String getImageurl() { return imageurl; }

    public void setImageurl(String imageurl) { this.imageurl = imageurl; }

    public Integer getStar() { return star; }

    public void setStar(Integer star) { this.star = star; }

    public String getStorename() { return storename; }

    public void setStorename(String storename) { this.storename = storename; }

    public Integer getWaiting() { return waiting; }

    public void setWaiting(Integer waiting) { this.waiting = waiting; }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
