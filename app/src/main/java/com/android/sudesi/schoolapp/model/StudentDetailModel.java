package com.android.sudesi.schoolapp.model;

import java.io.Serializable;

/**
 * Created by i5 on 7/24/2017.
 */

public class StudentDetailModel implements Serializable {
    private String rollNo;
    private String name;
    private String dob;

    public String getTodaysAttendance() {
        return todaysAttendance;
    }

    public void setTodaysAttendance(String todaysAttendance) {
        this.todaysAttendance = todaysAttendance;
    }

    public void setMark_attendance(String mark_attendance) {
        this.mark_attendance = mark_attendance;
    }

    private String todaysAttendance;

    public StudentDetailModel(String rollNo, String name, String dob, String address, String parent_MobNo, String standard, String division) {
        this.rollNo = rollNo;
        this.name = name;
        this.dob = dob;
        this.address = address;
        Parent_MobNo = parent_MobNo;
        this.standard = standard;
        this.division = division;
        this.todaysAttendance=todaysAttendance;
    }
    public StudentDetailModel(){

    }

    private String address;
    private String Parent_MobNo;
    private String standard;
    private String division;

    public String getMark_attendance() {
        return mark_attendance;
    }

    public void C(String mark_attendance) {
        this.mark_attendance = mark_attendance;
    }

    private String mark_attendance;

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
