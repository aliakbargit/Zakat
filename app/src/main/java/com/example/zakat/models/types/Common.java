package com.example.zakat.models.types;

import android.os.Parcel;
import android.os.Parcelable;

public class Common implements Parcelable {
    private String uid;
    private String name;
    private String ic;
    private String postCode;
    private String number;
    private String amount;
    private String address;

    private String city;
    private String state;

    public Common() {
    }

    public Common(String uid, String name, String ic, String postCode, String number, String amount, String address) {
        this.uid = uid;
        this.name = name;
        this.ic = ic;
        this.postCode = postCode;
        this.number = number;
        this.amount = amount;
        this.address = address;
    }

    public Common(String uid, String name, String ic, String postCode, String number, String amount, String address, String city, String state) {
        this.uid = uid;
        this.name = name;
        this.ic = ic;
        this.postCode = postCode;
        this.number = number;
        this.amount = amount;
        this.address = address;
        this.city = city;
        this.state = state;
    }


    protected Common(Parcel in) {
        uid = in.readString();
        name = in.readString();
        ic = in.readString();
        postCode = in.readString();
        number = in.readString();
        amount = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
    }

    public static final Creator<Common> CREATOR = new Creator<Common>() {
        @Override
        public Common createFromParcel(Parcel in) {
            return new Common(in);
        }

        @Override
        public Common[] newArray(int size) {
            return new Common[size];
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

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Common{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", ic='" + ic + '\'' +
                ", postCode='" + postCode + '\'' +
                ", number='" + number + '\'' +
                ", amount='" + amount + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(ic);
        dest.writeString(postCode);
        dest.writeString(number);
        dest.writeString(amount);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
    }
}

