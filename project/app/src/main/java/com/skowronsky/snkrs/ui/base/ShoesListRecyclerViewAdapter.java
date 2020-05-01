package com.skowronsky.snkrs.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.ui.home.shoes.ShoesRecyclerViewAdapter;
import com.skowronsky.snkrs.ui.home.shoes.ShoesViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoesListRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Acitivity context;
    ShoesListViewModel shoesViewModel;
    List<com.skowronsky.snkrs.database.Shoes>  shoesList = new ArrayList<com.skowronsky.snkrs.database.Shoes>();

    public ShoesListRecyclerViewAdapter(Acitivity context, ShoesListViewModel shoesViewModel){
        this.context = context;
        this.shoesViewModel = shoesViewModel;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.item_shoes,parent,false);
        return new ShoesListRecyclerViewAdapter.RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        com.skowronsky.snkrs.database.Shoes shoes = shoesList.get(position);
        final ShoesListRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (ShoesListRecyclerViewAdapter.RecyclerViewViewHolder) holder;
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

    public void setAllShoes(List<com.skowronsky.snkrs.database.Shoes> shoes,String brand){
        for(int i=0;i<shoes.size();i++){
        if (shoes.get(i).brand_name.equals(brand)){
           shoesList.add(shoes.get(i));
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
