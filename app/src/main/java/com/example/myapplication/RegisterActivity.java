package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                        Toast.makeText(RegisterActivity.this, etName.getText().toString()
                                + " is successfully registered", Toast.LENGTH_SHORT).show();

                        if (databaseUsername.isEmpty()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("keyname" + etUsername.getText().toString(),
                                    etUsername.getText().toString());
                            editor.putString("keypassword" + etUsername.getText().toString(),
                                    etPassword.getText().toString());
                            editor.putString("mPinPassword",etmPin.getText().toString());
                            editor.apply();
                        }
                    }
                }

            }
        });
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.activity_register,container,false);
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        final EditText etName = getView().findViewById(R.id.etName);
//        final EditText etUsername = getView().findViewById(R.id.etUsername);
//        final EditText etPassword = getView().findViewById(R.id.etPassword);
//        final EditText etRePassword = getView().findViewById(R.id.etRePassword);
//        Button btnOk = getView().findViewById(R.id.btnOk);
//        Button btnMpin = getView().findViewById(R.id.btnMpin);
//
//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getActivity().
//                        getSharedPreferences("myprefs", Context.MODE_PRIVATE);
//                String databaseUsername = sharedPreferences.getString(
//                        "keyname"+etUsername.getText().toString(),"");
//
//                if (!(etPassword.getText().toString()).equals(etRePassword.getText().toString())) {
//                    Toast.makeText(getContext(), "Password not matched",
//                            Toast.LENGTH_SHORT).show();
//                }
//                else if((etUsername.getText().toString()).equals(databaseUsername)) {
//                    Toast.makeText(getContext(), "Username already exist",
//                            Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getContext(), etName.getText().toString()
//                            +" is successfully registered", Toast.LENGTH_SHORT).show();
//
//                    if(databaseUsername.isEmpty()) {
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("keyname"+etUsername.getText().toString(),
//                                etUsername.getText().toString());
//                        editor.putString("keypassword"+etUsername.getText().toString(),
//                                etPassword.getText().toString());
//                        editor.apply();
//                    }
//                }
//
//
//            }
//        });
//        btnMpin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              mPinFragment fragment = new mPinFragment();
//              FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//              FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//              fragmentTransaction.replace(R.id.viewPager,fragment,"tag");
//              fragmentTransaction.addToBackStack(null);
//              fragmentTransaction.commit();
//            }
//        });
//        super.onViewCreated(view, savedInstanceState);
//    }
 }
