package com.example.zakat.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ApplicationModel implements Parcelable {
    private String uid;
    private String fullName;
    private String ic;
    private String email;
    private String address;
    private String city;
    private String state;
    private String phoneNo;
    private String job;
    private String marital;
    private String applicationType;
    private String children;
    private String siblings;
    private String otherIncome;

    public ApplicationModel() {
    }

    public ApplicationModel(String uid, String fullName, String ic, String email, String address, String city, String state, String phoneNo, String job, String marital, String applicationType, String children, String siblings, String otherIncome) {
        this.uid = uid;
        this.fullName = fullName;
        this.ic = ic;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNo = phoneNo;
        this.job = job;
        this.marital = marital;
        this.applicationType = applicationType;
        this.children = children;
        this.siblings = siblings;
        this.otherIncome = otherIncome;
    }

    protected ApplicationModel(Parcel in) {
        uid = in.readString();
        fullName = in.readString();
        ic = in.readString();
        email = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        phoneNo = in.readString();
        job = in.readString();
        marital = in.readString();
        applicationType = in.readString();
        children = in.readString();
        siblings = in.readString();
        otherIncome = in.readString();
    }

    public static final Creator<ApplicationModel> CREATOR = new Creator<ApplicationModel>() {
        @Override
        public ApplicationModel createFromParcel(Parcel in) {
            return new ApplicationModel(in);
        }

        @Override
        public ApplicationModel[] newArray(int size) {
            return new ApplicationModel[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getSiblings() {
        return siblings;
    }

    public void setSiblings(String siblings) {
        this.siblings = siblings;
    }

    public String getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(String otherIncome) {
        this.otherIncome = otherIncome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(fullName);
        dest.writeString(ic);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(phoneNo);
        dest.writeString(job);
        dest.writeString(marital);
        dest.writeString(applicationType);
        dest.writeString(children);
        dest.writeString(siblings);
        dest.writeString(otherIncome);
    }
}
