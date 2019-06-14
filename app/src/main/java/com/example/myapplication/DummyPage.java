package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class DummyPage extends AppCompatActivity {
    ArrayList<AccountListPojo> accountListPojos = new ArrayList<>();
    AccountListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
//        final EditText etCreateName = findViewById(R.id.etCreateName);
//        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        ImageView imageViewAddNew = findViewById(R.id.image_addNew);
        final RecyclerView recyclerViewFirst = findViewById(R.id.recyclerViewFirst);
        final RecyclerView recyclerViewSecond = findViewById(R.id.recyclerViewSecond);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getApplicationContext(),4);
        gridLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFirst.setLayoutManager(gridLayoutManager);
        recyclerViewSecond.setLayoutManager(gridLayoutManager2);

        imageViewAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankDetailsDialog bankDetailsDialog = new BankDetailsDialog(DummyPage.this);
                bankDetailsDialog.show();
//                accountListPojos.add(new AccountListPojo("hii"));
//                adapter = new AccountListAdapter(accountListPojos);
//                Log.e("counterr","count is"+adapter.getItemCount());
//                if(adapter.getItemCount()<=3)
//                recyclerViewFirst.setAdapter(adapter);
//                if (adapter.getItemCount()== 3)
//                    accountListPojos.remove(1);
//                if (adapter.getItemCount()>3)
//                    recyclerViewSecond.setAdapter(adapter);
            }
        });

//        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                accountListPojos.add(new AccountListPojo(etCreateName.getText().toString()));
//                adapter = new AccountListAdapter(accountListPojos);
//                recyclerView.setAdapter(adapter);
//                etCreateName.setText("");
//            }
//        });

    }
}
