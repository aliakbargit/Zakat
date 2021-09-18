package com.example.zakat.models.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.zakat.models.ApplicationModel;
import com.example.zakat.models.types.CommonType;
import com.example.zakat.models.types.Education;
import com.example.zakat.models.types.RentHouse;

public class ApplicationToSubmit implements Parcelable {
    private static final String TAG = "WriteToDb";
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String applicationId;
    private ApplicationModel applicantInfo;
    private CommonType commonType;
    private Education education;
    private RentHouse rentHouse;

    public ApplicationToSubmit() {
    }

    public ApplicationToSubmit(ApplicationModel applicantInfo, CommonType commonType, Education education, RentHouse rentHouse) {
        this.applicantInfo = applicantInfo;
        this.commonType = commonType;
        this.education = education;
        this.rentHouse = rentHouse;
    }



    public <T> ApplicationToSubmit(String createdAt, String updatedAt, String createdBy,String applicationId, ApplicationModel model, T type){
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.applicationId = applicationId;
        this.applicantInfo = model;
        if(type instanceof Education){
            this.education = (Education) type;
        }
        else if(type instanceof RentHouse){
            this.rentHouse = (RentHouse) type;
        }
        else if(type instanceof CommonType){
            this.commonType = (CommonType) type;
        }
        else
            Log.d(TAG, "WriteToDb: Error");
    }

    public <T> ApplicationToSubmit(ApplicationModel model, T type){
        this.applicantInfo = model;
        if(type instanceof Education){
            this.education = (Education) type;
        }
        else if(type instanceof RentHouse){
            this.rentHouse = (RentHouse) type;
        }
        else if(type instanceof CommonType){
            this.commonType = (CommonType) type;
        }
        else
            Log.d(TAG, "WriteToDb: Error");
    }


    protected ApplicationToSubmit(Parcel in) {
        createdAt = in.readString();
        updatedAt = in.readString();
        createdBy = in.readString();
        applicationId = in.readString();
        applicantInfo = in.readParcelable(ApplicationModel.class.getClassLoader());
        commonType = in.readParcelable(CommonType.class.getClassLoader());
        education = in.readParcelable(Education.class.getClassLoader());
        rentHouse = in.readParcelable(RentHouse.class.getClassLoader());
    }

    public static final Creator<ApplicationToSubmit> CREATOR = new Creator<ApplicationToSubmit>() {
        @Override
        public ApplicationToSubmit createFromParcel(Parcel in) {
            return new ApplicationToSubmit(in);
        }

        @Override
        public ApplicationToSubmit[] newArray(int size) {
            return new ApplicationToSubmit[size];
        }
    };

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ApplicationModel getApplicantInfo() {
        return applicantInfo;
    }

    public void setApplicantInfo(ApplicationModel applicantInfo) {
        this.applicantInfo = applicantInfo;
    }

    public CommonType getCommonType() {
        return commonType;
    }

    public void setCommonType(CommonType commonType) {
        this.commonType = commonType;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public RentHouse getRentHouse() {
        return rentHouse;
    }

    public void setRentHouse(RentHouse rentHouse) {
        this.rentHouse = rentHouse;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(createdBy);
        dest.writeString(applicationId);
        dest.writeParcelable(applicantInfo, flags);
        dest.writeParcelable(commonType, flags);
        dest.writeParcelable(education, flags);
        dest.writeParcelable(rentHouse, flags);
    }
}
