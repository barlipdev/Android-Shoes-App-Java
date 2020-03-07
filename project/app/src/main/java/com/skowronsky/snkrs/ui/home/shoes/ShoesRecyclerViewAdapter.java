package com.skowronsky.snkrs.ui.home.shoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;

import java.util.ArrayList;


public class ShoesRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    ArrayList<Shoes> ShoesArrayList;
    ShoesViewModel shoesViewModel;

    public ShoesRecyclerViewAdapter(Acitivity context, ArrayList<Shoes> ShoesArrayList, ShoesViewModel shoesViewModel){
        this.context = context;
        this.ShoesArrayList = ShoesArrayList;
        this.shoesViewModel = shoesViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.item_shoes,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Shoes shoes = ShoesArrayList.get(position);
        final RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.shoe_company.setText(shoes.getShoe_company());
        viewHolder.shoe_model.setText(shoes.getModel());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoesViewModel.eventNavToInfo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ShoesArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView shoe_company;
        TextView shoe_model;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_comp);
            shoe_model = itemView.findViewById(R.id.shoe_model);
        }
    }
}
