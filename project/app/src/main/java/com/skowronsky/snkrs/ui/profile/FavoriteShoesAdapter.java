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
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteShoesAdapter extends RecyclerView.Adapter<FavoriteShoesAdapter.FavoriteShoesHolder> {
    List<FavoriteShoes> favoriteShoesList = new ArrayList<>();
    private Context context;
    private NavigationStorage navigationStorage;
    private ProfileViewModel profileViewModel;

    public FavoriteShoesAdapter(ProfileViewModel profileViewModel){
        navigationStorage = NavigationStorage.getInstance();
        this.profileViewModel = profileViewModel;
    }

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
        holder.textViewShoesModel.setText(favoriteShoes.brandShoes.shoes.getModelName());
        holder.textViewShoesBrand.setText(favoriteShoes.brandShoes.brand.getBrandName());
        holder.textViewShoesSize.setText(String.valueOf(favoriteShoes.brandSize.sizeChart.getUs()));
        Picasso.with(context).load(favoriteShoes.brandShoes.shoes.getImage()).into(
                holder.imageViewShoesIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationStorage.setFavoriteShoes(favoriteShoes);
                profileViewModel.eventNavToInfo();
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteShoesList.size();
    }

    /**
     * Metoda odpowiadaj??ca za ustawienie listy ulubionych but??w przekazanej przez parametr dla recyclerView
     * @param favoriteShoesList lista ulubionych but??w
     */
    public void setFavoriteShoesList(List<FavoriteShoes> favoriteShoesList){
        this.favoriteShoesList = favoriteShoesList;
        notifyDataSetChanged();
    }

    public void delete(int pos, ProfileViewModel viewModel){
        //TODO Delete from database

        viewModel.deleteFavorite(favoriteShoesList.get(pos).favorite);
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
