package com.android.sudesi.schoolapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;


public class FragmentC extends Fragment {
    Spinner spin_standard1,spin_division1;
    Button btn_attendance;
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_c, container, false);
        spin_standard1=(Spinner)rootview.findViewById(R.id.spin_standard1);
        spin_division1=(Spinner)rootview.findViewById(R.id.spin_division1);
        btn_attendance=(Button)rootview.findViewById(R.id.btn_attendance);
        return rootview;
    }

}
