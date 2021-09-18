package com.example.zakat.models.user;

import android.os.Parcel;
import android.os.Parcelable;

public class UserApplicationPhaseOne implements Parcelable {
    private String uid;
    private String key;
    private String name;

    public UserApplicationPhaseOne(String uid, String key, String name) {
        this.uid = uid;
        this.key = key;
        this.name = name;
    }

    protected UserApplicationPhaseOne(Parcel in) {
        uid = in.readString();
        key = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(key);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserApplicationPhaseOne> CREATOR = new Creator<UserApplicationPhaseOne>() {
        @Override
        public UserApplicationPhaseOne createFromParcel(Parcel in) {
            return new UserApplicationPhaseOne(in);
        }

        @Override
        public UserApplicationPhaseOne[] newArray(int size) {
            return new UserApplicationPhaseOne[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
