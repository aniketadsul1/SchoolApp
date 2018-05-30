package com.android.sudesi.schoolapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.libs.Utils;
import com.android.sudesi.schoolapp.model.StudentDetailModel;
import com.android.sudesi.schoolapp.model.TimetableDetailsModel;

import java.util.List;

public class TimeTableAdapter extends BaseAdapter {
    private Context mContext;
    protected Utils utils;
    private LayoutInflater inflater = null;
    private TimeTableAdapter.ViewHolder viewHolder;
    private List<TimetableDetailsModel> timetableDetailsModelList;

   public TimeTableAdapter(Context context, List<TimetableDetailsModel> timetableDetailsModels) {
        this.mContext = context;
        this.timetableDetailsModelList = timetableDetailsModels;
        utils = new Utils(this.mContext);
    }

    @Override
    public int getCount() {
        return timetableDetailsModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return timetableDetailsModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewHolder = new TimeTableAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_timetable, null);
            viewHolder.moday=(TextView)convertView.findViewById(R.id.monday);
            viewHolder.tuesday=(TextView)convertView.findViewById(R.id.tuesday);
            viewHolder.wednesday=(TextView)convertView.findViewById(R.id.wednesday);
            viewHolder.thursday=(TextView)convertView.findViewById(R.id.thursday);
            viewHolder.friday=(TextView)convertView.findViewById(R.id.friday);
            viewHolder.saturday=(TextView)convertView.findViewById(R.id.saturday);
            convertView.setTag(viewHolder);


        }else {
            viewHolder = (TimeTableAdapter.ViewHolder) convertView.getTag();
        }
        final TimetableDetailsModel timetableDetailsModel = timetableDetailsModelList.get(i);
        viewHolder.moday.setText(timetableDetailsModel.getMonday());
        viewHolder.tuesday.setText(timetableDetailsModel.getTuesday());
        viewHolder.wednesday.setText(timetableDetailsModel.getWednesday());
        viewHolder.thursday.setText(timetableDetailsModel.getThursday());
        viewHolder.friday.setText(timetableDetailsModel.getFriday());
        viewHolder.saturday.setText(timetableDetailsModel.getSaturday());
        return convertView;
    }
    public class ViewHolder {

      TextView moday,tuesday,wednesday,thursday,friday, saturday;

    }
}