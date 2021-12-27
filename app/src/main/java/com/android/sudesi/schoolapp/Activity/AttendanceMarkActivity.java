package com.android.sudesi.schoolapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sudesi.schoolapp.Adapter.AttendanceDetailsAdapter;
import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.SchoolApp;
import com.android.sudesi.schoolapp.SmileyToast.TastyToast;
import com.android.sudesi.schoolapp.dbconfig.DbHelper;
import com.android.sudesi.schoolapp.model.StudentDetailModel;

import java.util.ArrayList;
import java.util.List;

public class AttendanceMarkActivity extends Activity {
    ListView list_student1;
    String division,standard;
    StudentDetailModel studentDetailModel;
    AttendanceDetailsAdapter attendanceDetailsAdapter;
    Context mContext;
    private List<StudentDetailModel> studentDetailModelList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_mark);
        list_student1=(ListView)findViewById(R.id.list_student1);
        mContext=AttendanceMarkActivity.this;
        studentDetailModelList=new ArrayList<StudentDetailModel>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            standard = bundle.getString("standard");
            division=bundle.getString("division");
        }
        if (standard!=null && !TextUtils.isEmpty(standard)&&division!=null&&!TextUtils.isEmpty(division)){

            String where = " where Standard = '"+standard+"' and Division = '"+division+"'";
            Cursor cursor = SchoolApp.dbCon.fetchFromSelect(DbHelper.TABLE_DB_SCHOOL, where);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    studentDetailModel = createrstudentmodel(cursor);
                    studentDetailModelList.add(studentDetailModel);

                } while (cursor.moveToNext());
                cursor.close();

            } else {
                TastyToast.makeText(mContext, "No data found..!", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
            attendanceDetailsAdapter = new AttendanceDetailsAdapter(mContext, studentDetailModelList);
            list_student1.setAdapter(attendanceDetailsAdapter);
            setListViewHeightBasedOnItems(list_student1);
            attendanceDetailsAdapter.notifyDataSetChanged();




        }


    }



    public StudentDetailModel createrstudentmodel(Cursor cursor) {

        studentDetailModel = new StudentDetailModel();
        try {
            studentDetailModel.setRollNo(cursor.getString(cursor.getColumnIndex("Roll_No")));
            studentDetailModel.setName(cursor.getString(cursor.getColumnIndex("Name")));
            studentDetailModel.setDob(cursor.getString(cursor.getColumnIndex("DOB")));
            studentDetailModel.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
            studentDetailModel.setParent_MobNo(cursor.getString(cursor.getColumnIndex("Parent_Mobile_Number")));
            studentDetailModel.setStandard(cursor.getString(cursor.getColumnIndex("Standard")));
            studentDetailModel.setDivision(cursor.getString(cursor.getColumnIndex("Division")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentDetailModel;

    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;

        } else {
            return false;
        }

    }
}
