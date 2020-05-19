package com.skowronsky.snkrs.ui.home;

import android.content.Context;
import android.util.Log;
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
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Acitivity context;
    ArrayList<String> baseInfo;
    HomeViewModel shoesViewModel;
    List<BaseShoes> baseShoesList;
    NavigationStorage navigationStorage;

    public HomeRecyclerViewAdapter(Acitivity context , HomeViewModel homeViewModel){
        this.context = context;
        this.shoesViewModel = homeViewModel;
        baseShoesList = new ArrayList<>();
        this.baseInfo = new ArrayList<String>();
        this.navigationStorage = NavigationStorage.getInstance();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.base_list,parent,false);
        return new HomeRecyclerViewAdapter.RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        com.skowronsky.snkrs.database.Shoes shoes = baseShoesList.get(position).shoes;
        final HomeRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (HomeRecyclerViewAdapter.RecyclerViewViewHolder) holder;
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
                navigationStorage.setBaseShoe(baseShoesList.get(position));
                baseInfo.add(String.valueOf(shoes.brand_name));
                baseInfo.add(String.valueOf(shoes.modelName));
                baseInfo.add(String.valueOf(baseShoesList.get(position).base.size));
                baseInfo.add(String.valueOf(shoes.image));
                baseInfo.add(String.valueOf(baseShoesList.get(position).base.id_base));
                shoesViewModel.eventBaseSet(baseInfo);
                shoesViewModel.eventNavToInfo();
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
    public void delete(int pos){
        shoesViewModel.deleteBaseShoes(baseShoesList.get(pos).shoes,baseShoesList.get(pos).base.size);
        notifyDataSetChanged();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView shoe_company;
        TextView shoe_model;
        TextView base_size;
        ImageView imageView;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_comp);
            shoe_model = itemView.findViewById(R.id.shoe_model);
            imageView = itemView.findViewById(R.id.shoe_icon);
            base_size = itemView.findViewById(R.id.size);
        }
    }
}
