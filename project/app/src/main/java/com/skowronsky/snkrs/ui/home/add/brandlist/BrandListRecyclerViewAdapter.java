package com.skowronsky.snkrs.ui.home.add.brandlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BrandListRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Acitivity context;
    private List<com.skowronsky.snkrs.database.Brand> brandList;
    private BrandListViewModel brandListViewModel;
    private NavigationStorage navigationStorage;

    public BrandListRecyclerViewAdapter(Acitivity context, BrandListViewModel brandListViewModel){
        this.context = context;
        this.navigationStorage = NavigationStorage.getInstance();
        this.brandListViewModel = brandListViewModel;
        this.brandList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Brand brand = brandList.get(position);
        final RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;

        viewHolder.company_photo.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_transition_animation));
        viewHolder.itemView.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_scale_animation));

        Picasso.with((Context) context).load(brand.getImage()).into(
                viewHolder.company_photo);
        viewHolder.shoe_company.setVisibility(View.INVISIBLE);
        viewHolder.shoe_company.setText(brand.getBrandName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationStorage.setBrand(brandList.get(position));
                brandListViewModel.eventNavToShoes();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    /**
     * Metoda ustawiająca listę marek które ma wyświetlić recyclerView
     * @param brandList lista marek butów
     */
    public void setBrandList(List<com.skowronsky.snkrs.database.Brand> brandList) {
        this.brandList = brandList;
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
