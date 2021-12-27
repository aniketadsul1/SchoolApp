package com.android.sudesi.schoolapp.Activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.RequiresApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.sudesi.schoolapp.Home_Activity;
import com.android.sudesi.schoolapp.R;
import com.android.sudesi.schoolapp.SchoolApp;
import com.android.sudesi.schoolapp.SmileyToast.TastyToast;
import com.android.sudesi.schoolapp.dbconfig.DataBaseCon;
import com.android.sudesi.schoolapp.dbconfig.DatabaseCopy;
import com.android.sudesi.schoolapp.dbconfig.DbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private CardView cv;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();

        DatabaseCopy databaseCopy = new DatabaseCopy();
        AssetManager assetManager = MainActivity.this.getAssets();
        databaseCopy.copy(assetManager, MainActivity.this);
        SchoolApp.dbCon = DataBaseCon.getInstance(getApplicationContext());
        exportDB();
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btGo = findViewById(R.id.bt_go);
        cv = findViewById(R.id.cv);
        fab = findViewById(R.id.fab);
    }

    private void setListener() {
        btGo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                Explode explode = new Explode();
                explode.setDuration(500);

                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                 Intent i=new Intent(MainActivity.this,Home_Activity.class);
               // Intent i=new Intent(MainActivity.this, MyCalendarActivity.class);
                startActivity(i);
//                Toast.makeText(MainActivity.this, "Login Sucessfully.....", Toast.LENGTH_SHORT).show();
                TastyToast.makeText(getApplicationContext(), "Login Sucessfully.....", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

            /*    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
                Intent i2 = new Intent(MainActivity.this,LoginSuccessActivity.class);
                startActivity(i2, oc2.toBundle());*/
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, fab, fab.getTransitionName());
                startActivity(new Intent(MainActivity.this, RegisterActivity.class), options.toBundle());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fab.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }


    private void exportDB() {
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source = null;
        FileChannel destination = null;
        String currentDBPath = "data/com.android.sudesi.schoolapp/databases/SchoolApp.sqlite";
        String backupDBPath = DbHelper.DATABASE_NAME;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
