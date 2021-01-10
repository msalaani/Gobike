package com.my.gobike;

public class renter {
    String id_renter;
    String  phonenumber;
    String location;
    String date11;
    String date22;
    String genres;

    public renter(String id_renter, String phonenumber, String location, String date11, String date22, String genres ) {
        this.id_renter = id_renter;
        this.phonenumber = phonenumber;
        this.location = location;
        this.date11 = date11;
        this.date22 = date22;
        this.genres = genres;

    }

    public String getId_renter() {
        return id_renter;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getLocation() {
        return location;
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



    public void setId_renter(String id_renter) {
        this.id_renter = id_renter;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setLocation(String location) {
        this.location = location;
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


}
