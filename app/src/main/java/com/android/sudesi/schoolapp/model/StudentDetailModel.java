package com.android.sudesi.schoolapp.model;

import java.io.Serializable;

/**
 * Created by i5 on 7/24/2017.
 */

public class StudentDetailModel implements Serializable {
    private String rollNo;
    private String name;
    private String dob;
    private String address;
    private String Parent_MobNo;
    private String standard;
    private String division;

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParent_MobNo() {
        return Parent_MobNo;
    }

    public void setParent_MobNo(String parent_MobNo) {
        Parent_MobNo = parent_MobNo;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }


}
