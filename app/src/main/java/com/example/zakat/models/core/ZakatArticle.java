package com.example.zakat.models.core;

import android.os.Parcel;
import android.os.Parcelable;

public class ZakatArticle implements Parcelable {
    private String id;
    private String title;
    private String image;
    private String body;
    private String date;
    private String author;

    public ZakatArticle() {
    }

    public ZakatArticle(String id, String title, String image, String body, String date, String author) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.body = body;
        this.date = date;
        this.author = author;
    }

    protected ZakatArticle(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        body = in.readString();
        date = in.readString();
        author = in.readString();
    }

    public static final Creator<ZakatArticle> CREATOR = new Creator<ZakatArticle>() {
        @Override
        public ZakatArticle createFromParcel(Parcel in) {
            return new ZakatArticle(in);
        }

        @Override
        public ZakatArticle[] newArray(int size) {
            return new ZakatArticle[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(body);
        dest.writeString(date);
        dest.writeString(author);
    }
}
