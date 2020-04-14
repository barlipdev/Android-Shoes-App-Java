package com.skowronsky.snkrs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.ui.home.HomeViewModel;

import java.util.ArrayList;

public class RecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Acitivity context;
    ArrayList<Company> CompanyArrayList;
    HomeViewModel homeViewModel;

    public RecyclerViewAdapter(Acitivity context, ArrayList<Company> CompanyArrayList,HomeViewModel homeViewModel){
        this.context = context;
        this.CompanyArrayList = CompanyArrayList;
        this.homeViewModel = homeViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Company Company = CompanyArrayList.get(position);
        final RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.shoe_company.setText(Company.getCompany_name());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.eventCompanyName(viewHolder.shoe_company.getText().toString());
                Toast.makeText((Context) context, "Numer: "+ viewHolder.shoe_company.getText(), Toast.LENGTH_SHORT).show();
                homeViewModel.eventNavToShoes();
            }
        });
    }

    @Override
    public int getItemCount() {
        return CompanyArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView shoe_company;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_company);
        }
    }
}
