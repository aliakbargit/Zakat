package com.example.zakat.models.user.type;

import android.os.Parcel;
import android.os.Parcelable;

public class UserTypeCommon implements Parcelable {
    private String uid;
    private String id;
    private String name;
    private String type;
    private String image;
    private String createdOn;
    private String updatedOn;

    public UserTypeCommon() {
    }

    public UserTypeCommon(String uid, String id, String name, String type, String image, String createdOn, String updatedOn) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    protected UserTypeCommon(Parcel in) {
        uid = in.readString();
        id = in.readString();
        name = in.readString();
        type = in.readString();
        image = in.readString();
        createdOn = in.readString();
        updatedOn = in.readString();
    }

    public static final Creator<UserTypeCommon> CREATOR = new Creator<UserTypeCommon>() {
        @Override
        public UserTypeCommon createFromParcel(Parcel in) {
            return new UserTypeCommon(in);
        }

        @Override
        public UserTypeCommon[] newArray(int size) {
            return new UserTypeCommon[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(image);
        dest.writeString(createdOn);
        dest.writeString(updatedOn);
    }
}
