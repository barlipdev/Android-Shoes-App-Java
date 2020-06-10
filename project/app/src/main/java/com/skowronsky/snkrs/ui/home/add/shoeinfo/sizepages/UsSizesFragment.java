package com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentUsSizesBinding;

import java.util.Objects;
/**
 * Fragment z lista rozmiarów US
 */
public class UsSizesFragment extends Fragment {

    private UsSizesViewModel mViewModel;
    private FragmentUsSizesBinding fragmentUsSizesBinding;
    private RecyclerView recyclerView;
    private SizesRecyclerViewAdapter<Context> recyclerViewAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(UsSizesViewModel.class);
        fragmentUsSizesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_us_sizes, container, false);
        fragmentUsSizesBinding.setUsSizesViewModel(mViewModel);
        fragmentUsSizesBinding.setLifecycleOwner(this);

        fragmentUsSizesBinding.recyclerviewsizes.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));
        fragmentUsSizesBinding.textView4.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = fragmentUsSizesBinding.recyclerviewsizes;
        recyclerViewAdapter = new SizesRecyclerViewAdapter<>(getContext());
        recyclerViewAdapter.init_sizes("US");
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.setAdapter(recyclerViewAdapter);

        return fragmentUsSizesBinding.getRoot();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
