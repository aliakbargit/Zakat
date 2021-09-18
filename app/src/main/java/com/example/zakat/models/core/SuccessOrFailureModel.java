package com.example.zakat.models.core;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zakat.util.SuccessOrErrorResource;

public class SuccessOrFailureModel implements Parcelable {
    private String className;
    private SuccessOrErrorResource resource;

    public SuccessOrFailureModel(String className, SuccessOrErrorResource resource) {
        this.className = className;
        this.resource = resource;
    }

    protected SuccessOrFailureModel(Parcel in) {
        className = in.readString();
    }

    public static final Creator<SuccessOrFailureModel> CREATOR = new Creator<SuccessOrFailureModel>() {
        @Override
        public SuccessOrFailureModel createFromParcel(Parcel in) {
            return new SuccessOrFailureModel(in);
        }

        @Override
        public SuccessOrFailureModel[] newArray(int size) {
            return new SuccessOrFailureModel[size];
        }
    };

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SuccessOrErrorResource getResource() {
        return resource;
    }

    public void setResource(SuccessOrErrorResource resource) {
        this.resource = resource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(className);
    }
}
