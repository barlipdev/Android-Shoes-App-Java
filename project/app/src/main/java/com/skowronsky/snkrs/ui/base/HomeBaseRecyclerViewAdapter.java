package com.skowronsky.snkrs.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeBaseRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Acitivity context;
    ArrayList<Shoes> ShoesArrayList;
    List<BaseShoes> baseShoes;
    ArrayList<String> baseInfo;
    HomeBaseViewModel shoesViewModel;
    List<BaseShoes> baseShoesList;

    public HomeBaseRecyclerViewAdapter(Acitivity context , HomeBaseViewModel homeViewModel){
        this.context = context;
        this.shoesViewModel = homeViewModel;
        baseShoesList = new ArrayList<>();
        this.baseInfo = new ArrayList<String>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.base_list,parent,false);
        return new HomeBaseRecyclerViewAdapter.RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        com.skowronsky.snkrs.database.Shoes shoes = baseShoesList.get(position).shoes;
        final HomeBaseRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (HomeBaseRecyclerViewAdapter.RecyclerViewViewHolder) holder;
        viewHolder.shoe_company.setText(shoes.brand_name);
        viewHolder.shoe_model.setText(shoes.modelName);
       // baseShoes = shoesViewModel.getAllBaseShoes().getValue();
        viewHolder.base_size.setText("Base size: "+String.valueOf(baseShoesList.get(position).base.size));
        if (shoes.image!=null){
            Picasso.with((Context) context).load(shoes.image).into(
                    viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseInfo.add(String.valueOf(shoes.brand_name));
                baseInfo.add(String.valueOf(shoes.modelName));
                baseInfo.add(String.valueOf(baseShoesList.get(position).base.size));
                baseInfo.add(String.valueOf(shoes.image));
                baseInfo.add(String.valueOf(baseShoesList.get(position).base.id_base));
                shoesViewModel.eventBaseSet(baseInfo);
                shoesViewModel.eventNavToInfo();
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoesViewModel.deleteBaseShoes(baseShoesList.get(position).shoes,baseShoesList.get(position).base.size);
            }
        });
    }

    @Override
    public int getItemCount() {
        return baseShoesList.size();
    }

    public void setBaseShoes(List<BaseShoes> baseShoesList){
        this.baseShoesList = baseShoesList;
        notifyDataSetChanged();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView shoe_company;
        TextView shoe_model;
        TextView base_size;
        ImageView imageView;
        Button deleteButton;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_comp);
            shoe_model = itemView.findViewById(R.id.shoe_model);
            imageView = itemView.findViewById(R.id.shoe_icon);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            base_size = itemView.findViewById(R.id.size);
        }
    }
}
