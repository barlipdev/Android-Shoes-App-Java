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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentEurSizesBinding;

import java.util.Objects;

/**
 * Fragment z lista rozmiarów EU
 */
public class EurSizesFragment extends Fragment {

    private EurSizesViewModel mViewModel;
    private FragmentEurSizesBinding fragmentEurSizesBinding;
    private RecyclerView recyclerView;
    private SizesRecyclerViewAdapter<Context> recyclerViewAdapter;

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
        recyclerViewAdapter = new SizesRecyclerViewAdapter<>(getContext());
        recyclerViewAdapter.init_sizes("EU");
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.setAdapter(recyclerViewAdapter);

        return fragmentEurSizesBinding.getRoot();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
