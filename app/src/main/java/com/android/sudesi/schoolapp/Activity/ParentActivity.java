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
import com.android.sudesi.schoolapp.model.ParentDetailModel;

import java.util.ArrayList;
import java.util.List;

public class ParentActivity extends Activity {

    private EditText edt_name_parent, edt_address_parent, edt_Mob_no;
    private Button btn_save_parent;
    private String str_P_name, str_P_address, str_P_mobNo;
    protected Utils utils;
    private Context mContext;
    private ParentDetailModel parentDetailModel;
    private List<ParentDetailModel> parentDetailModelList;
    private List<String> parentDetailsArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        init();
        mContext = ParentActivity.this;
        utils = new Utils(mContext);
        parentDetailModelList = new ArrayList<>();
        parentDetailsArrayList = new ArrayList<>();

        DatabaseCopy databaseCopy = new DatabaseCopy();
        AssetManager assetManager = this.getAssets();
        databaseCopy.copy(assetManager, mContext);
        SchoolApp.dbCon = DataBaseCon.getInstance(getApplicationContext());

    }

    private void init() {

        // EditText
        edt_name_parent = (EditText) findViewById(R.id.edt_name_parent);
        edt_address_parent = (EditText) findViewById(R.id.edt_address_parent);
        edt_Mob_no = (EditText) findViewById(R.id.edt_Mob_no);

        // Button
        btn_save_parent = (Button) findViewById(R.id.btn_save_parent);

        btn_save_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_P_name = edt_name_parent.getText().toString().trim();
                str_P_address = edt_address_parent.getText().toString().trim();
                str_P_mobNo = edt_Mob_no.getText().toString().trim();

                if (!TextUtils.isEmpty(edt_name_parent.getText().toString().trim()) && !TextUtils.isEmpty(edt_address_parent.getText().toString().trim()) &&
                        !TextUtils.isEmpty(edt_Mob_no.getText().toString().trim())) {
                    String valuesArray[] = {str_P_name, str_P_address, str_P_mobNo};
                    String[] selectionArgs = {str_P_name/*, str_P_address, str_P_mobNo*/};//
                    String selection = "Name" + " = ?";
                    boolean result = SchoolApp.dbCon.update(DbHelper.TABLE_DB_PARENT, selection, valuesArray, utils.column_parentDetails, selectionArgs);
                    if (result) {
                        Toast.makeText(mContext, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                        edt_name_parent.setText("");
                        edt_address_parent.setText("");
                        edt_Mob_no.setText("");
                    }


                /*    // Cursor cursor = null;
                    String pincodeColumnNames[] = {"fname", "lname"};

                    Cursor cursor = SchoolApp.dbCon.fetch(DbHelper.TABLE_DB_SCHOOL, pincodeColumnNames, null, null, "fname", null, false, "lname", null);

                    if (cursor != null && cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        do {
                            String fname = "";
                            String lname = "";
                            fname = cursor.getString(cursor.getColumnIndex("fname"));

                            lname = cursor.getString(cursor.getColumnIndex("lname"));


                            parentDetailsArrayList.add(fname + " " + lname);
                        } while (cursor.moveToNext());
                        cursor.close();
                    }*/

                }
            }
        });
    }
}
