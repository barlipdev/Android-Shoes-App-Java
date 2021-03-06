package com.skowronsky.snkrs.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.BrandShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter RecyclerView prezentujący listę posiadanych baz
 */
public class HomeRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Acitivity context;
    HomeViewModel shoesViewModel;
    List<BaseShoes> baseShoesList;
    NavigationStorage navigationStorage;

    public HomeRecyclerViewAdapter(Acitivity context , HomeViewModel homeViewModel){
        this.context = context;
        this.shoesViewModel = homeViewModel;
        baseShoesList = new ArrayList<>();
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
        BrandShoes brandShoes = baseShoesList.get(position).brandShoes;
        final HomeRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (HomeRecyclerViewAdapter.RecyclerViewViewHolder) holder;
        //TODO get brandName
        viewHolder.shoe_company.setText(String.valueOf(brandShoes.brand.getBrandName()));
        viewHolder.shoe_model.setText(brandShoes.shoes.getModelName());
        //TODO BaseShoes 1.5
        viewHolder.base_sizeUs.setText("US: "+ String.valueOf(baseShoesList.get(position).brandSize.sizeChart.getUs()));
        if (brandShoes.shoes.getImage()!=null){
            Picasso.with((Context) context).load(brandShoes.shoes.getImage()).into(
                    viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationStorage.setBaseShoe(baseShoesList.get(position));
                shoesViewModel.eventNavToInfo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return baseShoesList.size();
    }

    /**
     * Metoda odpowiadająca za ustawienie listy dla recyclerview
     * @param baseShoesList lista baz butów
     */
    public void setBaseShoes(List<BaseShoes> baseShoesList){
        this.baseShoesList = baseShoesList;
        notifyDataSetChanged();
    }
    /**
     * Metoda która usuwa element z listy baz butów, na którym w danej chwili operujemy
     * @param pos zmienna wskazująca dany element na którym pracujemy, index elementu
     */
    public void delete(int pos){
//        TODO BaseShoes 1.6
        //shoesViewModel.deleteBaseShoes(baseShoesList.get(pos).shoes,baseShoesList.get(pos).base.size);
        notifyDataSetChanged();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView shoe_company;
        TextView shoe_model;
        TextView base_sizeUs;
        ImageView imageView;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_company = itemView.findViewById(R.id.shoe_comp);
            shoe_model = itemView.findViewById(R.id.shoe_model);
            imageView = itemView.findViewById(R.id.shoe_icon);
            base_sizeUs = itemView.findViewById(R.id.size);

        }
    }
}
