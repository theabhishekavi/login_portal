package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BeneficiaryListAdapter extends RecyclerView.Adapter
        <BeneficiaryListAdapter.AccountListHolder> {

    private ArrayList<BeneficiaryClass> arrayListBeneficary;

    public BeneficiaryListAdapter(ArrayList<BeneficiaryClass> arrayListBeneficary) {
        this.arrayListBeneficary = arrayListBeneficary;
    }

    @NonNull
    @Override
    public AccountListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View inflatedView = li.inflate(R.layout.accountlist_adapter,
                viewGroup,false);
        return new AccountListHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountListHolder accountListHolder, int position) {
        final BeneficiaryClass currentAccount = arrayListBeneficary.get(position);
        accountListHolder.name.setText(currentAccount.getName());
    }

    @Override
    public int getItemCount() {
        return arrayListBeneficary.size();
    }

    class AccountListHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        public AccountListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvAccountList);
        }
    }
}
