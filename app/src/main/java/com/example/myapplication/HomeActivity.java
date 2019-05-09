package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements SensorEventListener {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private SensorManager mSensorManager;
    private Sensor mRotationSensor;

    private static final int SENSOR_DELAY = 500 * 1000; // 500ms
    private static final int FROM_RADS_TO_DEGS = -57;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new mPinFragment(),"Easy PIN");
        adapter.addFragment(new LoginFragment(), "Password");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        try {
            mSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
            mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            mSensorManager.registerListener(this, mRotationSensor, SENSOR_DELAY);
        } catch (Exception e) {
            Toast.makeText(this, "Hardware compatibility issue", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mRotationSensor) {
            if (event.values.length > 4) {
                float[] truncatedRotationVector = new float[4];
                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4);
                update(truncatedRotationVector);
            } else {
                update(event.values);
            }
        }
    }
    private void update(float[] vectors) {
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
        int worldAxisX = SensorManager.AXIS_X;
        int worldAxisZ = SensorManager.AXIS_Z;
        float[] adjustedRotationMatrix = new float[9];
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix);
        float[] orientation = new float[3];
        SensorManager.getOrientation(adjustedRotationMatrix, orientation);
        float pitch = orientation[1] * FROM_RADS_TO_DEGS;
        float roll = orientation[2] * FROM_RADS_TO_DEGS;
        Log.e("pitchh","Pitch: "+(int)(pitch*2));
//        if (Math.abs((int)(pitch*2))<=50)
//        Toast.makeText(this,"Upright",Toast.LENGTH_SHORT).show();
//        if (Math.abs((int)(pitch*2))>50 && Math.abs((int)(pitch*2))<150)
//            Toast.makeText(this,"Tilt",Toast.LENGTH_SHORT).show();
//        if (Math.abs((int)(pitch*2))>=150)
//            Toast.makeText(this,"Rest",Toast.LENGTH_SHORT).show();
//        Log.e("pitchhhhh","Roll is "+(int)roll);
//        ((TextView)findViewById(R.id.pitch)).setText("Pitch: "+pitch);
//        ((TextView)findViewById(R.id.roll)).setText("Roll: "+roll);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
