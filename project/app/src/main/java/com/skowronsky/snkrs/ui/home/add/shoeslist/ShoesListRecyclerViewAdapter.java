package com.skowronsky.snkrs.ui.home.add.shoeslist;

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
    private List<Shoes> shoesList;
    private List<Shoes> shoesListFiltered;
    private ShoesListViewModel shoesListViewModel;
    private NavigationStorage navigationStorage;

    public ShoesListRecyclerViewAdapter(Acitivity context, ShoesListViewModel shoesListViewModel){
        this.context = context;
        this.shoesListViewModel = shoesListViewModel;
        this.shoesList = new ArrayList<>();
        this.shoesListFiltered = new ArrayList<>();
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
        Shoes shoes = shoesListFiltered.get(position);
        final RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;

        viewHolder.imageView.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_transition_animation));
        viewHolder.shoe_company.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_scale_animation));
        viewHolder.shoe_model.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_scale_animation));
        //TODO get brandName
        viewHolder.shoe_company.setText(navigationStorage.getBrand().getBrandName());
        viewHolder.shoe_model.setText(shoes.getModelName());
        if (shoes.getImage()!=null){
            Picasso.with((Context) context).load(shoes.getImage()).into(
                    viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationStorage.setShoe(shoesListFiltered.get(position));
                shoesListViewModel.eventNavToInfo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoesListFiltered.size();
    }

    /**
     * Metoda ustawiająca listę butów dla recyclerView
     * @param shoesList lista butów
     */
    public void setShoesList(List<Shoes> shoesList){
        this.shoesList = shoesList;
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
                        if (row.getModelName().toLowerCase().contains(key.toLowerCase())){
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
