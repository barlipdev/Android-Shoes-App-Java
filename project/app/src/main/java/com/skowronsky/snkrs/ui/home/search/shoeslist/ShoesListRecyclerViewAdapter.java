package com.skowronsky.snkrs.ui.home.search.shoeslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
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

public class ShoesListRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    Acitivity context;
    ShoesListViewModel shoesViewModel;
    List<com.skowronsky.snkrs.database.Shoes>  shoesList = new ArrayList<com.skowronsky.snkrs.database.Shoes>();
    List<Shoes> shoesListFiltered;
    NavigationStorage navigationStorage;

    public ShoesListRecyclerViewAdapter(Acitivity context, ShoesListViewModel shoesViewModel){
        this.context = context;
        this.shoesViewModel = shoesViewModel;
        this.navigationStorage = NavigationStorage.getInstance();
        this.shoesListFiltered = new ArrayList<>();
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
        com.skowronsky.snkrs.database.Shoes shoes = shoesListFiltered.get(position);
        final ShoesListRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (ShoesListRecyclerViewAdapter.RecyclerViewViewHolder) holder;

        viewHolder.imageView.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_transition_animation));
        viewHolder.shoe_company.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_scale_animation));
        viewHolder.shoe_model.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_scale_animation));

        viewHolder.shoe_company.setText(shoes.brandName);
        viewHolder.shoe_model.setText(shoes.modelName);
        if (shoes.image!=null){
            Picasso.with((Context) context).load(shoes.image).into(
                    viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoesViewModel.eventSendShoe(shoesListFiltered.get(position));
                shoesViewModel.eventNavToInfo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoesListFiltered.size();
    }

    /**
     * Metoda która ustawia listę butów dla recyclerView
     * @param shoes lista butów które mają być ustawione
     */
    public void setAllShoes(List<com.skowronsky.snkrs.database.Shoes> shoes){
        for(int i=0;i<shoes.size();i++){
        if (shoes.get(i).brandName.equals(navigationStorage.getBrand())){
           shoesList.add(shoes.get(i));
         }
        }
        shoesListFiltered = this.shoesList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()){
                    shoesListFiltered = shoesList;
                }else{
                    List<Shoes> listFiltered = new ArrayList<>();
                    for (Shoes row : shoesList){
                        if (row.modelName.toLowerCase().contains(key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    }
                    shoesListFiltered = listFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = shoesListFiltered;
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                shoesListFiltered = (List<Shoes>) results.values;
                notifyDataSetChanged();
            }
        };
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
