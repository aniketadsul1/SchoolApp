package com.android.sudesi.schoolapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.SchoolApp;
import com.android.sudesi.schoolapp.SmileyToast.TastyToast;
import com.android.sudesi.schoolapp.dbconfig.DbHelper;
import com.android.sudesi.schoolapp.libs.Utils;
import com.android.sudesi.schoolapp.model.StudentDetailModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AttendanceDetailsAdapter extends BaseAdapter {

    private Context mContext;
    private List<StudentDetailModel> studentDetailModelList;
    private LayoutInflater inflater = null;
    //    private TopNShareModel topNShareModel;
    private AttendanceDetailsAdapter.ViewHolder viewHolder;
    protected Utils utils;

    public AttendanceDetailsAdapter(Context context, List<StudentDetailModel> studentDetailModels) {
        this.mContext = context;
        this.studentDetailModelList = studentDetailModels;
        utils = new Utils(this.mContext);
    }

    @Override
    public int getCount() {
        return studentDetailModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentDetailModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewHolder = new AttendanceDetailsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_student_attendance, null);

            if (position % 2 == 0) {
                convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.background));
            } else {
                convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.background1));
            }

            viewHolder.roll_no = (TextView) convertView.findViewById(R.id.roll_no1);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name1);
            viewHolder.mobile_no = (TextView) convertView.findViewById(R.id.mobile_no1);
            viewHolder.standard = (TextView) convertView.findViewById(R.id.standard1);
            viewHolder.division = (TextView) convertView.findViewById(R.id.division1);
            viewHolder.todays_date = (TextView) convertView.findViewById(R.id.todays_date);
            viewHolder.rb_present = (RadioButton) convertView.findViewById(R.id.present);
            viewHolder.rb_absent = (RadioButton) convertView.findViewById(R.id.absent);
            viewHolder.rg_attendance = (RadioGroup) convertView.findViewById(R.id.rg_attendance);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AttendanceDetailsAdapter.ViewHolder) convertView.getTag();
        }
        final StudentDetailModel studentDetailModel = studentDetailModelList.get(position);

        viewHolder.roll_no.setText(studentDetailModel.getRollNo());
        viewHolder.name.setText(studentDetailModel.getName());
        viewHolder.mobile_no.setText(studentDetailModel.getParent_MobNo());
        viewHolder.standard.setText(studentDetailModel.getStandard());
        viewHolder.division.setText(studentDetailModel.getDivision());
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        final String str_todays_date = df.format(c);
        viewHolder.todays_date.setText(str_todays_date);


        viewHolder.rg_attendance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(i);
            /*    final StudentDetailModel studentDetailModel1=new StudentDetailModel();
                studentDetailModel1.setRollNo(studentDetailModel.getRollNo());
                studentDetailModel1.setName(studentDetailModel.getParent_MobNo());
                studentDetailModel1.setStandard(studentDetailModel.getStandard());
                studentDetailModel1.setDivision(studentDetailModel.getDivision());
                studentDetailModel1.setTodaysAttendance(str_todays_date);
                studentDetailModel1.setMark_attendance(radioButton.getText().toString());*/

                String valuesArray[] = {studentDetailModel.getRollNo(), studentDetailModel.getName(), studentDetailModel.getParent_MobNo(), studentDetailModel.getStandard(), studentDetailModel.getDivision(), str_todays_date, radioButton.getText().toString()};
                String[] selectionArgs = {studentDetailModel.getRollNo()};//
                String selection = "roll_no" + " = ?";
                //  boolean result = SchoolApp.dbCon.updateBulk(DbHelper.TABLE_DB_SCHOOL, selection, valuesArray, utils.column_studentDetails, selectionArgs);
                boolean result = SchoolApp.dbCon.updateBulk(DbHelper.TABLE_DB_MARK_DAILYATTENDANCE, selection, valuesArray, utils.column_mark_attendanceDetails, selectionArgs);
                if (result) {
                    TastyToast.makeText(mContext, "Data inserted Successfully", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                }

            }
        });
        return convertView;
    }

    public class ViewHolder {

        private TextView roll_no, name, mobile_no, standard, division, todays_date;
        private RadioButton rb_present, rb_absent;
        private RadioGroup rg_attendance;

    }
}

