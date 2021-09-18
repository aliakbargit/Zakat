package com.example.zakat.models.user.type;

import android.os.Parcelable;

public class Approve extends UserTypeCommon implements Parcelable {
    private String by;

    public Approve() {
    }

    public Approve(String uid, String id, String name, String type, String image, String createdOn, String updatedOn, String by) {
        super(uid, id, name, type, image, createdOn, updatedOn);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
