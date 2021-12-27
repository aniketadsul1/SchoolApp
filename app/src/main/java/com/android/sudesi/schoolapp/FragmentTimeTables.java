/*
 * Copyright (c) 2016 Lung Razvan
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.android.sudesi.schoolapp;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.sudesi.schoolapp.Activity.MainActivity;
import com.android.sudesi.schoolapp.Activity.TimetableActivity;
import com.android.sudesi.schoolapp.SmileyToast.TastyToast;
import com.android.sudesi.schoolapp.SmilyRating.BaseRating;
import com.android.sudesi.schoolapp.SmilyRating.SmileRating;
import com.android.sudesi.schoolapp.SweetAlert.SweetAlertDialog;

import static android.content.ContentValues.TAG;

public class FragmentTimeTables extends Fragment {

    ImageView logoutBtn;
    Button btn_timetable, btn_feedback;
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_e, container, false);
        init(rootview);

        return rootview;
    }

    private void init(View rootview) {

        btn_timetable = (Button) rootview.findViewById(R.id.btn_timetable);
        btn_feedback = (Button) rootview.findViewById(R.id.btn_feedback);
        logoutBtn = (ImageView) rootview.findViewById(R.id.logoutBtn);

        btn_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, TimetableActivity.class);
                startActivity(i);

            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.feedback_dialog);
                SmileRating ratingView;

                Button dialogfeedbackResponce = (Button) dialog.findViewById(R.id.btn_feedback_responce);
                Button dialogCancel = (Button) dialog.findViewById(R.id.btn_feedback_cancel);
                ratingView = (SmileRating) dialog.findViewById(R.id.ratingView);
                ratingView.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                    @Override
                    public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                        // reselected is false when user selects different smiley that previously selected one
                        // true when the same smiley is selected.
                        // Except if it first time, then the value will be false.
                        switch (smiley) {
                            case SmileRating.BAD:
                                Log.i(TAG, "Bad");
                                break;
                            case SmileRating.GOOD:
                                Log.i(TAG, "Good");
                                break;
                            case SmileRating.GREAT:
                                Log.i(TAG, "Great");
                                break;
                            case SmileRating.OKAY:
                                Log.i(TAG, "Okay");
                                break;
                            case SmileRating.TERRIBLE:
                                Log.i(TAG, "Terrible");
                                break;
                        }
                    }
                });
                dialogfeedbackResponce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TastyToast.makeText(mContext, "Sent Successfully", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                    }
                });
                dialogCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

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

}
