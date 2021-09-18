package com.example.zakat.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zakat.models.core.Profile;
import com.example.zakat.models.user.type.Approve;
import com.example.zakat.models.user.type.Pending;
import com.example.zakat.models.user.type.Reject;

public class UserProfile extends Profile implements Parcelable {
    private Approve approve;
    private Pending pending;
    private Reject reject;

    public UserProfile() {
    }

    public UserProfile(String uid, String name, String email, String phone, String type, String image, String cratedOn, String updatedOn, Approve approve, Pending pending, Reject reject) {
        super(uid, name, email, phone, type, image, cratedOn, updatedOn);
        this.approve = approve;
        this.pending = pending;
        this.reject = reject;
    }

    public UserProfile(String uid, String name, String email, String phone, String type, String image, String cratedOn, String updatedOn) {
        super(uid, name, email, phone, type, image, cratedOn, updatedOn);
    }

    protected UserProfile(Parcel in) {
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    public Approve getApprove() {
        return approve;
    }

    public void setApprove(Approve approve) {
        this.approve = approve;
    }

    public Pending getPending() {
        return pending;
    }

    public void setPending(Pending pending) {
        this.pending = pending;
    }

    public Reject getReject() {
        return reject;
    }

    public void setReject(Reject reject) {
        this.reject = reject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
