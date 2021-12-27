package com.android.sudesi.schoolapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.sudesi.schoolapp.Activity.MainActivity;
import com.android.sudesi.schoolapp.Adapter.StudentDetailsAdapter;
import com.android.sudesi.schoolapp.SmileyToast.TastyToast;
import com.android.sudesi.schoolapp.SweetAlert.SweetAlertDialog;
import com.android.sudesi.schoolapp.dbconfig.DbHelper;
import com.android.sudesi.schoolapp.model.StudentDetailModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FragmentNotifcation extends Fragment {

    Spinner spin_standard, spin_division;
    ImageView ivImage, logoutBtn;
    private Context mContext;
    private List<String> StudentDetailsArrayList;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private ListView list_student;
    private StudentDetailModel studentDetailModel;
    private List<StudentDetailModel> studentDetailModelList;
    Button btn_save;
    StudentDetailsAdapter studentDetailsAdapter;
    LinearLayout header;
    private InternetBroadcast internetBroadcast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);
        init(rootView);
        internetBroadcast = new InternetBroadcast();
        registerNetworkBroadcastForNougat();
        return rootView;

    }

    private void init(View rootview) {
        StudentDetailsArrayList = new ArrayList<>();
        studentDetailModelList = new ArrayList<StudentDetailModel>();
        spin_standard = (Spinner) rootview.findViewById(R.id.spin_standard);
        spin_division = (Spinner) rootview.findViewById(R.id.spin_division);
        ivImage = (ImageView) rootview.findViewById(R.id.ivImage);
        logoutBtn = (ImageView) rootview.findViewById(R.id.logoutBtn);

        list_student = (ListView) rootview.findViewById(R.id.list_student);
        btn_save = (Button) rootview.findViewById(R.id.btn_save);
        header = (LinearLayout) rootview.findViewById(R.id.header);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String std = spin_standard.getSelectedItem().toString();
                String div = spin_division.getSelectedItem().toString();


                String where = " where Standard = '" + std + "' and Division = '" + div + "'";
                Cursor cursor = SchoolApp.dbCon.fetchFromSelect(DbHelper.TABLE_DB_SCHOOL, where);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
                        studentDetailModel = createrstudentmodel(cursor);
                        studentDetailModelList.add(studentDetailModel);

                    } while (cursor.moveToNext());
                    cursor.close();

                } else {
                    TastyToast.makeText(getContext(), "No data found..!", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                }
                studentDetailsAdapter = new StudentDetailsAdapter(mContext, studentDetailModelList);
                header.setVisibility(View.VISIBLE);
                list_student.setAdapter(studentDetailsAdapter);
                setListViewHeightBasedOnItems(list_student);
                studentDetailsAdapter.notifyDataSetChanged();

            }
        });

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
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

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getContext());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ivImage.setImageBitmap(bm);
    }


    public StudentDetailModel createrstudentmodel(Cursor cursor) {

        studentDetailModel = new StudentDetailModel();
        try {
            studentDetailModel.setRollNo(cursor.getString(cursor.getColumnIndex("Roll_No")));
            studentDetailModel.setName(cursor.getString(cursor.getColumnIndex("Name")));
            studentDetailModel.setDob(cursor.getString(cursor.getColumnIndex("DOB")));
            studentDetailModel.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
            studentDetailModel.setParent_MobNo(cursor.getString(cursor.getColumnIndex("Parent_Mobile_Number")));
            studentDetailModel.setStandard(cursor.getString(cursor.getColumnIndex("Standard")));
            studentDetailModel.setDivision(cursor.getString(cursor.getColumnIndex("Division")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentDetailModel;

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

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mContext.registerReceiver(internetBroadcast, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mContext.registerReceiver(internetBroadcast, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            mContext.unregisterReceiver(internetBroadcast);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
