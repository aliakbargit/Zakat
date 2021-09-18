package com.example.zakat.models.types;

import android.os.Parcelable;

public class Education extends Common implements Parcelable {
    private String institution;
    private String stuClass;

    public Education() {
    }

    public Education(String uid, String name, String ic, String postCode, String number, String amount, String schoolAddress, String institution, String stuClass) {
        super(uid, name, ic, postCode, number, amount, schoolAddress);
        this.institution = institution;
        this.stuClass = stuClass;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    @Override
    public String toString() {
        return "Education{" +
                "institution='" + institution + '\'' +
                ", stuClass='" + stuClass + '\'' +
                '}';
    }

}
