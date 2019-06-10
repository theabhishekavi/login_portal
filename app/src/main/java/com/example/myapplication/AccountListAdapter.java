package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountListAdapter extends RecyclerView.Adapter
        <AccountListAdapter.AccountListHolder> {

    private ArrayList<AccountListPojo> accountListPojos;

    public AccountListAdapter(ArrayList<AccountListPojo> accountListPojos) {
        this.accountListPojos = accountListPojos;
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
        final AccountListPojo currentAccount = accountListPojos.get(position);
        accountListHolder.name.setText(currentAccount.getName());
    }

    @Override
    public int getItemCount() {
        return accountListPojos.size();
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
