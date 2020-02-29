package com.skowronsky.snkrs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.ui.home.HomeFragment;

import java.util.ArrayList;

public class RecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    ArrayList<Company> CompanyArrayList;

    public RecyclerViewAdapter(Acitivity context, ArrayList<Company> CompanyArrayList){
        this.context = context;
        this.CompanyArrayList = CompanyArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
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
