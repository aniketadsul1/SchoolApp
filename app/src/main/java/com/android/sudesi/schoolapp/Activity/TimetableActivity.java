package com.android.sudesi.schoolapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.sudesi.schoolapp.Adapter.TimeTableAdapter;
import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.SchoolApp;
import com.android.sudesi.schoolapp.dbconfig.DbHelper;
import com.android.sudesi.schoolapp.model.StudentDetailModel;
import com.android.sudesi.schoolapp.model.TimetableDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class TimetableActivity extends Activity {
    ListView list_timetable;
    TimetableDetailsModel timetableDetailsModel;
    TimeTableAdapter timeTableAdapter;
    Context mContext;
    private List<TimetableDetailsModel> timetableDetailsModelList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        list_timetable = (ListView) findViewById(R.id.list_timetable);
        mContext = TimetableActivity.this;
        timetableDetailsModelList = new ArrayList<TimetableDetailsModel>();


        Cursor cursor = SchoolApp.dbCon.fetchFromSelect(DbHelper.TABLE_DB_TIMETABLE,"");
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                timetableDetailsModel = creatertimetablemodel(cursor);
                timetableDetailsModelList.add(timetableDetailsModel);

            } while (cursor.moveToNext());
            cursor.close();

        } else {
            Toast.makeText(mContext, "No data found..!", Toast.LENGTH_SHORT).show();
        }
        timeTableAdapter = new TimeTableAdapter(mContext, timetableDetailsModelList);
        list_timetable.setAdapter(timeTableAdapter);
        setListViewHeightBasedOnItems(list_timetable);
        timeTableAdapter.notifyDataSetChanged();

    }

    public TimetableDetailsModel creatertimetablemodel(Cursor cursor) {

        timetableDetailsModel = new TimetableDetailsModel();
        try {
            timetableDetailsModel.setMonday(cursor.getString(cursor.getColumnIndex("monday")));
            timetableDetailsModel.setTuesday(cursor.getString(cursor.getColumnIndex("tuesday")));
            timetableDetailsModel.setWednesday(cursor.getString(cursor.getColumnIndex("wednesday")));
            timetableDetailsModel.setThursday(cursor.getString(cursor.getColumnIndex("thursday")));
            timetableDetailsModel.setFriday(cursor.getString(cursor.getColumnIndex("friday")));
            timetableDetailsModel.setSaturday(cursor.getString(cursor.getColumnIndex("saturday")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timetableDetailsModel;

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
