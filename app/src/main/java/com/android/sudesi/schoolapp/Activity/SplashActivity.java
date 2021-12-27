package com.android.sudesi.schoolapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;

import com.android.sudesi.schoolapp.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 6000;
    Context mContext;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        StrictMode.VmPolicy.Builder builder1 = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder1.build());
        mContext = this;

        adjustFontScale();

        new Handler().postDelayed(new Runnable() {


//             * Showing splash screen with a timer. This will be useful when you
//			 * want to show case your app logo / company


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_lotus_main, menu);
        return true;
    }

    public void adjustFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = (float) 1; //0.85 small size, 1 normal size, 1,15 big etc

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

}

