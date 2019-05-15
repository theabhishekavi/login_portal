package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

public class DeviceActivity extends AppCompatActivity {


    TelephonyManager telephonyManager;
    WifiManager wifiManager;
    private String IMEI, IMSI, SimNo, macAddress,wifiId,model,operatorName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        TextView tvImei = findViewById(R.id.tvimei);

        telephonyManager = (TelephonyManager)DeviceActivity.this
                .getSystemService(Context.TELEPHONY_SERVICE);
        operatorName = telephonyManager.getNetworkOperatorName();

        int permission = ContextCompat.checkSelfPermission(DeviceActivity.this,
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

        }

        model = Build.MODEL;


        wifiManager = (WifiManager) DeviceActivity.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        macAddress = wifiInfo.getMacAddress();
        wifiId = "";
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
            wifiId = wifiInfo.getSSID();
        }


        tvImei.setText(" IMEI number is "+IMEI +
                " \n SimNo is "+SimNo +
                "\n IMSI is"+IMSI +
                "\n Operator name is "+operatorName+
                "\n Mac Address is "+macAddress+
                "\n Model number is " +model);

    }
}
