package com.android.sudesi.schoolapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.model.StudentDetailModel;

import java.util.List;

public class AttendanceDetailsAdapter extends BaseAdapter {

    private Context mContext;
    private List<StudentDetailModel> studentDetailModelList;
    private LayoutInflater inflater = null;
    //    private TopNShareModel topNShareModel;
    private AttendanceDetailsAdapter.ViewHolder viewHolder;

    public AttendanceDetailsAdapter(Context context, List<StudentDetailModel> studentDetailModels) {
        this.mContext = context;
        this.studentDetailModelList = studentDetailModels;
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
            viewHolder.roll_no = (TextView) convertView.findViewById(R.id.roll_no1);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name1);
            viewHolder.mobile_no = (TextView) convertView.findViewById(R.id.mobile_no1);
            viewHolder.standard = (TextView) convertView.findViewById(R.id.standard1);
            viewHolder.division = (TextView) convertView.findViewById(R.id.division1);

            viewHolder.rb_present = (RadioButton) convertView.findViewById(R.id.present);
            viewHolder.rb_absent = (RadioButton) convertView.findViewById(R.id.absent);


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
        viewHolder.rb_present.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (viewHolder.rb_present.isChecked()) {
                    viewHolder.rb_absent.setChecked(false);
                }
            }
        });
        viewHolder.rb_absent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (viewHolder.rb_absent.isChecked()) {
                    viewHolder.rb_present.setChecked(false);
                }
            }
        });



        return convertView;
    }

    public class ViewHolder {
        private TextView roll_no, name, mobile_no, standard, division;
        private RadioButton rb_present, rb_absent;
    }
}

