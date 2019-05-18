package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment implements PagerInterface {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText loginUsername = getView().findViewById(R.id.loginUsername);
        final EditText loginPassword = getView().findViewById(R.id.loginPassword);
        Button btnlogin =getView().findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((loginPassword.getText().toString().isEmpty())||
                        (loginUsername.getText().toString().isEmpty())){
                    Toast.makeText(getContext(),"Enter the credentials first",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                    String databaseUsername = sharedPreferences.getString(
                            "keyname" + loginUsername.getText().toString(), "");
                    String databaseUserpassword = sharedPreferences.getString(
                            "keypassword" + loginUsername.getText().toString(), "");
                    Log.e("tagggg", "++" + databaseUserpassword);

                    if (!(loginPassword.getText().toString().isEmpty()) && (databaseUserpassword).equals(loginPassword.getText().toString())) {
                        Toast.makeText(getContext(),
                                "Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), DummyPage.class));
                    } else {
                        Toast.makeText(getContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                    if (databaseUsername.isEmpty())
                        Toast.makeText(getContext(),
                                "User not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void fragmentBecameVisible() {

    }
}
