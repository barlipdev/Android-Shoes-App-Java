package com.skowronsky.snkrs.ui.home.search.brandlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BrandListRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    BrandListViewModel viewModel;
    List<com.skowronsky.snkrs.database.Brand> brands;
    NavigationStorage navigationStorage;

    public BrandListRecyclerViewAdapter(Acitivity context, BrandListViewModel brandListViewModel){
        this.context = context;
        this.viewModel = brandListViewModel;
        brands = new ArrayList<com.skowronsky.snkrs.database.Brand>();
        this.navigationStorage = NavigationStorage.getInstance();
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
                navigationStorage.setBrand(viewHolder.shoe_company.getText().toString());
                viewModel.eventNavToShoes();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    /**
     * Metoda ustawiająca listę marek które ma wyświetlać recyclerView
     * @param brands lista marek butów
     */
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
