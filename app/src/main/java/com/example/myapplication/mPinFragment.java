package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
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

import java.lang.reflect.Method;

public class mPinFragment extends Fragment implements SensorEventListener {
    int counter = 0;
    long startTime = SystemClock.elapsedRealtime();
    TextView timelapse, cordinates, area, tvorientation, deviceDetail,tvImei;


    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float orientationX, orientationY, orientationZ;

    TelephonyManager telephonyManager;
    private String IMEI, IMSI, SimNo;

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
        final TextView tvNewUser = getView().findViewById(R.id.tvNewUser);

        area = getView().findViewById(R.id.area);
        timelapse = getView().findViewById(R.id.timelapse);
        cordinates = getView().findViewById(R.id.cordinates);
        tvorientation = getView().findViewById(R.id.orientation);
        deviceDetail = getView().findViewById(R.id.deviceDetail);
        tvImei = getView().findViewById(R.id.tvimei);

        tvNewUser.setPaintFlags(tvNewUser.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });

//        String imsi = android.os.SystemProperties.get(android.telephony.TelephonyPropertie.PROPERTY_IMSI);


        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String macAddress = wifiInfo.getMacAddress();
        String wifiid = "";
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
            wifiid = wifiInfo.getSSID();
        }
        Log.e("addresssss", "mac is " + macAddress + " wifi id is " + wifiid);

        String model = Build.MODEL;
        Log.e("modelll", "model name is " + model);


        telephonyManager = (TelephonyManager) getContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        String operatorName = telephonyManager.getNetworkOperatorName();

        Log.e("operator ", "operator name is " + operatorName);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           ActivityCompat.requestPermissions(getActivity(),
                   new String[]{Manifest.permission.READ_PHONE_STATE},
                   123);

        }
        int permission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_PHONE_STATE);
        if(permission == PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                IMEI = telephonyManager.getImei();
                SimNo = telephonyManager.getSimSerialNumber();
                IMSI = telephonyManager.getSubscriberId();
            }
            else{
                IMEI = telephonyManager.getDeviceId();
                SimNo = telephonyManager.getSimSerialNumber();
                IMSI = telephonyManager.getSubscriberId();

            }
            tvImei.setText("IMEI number is "+IMEI + " \n SimNo is "+SimNo +
                    "\n IMSI is"+IMSI);
        }

        deviceDetail.setText(" Mac Address is "+macAddress+"\n Model number is "
        +model+"\n Operator name is "+operatorName);

        //not done sections

//        telephonyManager.getSubscriberId();
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
////            IMEI = telephonyManager.getDeviceId();
//            return;
//        }


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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//    public static String get(Context context, String key) {
//        String ret = "";
//
//        try {
//            ClassLoader cl = context.getClassLoader();
//            @SuppressWarnings("rawtypes")
//            Class SystemProperties = cl.loadClass("android.os.SystemProperties");
//
//            //Parameters Types
//            @SuppressWarnings("rawtypes")
//            Class[] paramTypes= new Class[1];
//            paramTypes[0]= String.class;
//
//            Method get = SystemProperties.getMethod("get", paramTypes);
//
//            //Parameters
//            Object[] params = new Object[1];
//            params[0] = new String(key);
//
//            ret = (String) get.invoke(SystemProperties, params);
//        } catch(Exception e) {
//            ret = "";
//            //TODO : Error handling
//        }
//
//        return ret;
//    }
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
