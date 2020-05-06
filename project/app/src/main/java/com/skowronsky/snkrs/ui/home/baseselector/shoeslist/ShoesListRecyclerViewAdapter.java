package com.skowronsky.snkrs.ui.home.baseselector.shoeslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ShoesListRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    private List<Shoes> shoesList;
    private ShoesListViewModel shoesListViewModel;
    private NavigationStorage navigationStorage;

    public ShoesListRecyclerViewAdapter(Acitivity context, ShoesListViewModel shoesListViewModel){
        this.context = context;
        this.shoesListViewModel = shoesListViewModel;
        this.shoesList = new ArrayList<>();
        this.navigationStorage = NavigationStorage.getInstance();
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
                navigationStorage.setShoe(shoesList.get(position));
                shoesListViewModel.eventNavToInfo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoesList.size();
    }

    public void setShoesList(List<Shoes> shoesList){
        for(int i=0;i<shoesList.size();i++){
            if (shoesList.get(i).brand_name.equals(navigationStorage.getBrand())){
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
