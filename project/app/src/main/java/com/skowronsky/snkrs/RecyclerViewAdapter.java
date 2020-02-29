package com.skowronsky.snkrs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    ArrayList<Shoes> shoesArrayList;

    public RecyclerViewAdapter(Acitivity context, ArrayList<Shoes> shoesArrayList){
        this.context = context;
        this.shoesArrayList = shoesArrayList;
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
        Shoes shoes = shoesArrayList.get(position);
        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.shoe_company.setText(shoes.getCompany_name());
        viewHolder.shoe_size.setText(shoes.getSize());
        viewHolder.shoe_model.setText(shoes.getModel());

    }

    @Override
    public int getItemCount() {
        return shoesArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView shoe_photo;
        TextView shoe_model;
        TextView shoe_company;
        TextView shoe_size;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_photo = itemView.findViewById(R.id.shoe_photo);
            shoe_model = itemView.findViewById(R.id.shoe_model);
            shoe_company = itemView.findViewById(R.id.shoe_company);
            shoe_size = itemView.findViewById(R.id.shoe_size);
        }
    }
}
