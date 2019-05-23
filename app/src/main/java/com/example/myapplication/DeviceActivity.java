package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeviceActivity extends AppCompatActivity {


    TelephonyManager telephonyManager;
    WifiManager wifiManager;
    private String IMEI, IMSI, SimNo, macAddress,wifiId,model,operatorName,ipV4;

    JSONObject deviceObject = new JSONObject();

    JSONObject object = new JSONObject();


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

//        macAddress = wifiInfo.getMacAddress();
        wifiId = "";
        wifiId = wifiInfo.getSSID();

        macAddress = getMACAddress("wlan0");
        //eth0, wlan0 or NULL = use first interface
        ipV4 = getIPAddress(true);



        tvImei.setText(" IMEI number is "+IMEI +
                " \n SimNo is "+SimNo +
                "\n IMSI is"+IMSI +
                "\n Operator name is "+operatorName+
                "\n Mac Address is "+macAddress+
                "\n Model number is " +model+
                "\n Wifi Id is " +wifiId+
                "\n IP Address is "+ipV4);

        try {


            object.put("IMEI", "" + IMEI);
            object.put("SIM",""+SimNo);
            object.put("IMSI",""+IMSI);
            object.put("OPERATOR",""+operatorName);
            object.put("MAC",""+macAddress);
            object.put("MODEL",""+model);
            object.put("WIFI",""+wifiId);
            object.put("IPV4",""+ipV4);

        deviceObject.put("device_detail",object);
        Log.e("postttobje","object is"+deviceObject.toString());

          final MediaType mediaType = MediaType.parse("application/json");

            OkHttpClient client = new OkHttpClient();

                RequestBody body = RequestBody.create(mediaType, deviceObject.toString());
                Request request =
                        new Request.Builder()
                        .url("http://139.59.75.118/torit")
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.e("failureeee","it ranon failure");

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("failureeee","it ran success");
                    }
                });


        }
        catch (Exception e)
        {
            Log.e("failureeee","it ran");
            e.printStackTrace();
        }


    }


    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac == null) return "";
                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) buf.append(String.format("%02X:", aMac));
                if (buf.length() > 0) buf.deleteCharAt(buf.length() - 1);
                return buf.toString();
            }
        } catch (Exception ignored) {
        } // for now eat exceptions
        return "";
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) { } // for now eat exceptions
        return "";
    }



}
