package com.android.sudesi.schoolapp.model;

import java.io.Serializable;

public class TeacherDetailModel implements Serializable {

    private String t_No;
    private String t_name;
    private String t_standard;
    private String t_division;
    private String t_subject;

    public String getT_No() {
        return t_No;
    }

    public void setT_No(String t_No) {
        this.t_No = t_No;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_standard() {
        return t_standard;
    }

    public void setT_standard(String t_standard) {
        this.t_standard = t_standard;
    }

    public String getT_division() {
        return t_division;
    }

    public void setT_division(String t_division) {
        this.t_division = t_division;
    }

    public String getT_subject() {
        return t_subject;
    }

    public void setT_subject(String t_subject) {
        this.t_subject = t_subject;
    }
}
