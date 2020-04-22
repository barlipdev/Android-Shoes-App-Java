package com.skowronsky.snkrs.ui.home.shoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;

import java.util.ArrayList;

public class ShoesInformationRecyclerViewAdapter<Acitivity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Acitivity context;
    ArrayList<Double> sizes = new ArrayList<>();
    ShoesInformationViewModel viewModel;

    public ShoesInformationRecyclerViewAdapter(Acitivity context, ArrayList<Double> sizes, ShoesInformationViewModel viewModel){
        this.context = context;
        this.sizes = sizes;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        View rootView = LayoutInflater.from((Context) context).inflate(R.layout.check_size_boxes,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ShoesInformationRecyclerViewAdapter.RecyclerViewViewHolder viewHolder = (ShoesInformationRecyclerViewAdapter.RecyclerViewViewHolder) holder;
        viewHolder.checkBox.setText(sizes.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }
    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox2);
        }
    }
}
