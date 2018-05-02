package com.android.sudesi.schoolapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.SchoolApp;
import com.android.sudesi.schoolapp.dbconfig.DataBaseCon;
import com.android.sudesi.schoolapp.dbconfig.DatabaseCopy;
import com.android.sudesi.schoolapp.dbconfig.DbHelper;
import com.android.sudesi.schoolapp.libs.Utils;
import com.android.sudesi.schoolapp.model.StudentDetailModel;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends Activity {

    private EditText edt_roll_no, edt_name, edt_dob, edt_address, edt_Parent_Mob_no, edt_std, edt_div;
    private Button btn_save;
    private String str_rollNo, str_name, str_dob, str_address, str_parent_MobNo, str_std, str_div;
    protected Utils utils;
    private Context mContext;
    private StudentDetailModel studentDetailModel;
    private List<StudentDetailModel> studentDetailModelList;
    private List<String> studentDetailsArrayList;
    DbHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        init();
        mContext = StudentActivity.this;
        utils = new Utils(StudentActivity.this);
        studentDetailModelList = new ArrayList<>();
        studentDetailsArrayList = new ArrayList<>();

        DatabaseCopy databaseCopy = new DatabaseCopy();
        AssetManager assetManager = StudentActivity.this.getAssets();
        databaseCopy.copy(assetManager, StudentActivity.this);
        SchoolApp.dbCon = DataBaseCon.getInstance(getApplicationContext());
        dbHelper = new DbHelper(this);
    }

    private void init() {

        //EditText
        edt_roll_no = (EditText) findViewById(R.id.edt_roll_no);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_dob = (EditText) findViewById(R.id.edt_dob);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_Parent_Mob_no = (EditText) findViewById(R.id.edt_Parent_Mob_no);
        edt_std = (EditText) findViewById(R.id.edt_std);
        edt_div = (EditText) findViewById(R.id.edt_div);

        //Button
        btn_save = (Button) findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    str_rollNo = edt_roll_no.getText().toString().trim();
                    str_name = edt_name.getText().toString().trim();
                    str_dob = edt_dob.getText().toString().trim();
                    str_address = edt_address.getText().toString().trim();
                    str_parent_MobNo = edt_Parent_Mob_no.getText().toString().trim();
                    str_std = edt_std.getText().toString().trim();
                    str_div = edt_div.getText().toString().trim();

                    if (!TextUtils.isEmpty(edt_roll_no.getText().toString().trim()) && !TextUtils.isEmpty(edt_name.getText().toString().trim()) &&
                            !TextUtils.isEmpty(edt_dob.getText().toString().trim()) && !TextUtils.isEmpty(edt_address.getText().toString().trim()) &&
                            !TextUtils.isEmpty(edt_Parent_Mob_no.getText().toString().trim()) && !TextUtils.isEmpty(edt_std.getText().toString().trim()) &&
                            !TextUtils.isEmpty(edt_div.getText().toString().trim())) {

                        String valuesArray[] = {str_rollNo, str_name, str_dob, str_address, str_parent_MobNo, str_std, str_div};
                        String[] selectionArgs = {str_rollNo};//
                        String selection = "Roll_No" + " = ?";
                        boolean result = SchoolApp.dbCon.updateBulk(DbHelper.TABLE_DB_SCHOOL, selection, valuesArray, utils.column_studentDetails, selectionArgs);
                        if (result) {
                            Toast.makeText(mContext, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                            edt_roll_no.setText("");
                            edt_name.setText("");
                            edt_dob.setText("");
                            edt_address.setText("");
                            edt_Parent_Mob_no.setText("");
                            edt_std.setText("");
                            edt_div.setText("");
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
