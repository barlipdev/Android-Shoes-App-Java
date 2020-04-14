package com.skowronsky.snkrs.ui.home.shoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;
import com.skowronsky.snkrs.model.Shoes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ShoesRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    ArrayList<com.skowronsky.snkrs.model.Shoes> ShoesArrayList;
    ShoesViewModel shoesViewModel;

    public ShoesRecyclerViewAdapter(Acitivity context, ArrayList<com.skowronsky.snkrs.model.Shoes> ShoesArrayList, ShoesViewModel shoesViewModel){
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Shoes shoes = ShoesArrayList.get(position);
        final RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.shoe_company.setText(shoes.getBrandName());
        viewHolder.shoe_model.setText(shoes.getModelName());
        if (shoes.getImage()!=null){
            Picasso.with((Context) context).load(shoes.getImage()).into(
                    viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoesViewModel.eventSendShoe(ShoesArrayList.get(position));
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
        ImageView imageView;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_comp);
            shoe_model = itemView.findViewById(R.id.shoe_model);
            imageView = itemView.findViewById(R.id.shoe_icon);
        }
    }
}
