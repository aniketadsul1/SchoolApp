package com.android.sudesi.schoolapp.SmileyToast;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sudesi.schoolapp.R;

public class TastyToast {
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;


    public static final int SUCCESS = 1;
    public static final int ERROR = 2;

    static SuccessToastView successToastView;
    static ErrorToastView errorToastView;

    public static Toast makeText(Context context, String msg, int length, int type) {

        Toast toast = new Toast(context);


        switch (type) {
            case 1: {
                View layout = LayoutInflater.from(context).inflate(R.layout.success_toast_layout, null, false);
                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);
                successToastView = (SuccessToastView) layout.findViewById(R.id.successView);
                successToastView.startAnim();
                text.setBackgroundResource(R.drawable.success_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }

            case 2: {
                View layout = LayoutInflater.from(context).inflate(R.layout.error_toast_layout, null, false);

                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);
                errorToastView = (ErrorToastView) layout.findViewById(R.id.errorView);
                errorToastView.startAnim();
                text.setBackgroundResource(R.drawable.error_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }

        }
        toast.setDuration(length);
        toast.show();
        return toast;
    }

}
