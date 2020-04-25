package com.skowronsky.snkrs.ui.base;

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
import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewBaseHomeAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Acitivity context;
    ArrayList<Shoes> ShoesArrayList;
    List<BaseShoes> baseShoes;
    ArrayList<String> baseInfo;
    HomeBaseViewModel shoesViewModel;

    public RecyclerViewBaseHomeAdapter(Acitivity context ,HomeBaseViewModel homeViewModel,ArrayList<Shoes> ShoesArrayList,List<BaseShoes> baseShoes){
        this.context = context;
        this.shoesViewModel = homeViewModel;
        this.ShoesArrayList = ShoesArrayList;
        this.baseShoes = baseShoes;
        this.baseInfo = new ArrayList<String>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.base_list,parent,false);
        return new RecyclerViewBaseHomeAdapter.RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Shoes shoes = ShoesArrayList.get(position);
        final RecyclerViewBaseHomeAdapter.RecyclerViewViewHolder viewHolder = (RecyclerViewBaseHomeAdapter.RecyclerViewViewHolder) holder;
        viewHolder.shoe_company.setText(shoes.getBrandName());
        viewHolder.shoe_model.setText(shoes.getModelName());
        baseShoes = shoesViewModel.getAllBaseShoes().getValue();
        viewHolder.base_size.setText("Base size: "+String.valueOf(baseShoes.get(position).base.size));
        if (shoes.getImage()!=null){
            Picasso.with((Context) context).load(shoes.getImage()).into(
                    viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseInfo.add(String.valueOf(shoes.getBrandName()));
                baseInfo.add(String.valueOf(shoes.getModelName()));
                baseInfo.add(String.valueOf(baseShoes.get(position).base.size));
                baseInfo.add(String.valueOf(shoes.getImage()));
                baseInfo.add(String.valueOf(baseShoes.get(position).base.id_base));
                shoesViewModel.eventBaseSet(baseInfo);
                shoesViewModel.eventNavToInfo();
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoesViewModel.deleteBaseShoes(ShoesArrayList.get(position),baseShoes.get(position).base.size);
                ShoesArrayList.remove(position);
                shoesViewModel.refresh(ShoesArrayList);

                //shoesViewModel.init(baseShoes);
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
