package com.skowronsky.snkrs.ui.base;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.ShoesListFragmentBinding;

public class ShoesListFragment extends Fragment {

    private ShoesListViewModel viewModel;
    private ShoesListFragmentBinding shoesListFragmentBinding;

    private ShoesListViewModel mViewModel;

    public static ShoesListFragment newInstance() {
        return new ShoesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shoes_list_fragment, container, false);
    }

}
