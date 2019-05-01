package com.example.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = findViewById(R.id.etName);
        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etPassword = findViewById(R.id.etPassword);
        final EditText etRePassword = findViewById(R.id.etRePassword);
        Button btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("myprefs", MODE_PRIVATE);
                String databaseUsername = sharedPreferences.getString(
                        "keyname"+etUsername.getText().toString(),"");

                if (!(etPassword.getText().toString()).equals(etRePassword.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Password not matched",
                            Toast.LENGTH_SHORT).show();
                }
                else if((etUsername.getText().toString()).equals(databaseUsername)) {
                    Toast.makeText(MainActivity.this, "Username already exist",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, etName.getText().toString()
                            +" is successfully registered", Toast.LENGTH_SHORT).show();

                    if(databaseUsername.isEmpty()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("keyname"+etUsername.getText().toString(),
                                etUsername.getText().toString());
                        editor.putString("keypassword"+etUsername.getText().toString(),
                                etPassword.getText().toString());
                        editor.apply();
                    }
                }


            }
        });
    }
}
