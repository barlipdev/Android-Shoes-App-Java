package com.skowronsky.snkrs.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteShoesAdapter extends RecyclerView.Adapter<FavoriteShoesAdapter.FavoriteShoesHolder> {
    List<FavoriteShoes> favoriteShoesList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public FavoriteShoesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_shoes,parent,false);
        context = parent.getContext();
        return new FavoriteShoesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteShoesHolder holder, int position) {
        FavoriteShoes favoriteShoes = favoriteShoesList.get(position);
        holder.textViewShoesModel.setText(favoriteShoes.shoes.modelName);
        holder.textViewShoesBrand.setText(favoriteShoes.shoes.brand_name);
        holder.textViewShoesSize.setText(String.valueOf(favoriteShoes.favorite.size));
        Picasso.with(context).load(favoriteShoes.shoes.image).into(
                holder.imageViewShoesIcon);
    }

    @Override
    public int getItemCount() {
        return favoriteShoesList.size();
    }

    /**
     * Metoda odpowiadająca za ustawienie listy ulubionych butów przekazanej przez parametr dla recyclerView
     * @param favoriteShoesList lista ulubionych butów
     */
    public void setFavoriteShoesList(List<FavoriteShoes> favoriteShoesList){
        this.favoriteShoesList = favoriteShoesList;
        notifyDataSetChanged();
    }

    public class FavoriteShoesHolder extends RecyclerView.ViewHolder{
        private TextView textViewShoesModel;
        private TextView textViewShoesBrand;
        private TextView textViewShoesSize;
        private ImageView imageViewShoesIcon;
        public FavoriteShoesHolder(@NonNull View itemView) {
            super(itemView);
            textViewShoesModel = itemView.findViewById(R.id.shoes_model);
            textViewShoesBrand = itemView.findViewById(R.id.shoes_brand);
            textViewShoesSize = itemView.findViewById(R.id.shoes_size);
            imageViewShoesIcon = itemView.findViewById(R.id.shoes_icon);
        }
    }

}
