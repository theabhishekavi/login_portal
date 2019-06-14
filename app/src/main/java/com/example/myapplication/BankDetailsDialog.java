package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BankDetailsDialog extends Dialog implements View.OnClickListener {

    private Activity c;
    private Button create, cancel;
    EditText AccNo,IfscCode,HolderName,BankName,BranchName;
    String dbUsername;
    JSONObject beneficary = new JSONObject();
    JSONObject beneficaryObject = new JSONObject();

    public BankDetailsDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bank_details_dialog);
        create = findViewById(R.id.dialogCreate);
        cancel = findViewById(R.id.dialogCancel);
        AccNo = findViewById(R.id.dialogAccNo);
        IfscCode = findViewById(R.id.dialogIfsc);
        HolderName = findViewById(R.id.dialogHolderName);
        BankName = findViewById(R.id.dialogBankName);
        BranchName = findViewById(R.id.dialogBranchName);
        SharedPreferences sharedPreferences = c.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        dbUsername = sharedPreferences.getString("username", "");

        create.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogCreate:
                try {
                    beneficaryObject.put("Username",dbUsername);
                    beneficaryObject.put("AccountNo",AccNo.getText().toString());
                    beneficaryObject.put("IFSCCode",IfscCode.getText().toString());
                    beneficaryObject.put("AccHolderName",HolderName.getText().toString());
                    beneficaryObject.put("BankName",BankName.getText().toString());
                    beneficaryObject.put("BranchName",BranchName.getText().toString());
                    beneficary.put("BeneficaryDetails",beneficaryObject);
                    postToServer();
                    Toast.makeText(c.getBaseContext(),"Benficary added successfully",Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.dialogCancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
    private void postToServer(){
        final MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, beneficary.toString());
        Request request = new Request.Builder()
                .url("http://139.59.75.118/addbeneficiary")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }
}
