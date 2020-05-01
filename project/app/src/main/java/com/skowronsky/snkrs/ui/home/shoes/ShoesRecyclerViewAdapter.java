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
import com.skowronsky.snkrs.database.Shoes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ShoesRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    List<Shoes> shoesList;
    ShoesViewModel shoesViewModel;

    public ShoesRecyclerViewAdapter(Acitivity context, ShoesViewModel shoesViewModel){
        this.context = context;
        this.shoesViewModel = shoesViewModel;
        this.shoesList = new ArrayList<>();
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
        Shoes shoes = shoesList.get(position);
        final RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.shoe_company.setText(shoes.brand_name);
        viewHolder.shoe_model.setText(shoes.modelName);
        if (shoes.image!=null){
            Picasso.with((Context) context).load(shoes.image).into(
                    viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoesViewModel.eventSendShoe(shoesList.get(position));
                shoesViewModel.eventNavToInfo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoesList.size();
    }

    public void setShoesList(List<Shoes> shoesList,String brand){
        for(int i=0;i<shoesList.size();i++){
            if (shoesList.get(i).brand_name.equals(brand)){
                this.shoesList.add(shoesList.get(i));
            }
        }
        notifyDataSetChanged();
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
