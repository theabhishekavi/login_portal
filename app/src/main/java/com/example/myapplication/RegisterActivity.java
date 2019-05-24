package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {



     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etName = findViewById(R.id.etName);
        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etPassword = findViewById(R.id.etPassword);
        final EditText etRePassword = findViewById(R.id.etRePassword);
        final EditText etmPin = findViewById(R.id.etmPin);
        Button btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().isEmpty() ||
                        etUsername.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty() ||
                        etRePassword.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter the details",
                            Toast.LENGTH_SHORT).show();
                }
                else if (etmPin.getText().toString().length()!= 6){
                    Toast.makeText(RegisterActivity.this,"mPin must be of 6 digits",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences sharedPreferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                    String databaseUsername = sharedPreferences.getString(
                            "keyname" + etUsername.getText().toString(), "");

                    if (!(etPassword.getText().toString()).equals(etRePassword.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "Password not matched",
                                Toast.LENGTH_SHORT).show();
                    } else if ((etUsername.getText().toString()).equals(databaseUsername)) {
                        Toast.makeText(RegisterActivity.this, "Username already exist",
                                Toast.LENGTH_SHORT).show();
                    } else {
//                        username = etUsername.getText().toString();
//                        name = etName.getText().toString();
//                        try {
//                            JSONObject registerObject = new JSONObject();
//                            registerObject.put("username", username);
//                            registerObject.put("name", name);
//
//                            final MediaType mediaType = MediaType.parse("application/json");
//
//                            OkHttpClient client = new OkHttpClient();
//
//                            RequestBody body = RequestBody.create(mediaType, registerObject.toString());
//                            Request request =
//                                    new Request.Builder()
//                                            .url("http://139.59.75.118/torit")
//                                            .post(body)
//                                            .build();
//                            client.newCall(request).enqueue(new Callback() {
//                                @Override
//                                public void onFailure(Call call, IOException e) {
//
//                                    Log.e("failureeee","it ran");
//
//                                }
//
//                                @Override
//                                public void onResponse(Call call, Response response) throws IOException {
////
//                                }
//                            });
//
//
//
//                        }
//                        catch (JSONException e){
//                            e.printStackTrace();
//                        }

                        Toast.makeText(RegisterActivity.this, etName.getText().toString()
                                + " is successfully registered", Toast.LENGTH_SHORT).show();

                        if (databaseUsername.isEmpty()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("keyname" + etUsername.getText().toString(),
                                    etUsername.getText().toString());
                            editor.putString("keypassword" + etUsername.getText().toString(),
                                    etPassword.getText().toString());
                            editor.putString("mPinPassword",etmPin.getText().toString());
                            editor.putString("username",etUsername.getText().toString());
                            editor.putString("name",etName.getText().toString());
                            editor.apply();
                        }
                    }
                }

            }
        });
    }

 }
