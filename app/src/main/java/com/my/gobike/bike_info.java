package com.my.gobike;

public class bike_info {
    private  String date11, date22, genres, idrenter, location,phonenumber;

    public bike_info(String date11, String date22, String genres, String idrenter, String location, String phonenumber) {
        this.date11 = date11;
        this.date22 = date22;
        this.genres = genres;
        this.idrenter = idrenter;
        this.location = location;
        this.phonenumber = phonenumber;
    }

    public String getDate11() {
        return date11;
    }

    public String getDate22() {
        return date22;
    }

    public String getGenres() {
        return genres;
    }

    public String getIdrenter() {
        return idrenter;
    }

    public String getLocation() {
        return location;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setDate11(String date11) {
        this.date11 = date11;
    }

    public void setDate22(String date22) {
        this.date22 = date22;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setIdrenter(String idrenter) {
        this.idrenter = idrenter;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
