package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class mPinFragment extends Fragment implements SensorEventListener {
    int counter = 0,timecounter =0;
    long startTime = SystemClock.elapsedRealtime();
    TextView timelapse, cordinates, area, tvorientation;



    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float orientationX, orientationY, orientationZ;

    Button btnpin1,btnpin2,btnpin3,btnpin4,btnpin5,btnpin6;

    String temp1,temp2,temp3,temp4,temp5,temp6;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mpin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Button btn1 = getView().findViewById(R.id.btn1);
        final Button btn2 = getView().findViewById(R.id.btn2);
        final Button btn3 = getView().findViewById(R.id.btn3);
        final Button btn4 = getView().findViewById(R.id.btn4);
        final Button btn5 = getView().findViewById(R.id.btn5);
        final Button btn6 = getView().findViewById(R.id.btn6);
        final Button btn7 = getView().findViewById(R.id.btn7);
        final Button btn8 = getView().findViewById(R.id.btn8);
        final Button btn9 = getView().findViewById(R.id.btn9);
        final Button btn0 = getView().findViewById(R.id.btn0);
        final Button btnclr = getView().findViewById(R.id.btnclr);
        final Button btnenter = getView().findViewById(R.id.btnenter);

         btnpin1 = getView().findViewById(R.id.btnpin1);
         btnpin2 = getView().findViewById(R.id.btnpin2);
         btnpin3 = getView().findViewById(R.id.btnpin3);
         btnpin4 = getView().findViewById(R.id.btnpin4);
         btnpin5 = getView().findViewById(R.id.btnpin5);
         btnpin6 = getView().findViewById(R.id.btnpin6);

        final TextView tvNewUser = getView().findViewById(R.id.tvNewUser);

        area = getView().findViewById(R.id.area);
        timelapse = getView().findViewById(R.id.timelapse);
        cordinates = getView().findViewById(R.id.cordinates);
        tvorientation = getView().findViewById(R.id.orientation);

        tvNewUser.setPaintFlags(tvNewUser.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });


        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                   new String[]{Manifest.permission.READ_PHONE_STATE,
                           Manifest.permission.ACCESS_FINE_LOCATION},
                   123);

        }


        try {
            mSensorManager = (SensorManager) getContext()
                    .getSystemService(Activity.SENSOR_SERVICE);
            mAccelerometer = mSensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager
                    .registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Hardware compatibility issue", Toast.LENGTH_LONG).show();
        }

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("counterr", "counter is "+counter);
//                text = "1";
//                setValue("1");
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("counterr", "counter is "+counter);
//                text = "2";
//                setValue("2");
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("counterr", "counter is "+counter);
//                text = "3";
//                setValue("3");
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text ="4";
//                setValue("4");
//            }
//        });
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text ="5";
//                setValue("5");
//            }
//        });
//        btn6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text = "6";
//                setValue("6");
//            }
//        });
//        btn7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text = "7";
//                setValue("7");
//            }
//        });
//        btn8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text = "8";
//                setValue("8");
//            }
//        });
//        btn9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text = "9";
//                setValue("9");
//            }
//        });
//        btn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text = "0";
//                setValue("0");
//            }
//        });
//        btnclr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnenter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });





        btn1.setOnTouchListener(handleTouch);
        btn2.setOnTouchListener(handleTouch);
        btn3.setOnTouchListener(handleTouch);
        btn4.setOnTouchListener(handleTouch);
        btn5.setOnTouchListener(handleTouch);
        btn6.setOnTouchListener(handleTouch);
        btn7.setOnTouchListener(handleTouch);
        btn8.setOnTouchListener(handleTouch);
        btn9.setOnTouchListener(handleTouch);
        btn0.setOnTouchListener(handleTouch);
        btnclr.setOnTouchListener(handleTouch);
        btnenter.setOnTouchListener(handleTouch);



    }

    public void setValue(String text){
        if (counter == 0) {
            temp1 = text;
            btnpin1.setText("*");
        }
        if (counter == 1){
            temp2 = text;
            btnpin2.setText("*");
        }
        if (counter == 2){
            temp3 = text;
            btnpin3.setText("*");
        }
        if (counter == 3){
            temp4 =text;
            btnpin4.setText("*");
        }
        if (counter == 4) {
            temp5 = text;
            btnpin5.setText("*");
        }
        if (counter == 5){
            temp6 = text;
            btnpin6.setText("*");
        }
    }

    public void takeValue(View v){

        if(v.getId() == R.id.btn0)
            setValue("0");
        if(v.getId() == R.id.btn1)
            setValue("1");
        if(v.getId() == R.id.btn2)
            setValue("2");
        if(v.getId() == R.id.btn3)
            setValue("3");
        if(v.getId() == R.id.btn4)
            setValue("4");
        if(v.getId() == R.id.btn5)
            setValue("5");
        if(v.getId() == R.id.btn6)
            setValue("6");
        if(v.getId() == R.id.btn7)
            setValue("7");
        if(v.getId() == R.id.btn8)
            setValue("8");
        if(v.getId() == R.id.btn9)
            setValue("9");
        if(v.getId() == R.id.btnclr){
            counter = 0;
            btnpin1.setText("");
            btnpin2.setText("");
            btnpin3.setText("");
            btnpin4.setText("");
            btnpin5.setText("");
            btnpin6.setText("");
        }
        if (v.getId() == R.id.btnenter) {
            counter = 0;
            String password = temp1+temp2+temp3+ temp4+temp5+temp6;
            Log.e("passwordd", "pass is "+password);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myprefs", Context.MODE_PRIVATE);
            String databasemPinPassword = sharedPreferences.getString(
                    "mPinPassword", "");
            if (!(password.isEmpty()) && databasemPinPassword.equals(password)){
                startActivity(new Intent(getContext(),DummyPage.class));
            }
            else
                Toast.makeText(getContext(),"Wrong mPin! Please try again",
                        Toast.LENGTH_SHORT).show();

        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int)event.getRawX();
                int y = (int)event.getRawY();
                Log.e("counterr", "counter is "+counter);

                takeValue(v);

                tvorientation.setText("Orientation x : "+orientationX+" y : "+orientationY
                +" z : "+orientationZ);
                area.setText("size is "+event.getSize(0));
                cordinates.setText("Cordinates are x:"+x+" and y:"+y);
                long difference = (SystemClock.elapsedRealtime()-startTime);
                if(timecounter == 0) {
                    timelapse.setText("Time counter started");
                    timecounter++;
                }
                else
                    timelapse.setText("Time elapsed "+difference+" milliseconds");

                startTime = SystemClock.elapsedRealtime();



                if(v.getId()!= R.id.btnclr && v.getId()!= R.id.btnenter)
                counter++;

                if(v.getId() == R.id.btnclr){
                    btnpin1.setText("");
                    btnpin2.setText("");
                    btnpin3.setText("");
                    btnpin4.setText("");
                    btnpin5.setText("");
                    btnpin6.setText("");
                }

            }
            return true;
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {
        orientationX = event.values[0];
        orientationY = event.values[1];
        orientationZ = event.values[2];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
