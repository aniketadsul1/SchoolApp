package com.android.sudesi.schoolapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.SchoolApp;
import com.android.sudesi.schoolapp.dbconfig.DbHelper;
import com.android.sudesi.schoolapp.libs.Utils;
import com.android.sudesi.schoolapp.model.TeacherDetailModel;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends Activity {

    private EditText edt_teacher_no, edt_teacher_name, edt_teachr_std, edt_teacher_div, edt_subject;
    private Button btn_save_teacher;
    private String str_teacherNo, str_teacherName, str_teacherStd, str_teacherDiv, str_subject;
    protected Utils utils;
    private Context mContext;
    private TeacherDetailModel teacherDetailModel;
    private List<TeacherDetailModel> teacherDetailModelList;
    private List<String> teacherDetailsArrayList;
    DbHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        init();
        mContext = TeacherActivity.this;
        utils = new Utils(TeacherActivity.this);
        teacherDetailModelList = new ArrayList<>();
        teacherDetailsArrayList = new ArrayList<>();
        dbHelper = new DbHelper(this);
    }

    private void init() {

        //EditText
        edt_teacher_no = (EditText) findViewById(R.id.edt_teacher_no);
        edt_teacher_name = (EditText) findViewById(R.id.edt_teacher_name);
        edt_teachr_std = (EditText) findViewById(R.id.edt_teachr_std);
        edt_teacher_div = (EditText) findViewById(R.id.edt_teacher_div);
        edt_subject = (EditText) findViewById(R.id.edt_subject);

        //Button
        btn_save_teacher = (Button) findViewById(R.id.btn_save_teacher);

        btn_save_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    str_teacherNo = edt_teacher_no.getText().toString().trim();
                    str_teacherName = edt_teacher_name.getText().toString().trim();
                    str_teacherStd = edt_teachr_std.getText().toString().trim();
                    str_teacherDiv = edt_teacher_div.getText().toString().trim();
                    str_subject = edt_subject.getText().toString().trim();


                    if (!TextUtils.isEmpty(edt_teacher_no.getText().toString().trim()) && !TextUtils.isEmpty(edt_teacher_name.getText().toString().trim()) &&
                            !TextUtils.isEmpty(edt_teachr_std.getText().toString().trim()) && !TextUtils.isEmpty(edt_teacher_div.getText().toString().trim()) &&
                            !TextUtils.isEmpty(edt_subject.getText().toString().trim())) {

                        String valuesArray[] = {str_teacherNo, str_teacherName, str_teacherStd, str_teacherDiv, str_subject};
                        String[] selectionArgs = {str_teacherNo};//
                        String selection = "Teacher_No" + " = ?";
                        boolean result = SchoolApp.dbCon.updateBulk(DbHelper.TABLE_DB_TEACHER, selection, valuesArray, utils.column_teacherDetails, selectionArgs);
                        if (result) {
                            Toast.makeText(mContext, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                            edt_teacher_no.setText("");
                            edt_teacher_name.setText("");
                            edt_teachr_std.setText("");
                            edt_teacher_div.setText("");
                            edt_subject.setText("");
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
