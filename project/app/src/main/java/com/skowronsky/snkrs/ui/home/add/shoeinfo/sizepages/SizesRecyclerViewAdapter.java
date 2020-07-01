package com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.storage.NavigationStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SizesRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    private List<Size> sizes;
    private NavigationStorage navigationStorage;
    private EurSizesViewModel eurSizesViewModel;
    private int index;
    private String type_of_sizes;
    private Size size;
    public HashMap<Integer, RecyclerView.ViewHolder> holderlist;


    public SizesRecyclerViewAdapter(Acitivity context){
        this.context = context;
        this.navigationStorage = NavigationStorage.getInstance();
        this.sizes = new ArrayList<>();
        this.size = new Size(-1,-1,-1);
        holderlist = new HashMap<>();
    }
    public SizesRecyclerViewAdapter(Acitivity context, EurSizesViewModel eurSizesViewModel){
        this.context = context;
        this.navigationStorage = NavigationStorage.getInstance();
        this.eurSizesViewModel = eurSizesViewModel;
        this.sizes = new ArrayList<>();
        this.size = new Size(-1,-1,-1);
        holderlist = new HashMap<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.item_sizes,parent,false);
        return new SizesRecyclerViewAdapter.RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final SizesRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (SizesRecyclerViewAdapter.RecyclerViewViewHolder) holder;

        if (type_of_sizes.equals("EU")){
            viewHolder.size.setText(String.valueOf(sizes.get(position).getEu()));
        }
        if (type_of_sizes.equals("US")){
            viewHolder.size.setText(String.valueOf(sizes.get(position).getUs()));
        }
        if (type_of_sizes.equals("UK")){
            viewHolder.size.setText(String.valueOf(sizes.get(position).getUk()));
        }

        if(!holderlist.containsKey(position)){
            holderlist.put(position,holder);
        }



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //index = position;
                navigationStorage.setSizes(sizes.get(position));
                eurSizesViewModel.eventSetPos(position);
                navigationStorage.setSize_pos(position);
                //notifyDataSetChanged();
                viewHolder.size.setAnimation(AnimationUtils.loadAnimation((Context) context,R.anim.fade_scale_animation));
            }
        });
        //if (index == position) {
       //     viewHolder.linearLayout.setBackgroundResource(R.drawable.sizes_border_light);
        //    viewHolder.size.setTextColor(Color.parseColor("#ffffff"));
        //}
    }

    public RecyclerView.ViewHolder getViewByPosition(int pos){
        return holderlist.get(pos);
    }

    public Size getSizeByPosition(int pos){
        return sizes.get(pos);
    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }

    public void init_sizes(String type_of_sizes){
        this.type_of_sizes = type_of_sizes;
        Double eu = 36.0;
        Double us = 4.0;
        Double uk = 3.5;
        for(int i=0;i<=16;i++){
            if(i==0){
                sizes.add(new Size(eu,us,uk));
            }
            if(i==6){
                eu = 40.0;
                us = 7.0;
                uk = 6.5;
                sizes.add(new Size(eu,us,uk));
            }
            if(i==9){
                eu = 42.0;
                us = 8.5;
                uk = 8.0;
                sizes.add(new Size(eu,us,uk));
            }
            if(i==12){
                eu = 44.0;
                us = 10.0;
                uk = 9.5;
                sizes.add(new Size(eu,us,uk));
            }
            if(i==15){
                eu = 46.0;
                us = 11.5;
                uk = 11.0;
                sizes.add(new Size(eu,us,uk));
            }
            else{
                eu = eu + 0.5;
                us = us + 0.5;
                uk = uk + 0.5;
                sizes.add(new Size(eu,us,uk));
            }
        }
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView size;
        LinearLayout linearLayout;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            size = itemView.findViewById(R.id.selected_size);
            linearLayout = itemView.findViewById(R.id.sizeslayout);
        }
    }
}
