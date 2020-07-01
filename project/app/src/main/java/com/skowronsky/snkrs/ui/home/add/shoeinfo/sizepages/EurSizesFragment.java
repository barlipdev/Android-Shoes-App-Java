package com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentEurSizesBinding;
import com.skowronsky.snkrs.storage.NavigationStorage;

import org.w3c.dom.Text;

import java.util.Objects;

/**
 * Fragment z lista rozmiarów EU
 */
public class EurSizesFragment extends Fragment {

    private EurSizesViewModel mViewModel;
    private FragmentEurSizesBinding fragmentEurSizesBinding;
    private RecyclerView recyclerView;
    private SizesRecyclerViewAdapter<Context> recyclerViewAdapter;
    private TextView textView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(EurSizesViewModel.class);
        fragmentEurSizesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_eur_sizes, container, false);
        fragmentEurSizesBinding.setEurSizesViewModel(mViewModel);
        fragmentEurSizesBinding.setLifecycleOwner(this);

        fragmentEurSizesBinding.recyclerviewsizes.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));
        fragmentEurSizesBinding.textView4.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = fragmentEurSizesBinding.recyclerviewsizes;
        recyclerViewAdapter = new SizesRecyclerViewAdapter<>(getContext(),mViewModel);
        recyclerViewAdapter.init_sizes("EU");
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.setAdapter(recyclerViewAdapter);

//        mViewModel.getSize().observe(getViewLifecycleOwner(), new Observer<Size>() {
//            @Override
//            public void onChanged(Size size) {
//                Log.i("adaa",String.valueOf(recyclerViewAdapter.getViewByPosition(1)));
//                View view = recyclerViewAdapter.getViewByPosition(1).itemView;
//                textView = view.findViewById(R.id.selected_size);
//                if (mViewModel.check(size)){
//                    textView.setTextColor(Color.parseColor("#ffffff"));
//                    view.setBackgroundResource(R.drawable.sizes_border_light);
//                }else{
//                    textView.setTextColor(Color.parseColor("#c9c9c9"));
//                    view.setBackgroundResource(R.drawable.sizes_border);
//                }
//            }
//        });

//        mViewModel.getChecked().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if (aBoolean){
//                    for(int i=0;i<10;i++){
//                        Log.i("adaa",String.valueOf(recyclerViewAdapter.getViewByPosition(i)));
//                        View view = recyclerViewAdapter.getViewByPosition(i).itemView;
//                        textView = view.findViewById(R.id.selected_size);
//                        if (mViewModel.check(mViewModel.getSize().getValue())){
//                            textView.setTextColor(Color.parseColor("#ffffff"));
//                            view.setBackgroundResource(R.drawable.sizes_border_light);
//                            mViewModel.eventSetUnChecked();
//                        }else{
//                            textView.setTextColor(Color.parseColor("#c9c9c9"));
//                            view.setBackgroundResource(R.drawable.sizes_border);
//                            mViewModel.eventSetUnChecked();
//                        }
//                    }
//                }
//            }
//        });



        mViewModel.getPos().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                View view = recyclerViewAdapter.getViewByPosition(integer).itemView;
                textView = view.findViewById(R.id.selected_size);
                textView.setTextColor(Color.parseColor("#ffffff"));
                view.setBackgroundResource(R.drawable.sizes_border_light);

                for (int i=0;i<21;i++){
                    view = recyclerViewAdapter.getViewByPosition(i).itemView;
                    textView = view.findViewById(R.id.selected_size);
                    if (mViewModel.eventGetPos() == i){
                        textView.setTextColor(Color.parseColor("#ffffff"));
                        view.setBackgroundResource(R.drawable.sizes_border_light);
                    }
                }

            }
        });

        return fragmentEurSizesBinding.getRoot();

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
