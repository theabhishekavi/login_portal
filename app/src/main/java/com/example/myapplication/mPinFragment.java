package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class mPinFragment extends Fragment implements SensorEventListener {
    int counter = 0;
    long startTime = SystemClock.elapsedRealtime();
    TextView timelapse,cordinates,area,tvorientation;


    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    float orientationX,orientationY,orientationZ;
//    private static final int SENSOR_DELAY = 500 * 1000; // 500ms
//    private static final int FROM_RADS_TO_DEGS = -57;
//    private float pitch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mpin,container,false);
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
        final TextView tvNewUser = getView().findViewById(R.id.tvNewUser);

        area = getView().findViewById(R.id.area);
        timelapse = getView().findViewById(R.id.timelapse);
        cordinates = getView().findViewById(R.id.cordinates);
        tvorientation = getView().findViewById(R.id.orientation);
        tvNewUser.setPaintFlags(tvNewUser.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });

        try {
            mSensorManager = (SensorManager) getContext().getSystemService(Activity.SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Hardware compatibility issue", Toast.LENGTH_LONG).show();
        }

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
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
    View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int)event.getRawX();
                int y = (int)event.getRawY();

//                if (Math.abs((int)(pitch*2))<=50)
//                    Toast.makeText(getContext(),"Upright",Toast.LENGTH_SHORT).show();
//                if (Math.abs((int)(pitch*2))>50 && Math.abs((int)(pitch*2))<150)
//                    Toast.makeText(getContext(),"Tilt",Toast.LENGTH_SHORT).show();
//                if (Math.abs((int)(pitch*2))>=150)
//                    Toast.makeText(getContext(),"Rest",Toast.LENGTH_SHORT).show();
//
                tvorientation.setText("Orientation x : "+orientationX+" y : "+orientationY
                +" z : "+orientationZ);
                area.setText("size is "+event.getSize(0));

                cordinates.setText("Cordinates are x:"+x+" and y:"+y);
                long difference = (SystemClock.elapsedRealtime()-startTime);
                if(counter == 0)
                    timelapse.setText("Time counter started");
                else
                    timelapse.setText("Time elapsed "+difference+" milliseconds");
                startTime = SystemClock.elapsedRealtime();
                counter++;
            }
            return true;
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {
        orientationX = event.values[0];
        orientationY = event.values[1];
        orientationZ = event.values[2];

//        Log.e("motionn", "x:"+event.values[0]+" y:"+event.values[1]+"z: "+
//                event.values[2]);
//        if (event.sensor == mRotationSensor) {
//            if (event.values.length > 4) {
//                float[] truncatedRotationVector = new float[4];
//                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4);
//                update(truncatedRotationVector);
//            } else {
//                update(event.values);
//            }
//        }
    }

//    private void update(float[] vectors) {
//        float[] rotationMatrix = new float[9];
//        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
//        int worldAxisX = SensorManager.AXIS_X;
//        int worldAxisZ = SensorManager.AXIS_Z;
//        float[] adjustedRotationMatrix = new float[9];
//        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix);
//        float[] orientation = new float[3];
//        SensorManager.getOrientation(adjustedRotationMatrix, orientation);
//        pitch = orientation[1] * FROM_RADS_TO_DEGS;
//        float roll = orientation[2] * FROM_RADS_TO_DEGS;
//        Log.e("pitchh","Pitch: "+(int)(pitch*2));
//    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
