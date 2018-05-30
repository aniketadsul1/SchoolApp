package com.android.sudesi.schoolapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.sudesi.schoolapp.Activity.AttendanceMarkActivity;
import com.android.sudesi.schoolapp.Activity.MainActivity;
import com.android.sudesi.schoolapp.SweetAlert.SweetAlertDialog;


public class FragmentMarkAttendance extends Fragment {

    ImageView logoutBtn;
    Spinner spin_standard1, spin_division1;
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
        View rootview = inflater.inflate(R.layout.fragment_c, container, false);
        init(rootview);
        return rootview;
    }

    private void init(View rootview) {

        spin_standard1 = (Spinner) rootview.findViewById(R.id.spin_standard1);
        spin_division1 = (Spinner) rootview.findViewById(R.id.spin_division1);
        btn_attendance = (Button) rootview.findViewById(R.id.btn_attendance);
        logoutBtn = (ImageView) rootview.findViewById(R.id.logoutBtn);

        btn_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateDetails()) {
                    String standard = spin_standard1.getSelectedItem().toString();
                    String division = spin_division1.getSelectedItem().toString();
                    Intent i = new Intent(mContext, AttendanceMarkActivity.class);
                    i.putExtra("standard", standard);
                    i.putExtra("division", division);
                    startActivity(i);
                }
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure you want to Logout?")
//                        .setContentText("Won't be able to recover this file!")
                        .setCancelText("No")
                        .setConfirmText("Yes")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                               /* sDialog.setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);*/

                                sDialog.dismiss();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.setTitleText("Log out!")
                                        .setContentText("You are Logged out successfully")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                Intent i = new Intent(mContext, MainActivity.class);
                                                startActivity(i);
                                                sweetAlertDialog.dismiss();
                                            }
                                        })
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            }
        });

    }

    private Boolean validateDetails() {
        boolean isValidate = true;
        try {
            if (spin_standard1.getSelectedItem().toString().equalsIgnoreCase("Select Standard")) {
                Toast.makeText(mContext, "Please Select Standard", Toast.LENGTH_SHORT).show();
                isValidate = false;
                return isValidate;
            }
            if (spin_division1.getSelectedItem().toString().equalsIgnoreCase("Select Division")) {
                Toast.makeText(mContext, "Please Select Standard", Toast.LENGTH_SHORT).show();
                isValidate = false;
                return isValidate;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValidate;
    }

}
