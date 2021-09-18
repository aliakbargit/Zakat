package com.example.zakat.models.types;

import android.os.Parcel;
import android.os.Parcelable;

public class CommonType extends Common implements Parcelable {
    //Needy people, Debtors,Collector,Converted Muslims, Fee sabillah;

    public CommonType() {
    }

    public CommonType(String uid, String name, String ic, String postCode, String number, String amount, String address) {
        super(uid, name, ic, postCode, number, amount, address);
    }

    protected CommonType(Parcel in) {
        super(in);
    }

    public static final Creator<CommonType> CREATOR = new Creator<CommonType>() {
        @Override
        public CommonType createFromParcel(Parcel in) {
            return new CommonType(in);
        }

        @Override
        public CommonType[] newArray(int size) {
            return new CommonType[size];
        }
    };

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }
}
