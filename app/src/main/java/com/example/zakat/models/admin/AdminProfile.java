package com.example.zakat.models.admin;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zakat.models.core.Profile;

public class AdminProfile extends Profile implements Parcelable {
    private String pending;
    private String approve;
    private String reject;
    private String total;

    public AdminProfile() {
    }

    public AdminProfile(String uid, String name, String email, String phone, String type, String image, String cratedOn, String updatedOn) {
        super(uid, name, email, phone, type, image, cratedOn, updatedOn);
    }

    public AdminProfile(String uid, String name, String email, String phone, String type, String image, String cratedOn, String updatedOn, String pending, String approve, String reject, String total) {
        super(uid, name, email, phone, type, image, cratedOn, updatedOn);
        this.pending = pending;
        this.approve = approve;
        this.reject = reject;
        this.total = total;
    }

    protected AdminProfile(Parcel in) {
        pending = in.readString();
        approve = in.readString();
        reject = in.readString();
        total = in.readString();
    }

    public static final Creator<AdminProfile> CREATOR = new Creator<AdminProfile>() {
        @Override
        public AdminProfile createFromParcel(Parcel in) {
            return new AdminProfile(in);
        }

        @Override
        public AdminProfile[] newArray(int size) {
            return new AdminProfile[size];
        }
    };

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pending);
        dest.writeString(approve);
        dest.writeString(reject);
        dest.writeString(total);
    }
}
