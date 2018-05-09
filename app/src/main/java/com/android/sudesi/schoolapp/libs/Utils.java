package com.android.sudesi.schoolapp.libs;

import android.content.Context;

import com.android.sudesi.schoolapp.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/*import com.narnolia.app.R;
import com.narnolia.app.SharedPref;*/

/**
 * Created by Admin on 25-10-2016.
 */

public class Utils {
    private Context mContext;

    public static String URL = "http://182.72.128.5:83/LMSWS/Service1.svc";   //  ......... Narnolia UAT server

    public static String KEY_LEAD_DATA = "lead_data";
    public String[] column_studentDetails = new String[50];
    public String[] column_parentDetails = new String[50];
    public String[] column_teacherDetails = new String[50];
    public String[] column_mark_attendanceDetails = new String[50];

    public Utils(Context mContext) {

        this.mContext = mContext;

        //Student Details
        String[] studentDetailsArray = {mContext.getString(R.string.rollNo), mContext.getString(R.string.name),
                mContext.getString(R.string.dob), mContext.getString(R.string.address),
                mContext.getString(R.string.parent_MobNo), mContext.getString(R.string.std),
                mContext.getString(R.string.div)};

        column_studentDetails = Arrays.copyOf(studentDetailsArray, studentDetailsArray.length);

        //Parent Details
        String[] parentDetailsArray = {mContext.getString(R.string.P_Name), mContext.getString(R.string.P_address),
                mContext.getString(R.string.P_MobNo)};

        column_parentDetails = Arrays.copyOf(parentDetailsArray, parentDetailsArray.length);

        //Teacher Details
        String[] teacherDetailsArray = {mContext.getString(R.string.t_no), mContext.getString(R.string.t_name),
                mContext.getString(R.string.t_std), mContext.getString(R.string.t_div), mContext.getString(R.string.t_subject)};

        column_teacherDetails = Arrays.copyOf(teacherDetailsArray, teacherDetailsArray.length);
        String[] mark_attendanceDetailsArrays = {"roll_no", "name", "mobile_no", "standard", "division", "date", "mark_attendance"};
        column_mark_attendanceDetails = Arrays.copyOf(mark_attendanceDetailsArrays, mark_attendanceDetailsArrays.length);

    }

   /* public void logout(Context mContext) {
        SharedPref sharedPref = new SharedPref(mContext);
        sharedPref.clearResult();
    }*/

    public static String getSelectedDate(int noOfDays) {
        String selDate = "";
        try {
            Calendar now = Calendar.getInstance();
            // SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            now.setTime(new Date());
            now.add(Calendar.DAY_OF_YEAR, noOfDays);
            Date prevDate = now.getTime();
            selDate = df.format(prevDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selDate;
    }
}
