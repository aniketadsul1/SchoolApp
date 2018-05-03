package com.android.sudesi.schoolapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.model.StudentDetailModel;

import java.util.List;

public class StudentDetailsAdapter extends BaseAdapter {

    private Context mContext;
    private List<StudentDetailModel> studentDetailModelList;
    private LayoutInflater inflater = null;
    //    private TopNShareModel topNShareModel;
    private ViewHolder viewHolder;

    public StudentDetailsAdapter(Context context, List<StudentDetailModel> studentDetailModels) {
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
                viewHolder = new StudentDetailsAdapter.ViewHolder();
                convertView = inflater.inflate(R.layout.list_item_student_details, null);
                viewHolder.roll_no = (TextView) convertView.findViewById(R.id.roll_no);
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);
                viewHolder.dob = (TextView) convertView.findViewById(R.id.dob);
                viewHolder.address = (TextView) convertView.findViewById(R.id.address);
                viewHolder.mobile_no = (TextView) convertView.findViewById(R.id.mobile_no);
                viewHolder.standard = (TextView) convertView.findViewById(R.id.standard);
                viewHolder.division = (TextView) convertView.findViewById(R.id.division);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (StudentDetailsAdapter.ViewHolder) convertView.getTag();
            }
            final StudentDetailModel studentDetailModel = studentDetailModelList.get(position);
            viewHolder.roll_no.setText(studentDetailModel.getRollNo());
            viewHolder.name.setText(studentDetailModel.getName());
            viewHolder.dob.setText(studentDetailModel.getDob());
            viewHolder.address.setText(studentDetailModel.getAddress());
            viewHolder.mobile_no.setText(studentDetailModel.getParent_MobNo());
            viewHolder.standard.setText(studentDetailModel.getStandard());
            viewHolder.division.setText(studentDetailModel.getDivision());



        return convertView;
    }

    public class ViewHolder {
        private TextView roll_no, name, dob, address, mobile_no, standard, division;
    }
}
