package com.example.zakat.models.core;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    private String uid;
    private String name;
    private String email;
    private String phone;
    private String type;
    private String image;
    private String cratedOn;
    private String updatedOn;


    public Profile() {
    }

    public Profile(String uid, String name, String email, String phone, String type, String image, String cratedOn, String updatedOn) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.image = image;
        this.cratedOn = cratedOn;
        this.updatedOn = updatedOn;
    }

    protected Profile(Parcel in) {
        uid = in.readString();
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        type = in.readString();
        image = in.readString();
        cratedOn = in.readString();
        updatedOn = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCratedOn() {
        return cratedOn;
    }

    public void setCratedOn(String cratedOn) {
        this.cratedOn = cratedOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(type);
        dest.writeString(image);
        dest.writeString(cratedOn);
        dest.writeString(updatedOn);
    }
}