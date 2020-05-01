package com.skowronsky.snkrs.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.ui.home.HomeViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BrandListRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    BrandListViewModel viewModel;
    List<com.skowronsky.snkrs.database.Brand> brands;

    public BrandListRecyclerViewAdapter(Acitivity context, BrandListViewModel brandListViewModel){
        this.context = context;
        this.viewModel = brandListViewModel;
        brands = new ArrayList<com.skowronsky.snkrs.database.Brand>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.item,parent,false);
        return new BrandListRecyclerViewAdapter.RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        com.skowronsky.snkrs.database.Brand brand = brands.get(position);
        final BrandListRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (BrandListRecyclerViewAdapter.RecyclerViewViewHolder) holder;
        Picasso.with((Context) context).load(brand.image).into(
                viewHolder.company_photo);
        viewHolder.shoe_company.setVisibility(View.INVISIBLE);
        viewHolder.shoe_company.setText(brand.brand_name);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.eventCompanyName(viewHolder.shoe_company.getText().toString());
                Toast.makeText((Context) context, "Numer: "+ viewHolder.shoe_company.getText(), Toast.LENGTH_SHORT).show();
                viewModel.eventNavToShoes();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public void setBrandList(List<com.skowronsky.snkrs.database.Brand> brands){
        this.brands = brands;
        notifyDataSetChanged();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView shoe_company;
        ImageView company_photo;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_company);
            company_photo = itemView.findViewById(R.id.shoe_photo);
        }
    }
}
