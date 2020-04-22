package com.skowronsky.snkrs.ui.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.ui.home.HomeViewModel;
import com.skowronsky.snkrs.ui.home.shoes.ShoesRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewBaseHomeAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Acitivity context;
    ArrayList<Shoes> ShoesArrayList;
    HomeBaseViewModel shoesViewModel;

    public RecyclerViewBaseHomeAdapter(Acitivity context, ArrayList<Shoes> ShoesArrayList,HomeBaseViewModel homeViewModel){
        this.context = context;
        this.ShoesArrayList = ShoesArrayList;
        this.shoesViewModel = homeViewModel;
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
        if (shoes.getImage()!=null){
            Picasso.with((Context) context).load(shoes.getImage()).into(
                    viewHolder.imageView);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("but",ShoesArrayList.get(position).getBrandName());
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoesArrayList.remove(position);
                //shoesViewModel.init(ShoesArrayList);
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
        Button deleteButton;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_comp);
            shoe_model = itemView.findViewById(R.id.shoe_model);
            imageView = itemView.findViewById(R.id.shoe_icon);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
