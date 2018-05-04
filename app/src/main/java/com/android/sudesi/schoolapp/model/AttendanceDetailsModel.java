package com.android.sudesi.schoolapp.model;

import java.io.Serializable;

public class AttendanceDetailsModel implements Serializable {

    String attendance;
    String date;

    public AttendanceDetailsModel() {
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getDate1() {
        return date;
    }

    public void setDate1(String date) {
        this.date = date;
    }


}