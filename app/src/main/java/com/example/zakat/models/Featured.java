package com.example.zakat.models;

public class Featured {
    private String id;
    private String image;
    private String title;
    private String date;

    public Featured() {
    }

    public Featured(String id, String image, String title, String date) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
