package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final EditText loginUsername = findViewById(R.id.loginUsername);
        final EditText loginPassword = findViewById(R.id.loginPassword);
        Button btnlogin =findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("myprefs", MODE_PRIVATE);
                String databaseUsername = sharedPreferences.getString(
                        "keyname"+loginUsername.getText().toString(),"");
                String databaseUserpassword = sharedPreferences.getString(
                        "keypassword"+loginUsername.getText().toString(),"");
                if(databaseUserpassword.equals(loginPassword))
                    Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                if (databaseUsername.isEmpty())
                    Toast.makeText(LoginActivity.this,"User not registered",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
