package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class DummyPage extends AppCompatActivity {
    ArrayList<BeneficiaryClass> beneficiaryClasses1 = new ArrayList<>();
    ArrayList<BeneficiaryClass> beneficiaryClasses2 = new ArrayList<>();
    BeneficiaryListAdapter adapter1;
    BeneficiaryListAdapter adapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        ImageView imageViewAddNew = findViewById(R.id.image_addNew);
        final RecyclerView recyclerViewFirst = findViewById(R.id.recyclerViewFirst);
        final RecyclerView recyclerViewSecond = findViewById(R.id.recyclerViewSecond);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getApplicationContext(),3);
        gridLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getApplicationContext(),4);
        gridLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFirst.setLayoutManager(gridLayoutManager1);
        recyclerViewSecond.setLayoutManager(gridLayoutManager2);
        beneficiaryClasses1.add(new BeneficiaryClass("Abhishek"));
        beneficiaryClasses1.add(new BeneficiaryClass("Aman"));
        beneficiaryClasses1.add(new BeneficiaryClass("Anurag"));
        beneficiaryClasses2.add(new BeneficiaryClass("Suman"));
        beneficiaryClasses2.add(new BeneficiaryClass("Kuddus"));
        adapter1 = new BeneficiaryListAdapter(beneficiaryClasses1);
        adapter2 = new BeneficiaryListAdapter(beneficiaryClasses2);
        recyclerViewFirst.setAdapter(adapter1);
        recyclerViewSecond.setAdapter(adapter2);

        imageViewAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankDetailsDialog bankDetailsDialog = new BankDetailsDialog(DummyPage.this);
                bankDetailsDialog.show();


//                beneficiaryClasses1.add(new BeneficiaryClass("hii"));
//                adapter = new BeneficiaryListAdapter(beneficiaryClasses1);
//                Log.e("counterr","count is"+adapter.getItemCount());
//                if(adapter.getItemCount()<=3)
//                recyclerViewFirst.setAdapter(adapter);
//                if (adapter.getItemCount()== 3)
//                    beneficiaryClasses1.remove(1);
//                if (adapter.getItemCount()>3)
//                    recyclerViewSecond.setAdapter(adapter);
            }
        });



    }
}
